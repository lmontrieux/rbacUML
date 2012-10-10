/**
 * 
 */
package uk.ac.open.rbacuml.plugin.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;

import com.ibm.xtools.modeler.ui.UMLModeler;

/**
 * @author Lionel Montrieux <L.M.C.Montrieux@open.ac.uk>
 *
 */
public class ConstraintSelectionDialog extends Dialog {
	Label orderedValidationLabel;
	Button orderedValidationButton;
	Label wfLabel;
	Label verLabel;
	Label satLabel;
	Label covLabel;
	Label compLabel;
	Label redLabel;
	Button wfButton;
	Button verButton;
	Button satButton;
	Button covButton;
	Button compButton;
	Button redButton;

	/**
	 * @param parentShell
	 */
	public ConstraintSelectionDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * @param parentShell
	 */
	public ConstraintSelectionDialog(IShellProvider parentShell) {
		super(parentShell);
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite)super.createDialogArea(parent);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 2;
		
		orderedValidationLabel = new Label(composite, SWT.NULL);
		orderedValidationLabel.setText("Lazy constraints evaluation");
		orderedValidationButton = new Button(composite, SWT.CHECK);
		orderedValidationButton.setSelection(true);
		
		wfLabel = new Label(composite, SWT.NULL);
		wfLabel.setText("Include Well-Formedness constraints");
		wfButton = new Button(composite, SWT.CHECK);
		wfButton.setSelection(true);
		
		verLabel = new Label(composite, SWT.NULL);
		verLabel.setText("Include Verification constraints");
		verButton = new Button(composite, SWT.CHECK);
		verButton.setSelection(true);
		
		satLabel = new Label(composite, SWT.NULL);
		satLabel.setText("Include Satisfiability constraints");
		satButton = new Button(composite, SWT.CHECK);
		satButton.setSelection(true);
		
		covLabel = new Label(composite, SWT.NULL);
		covLabel.setText("Include Coverage constraints");
		covButton = new Button(composite, SWT.CHECK);
		covButton.setSelection(true);
		
		compLabel = new Label(composite, SWT.NULL);
		compLabel.setText("Include Completeness constraints");
		compButton = new Button(composite, SWT.CHECK);
		compButton.setSelection(true);
		
		redLabel = new Label(composite, SWT.NULL);
		redLabel.setText("Include Redundancy constraints");
		redButton = new Button(composite, SWT.CHECK);
		redButton.setSelection(true);
		
		return composite;
	}
	
	protected void okPressed() {
		performValidation();
	}
	
	private void performValidation() {
		IRbacUMLValidator validator = new SimpleRbacUMLValidator();
		validator.setElements(this.computeElementsList());
		validator.setLazyEvaluation(this.orderedValidationButton.getSelection());
		validator.setConstraintEvaluation("wf", this.wfButton.getSelection());
		validator.setConstraintEvaluation("ver", this.verButton.getSelection());
		validator.setConstraintEvaluation("sat", this.satButton.getSelection());
		validator.setConstraintEvaluation("cov", this.covButton.getSelection());
		validator.setConstraintEvaluation("comp", this.compButton.getSelection());
		validator.setConstraintEvaluation("red", this.redButton.getSelection());
		validator.evaluate();
		long time = validator.getTimeMilliseconds();
		String message = "";
		if (validator.getResult().isOK())
			message = "pass in " + time + "ms";
		else
			message = "fail in " + time + "ms: " + validator.getResult().toString();
		String[] buttons = {"OK"};
		MessageDialog result = new MessageDialog(new Shell(), "rbacUML validation result", null, message, 0, buttons, 0);
		result.open();
	}
	
	private List<Element> computeElementsList() {
		List selected = UMLModeler.getUMLUIHelper().getSelectedElements();
		Element elt = (Element)UMLModeler.getUMLUIHelper().getSelectedElements().get(0);
		Package p = elt.getNearestPackage();
		EList<Package> pkgs = elt.getNearestPackage().allOwningPackages();
		List<Element> elements = p.allOwnedElements();
		List<Element> elts = new ArrayList<Element>();
		for (Element candidate:elements) {
				if (candidate.getAppliedStereotypes().size() > 0)
					elts.add(candidate);
		}
		return elts;
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Select rbacUML validation option");
	}

}
