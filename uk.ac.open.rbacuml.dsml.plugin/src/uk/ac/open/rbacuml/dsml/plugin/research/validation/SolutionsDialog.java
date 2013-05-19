package uk.ac.open.rbacuml.dsml.plugin.research.validation;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import uk.ac.open.rbacuml.dsml.plugin.research.fixing.Fix;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacTree;

public class SolutionsDialog extends Dialog {
	private Label label;
	private Composite scrollComp;
	private Label solutionsLabel;
	private List<List<Fix>> solutions;
	
	public SolutionsDialog(Shell parent, RbacTree solutions) {
		super(parent);
		this.solutions = solutions.getSolutions();
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite)super.createDialogArea(parent);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 1;
		
		label = new Label(composite, SWT.NULL);
		label.setText(solutions.size() + " solutions:");
		scrollComp = new Composite(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		GridLayout scrollLayout = new GridLayout();
		scrollLayout.numColumns = 1;
		scrollComp.setLayout(scrollLayout);
		for (List<Fix> solution:this.solutions) {
			Composite solutionComp = new Composite(scrollComp, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			GridLayout solutionLayout = new GridLayout();
			solutionLayout.numColumns = 1;
			solutionComp.setLayout(solutionLayout);
			Label solLabel = new Label(solutionComp, SWT.NULL);
			solLabel.setText("Solution:");
			for (Fix fix:solution) {
				solutionsLabel = new Label(solutionComp, SWT.NULL);
				solutionsLabel.setText(fix.toString());
			}
		}
		
		return composite;
	}
	

}
