/**
 * 
 */
package uk.ac.open.rbacuml.dsml.plugin.research.validation;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;

import uk.ac.open.rbacuml.dsml.plugin.research.RDNavHelper;
import uk.ac.open.rbacuml.dsml.plugin.research.RDSterHelper;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.Change;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.StereotypeAssociationAddChange;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.StereotypeAssociationDeleteChange;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacNode;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacTree;
import uk.ac.open.tree.traversal.ITraversalStrategy.Traversal;

import com.ibm.xtools.modeler.ui.UMLModeler;

/**
 * 
 * This is an action used for debugging puposes only. Should not be compiled
 * in the final version.
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public class RbacDSMLDebugAction extends AbstractActionDelegate implements
		IViewActionDelegate {
	private Logger log = Logger.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate#doRun(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		System.out.println("rbacDSML fixing debug started");
		Logger.getRootLogger().setLevel(Level.TRACE);
		if (UMLModeler.getEditingDomain().getCommandStack().canUndo())
			log.info("CAN UNDO");
		else
			log.info("CANNOT UNDO");
		List<Element> allElements = ((Element)UMLModeler.getUMLUIHelper().getSelectedElements().get(0)).getNearestPackage().allOwnedElements();
		Class granted = null;
		Class role = null;
		Class user = null;
		for (Element element:allElements) {
			if (element instanceof Class) {
				Class aClass = (Class)element;
				log.debug("Considering class " + aClass.getName());
				if (aClass.getAppliedStereotype(RDSterHelper.GRANTED) != null) {
					log.debug("Class " + aClass.getName() + " is Granted");
					granted = aClass;
				} else if (aClass.getAppliedStereotype(RDSterHelper.USER) != null) {
					log.debug("Class " + aClass.getName() + " is a user");
					user = aClass;
				} else if (aClass.getAppliedStereotype(RDSterHelper.ROLE) != null) {
					log.debug("Class " + aClass.getName() + " is a role");
					role = aClass;
				}
			}
		}
		
		Class attachedUser = RDNavHelper.scenarioGetUser(granted);
		log.debug("The user is " + attachedUser.getName());
		RbacTree tree = new RbacTree(Traversal.BFS, 10, "debug", 10);
		RbacNode node = new RbacNode(null, null, tree);
		Change firstChange = StereotypeAssociationAddChange.createAssociationAddChange(node, attachedUser, role, "", null);
		UMLModeler.getEditingDomain().getCommandStack().execute(firstChange.getEMFCommand());
		log.debug("Created new association");
		Association association = RDNavHelper.getAssociation(granted, user, null);
		Change change = StereotypeAssociationDeleteChange.createAssociationDeleteChange(node, association);
		log.debug("Deleted user-scenario association");
		UMLModeler.getEditingDomain().getCommandStack().execute(change.getEMFCommand());
		log.debug("Undoing last change");
		UMLModeler.getEditingDomain().getCommandStack().undo();
		UMLModeler.getEditingDomain().getCommandStack().undo();
		log.debug("Undone");
		Class user2 = RDNavHelper.scenarioGetUser(granted);
		if (user2 == null)
			log.debug("User is null");
		else
			log.debug("User is " + user2.getName());
	}
}
