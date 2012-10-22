package uk.ac.open.rbacuml.plugin.validation;

import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class BulkEvaluationDialog extends Dialog {
	private Logger log = Logger.getLogger(this.getClass());

	private String model;
	private String db;
	private String table;
	Label fileSelectionLabel;
	Text fileSelectionText;
	Button fileSelectionButton;
	Label dbSelectionLabel;
	Text dbSelectionText;
	Button dbSelectionButton;
	Label dbTableLabel;
	Text dbTableText;
	Label individualEvaluationLabel;
	Button individualEvaluationButton;
	Label fullEvaluationLabel;
	Button fullEvaluationButton;
	Label lazyEvaluationLabel;
	Button lazyEvaluationButton;
	Button cancelButton;
	Button okButton;
	Map<Integer, Boolean> evaluation = null;
	
	
	protected BulkEvaluationDialog(Shell parentShell) {
		super(parentShell);
		this.evaluation = new Hashtable<Integer, Boolean>();
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite)super.createDialogArea(parent);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 1;
		
		// Layout for the files (db and sources) selection part
		Composite compThreeCol = (Composite)super.createDialogArea(composite);
		GridLayout layoutThreeCol = new GridLayout();
		compThreeCol.setLayout(layoutThreeCol);
		layoutThreeCol.numColumns = 3;
		
		fileSelectionLabel = new Label(compThreeCol, SWT.NULL);
		fileSelectionLabel.setText("Select the model to evaluate");
		fileSelectionText = new Text(compThreeCol, SWT.SINGLE);
		fileSelectionButton = new Button(compThreeCol, SWT.PUSH);
		fileSelectionButton.setText("Browse");
		
		dbSelectionLabel = new Label(compThreeCol, SWT.NULL);
		dbSelectionLabel.setText("Select the SQLite database to export the evaluation results");
		dbSelectionText = new Text(compThreeCol, SWT.SINGLE);
		dbSelectionButton = new Button(compThreeCol, SWT.PUSH);
		dbSelectionButton.setText("Browse");
		
		// Layout for the evaluation selection part
		Composite compTwoCol = (Composite)super.createDialogArea(composite);
		GridLayout layoutTwoCol = new GridLayout();
		compTwoCol.setLayout(layoutTwoCol);
		layoutTwoCol.numColumns = 2;
		
		dbTableLabel = new Label(compTwoCol, SWT.NULL);
		dbTableLabel.setText("SQLite table");
		dbTableText = new Text(compTwoCol, SWT.SINGLE);
		
		individualEvaluationButton = new Button(compTwoCol, SWT.CHECK);
		individualEvaluationLabel = new Label(compTwoCol, SWT.NULL);
		individualEvaluationLabel.setText("Individual evaluation of each category");
		evaluation.put(RbacUMLValidatorUtils.EVAL_WF, false);
		evaluation.put(RbacUMLValidatorUtils.EVAL_VER, false);
		evaluation.put(RbacUMLValidatorUtils.EVAL_SAT, false);
		evaluation.put(RbacUMLValidatorUtils.EVAL_COV, false);
		evaluation.put(RbacUMLValidatorUtils.EVAL_COMP, false);
		evaluation.put(RbacUMLValidatorUtils.EVAL_RED, false);
		individualEvaluationButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				evaluation.put(RbacUMLValidatorUtils.EVAL_WF, individualEvaluationButton.getSelection());
				evaluation.put(RbacUMLValidatorUtils.EVAL_VER, individualEvaluationButton.getSelection());
				evaluation.put(RbacUMLValidatorUtils.EVAL_SAT, individualEvaluationButton.getSelection());
				evaluation.put(RbacUMLValidatorUtils.EVAL_COV, individualEvaluationButton.getSelection());
				evaluation.put(RbacUMLValidatorUtils.EVAL_COMP, individualEvaluationButton.getSelection());
				evaluation.put(RbacUMLValidatorUtils.EVAL_RED, individualEvaluationButton.getSelection());
				log.info("Individual evaluation of each category set to " + individualEvaluationButton.getSelection());
			}
		});
		
		fullEvaluationButton = new Button(compTwoCol, SWT.CHECK);
		fullEvaluationLabel = new Label(compTwoCol, SWT.NULL);
		fullEvaluationLabel.setText("Full evaluation");
		evaluation.put(RbacUMLValidatorUtils.EVAL_FULL, false);
		fullEvaluationButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				evaluation.put(RbacUMLValidatorUtils.EVAL_FULL, fullEvaluationButton.getSelection());
				log.info("Full evaluation set to " + fullEvaluationButton.getSelection());
			}
		});
		
		lazyEvaluationButton = new Button(compTwoCol, SWT.CHECK);
		lazyEvaluationLabel = new Label(compTwoCol, SWT.NULL);
		lazyEvaluationLabel.setText("Lazy evaluation");
		evaluation.put(RbacUMLValidatorUtils.EVAL_FULL_LAZY, false);
		lazyEvaluationButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				evaluation.put(RbacUMLValidatorUtils.EVAL_FULL_LAZY, lazyEvaluationButton.getSelection());
				log.info("Lazy evaluation set to " + lazyEvaluationButton.getSelection());
			}
		});		
		
		return composite;
	}
	
	private void saveValues() {
		this.model = this.fileSelectionText.getText().toString();
		this.db = this.dbSelectionText.getText().toString();
		this.table = this.dbTableText.getText().toString();
	}
	
	protected String getModel() {
		return this.model;
	}
	
	protected String getDatabase() {
		return this.db;
	}
	
	protected String getTable() {
		return this.table;
	}
	
	protected boolean isEnabled(int evaluation) {
		assert(this.evaluation != null);
		if (this.evaluation.containsKey(evaluation))
			return this.evaluation.get(evaluation);
		return false;
	}
	
	protected void okPressed() {
		saveValues();
		super.okPressed();
	}
	
	protected Map<Integer, Boolean> getEvaluationSettings() {
		return this.evaluation;
	}
	

}
