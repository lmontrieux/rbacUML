package uk.ac.open.rbacuml.dsml.plugin.research.fixing.problem;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;

import uk.ac.open.rbacuml.dsml.plugin.research.RDNavHelper;
import uk.ac.open.rbacuml.dsml.plugin.research.RDSterHelper;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.Fix;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.Change;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.RDEltAddChange;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.StereotypeAssociationAddChange;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.StereotypeAssociationDeleteChange;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacNode;

public class ActivateRolesProblem extends Problem {

	public ActivateRolesProblem(ConstraintStatus status, RbacNode node) {
		super(status, node);
		log.info("ActivateRoles Problem created.");
	}

	@Override
	public boolean solve() {
		log.info("Solving Scenario::WF ActivateRoles");
		log.info("Scenario is " + ((Class)target).getName());
		List<Class> roles = findATargets();
		log.debug("The Scenario activates " + roles.size() + " roles");
		log.debug("Type 1: unassigned roles");
		this.solutions = fixUnassignedRoles(roles, RDNavHelper.scenarioGetUser((Class)target));
		log.debug("Type 1: " + this.solutions.size() + " solutions");
		log.debug("Type 2: swapping users");
		List<Class> users = RDNavHelper.getAllUsers((Class)target);
		users.remove(RDNavHelper.scenarioGetUser((Class)target));
		for (Class user:users) {
			List<Change> changes = swapUser(user);
			List<Fix> newChanges = assignRoles(roles, user);
			for (Fix fix:newChanges) {
				Fix newFix = new Fix();
				newFix.addAllChanges(changes);
				newFix.addAllChanges(fix.getAllChanges());
				this.solutions.add(newFix);
			}
		}
		log.debug("Type 2: " + this.solutions.size() + " solutions in total");
		log.debug("Type 3: swapping with a new created user");
		RDEltAddChange newUser = this.createNewUser((Class)target);
		List<Change> swap = swapUser(newUser);
		Fix newUserFix = new Fix();
		newUserFix.addChange(newUser);
		newUserFix.addAllChanges(swap);
		newUserFix.addAllChanges(assignRoles(roles, newUser).getAllChanges());
		this.solutions.add(newUserFix);
		removeNonMinimalSolutions();
		purgeKeptChanges();
		return true;
	}
	
	private List<Fix> fixUnassignedRoles(List<Class> roles, Class user) {
		List<Fix> fixes = new ArrayList<Fix>();
		List<Change> additions = new ArrayList<Change>();
		List<Change> deletions = new ArrayList<Change>();
 		for (Class role:roles) {
			Change change = StereotypeAssociationAddChange.createAssociationAddChange(node, role, user, "", null);
			additions.add(change);
			Change change2 = StereotypeAssociationDeleteChange.createAssociationDeleteChange(node, RDNavHelper.getAssociation(role, (Class)target, null));
			deletions.add(change2);
		}
 		fixes = combine(additions, deletions);
 		return fixes;
	}
	
	private List<Fix> combine(List<Change> list1, List<Change> list2) {
		assert(list1.size() == list2.size());
		log.debug("Combining two lists of size " + list1.size());
		List<Fix> fixes = new ArrayList<Fix>();
		Fix fix1 = new Fix();
		Fix fix2 = new Fix();
		if (list1.size() == 0) {
			return fixes;
		} else if (list1.size() == 1) {
			fix1.addChange(list1.get(0));
			fix2.addChange(list2.get(0));
			fixes.add(fix1);
			fixes.add(fix2);
		} else {
			List<Fix> combined = combine(list1.subList(1, list1.size() - 1), list2.subList(1, list2.size() - 1));
			for (Fix fix:combined) {
				Fix newFix = new Fix();
				newFix.addChange(list1.get(0));
				newFix.addAllChanges(fix.getAllChanges());
				fixes.add(newFix);
				Fix newFix2 = new Fix();
				newFix2.addChange(list2.get(0));
				newFix2.addAllChanges(fix.getAllChanges());
				fixes.add(newFix2);
			}
		}
		log.debug("Two lists of size " + list1.size() + " merged into a list of size " + fixes.size());
		return fixes;
	}
	
	private List<Fix> assignRoles(List<Class> roles, Class user) {
		if (user == null)
			throw new RuntimeException("User can't be null");
		List<Fix> fixes = new ArrayList<Fix>();
		for (Class role:roles) {
			if (!RDNavHelper.roleGetUsers(role).contains(user)) {
				Fix fix = new Fix();
				Change change = StereotypeAssociationAddChange.createAssociationAddChange(node, role, user, "", null);
				fix.addChange(change);
				fixes.add(fix);
			}
		}
		return fixes;
	}
	
	/**
	 * Assigns ALL the roles passed as argument to the new user
	 * @param roles the list of roles to assign
	 * @param user the user that gets the roles
	 * @return a fix that assigns all the roles to the user
	 */
	private Fix assignRoles(List<Class> roles, RDEltAddChange user) {
		Fix fix = new Fix();
		for (Class role:roles) {
			Change change = StereotypeAssociationAddChange.createAssociationAddChange(node, role, user, "", null);
			fix.addChange(change);
		}
		return fix;
	}
	
	
	private List<Class> findATargets() {
		log.debug("Finding all path A targets");
		List<Class> roles = RDNavHelper.scenarioGetRoles((Class)target);
		return roles;
	}
	
	private List<Change> swapUser(Class user) {
		assert(user.getAppliedStereotype(RDSterHelper.USER) != null);
		List<Change> changes = new ArrayList<Change>();
		Association association = RDNavHelper.getAssociation((Class)target, RDNavHelper.scenarioGetUser((Class)target), null);
		Change change = StereotypeAssociationDeleteChange.createAssociationDeleteChange(node, association);
		changes.add(change);
		Change change2 = StereotypeAssociationAddChange.createAssociationAddChange(node, (Class)target, user, "", null);
		changes.add(change2);
		return changes;
	}
	
	private List<Change> swapUser(RDEltAddChange user) {
		List<Change> changes = new ArrayList<Change>();
		Association association = RDNavHelper.getAssociation((Class)target, RDNavHelper.scenarioGetUser((Class)target), null);
		Change change = StereotypeAssociationDeleteChange.createAssociationDeleteChange(node, association);
		changes.add(change);
		Change change2 = StereotypeAssociationAddChange.createAssociationAddChange(node, (Class)target, user, "", null);
		changes.add(change2);
		return changes;
	}
}
