/**
 * 
 */
package uk.ac.open.rbacuml.dsml.plugin.research.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.uml2.uml.Element;

import uk.ac.open.rbacuml.dsml.plugin.research.fixing.Fix;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacNode;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacTree;
import uk.ac.open.tree.Tree;
import uk.ac.open.tree.traversal.ITraversalStrategy.Traversal;

import com.ibm.xtools.modeler.ui.UMLModeler;

/**
 * @author Lionel Montrieux <L.M.C.Montrieux@open.ac.uk>
 *
 */
public class RbacDSMLValidationAction extends AbstractActionDelegate implements
		IViewActionDelegate {
	Logger log = Logger.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate#doRun(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		System.out.println("Validation Action started");
		Logger.getRootLogger().setLevel(Level.INFO);
		ResourceSet set = getResourceSetFromSelection();
		if (set != null) {
			IRBACValidator validator = OCLRBACValidator.getValidator();
			List<Element> elements = new ArrayList<Element>();
			org.eclipse.uml2.uml.Package pkg = ((Element)UMLModeler.getUMLUIHelper().getSelectedElements().get(0)).getNearestPackage();
			List<Element> allElements = pkg.allOwnedElements();
			for(EObject object: UMLModeler.getUMLUIHelper().getSelectedElements()) {
				if (object instanceof Element)
					elements.add((Element) object);
			}
			
			RDFixingWizard wizard = new RDFixingWizard();
			WizardDialog dialog = new WizardDialog(new Shell(), wizard);
			dialog.create();
			dialog.open();

//			String name = wizard.getStatFileName();
//			Tree tree = new RbacTree(wizard.getTraversal(), wizard.getMaxD(), wizard.getStatFileName(), wizard.getMaxSol());
			
//			String name = "marks-keep-scenario-20-5-AVG_COST-cat-3";
//			Tree tree = new RbacTree(Traversal.AVG_COST, 20, name, 5);
//			tree.setRoot(new RbacNode(null, null, tree));
//			tree.fixModel();
//			List<Fix> solutions = ((RbacTree)tree).getSolutions();
//			for (Object solution:solutions) {
//				log.info("Solution: " + solution.toString());
//			}
//			if (((RbacTree)tree).getStatsObject().toFile(name + ".txt"))
//				log.debug("Stats saved to file");
//			else
//				log.debug("Could not save stats to file");
		}
	}
	
	private ResourceSet getResourceSetFromSelection() {
		EObject eObj = UMLModeler.getUMLUIHelper().getSelectedElements().get(0);
		if (eObj instanceof Element) {
			Element element = (Element) eObj;
			return element.eResource().getResourceSet();
		}
		log.error("No UML element selected");
		return null;
	}

}
