package uk.ac.open.rbacuml.dsml.plugin.research.fixing.problem;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;

import uk.ac.open.rbacuml.dsml.plugin.research.RDNavHelper;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.Fix;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.Change;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.RDEltAddChange;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.StereotypeAssociationAddChange;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.StereotypeAssociationDeleteChange;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.path.Path;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacNode;

public class GrantedProblem extends ScenarioProblem {

	public GrantedProblem(ConstraintStatus status, RbacNode node) {
		super(status, node);
		log.info("Granted Problem created");
	}

	@Override
	public boolean solve() {
		log.info("Solving Granted::VER error");
		List<Class> permissions = findATargets();
		log.debug("The Granted scenario requires " 
				+ permissions.size() + " permissions");
		for (Class permission:permissions) {
			if (!hasBPath(permission)) {
				log.info("Permission " + permission.getName() 
						+ " is not available for the scenario (no B path)");
				this.solutions = mergeFixes(this.solutions, fixCycle(permission));
			}
		}
		removeNonMinimalSolutions();
		purgeKeptChanges();
		return true;
	}
	
	/**
	 * Generates all the minimal fixes related to the path A that ends in permission
	 * and has no path B. Fixes can be either the destruction of all the paths
	 * A to the permission, or the creation of one path B
	 * @param permission the end of a path A that has no path B
	 * @return a list of fixes, some destroying the path A, others creating the path B
	 */
	private List<Fix> fixCycle(Class permission) {
		log.debug("Fixing cycles that pass through permission " + permission.getName());
		List<Fix> fixes = new ArrayList<Fix>();
		List<Path> paths = getAPaths(permission);
		fixes.addAll(breakAPaths(paths));
		if (!hasBPath(permission)) {
			RDEltAddChange newRole = this.createNewRole(permission);
			fixes.addAll(createBPaths(permission, newRole));
		}
		log.debug("Generated " + fixes.size() + " fixes for permission " + permission.getName());
		return fixes;
	}
	
	/**
	 * Returns a list of all the A paths between the target and a permission
	 * @param permission the permission that's the end of the A paths
	 * @return a list (possibly empty) of A paths
	 */
	private List<Path> getAPaths(Class permission) {
		log.debug("Finding all paths A between the target and permisison " + permission.getName());
		List<Path> paths = new ArrayList<Path>();
		List<Class> resources = RDNavHelper.scenarioGetResources((Class)target);
		for (Class resource:resources) {
			if (RDNavHelper.resourceGetPermissions(resource).contains(permission)) {
				Path path = new Path();
				path.addAssociation(RDNavHelper.getAssociation((Class)target, resource, null));
				path.addAssociation(RDNavHelper.getAssociation(resource, permission, null));
				paths.add(path);
			}
		}
		log.debug("Found " + paths.size() + " path(s) between scenario " 
				+ ((Class)target).getName() + " and permission " + permission.getName());
		return paths;
	}
	
	/**
	 * Produces fixes that break all the A paths
	 * @param paths the list of A paths to break
	 * @return a list of fixes, each fix breaking all the A paths
	 */
	private List<Fix> breakAPaths(List<Path> paths) {
		log.debug("Breaking " + paths.size() + " paths A");
		List<Fix> fixes = new ArrayList<Fix>();
		for (Path path:paths) {
			fixes = mergeFixes(fixes, breakAPath(path));
		}
		log.debug("Paths A broken");
		return fixes;
	}
	
	/**
	 * Returns a list of all the minimal fixes that would break an A path
	 * @param path the path to break
	 * @return a list of all the minimal (i.e. only one association removed)
	 * ways of breaking the path
	 */
	private List<Fix> breakAPath(Path path) {
		log.debug("Breaking path A: " + path.toString());
		List<Fix> fixes = new ArrayList<Fix>();
		for (Association association:path.getAssociations()) {
			Fix fix = new Fix();
			Change change = StereotypeAssociationDeleteChange.createAssociationDeleteChange(node, association);
			fix.addChange(change);
			fixes.add(fix);
		}
		log.debug("Path broken");
		return fixes;
	}
	
	/**
	 * Creates all the B paths from a permission back to the target
	 * @param permission the origin of the B path, a permission
	 * @return a list of fixes that create all the possible B paths from the
	 * permission back to the target.
	 */
	private List<Fix> createBPaths(Class permission, RDEltAddChange newRole) {
		log.debug("Creating all possible B paths from permission " + permission.getName());
		List<Fix> fixes = new ArrayList<Fix>();
		List<Class> roles = RDNavHelper.getAllRoles(permission);
		for (Class role:roles)
			fixes.add(createBPath(permission, role));
		fixes.add(createBPathAndElement(permission, newRole));
		log.debug("Generated " + fixes.size() + " paths B");
		return fixes;
	}
	
	/**
	 * Creates a B path between a permission and the target, through a role.
	 * @param permission the origin of the B path
	 * @param role the role through which the path needs to travel
	 * @return a fix, or null if there is already a B path
	 */
	private Fix createBPath(Class permission, Class role) {
		log.debug("Creating a path B between permission " + permission.getName() 
				+ " and the target, through role " + role.getName());
		Fix fix = new Fix();
		if (!RDNavHelper.permissionGetRoles(permission).contains(role)) {
			Change change = StereotypeAssociationAddChange.createAssociationAddChange(node, permission, role, "", null);
			fix.addChange(change);
		}
		if (!RDNavHelper.roleGetScenarios(role).contains((Class)target)) {
			Change change = StereotypeAssociationAddChange.createAssociationAddChange(node, role, (Class) target, "", null);
			fix.addChange(change);
		}
		if (fix.getAllChanges().size() == 0) {
			log.debug("ERROR: the fix is empty");
			return null;
		}
		log.debug("path B created");
		return fix;
	}
	
	
	/**
	 * Create a path between the permission and the role, and puts the role
	 * change in first position in the fix.
	 * @param permission
	 * @param role
	 * @return
	 */
	private Fix createBPathAndElement(Class permission, RDEltAddChange role) {
		log.debug("Creating a path B between permission " + permission.getName() 
				+ " and the target, through role " + role.getName());
		Fix fix = new Fix();
		fix.addChange(role);
		if (!RDNavHelper.permissionGetRoles(permission).contains(role)) {
			Change change = StereotypeAssociationAddChange.createAssociationAddChange(node, permission, role, "", null);
			fix.addChange(change);
		}
		Change change = StereotypeAssociationAddChange.createAssociationAddChange(node, role, (Class)target, "", null);
		fix.addChange(change);
		if (fix.getAllChanges().size() == 0) {
			log.debug("ERROR: the fix is empty");
			return null;
		}
		log.debug("path B created");
		return fix;
	}
}
