/**
 * 
 */
package uk.ac.open.rbacuml.dsml.plugin.research.fixing.problem;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.uml2.uml.Class;

import uk.ac.open.rbacuml.dsml.plugin.research.RDNavHelper;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacNode;

/**
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public abstract class ScenarioProblem extends Problem {

	public ScenarioProblem(ConstraintStatus status, RbacNode node) {
		super(status, node);
	}
	
	/**
	 * Finds all the permissions that are at the end of an A path
	 * @return a list of permission classes connected to the context through 
	 * a path A.
	 */
	protected List<Class> findATargets() {
		log.debug("Finding all path A targets");
		List<Class> permissions = new ArrayList<Class>();
		List<Class> resources = RDNavHelper.scenarioGetResources((Class)target);
		for (Class resource:resources) {
			List<Class> candidates = RDNavHelper.resourceGetPermissions(resource);
			for (Class candidate:candidates) {
				if (!permissions.contains(candidate))
					permissions.add(candidate);
			}
		}
		log.debug("Found " + permissions.size() + " path A targets");
		return permissions;
	}
	
	/**
	 * Determines if there is a path B from the permission back to the context
	 * @param permission the permission from which the path B should start
	 * @return true if there is a path B, false otherwise.
	 */
	protected boolean hasBPath(Class permission) {
		log.debug("Determining if there is a path B from " + permission.getName());
		List<Class> roles = RDNavHelper.permissionGetRoles(permission);
		for (Class role:roles) {
			List<Class> scenarios = RDNavHelper.roleGetScenarios(role);
			if (scenarios.contains((Class)target)) {
				log.debug("Yes, there is one path B");
				return true;
			}
		}
		log.debug("No, there is no path B");
		return false;
	}

}
