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

public class ForbiddenProblem extends ScenarioProblem {

	public ForbiddenProblem(ConstraintStatus status, RbacNode node) {
		super(status, node);
		log.info("Forbidden Problem created.");
	}

	@Override
	public boolean solve() {
		log.info("Solving Forbidden::VER error");
		List<Class> permissions = findATargets();
		log.debug("The Forbidden scenario requires "
				+ permissions.size() + " permissions.");
		// Iterating once to make sure there actually is an error,
		// i.e. all paths A have at least one path B
		for (Class permission:permissions) {
			if (!hasBPath(permission))
				return false;
		}
		// First type of solution: breaking one B path
		log.debug("Type 1: removing B paths from one permission");
		for (Class permission:permissions) {
			this.solutions.addAll(breakBPaths(permission));
		}
		log.debug("Type 2: adding an A path to an existing, non-required permission");
		// Second type of solution: adding one A path that has no B path
		List<Class> allPermissions = RDNavHelper.getAllPermissions(permissions.get(0));
		for (Class permission:permissions) {
			if (allPermissions.contains(permission)) {
				if (allPermissions.remove(permission))
					log.debug("Removed permission " + permission.getName() + " from the list of unrequired permissions");
				else
					log.debug("error removing permission " + permission.getName() + " form the list of unrequired permissions");
			}
		}
		// allPermissions is now the set of Permissions to which there is NO
		// Path A. We create a Path A for each of them, and break the Path B if
		// there is one.
		for (Class permission:allPermissions) {
			List<Fix> solutions = createAPaths(permission);
			if (hasBPath(permission))
				solutions = mergeFixes(solutions, breakBPaths(permission));
			this.solutions.addAll(solutions);
		}
		// Third type of solution: create a new permission and create a path A
		// to it
		log.debug("Type 3: creating a new Permission and adding A paths to it");
		RDEltAddChange newPerm = createNewPermission((Class)target);
		this.solutions.addAll(createAPaths(newPerm));
		removeNonMinimalSolutions();
		purgeKeptChanges();
		return true;
	}
	
	private List<Fix> createAPaths(RDEltAddChange permission) {
		log.debug("Creating A paths for a new permission");
		List<Fix> fixes = new ArrayList<Fix>();
		List<Class> allResources = RDNavHelper.getAllResources((Class)target);
		List<Class> targetResources = RDNavHelper.scenarioGetResources((Class)target);
		log.debug("There is a total of " + allResources.size() + ", and the scenario requires " + targetResources.size() + " of them.");
		for (Class resource:allResources) {
			Fix fix = new Fix();
			fix.addChange(permission);
			if (!targetResources.contains(resource)) {
				Change change = StereotypeAssociationAddChange.createAssociationAddChange(node, (Class)target, resource, "", null);
				fix.addChange(change);
			}
			Change change = StereotypeAssociationAddChange.createAssociationAddChange(node, resource, permission, "", null);
			fix.addChange(change);
			fixes.add(fix);
		}
		Fix insert = new Fix();
		insert.addChange(permission);
		insert.addAllChanges(insertResource(permission, (Class)target).getAllChanges());
		fixes.add(insert);
		log.debug("Created " + fixes.size() + " A paths to the new permission");
		return fixes;
	}
	
	/**
	 * Creates all the possible paths A from the target to a permission
	 * @PRE: there is no path A from the target to the given permission
	 * @param permission the end of the generated paths A
	 * @return a list of Fixes that create paths A
	 */
	private List<Fix> createAPaths(Class permission) {
		log.debug("Creating A paths to permission " + permission.getName());
		List<Fix> fixes = new ArrayList<Fix>();
		List<Class> resources = RDNavHelper.scenarioGetResources((Class)target);
		for (Class resource:RDNavHelper.getAllResources(permission)) {
			log.debug("Adding an A path through Resource " + resource.getName());
			Fix fix = new Fix();
			if (!resources.contains(resource)) {
				log.debug("Adding an association between " + ((Class)target).getName() + " and " + resource.getName());
				Change change = StereotypeAssociationAddChange.createAssociationAddChange(node, (Class)target, resource, "", null);
				fix.addChange(change);
			}
			if (!RDNavHelper.resourceGetPermissions(resource).contains(permission)) {
				log.debug("Adding an association between " + resource.getName() + " and " + permission.getName());
				Change change = StereotypeAssociationAddChange.createAssociationAddChange(node, resource, permission, "", null);
				fix.addChange(change);
			}
			if (fix.getAllChanges().size() > 0)
				fixes.add(fix);
		}
		fixes.add(insertResource(permission, (Class)target));
		log.debug("Created " + fixes.size() + " A paths to permission " 
				+ permission.getName());
		return fixes;
	}
	
