package uk.ac.open.rbacuml.plugin.validation;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;

public class SQLitePerformanceExport {
	private SQLiteConnection db = null;
	private String table = null;
	private Logger log = Logger.getLogger("SQLitePerformanceExport");
	public static final String TIME_FULL = "time_full";
	public static final String TIME_WF = "time_wf";
	public static final String TIME_VER = "time_ver";
	public static final String TIME_SAT = "time_sat";
	public static final String TIME_COV = "time_cov";
	public static final String TIME_COMP = "time_comp";
	public static final String TIME_RED = "time_red";
	public static final String ELEMENTS = "elements";
	public static final String ERRORS_FULL = "errors_full";
	public static final String ERRORS_WF = "errors_wf";
	public static final String ERRORS_VER = "errors_ver";
	public static final String ERRORS_SAT = "errors_sat";
	public static final String ERRORS_COV = "errors_cov";
	public static final String ERRORS_COMP = "errors_comp";
	public static final String ERRORS_RED = "errors_red";
	public static final String WARNINGS_FULL = "warnings_full";
	public static final String WARNINGS_WF = "warnings_wf";
	public static final String WARNINGS_VER = "warnings_ver";
	public static final String WARNINGS_SAT = "warnings_sat";
	public static final String WARNINGS_COV = "warnings_cov";
	public static final String WARNINGS_COMP = "warnings_comp";
	public static final String WARNINGS_RED = "warnings_red";
	public static final String TIME_FULL_LAZY = "time_full_lazy";
	public static final String ERRORS_FULL_LAZY = "errors_full_lazy";
	public static final String WARNINGS_FULL_LAZY = "warnings_full_lazy";
	public static final String TIME_FULL_MULTI = "time_full_multi";
	public static final String ERRORS_FULL_MULTI = "errors_full_multi";
	public static final String WARNINGS_FULL_MULTI = "warnings_full_multi";
	
	public SQLitePerformanceExport(String file, String table) throws IOException, SQLiteException {
		assert(file != null);
		assert(table != null);
		File dbfile = new File(file);
		if (!dbfile.exists())
			throw new IOException("File does not exist: " + dbfile.toString());
		this.table = table;
		db = new SQLiteConnection(dbfile);
		db.open(false);
	}
	
	public boolean isOpen() {
		return db.isOpen();
	}
	
	/**
	 * Updates a specific record in the database. Uses the table defined in the constructor.
	 * @param name the name of the model to update
	 * @param column the name of the column to update (use constants in SQLitePerformanceExport)
	 * @param value the value to insert in the column
	 * @return true if the record has been updated, false otherwise
	 */
	public boolean updateRecord(String name, String column, long value) {
		assert(db.isOpen());
		assert(!this.table.equals(null));
		
		String query = "UPDATE \"main\".\"" + this.table + "\" SET \"" 
				+ column + "\" = " + value + " WHERE \"name\" = \"" + name + "\"";
		log.info("Executing query " + query);
		try {
			db.exec(query);
		} catch (SQLiteException e) {
			log.error("Can't update the database column " + column 
					+ " with value " + value + " for model " + name + ": " 
					+ e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
