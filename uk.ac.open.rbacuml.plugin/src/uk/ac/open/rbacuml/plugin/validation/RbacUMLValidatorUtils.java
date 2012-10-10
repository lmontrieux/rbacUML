package uk.ac.open.rbacuml.plugin.validation;

public class RbacUMLValidatorUtils {
	public static final String prefix = "rbacUML";
	public static final String oclFilterPrefix = "^" + prefix + "::\\w+::";
	public static final String oclFilterSuffix = "[a-zA-Z ]+$";
	public static final int WF = 0;
	public static final int VER = 1;
	public static final int SAT = 2;
	public static final int COV = 3;
	public static final int COMP = 4;
	public static final int RED = 5;
	public static final int EVAL_WF = 0;
	public static final int EVAL_WF_VER = 1;
	public static final int EVAL_WF_VER_LAZY = 2;
	public static final int EVAL_FULL = 3;
	public static final int EVAL_FULL_LAZY = 4;
	public static final int EVAL_VER = 5;
	public static final int EVAL_SAT = 6;
	public static final int EVAL_COV = 7;
	public static final int EVAL_COMP = 8;
	public static final int EVAL_RED = 9;
	public static final int EVAL_FULL_MULTI = 10;
}
