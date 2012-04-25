package uk.ac.open.rbacuml.plugin.visualisation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;

import uk.ac.open.rbacuml.plugin.utils.RBACStereotypeNavigationHelper;

import com.ibm.xtools.modeler.ui.UMLModeler;

public class ConfigSelectionAction extends AbstractActionDelegate implements
		IViewActionDelegate {
	
	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		// this will contain the updated selection
		List<EObject> newSelection = new ArrayList<EObject>();
		// the elements selected when the menu item is clicked
		List<EObject> elements = UMLModeler.getUMLUIHelper().getSelectedElements();
		if (elements == null)
			return;
		newSelection.addAll(elements);
		for(EObject elt:elements) {
			if (elt instanceof Class) {
				Class cl = (Class)elt;
				if (cl.getAppliedStereotype("rbacUML::RBACUser") != null) {
					List<Class> rbacroles = RBACStereotypeNavigationHelper.getRBACRoles(cl);
					newSelection.addAll(rbacroles);
					for (Class rbacrole:rbacroles) {
						// adding all parent roles and their associated permissions
						for (Classifier parent:rbacrole.allParents()) {
							if (parent instanceof Class)
								newSelection.add(parent);
							List<Class> permissions = RBACStereotypeNavigationHelper.getPermissions((Class)parent);
							newSelection.addAll(permissions);
						}
						// adding the permissions associated to the roles
						List<Class> permissions = RBACStereotypeNavigationHelper.getPermissions(rbacrole);
						newSelection.addAll(permissions);
					}
				} else if (cl.getAppliedStereotype("rbacUML::RBACRole") != null) {
					List<Class> permissions = RBACStereotypeNavigationHelper.getPermissions(cl);
					newSelection.addAll(permissions);
					//TODO: for each role, add to the selection the users assigned to it
				} else if (cl.getAppliedStereotype("rbacUML::Permission") != null) {
					//TODO: for each permission, add to the selection the roles providing it, and then for each role, the users assigned to it
				}
			}
		}
		// now we update the selection
		UMLModeler.getUMLUIHelper().setSelectedElements("org.eclipse.ui.navigator.ProjectExplorer", newSelection);
	}
}
