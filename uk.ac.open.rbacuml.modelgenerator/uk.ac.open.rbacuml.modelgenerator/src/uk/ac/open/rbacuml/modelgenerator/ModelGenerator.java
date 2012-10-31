package uk.ac.open.rbacuml.modelgenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jdom.JDOMException;

import uk.ac.open.rbacuml.modelgenerator.rbac.RBACModel;
import uk.ac.open.rbacuml.modelgenerator.stats.IStatisticsExport;
import uk.ac.open.rbacuml.modelgenerator.stats.SQLiteStatisticsExport;

public class ModelGenerator {
	static Logger log = Logger.getLogger("ModelGenerator");
	static String defaultEMXModel = "empty rbacUML Model.emx";
	static String defaultXMIModel = "empty rbacUML Model.xmi";
	static ConfigParser config = null;
	File target = null;
	File emptyModel = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		configure();
		
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = null;
		
		try {
			cmd = parser.parse(createOptions(), args);
		} catch (ParseException e) {
			printCliHelp("Error in parsing arguments: " + e.getMessage());
		}
		if (cmd.hasOption("c")) {
			File options = new File(cmd.getOptionValue("c"));
			if (!options.exists() || !options.canRead()) {
				log.fatal("Can't read config file. Aborting.");
			} else {
				parseConfig(options);
			}
		} else {
			log.error("No config file");
			return;
		}
		if (cmd.hasOption("xmi")) {
			log.warn("An XMI file will be generated");
		} else {
			log.warn("An EMX file will be generated");
		}
		String targetDir = cmd.getOptionValue("o");
		File emptyModel = null;
		int nbrModels = 1;
		if (cmd.hasOption("m")) {
			nbrModels = (new Integer(cmd.getOptionValue("m"))).intValue();
		} else {
			nbrModels = 1;
		}
		if (cmd.hasOption("e")) {
			emptyModel = new File(cmd.getOptionValue("e"));
		} else if (cmd.hasOption("xmi")) {
			emptyModel = new File(ModelGenerator.defaultXMIModel);
		} else {
			emptyModel = new File(ModelGenerator.defaultEMXModel);
		}
		log.warn(emptyModel.toString());
		log.warn("Empty model is " + emptyModel.toString());
		log.warn("Target models will be saved in " + targetDir.toString());
		String database = null;
		String table = null;
		IStatisticsExport db = null;
		if (cmd.hasOption("tbl")) {
			table = cmd.getOptionValue("tbl");
		}
		if (cmd.hasOption("db")) {
			db = new SQLiteStatisticsExport(cmd.getOptionValue("db"), table);
		}
		generateModels(emptyModel, targetDir, true, nbrModels, db);
	}
	
	private static void generateModels(File emptyModel, String target, boolean emx, int nbrModels, IStatisticsExport stats) {
		List<Map<String, Object>> models = config.getAllOptions();
		for (Map<String, Object> model: models) {
			String basename = (String)model.get("name");
			for (int i = 1; i <= nbrModels; i++) {
				model.put("name", basename + "-" + i);
			log.info("Generating model " + model.get("name"));
			log.info("-------------------------------------");
			String filename = target + model.get("name");
			if (emx)
				filename += ".emx";
			else
				filename += ".xmi";
			RBACModel rbacUML = new RBACModel(model);
			rbacUML.toXMI(emptyModel, new File(filename));
			System.out.println("Model statistics:");
			for (String stat:rbacUML.stats()) {
				System.out.println(stat);
			}
			if (stats != null) {
				for (String stat:rbacUML.stats()) {
					stats.addModelStats(rbacUML.getName(), rbacUML.computeStats());
				}
			}
			log.info("End generating model-----------------");
			}
		}
		if (stats != null) {
			stats.close();
		}
	}
	
	private static void parseConfig(File file) {
		System.out.println("Parsing config");
		try {
			ModelGenerator.config = new ConfigParser(file);
		} catch (JDOMException e) {
			e.printStackTrace();
			log.fatal("Could not parse config file. Aborting.");
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			log.fatal("Could not parse config file. Aborting.");
			System.exit(1);
		}
		ModelGenerator.config.parse();
	}
	
	private static void printCliHelp(String message) {
	    System.out.println(message);
	    HelpFormatter formatter = new HelpFormatter();
	    formatter.printHelp("java -jar rbacumlGenerator.jar",createOptions());
	    System.exit(-1);
	}
	
	/**
	 * Configures log4j
	 */
	private static void configure() {
		BasicConfigurator.configure();
		log.setLevel(Level.DEBUG);
		log.trace("Log4j configured");
	}
	
	/**
	 * Creates command-line options using apache common CLI. Options are:
	 * -xmi (flag, not required) to tell the generator to generate an xmi file
	 *  instead of an emx file
	 * -c --config (required, 1 required argument) to tell the generator where 
	 *  to find the file describing the model settings
	 * -e --empty-model (not required, 1 required argument) to tell the generator 
	 *  to use another empty model that the default one
	 *  
	 * @return the options
	 */
	private static Options createOptions() {
		
		Options genOptions = new Options();
		OptionBuilder.withArgName("config file");
		OptionBuilder.withDescription("The configuration file that sets the number of elements in the generated model");
		OptionBuilder.isRequired(true);
		OptionBuilder.hasArg(true);
		OptionBuilder.withLongOpt("config");
		genOptions.addOption(OptionBuilder.create("c"));
		
		OptionBuilder.isRequired(false);
		OptionBuilder.hasArg(false);
		OptionBuilder.withDescription("Use this flag to create an XMI file instead of an EMX file");
		genOptions.addOption(OptionBuilder.create("xmi"));
		
		OptionBuilder.isRequired(false);
		OptionBuilder.hasArg(true);
		OptionBuilder.withArgName("empty model file");
		OptionBuilder.withLongOpt("empty-model");
		OptionBuilder.withDescription("Use this option to use another empty model than the default one");
		genOptions.addOption(OptionBuilder.create("e"));
		
		OptionBuilder.isRequired(true);
		OptionBuilder.hasArg(true);
		OptionBuilder.withArgName("output file");
		OptionBuilder.withLongOpt("output");
		genOptions.addOption(OptionBuilder.create("o"));
		
		OptionBuilder.isRequired(false);
		OptionBuilder.hasArg(true);
		OptionBuilder.withArgName("number of models");
		OptionBuilder.withLongOpt("models");
		OptionBuilder.withDescription("The number of models to generate for each model specification. Default: 1");
		genOptions.addOption(OptionBuilder.create("m"));
		
		OptionBuilder.isRequired(false);
		OptionBuilder.hasArg(true);
		OptionBuilder.withArgName("SQLite database");
		OptionBuilder.withLongOpt("sqlite");
		OptionBuilder.withDescription("Tells the generator to output the model statistics to an SQLite database");
		genOptions.addOption(OptionBuilder.create("db"));
		
		OptionBuilder.isRequired(false);
		OptionBuilder.hasArg(true);
		OptionBuilder.withArgName("table");
		OptionBuilder.withLongOpt("table");
		OptionBuilder.withDescription("Sets the name of the SQLite table to out for recording the model statistics");
		genOptions.addOption(OptionBuilder.create("tbl"));
		
		return genOptions;
	}

}
