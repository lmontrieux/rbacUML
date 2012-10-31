package uk.ac.open.rbacuml.modelgenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * This class is used to parse the generator's config file, and can produce
 * a list of options for every model described in said config file.
 * 
 * @author Lionel Montrieux <L.M.C.Montrieux@open.ac.uk>
 *
 */
public class ConfigParser {
	private Document doc = null;
	private Element root = null;
	private List<Map<String, Object>> options = null;
	/**
	 * Creates a new parser.
	 * @param config the config File
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public ConfigParser(File config) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		doc = builder.build(config);
		root = doc.getRootElement();
		options = new ArrayList<Map<String, Object>>();
	}
	
	public void parse() {
		System.out.println("parse()");
		List<Element> models = root.getChildren("model");
		for (Element model: models) {
			this.options.add(parseModel(model));
		}
	}
	
	/**
	 * Parses a model element and creates a Map of its options. It is assumed
	 * that the Element has been checked against the DTD before, and that /all/ 
	 * the required options are indeed present, and only once. Incorrectly formed
	 * model elements will lead to unspecified results.
	 * 
	 * @param model the model element to be parsed
	 * @return a Map of all the options for the model
	 */
	private Map<String, Object> parseModel(Element model) {
		System.out.println("parseModel()");
		Map<String, Object> options = new Hashtable<String, Object>();
		options.put("name", model.getAttributeValue("name"));
		options.put("well-formedness", model.getChild("well-formedness")
				.getAttributeValue("enforced"));
		options.put("verification", model.getChild("verification")
				.getAttributeValue("enforced"));
		options.put("coverage", model.getChild("coverage")
				.getAttributeValue("enforced"));
		options.put("completeness", model.getChild("completeness")
				.getAttributeValue("enforced"));
		options.put("redundancy", model.getChild("redundancy")
				.getAttributeValue("enforced"));
		options.put("satisfiability", model.getChild("satisfiability")
				.getAttributeValue("enforced"));
		options.put("users", model.getChild("users")
				.getAttributeValue("num"));
		options.put("roles", model.getChild("roles")
				.getAttributeValue("num"));
		options.put("permissions", model.getChild("permissions")
				.getAttributeValue("num"));
		options.put("partitions", model.getChild("partitions")
				.getAttributeValue("num"));
		options.put("actions", model.getChild("actions")
				.getAttributeValue("num"));
		options.put("granted", model.getChild("actions")
				.getAttributeValue("granted"));
		options.put("forbidden", model.getChild("actions")
				.getAttributeValue("forbidden"));
		options.put("operations", model.getChild("operations")
				.getAttributeValue("num"));
		options.put("restricted", model.getChild("operations")
				.getAttributeValue("restricted"));
		options.put("classes", model.getChild("classes")
				.getAttributeValue("num"));
		options.put("activities", model.getChild("activities")
				.getAttributeValue("num"));
		options.put("minSSoD", model.getChild("roles").getChild("ssod")
				.getAttributeValue("min"));
		options.put("maxSSoD", model.getChild("roles").getChild("ssod")
				.getAttributeValue("max"));
		options.put("minDSoD", model.getChild("roles").getChild("dsod")
				.getAttributeValue("min"));
		options.put("maxDSoD", model.getChild("roles").getChild("dsod")
				.getAttributeValue("max"));
		options.put("minRoleHierarchies", model.getChild("roles")
				.getChild("hierarchies").getAttributeValue("min"));
		options.put("maxRoleHierarchies", model.getChild("roles")
				.getChild("hierarchies").getAttributeValue("max"));
		options.put("minRolesPerUser", model.getChild("users").getChild("roles")
				.getAttributeValue("min"));
		options.put("maxRolesPerUser", model.getChild("users").getChild("roles")
				.getAttributeValue("max"));
		options.put("minPermissionsPerRole", model.getChild("roles")
				.getChild("permissions").getAttributeValue("min"));
		options.put("maxPermissionsPerRole", model.getChild("roles")
				.getChild("permissions").getAttributeValue("max"));
		options.put("minPermissionsPerOp", model.getChild("operations")
				.getChild("permissions").getAttributeValue("min"));
		options.put("maxPermissionsPerOp", model.getChild("operations")
				.getChild("permissions").getAttributeValue("max"));
		options.put("minOpsPerAction", model.getChild("actions")
				.getChild("operations").getAttributeValue("min"));
		options.put("maxOpsPerAction", model.getChild("actions")
				.getChild("operations").getAttributeValue("max"));
		options.put("minRestOpsPerAction", model.getChild("actions")
				.getChild("restricted-operations").getAttributeValue("min"));
		options.put("maxRestOpsPerAction", model.getChild("actions")
				.getChild("restricted-operations").getAttributeValue("max"));
		return options;
	}
	
	public List<Map<String, Object>> getAllOptions() {
		return options;
	}
	
	public Map<String, Object> getOptions(String model) {
		for (Map<String, Object> elt:this.options){
			if (((String)elt.get("name")).equals(model))
				return elt;
		}
		return null;
	}
	
}
