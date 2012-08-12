/**
 * 
 */
package uk.ac.open.rbacuml.plugin.validation;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;

/**
 * @author Lionel Montrieux <L.M.C.Montrieux@open.ac.uk>
 *
 */
public class ConstraintSelectionAction extends AbstractActionDelegate 
	implements IViewActionDelegate {

	/**
	 * 
	 */
	public ConstraintSelectionAction() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate#doRun(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		ConstraintSelectionDialog dialog = new ConstraintSelectionDialog(new Shell());
		dialog.open();
		
	}

}
