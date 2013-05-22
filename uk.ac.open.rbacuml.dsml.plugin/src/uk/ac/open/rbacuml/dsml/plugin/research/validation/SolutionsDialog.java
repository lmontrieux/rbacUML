package uk.ac.open.rbacuml.dsml.plugin.research.validation;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import uk.ac.open.rbacuml.dsml.plugin.research.fixing.Fix;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacTree;

public class SolutionsDialog {
	private Label label;
	private List<List<Fix>> solutions;
	
	public SolutionsDialog(Shell shell, List<List<Fix>> solutions) {
		this.solutions = solutions;
		shell.setLayout(new FillLayout());
		
		label = new Label(shell, SWT.NONE);
		label.setText("Found " + this.solutions.size() + " solutions");
		
		org.eclipse.swt.widgets.List list = new org.eclipse.swt.widgets.List(shell, SWT.V_SCROLL);
		
		for (List<Fix> solution:this.solutions) {
			String solStr = "Solution: ";
			for (Fix fix:solution) {
				solStr += "\n" + fix.toString();
			}
			list.add(solStr);
		}
		
		shell.open();
	}
	
//	public SolutionsDialog(Shell parent, RbacTree solutions) {
//		super(parent);
//		this.solutions = solutions.getSolutions();
//	}
//	
//	protected Control createDialogArea(Composite parent) {
//		Composite composite = (Composite) super.createDialogArea(parent);
//		composite.setSize(200, 800);
//		org.eclipse.swt.widgets.List list = new org.eclipse.swt.widgets.List(composite, SWT.V_SCROLL);
//		for (int i = 0; i < 255; i++) {
//			list.add(new Integer(i).toString());
//		}
//		
//		
////		GridLayout layout = new GridLayout();
////		layout.numColumns = 1;
////		composite.setLayout(layout);
////		
////		
////		label = new Label(composite, SWT.NULL);
////		label.setText(solutions.size() + " solution(s) found");
////		
////		ScrolledComposite scroll = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
////		GridLayout scrolledLayout = new GridLayout();
////		scrolledLayout.numColumns = 1;
////		scroll.setLayout(scrolledLayout);
////		scroll.setLayout(new FillLayout());
////		
////		Composite child = new Composite(scroll, SWT.NONE);
////	    GridLayout scrolledLayout = new GridLayout();
////	    scrolledLayout.numColumns = 1;
////	    child.setLayout(scrolledLayout);
////		
////		for (List<Fix> solution:this.solutions) {
////			org.eclipse.swt.widgets.List list = new org.eclipse.swt.widgets.List(child, SWT.BORDER | SWT.V_SCROLL);
////			for (Fix fix:solution) {
////				list.add(fix.toString());
////			}
////			ListViewer viewer = new ListViewer(list);
////		}
////		
////		scroll.setContent(child);
////		scroll.setExpandHorizontal(true);
////		scroll.setExpandVertical(true);
//		
////		Composite composite = (Composite)super.createDialogArea(parent);
////		GridLayout layout = new GridLayout();
////		composite.setLayout(layout);
////		layout.numColumns = 1;
////		
////		label = new Label(composite, SWT.NULL);
////		label.setText(solutions.size() + " solutions:");
////		scrollComp = new ScrolledComposite(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
////		scrollComp.setExpandHorizontal(true);
////		scrollComp.setExpandVertical(true);
////		Composite insideScrollComp = new Composite(scrollComp, SWT.NONE);
////		scrollComp.setContent(insideScrollComp);
////		GridLayout scrollLayout = new GridLayout();
////		scrollLayout.numColumns = 1;
////		insideScrollComp.setLayout(scrollLayout);
////		for (List<Fix> solution:this.solutions) {
////			Composite solutionComp = new Composite(insideScrollComp, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
////			GridLayout solutionLayout = new GridLayout();
////			solutionLayout.numColumns = 1;
////			solutionComp.setLayout(solutionLayout);
////			Label solLabel = new Label(solutionComp, SWT.NULL);
////			solLabel.setText("Solution:");
////			for (Fix fix:solution) {
////				solutionsLabel = new Label(solutionComp, SWT.NULL);
////				solutionsLabel.setText(fix.toString());
////			}
////		}
////		//insideScrollComp.setSize(insideScrollComp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
////		scrollComp.setSize(scrollComp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
//		
//		return composite;
//	}
//	

}
