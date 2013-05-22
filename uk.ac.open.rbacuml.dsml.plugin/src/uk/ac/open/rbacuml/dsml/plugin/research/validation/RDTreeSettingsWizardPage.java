/**
 * This class implements a wizard page for RDFixingWizard, where users are 
 * able to configure the behaviour of the solution tree creation algorithm.
 * 
 * This page allows users to set:
 * - the name of the file in which to save the stats
 * - the maximum height of the tree;
 * - the traversal strategy;
 * - the number of solutions to look for.
 * 
 *FIXME: the stats file name should move to another page, be optional, and 
 *come with a path too
 */
package uk.ac.open.rbacuml.dsml.plugin.research.validation;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import uk.ac.open.tree.traversal.ITraversalStrategy.Traversal;

/**
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public class RDTreeSettingsWizardPage extends WizardPage {
	private Text name;
	private Text maxD;
	private Text maxSol;
	private Combo traversalCombo;

	protected RDTreeSettingsWizardPage(String pageName) {
		super(pageName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		composite.setLayout(gl);
		
		// name for stats file
		Label nameLabel = new Label(composite, SWT.NONE);
		nameLabel.setText("Name of the statistics file");
		name = new Text(composite, SWT.BORDER);
		name.setText("model");
		name.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		name.setEditable(true);
		
		// max tree height
		Label maxDLabel = new Label(composite, SWT.NONE);
		maxDLabel.setText("Maximum tree height: ");
		maxD = new Text(composite, SWT.BORDER);
		maxD.setText("0");
		maxD.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		maxD.setEditable(true);
		
		// number of solutions
		Label numSolLabel = new Label(composite, SWT.NONE);
		numSolLabel.setText("Number of solutions to find: ");
		maxSol = new Text(composite, SWT.BORDER);
		maxSol.setText("0");
		maxSol.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		maxSol.setEditable(true);
		
		// traversal strategy
		Label traversalLabel = new Label(composite, SWT.NONE);
		traversalLabel.setText("Traversal strategy: ");
		traversalCombo = new Combo(composite, SWT.BORDER);
		// Adding all the traversal strategies defined
		Traversal[] traversalValues = Traversal.values();
		for (int i = 0; i < traversalValues.length; i++) {
			traversalCombo.add(traversalValues[i].name());
		}

		setControl(composite);
	}
	
	int getMaxD() {
		return new Integer(maxD.getText()).intValue();
	}
	
	int getMaxSol() {
		return new Integer(maxSol.getText()).intValue();
	}
	
	Traversal getTraversal() {
		return Traversal.valueOf(traversalCombo.getText());
	}
	
	String getStatFileName() {
		return name.getText();
	}

}
