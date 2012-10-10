package uk.ac.open.rbacuml.plugin.validation;

import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
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
	Label wfEvaluationLabel;
	Button wfEvaluationButton;
	Label fullEvaluationLabel;
	Button fullEvaluationButton;
	Label multiEvaluationLabel;
	Button multiEvaluationButton;
	Label lazyEvaluationLabel;
	Button lazyEvaluationButton;
	Label verEvaluationLabel;
	Button verEvaluationButton;
	Label verLazyEvaluationLabel;
	Button verLazyEvaluationButton;
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
		layout.numColumns = 2;
		
		fileSelectionLabel = new Label(composite, SWT.NULL);
		fileSelectionLabel.setText("Select the model to evaluate");
		fileSelectionText = new Text(composite, SWT.SINGLE);
		fileSelectionButton = new Button(composite, SWT.PUSH);
		fileSelectionButton.setText("Browse");
		
		dbSelectionLabel = new Label(composite, SWT.NULL);
		dbSelectionLabel.setText("Select the SQLite database to export the evaluation results");
		dbSelectionText = new Text(composite, SWT.SINGLE);
		dbSelectionButton = new Button(composite, SWT.PUSH);
		dbSelectionButton.setText("Browse");
		
		dbTableLabel = new Label(composite, SWT.NULL);
		dbTableLabel.setText("SQLite table");
		dbTableText = new Text(composite, SWT.SINGLE);
		
		individualEvaluationButton = new Button(composite, SWT.CHECK);
		individualEvaluationLabel = new Label(composite, SWT.NULL);
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
		
		fullEvaluationButton = new Button(composite, SWT.CHECK);
		fullEvaluationLabel = new Label(composite, SWT.NULL);
		fullEvaluationLabel.setText("Full evaluation");
		evaluation.put(RbacUMLValidatorUtils.EVAL_FULL, false);
		fullEvaluationButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				evaluation.put(RbacUMLValidatorUtils.EVAL_FULL, fullEvaluationButton.getSelection());
				log.info("Full evaluation set to " + fullEvaluationButton.getSelection());
			}
		});
		
		multiEvaluationButton = new Button(composite, SWT.CHECK);
		multiEvaluationLabel = new Label(composite, SWT.NULL);
		multiEvaluationLabel.setText("Multithreaded evaluation");
		evaluation.put(RbacUMLValidatorUtils.EVAL_FULL_MULTI, false);
		multiEvaluationButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				evaluation.put(RbacUMLValidatorUtils.EVAL_FULL_MULTI, multiEvaluationButton.getSelection());
				log.info("Multithreaded evaluation set to " + multiEvaluationButton.getSelection());
			}
		});
		
		lazyEvaluationButton = new Button(composite, SWT.CHECK);
		lazyEvaluationLabel = new Label(composite, SWT.NULL);
		lazyEvaluationLabel.setText("Lazy evaluation");
		evaluation.put(RbacUMLValidatorUtils.EVAL_FULL_LAZY, false);
		lazyEvaluationButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				evaluation.put(RbacUMLValidatorUtils.EVAL_FULL_LAZY, lazyEvaluationButton.getSelection());
				log.info("Lazy evaluation set to " + lazyEvaluationButton.getSelection());
			}
		});
		
		wfEvaluationButton = new Button(composite, SWT.CHECK);
		wfEvaluationLabel = new Label(composite, SWT.NULL);
		wfEvaluationLabel.setText("Well-formedness evaluation");
		evaluation.put(RbacUMLValidatorUtils.EVAL_WF, false);
		wfEvaluationButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				evaluation.put(RbacUMLValidatorUtils.EVAL_WF, wfEvaluationButton.getSelection());
				log.warn("WF evaluation set to " + wfEvaluationButton.getSelection());
			}
		});
		
		verEvaluationButton = new Button(composite, SWT.CHECK);
		verEvaluationLabel = new Label(composite, SWT.NULL);
		verEvaluationLabel.setText("Verification evaluation");
		evaluation.put(RbacUMLValidatorUtils.EVAL_WF_VER, false);
		verEvaluationButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				evaluation.put(RbacUMLValidatorUtils.EVAL_WF_VER, verEvaluationButton.getSelection());
				log.warn("VER evaluation set to " + verEvaluationButton.getSelection());
			}
		});
		
		verLazyEvaluationButton = new Button(composite, SWT.CHECK);
		verLazyEvaluationLabel = new Label(composite, SWT.NULL);
		verLazyEvaluationLabel.setText("Lazy verification evaluation");
		evaluation.put(RbacUMLValidatorUtils.EVAL_WF_VER_LAZY, false);
		verLazyEvaluationButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				evaluation.put(RbacUMLValidatorUtils.EVAL_WF_VER_LAZY, verLazyEvaluationButton.getSelection());
				log.warn("Lazy VER evaluation set to " + verLazyEvaluationButton.getSelection());
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
