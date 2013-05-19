/**
 * This class represents a Wizard where users can configure the fixing algorithm.
 * Users can set:
 * - the maximum height of the tree;
 * - the type of traversal strategy used to create said tree;
 * - the number of solutions to look for.
 * 
 * FIXME: In a future revision, users should also be able to select the constraint
 * selection strategy of their choosing.
 */
package uk.ac.open.rbacuml.dsml.plugin.research.validation;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Shell;

import uk.ac.open.rbacuml.dsml.plugin.research.fixing.Fix;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacNode;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacTree;
import uk.ac.open.tree.Tree;
import uk.ac.open.tree.traversal.ITraversalStrategy.Traversal;

/**
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public class RDFixingWizard extends Wizard {
	RDTreeSettingsWizardPage treeSettingsPage;
	Logger log = Logger.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		log.info("Wizard completed. MaxD set to " + getMaxD() 
				+ ", MaxSol set to " + getMaxSol()
				+ ", and Traversal strategy set to " + getTraversal());
		Tree tree = new RbacTree(getTraversal(), getMaxD(), getStatFileName(), getMaxSol());
		tree.setRoot(new RbacNode(null, null, tree));
		tree.fixModel();
		List<List<Fix>> solutions = ((RbacTree)tree).getSolutions();
		
		SolutionsDialog solutionsDialog = new SolutionsDialog(new Shell(), (RbacTree)tree);
		solutionsDialog.create();
		solutionsDialog.open();
		
		for (Object solution:solutions) {
			log.info("Solution: " + solution.toString());
		}
		if (((RbacTree)tree).getStatsObject().toFile(getStatFileName() + ".txt"))
			log.debug("Stats saved to file");
		else
			log.debug("Could not save stats to file");
		return true;
	}
	
	public void addPages() {
		treeSettingsPage = new RDTreeSettingsWizardPage("Solutions tree settings");
		addPage(treeSettingsPage);
	}
	
	private int getMaxD() {
		return treeSettingsPage.getMaxD();
	}
	
	private int getMaxSol() {
		return treeSettingsPage.getMaxSol();
	}
	
	private Traversal getTraversal() {
		return treeSettingsPage.getTraversal();
	}
	
	private String getStatFileName() {
		return treeSettingsPage.getStatFileName();
	}

}