	/**
	 * Inserts a new resource between a scenario and a permission.
	 * Creates the new resource and creates two new associations: from the
	 * scenario to the resource, and from the resource to the permission
	 * @param scenario the start of the path
	 * @param permission the end of the path
	 * @return A Fix that creates the path.
	 */
	private Fix insertResource(Class scenario, Class permission) {
		log.debug("Inserting a new resource between scenario " 
				+ scenario.getName() + " and permission " + permission.getName());
		Fix fix = new Fix();
		RDEltAddChange newResource = createNewResource(scenario);
		fix.addChange(newResource);
		Change change = StereotypeAssociationAddChange.createAssociationAddChange(node, scenario, newResource, "", null);
		fix.addChange(change);
		change = StereotypeAssociationAddChange.createAssociationAddChange(node, newResource, permission, "", null);
		fix.addChange(change);
		return fix;
	}
	
	private Fix insertResource(RDEltAddChange permission, Class scenario) {
		log.debug("Inserting a new resource between permission " 
				+ permission.getName() + " and scenario " + scenario.getName());
		Fix fix = new Fix();
		RDEltAddChange newResource = createNewResource(scenario);
		fix.addChange(newResource);
		Change change = StereotypeAssociationAddChange.createAssociationAddChange(node, scenario, newResource, "", null);
		fix.addChange(change);
		change = StereotypeAssociationAddChange.createAssociationAddChange(node, newResource, permission, "", null);
		fix.addChange(change);
		return fix;
	}
	
	/**
	 * Breaks all the B paths starting at a particular permission
	 * @param permission the starting point of the B paths to break
	 * @return a list of fixes, each fix being a way of breaking all B
	 * paths from the permission passed.
	 */
	private List<Fix> breakBPaths(Class permission) {
		List<Path> paths = getBPaths(permission);
		log.debug("Breaking " + paths.size() + " path(s) from permission " + permission.getName());
		List<Fix> fixes = new ArrayList<Fix>();
		for (Path path:paths) {
			if (fixes.size() == 0)
				fixes = breakBPath(path);
			else
				fixes = mergeFixes(fixes, breakBPath(path));
		}
		log.debug("Generated " + fixes.size() + " fixes to break " + paths.size() + " paths");
		return fixes;
	}
	
	private List<Path> getBPaths(Class permission) {
		log.debug("Getting the B paths from permission " + permission.getName());
		List<Path> paths = new ArrayList<Path>();
		List<Class> roles = RDNavHelper.permissionGetRoles(permission);
		for (Class role:roles) {
			if (RDNavHelper.roleGetScenarios(role).contains((Class)target)) {
				Path path = new Path();
				path.addAssociation(RDNavHelper.getAssociation(permission, role, null));
				path.addAssociation(RDNavHelper.getAssociation(role, (Class)target, null));
				paths.add(path);
			}
		}
		return paths;
	}
	
	private List<Fix> breakBPath(Path path) {
		log.debug("Breaking path B: " + path.toString());
		List<Fix> fixes = new ArrayList<Fix>();
		for (Association association:path.getAssociations()) {
			Fix fix = new Fix();
			Change change = StereotypeAssociationDeleteChange.createAssociationDeleteChange(node, association);
			fix.addChange(change);
			fixes.add(fix);
		}
		log.debug("Path broken in " + fixes.size() + " fix(es).");
		return fixes;
	}
}
