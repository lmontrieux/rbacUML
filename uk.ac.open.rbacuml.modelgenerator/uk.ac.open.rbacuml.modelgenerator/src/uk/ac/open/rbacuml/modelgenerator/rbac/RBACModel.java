package uk.ac.open.rbacuml.modelgenerator.rbac;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import uk.ac.open.rbacuml.modelgenerator.GeneratorUtils;
import uk.ac.open.rbacuml.modelgenerator.rbac.elements.Action;
import uk.ac.open.rbacuml.modelgenerator.rbac.elements.Activity;
import uk.ac.open.rbacuml.modelgenerator.rbac.elements.ActivityPartition;
import uk.ac.open.rbacuml.modelgenerator.rbac.elements.Element;
import uk.ac.open.rbacuml.modelgenerator.rbac.elements.ForbiddenAction;
import uk.ac.open.rbacuml.modelgenerator.rbac.elements.GrantedAction;
import uk.ac.open.rbacuml.modelgenerator.rbac.elements.Operation;
import uk.ac.open.rbacuml.modelgenerator.rbac.elements.Permission;
import uk.ac.open.rbacuml.modelgenerator.rbac.elements.RestrictedOperation;
import uk.ac.open.rbacuml.modelgenerator.rbac.elements.Role;
import uk.ac.open.rbacuml.modelgenerator.rbac.elements.UMLClass;
import uk.ac.open.rbacuml.modelgenerator.rbac.elements.UMLPackage;
import uk.ac.open.rbacuml.modelgenerator.rbac.elements.User;

public class RBACModel {
	static Logger log = Logger.getLogger("RBACModel");
	
	private String name = "";
	private Boolean wellFormed = null;
	private Boolean correct = null;
	private Boolean fullCoverage = null;
	private Boolean complete = null;
	private Boolean redundant = null;
	private Boolean satisfiable = null;
	private int nbrUsers;
	private int nbrRoles;
	private int nbrPermissions;
	private int nbrActivityPartitions;
	private int nbrActions;
	private int nbrGrantedActions;
	private int nbrForbiddenActions;
	private int nbrOperations;
	private int nbrRestrictedOperations;
	private int nbrClasses;
	private int nbrActivities;
	private int minRolesPerUser;
	private int maxRolesPerUser;
	private int minPermsPerRole;
	private int maxPermsPerRole;
	private int minPermsPerOp;
	private int maxPermsPerOp;
	private int minOpsPerAction;
	private int maxOpsPerAction;
	private int minRestOpsPerAction;
	private int maxRestOpsPerAction;
	private int minSSoD;
	private int maxSSoD;
	private int minDSoD;
	private int maxDSoD;
	private int minRoleHierarchies;
	private int maxRoleHierarchies;
	
	private List<User> users = new ArrayList<User>();
	private List<Role> roles = new ArrayList<Role>();
	private List<Permission> permissions = new ArrayList<Permission>();
	private List<Activity> activities = new ArrayList<Activity>();
	private List<ActivityPartition> activityPartitions = new ArrayList<ActivityPartition>();
	private List<Action> actions = new ArrayList<Action>();
	private List<GrantedAction> grantedActions = new ArrayList<GrantedAction>();
	private List<ForbiddenAction> forbiddenActions = new ArrayList<ForbiddenAction>();
	private List<Operation> operations = new ArrayList<Operation>();
	private List<RestrictedOperation> restrictedOperations = new ArrayList<RestrictedOperation>();
	private List<UMLClass> classes = new ArrayList<UMLClass>();
	
	/**
	 * Generates a new model according to the size options passed as parameters.
	 * For Boolean options, use 'null' for random;
	 * For int options, use '0' for random;
	 * @param name the model name
	 * @param wellFormed true if the model should be well-formed
	 * @param correct true if the model should be correct (i.e. well-formed 
	 * and verified)
	 * @param fullCoverage true if the model should be completely covered 
	 * (i.e. well-formed and verified too)
	 * @param complete true if the model should be complete (i.e. well-formed 
	 * and verified too)
	 * @param redundant true if there should be redundant elements
	 * @param nbrUsers number of users to generate
	 * @param nbrRoles number of roles to generate
	 * @param nbrPermissions number of permissions to generate
	 * @param nbrActivityPartitions number of Activity partitions to generate
	 * @param nbrActions number of unstereotyped Actions to generate
	 * @param nbrGrantedActions number of «Granted» Actions to generate
	 * @param nbrForbiddenActions number of «Forbidden» Actions to generate
	 * @param nbrOperations number of unstereotyped Operations to generate
	 * @param nbrRestrictedOperations number of «Restricted» to generate
	 * @param nbrClasses number of Classes to generate
	 * @param nbrActivityDiagrams number of Activity Diagrams to generate
	 * @param minRolesPerUser minimum number of Roles assigned to each User
	 * @param maxRolesPerUser maximum number of Roles assigned to each User
	 * @param minPermsPerRole minimum number of Permissions assigned to each 
	 * Role
	 * @param maxPermsPerRole maximum number of Permissions assigned to each 
	 * Role
	 * @param minPermsPerOp minimum number of Permissions for each 
	 * «Restricted» Operation
	 * @param maxPermsPerOp maximum number of Permissions for each 
	 * «Restricted» Operation
	 * @param minOpsPerAction minimum number of Operations per «Granted» or 
	 * «Forbidden» Action
	 * @param maxOpsPerAction maximum number of Operations per «Granted» or 
	 * «Forbidden» Action
	 * @param minRestOpsPerAction minimum number of «Restricted» Operations 
	 * per «Granted» or «Forbidden» Action
	 * @param maxRestOpsPerAction maximum number of «Restricted» Operations 
	 * per «Granted» or «Forbidden» Action
	 */
	public RBACModel(String name, boolean wellFormed, boolean correct, 
			boolean fullCoverage, boolean complete, boolean redundant, 
			boolean satisfiable, int nbrUsers, int nbrRoles, int nbrPermissions, 
			int nbrActivityPartitions, int nbrActions, int nbrGrantedActions, 
			int nbrForbiddenActions, int nbrOperations, 
			int nbrRestrictedOperations, int nbrClasses, 
			int nbrActivities, int minSSoD, int maxSSoD, int minDSoD, 
			int maxDSoD, int minRoleHierarchies, int maxRoleHierarchies,
			int minRolesPerUser, int maxRolesPerUser, int minPermsPerRole, 
			int maxPermsPerRole, int minPermsPerOp, int maxPermsPerOp, 
			int minOpsPerAction, int maxOpsPerAction, int minRestOpsPerAction, 
			int maxRestOpsPerAction) {
		log.setLevel(Level.DEBUG);
		
		if ((minPermsPerOp > maxPermsPerOp) || 
				(minOpsPerAction > maxOpsPerAction) ||
				(minRolesPerUser > maxRolesPerUser) || 
				(minPermsPerRole > maxPermsPerRole) ||
				(minRestOpsPerAction > maxRestOpsPerAction) ||
				(minSSoD > maxSSoD) ||
				(minDSoD > maxDSoD))
			throw new IllegalArgumentException();
		if (((maxPermsPerRole * nbrRoles) < nbrPermissions)
				|| ((maxRolesPerUser * nbrUsers) < nbrRoles)
				|| ((maxPermsPerOp * nbrOperations) < nbrPermissions))
			throw new IllegalArgumentException("Can't create a valid rbacUML model with these settings");
			
		this.name = name;
		this.wellFormed = wellFormed;
		this.correct = correct;
		this.fullCoverage = fullCoverage;
		this.complete = complete;
		this.redundant = redundant;
		this.satisfiable = satisfiable;
		this.nbrUsers = nbrUsers;
		this.nbrRoles = nbrRoles;
		this.nbrPermissions = nbrPermissions;
		this.nbrActivityPartitions = nbrActivityPartitions;
		this.nbrActions = nbrActions;
		this.nbrGrantedActions = nbrGrantedActions;
		this.nbrForbiddenActions = nbrForbiddenActions;
		this.nbrOperations = nbrOperations;
		this.nbrRestrictedOperations = nbrRestrictedOperations;
		this.nbrClasses = nbrClasses;
		this.nbrActivities = nbrActivities;
		this.minRolesPerUser = minRolesPerUser;
		this.maxRolesPerUser = maxRolesPerUser;
		this.minPermsPerRole = minPermsPerRole;
		this.maxPermsPerRole = maxPermsPerRole;
		this.minPermsPerOp = minPermsPerOp;
		this.maxPermsPerOp = maxPermsPerOp;
		this.minOpsPerAction = minOpsPerAction;
		this.maxOpsPerAction = maxOpsPerAction;
		this.minRestOpsPerAction = minRestOpsPerAction;
		this.maxRestOpsPerAction = maxRestOpsPerAction;
		this.minSSoD = minSSoD;
		this.maxSSoD = maxSSoD;
		this.minDSoD = minDSoD;
		this.maxDSoD = maxDSoD;
		this.minRoleHierarchies = minRoleHierarchies;
		this.maxRoleHierarchies = maxRoleHierarchies;
		generateModel();
	}
	
	/** Creates a new RBACModel from a Map of options
	 * 
	 * @param options
	 */
	public RBACModel(Map<String, Object> options) {
		log.setLevel(Level.WARN);
		this.name = (String)options.get("name");
		this.wellFormed = true;//(Boolean)options.get("well-formedness");
		this.correct = true;//(Boolean)options.get("verification");
		this.fullCoverage = true;//(Boolean)options.get("coverage");
		this.complete = true;//(Boolean)options.get("completeness");
		this.redundant = true;//(Boolean)options.get("redundancy");
		this.satisfiable = true;//(Boolean)options.get("satisfiability");
		this.nbrUsers = new Integer((String)options.get("users"));
		this.nbrRoles= new Integer((String)options.get("roles"));
		this.nbrPermissions = new Integer((String)options.get("permissions"));
		this.nbrActivityPartitions = new Integer((String)options.get("partitions"));
		this.nbrActions = new Integer((String)options.get("actions"));
		this.nbrGrantedActions = new Integer((String)options.get("granted"));
		this.nbrForbiddenActions = new Integer((String)options.get("forbidden"));
		this.nbrOperations = new Integer((String)options.get("operations"));
		this.nbrRestrictedOperations = new Integer((String)options.get("restricted"));
		this.nbrClasses = new Integer((String)options.get("classes"));
		this.nbrActivities = new Integer((String)options.get("activities"));
		this.minSSoD = new Integer((String)options.get("minSSoD"));
		this.maxSSoD = new Integer((String)options.get("maxSSoD"));
		this.minRoleHierarchies = new Integer((String)options.get("minRoleHierarchies"));
		this.maxRoleHierarchies = new Integer((String)options.get("maxRoleHierarchies"));
		this.minRolesPerUser = new Integer((String)options.get("minRolesPerUser"));
		this.maxRolesPerUser = new Integer((String)options.get("maxRolesPerUser"));
		this.minPermsPerRole = new Integer((String)options.get("minPermissionsPerRole"));
		this.maxPermsPerRole = new Integer((String)options.get("maxPermissionsPerRole"));
		this.minPermsPerOp = new Integer((String)options.get("minPermissionsPerOp"));
		this.maxPermsPerOp = new Integer((String)options.get("maxPermissionsPerOp"));
		this.minOpsPerAction = new Integer((String)options.get("minOpsPerAction"));
		this.maxOpsPerAction = new Integer((String)options.get("maxOpsPerAction"));
		this.minRestOpsPerAction = new Integer((String)options.get("minRestOpsPerAction"));
		this.maxRestOpsPerAction = new Integer((String)options.get("maxRestOpsPerAction"));
		
		generateModel();
	}
	
	private void generateModel() {
		UMLPackage root = new UMLPackage("Root Package", null);
		UMLPackage ac = new UMLPackage("Access Control", root);
		UMLPackage permissionsPkg = new UMLPackage("Permissions", ac);
		UMLPackage rolesPkg = new UMLPackage("Roles", ac);
		UMLPackage usersPkg = new UMLPackage("Users", ac);
		generatePermissions(this.nbrPermissions, permissionsPkg);
		generateRoles(this.nbrRoles, rolesPkg);
		generateUsers(this.nbrUsers, usersPkg);
		log.warn("Generated " + this.users.size() + " users, " 
				+ this.roles.size() + " roles, and " + this.permissions.size() 
				+ " permissions");
		if (this.maxRoleHierarchies > 0) {
			log.warn("Generating role hierarchies...");
			generateRoleHierarchies(this.minRoleHierarchies, this.maxRoleHierarchies);
			log.warn("Role hierarchies generated");
		}
		if (this.maxSSoD > 0) {
			log.warn("Generating SSoD constraints...");
			generateSSoD(this.minSSoD, this.maxSSoD);
			log.warn("SSoD constraints generated");
		}
		if (this.maxDSoD > 0) {
			log.warn("Generating DSoD constraints...");
			generateDSoD(this.minDSoD, this.maxDSoD);
			log.warn("DSoD constraints generated");
		}
		generateUserRoleAssignments(this.minRolesPerUser, this.maxRolesPerUser);
		generateRolePermissionAssignments(this.minPermsPerRole, this.maxPermsPerRole);
		log.warn("User -> Role and Role -> permission assignments generated");
		generateClasses(this.nbrClasses, root);
		log.warn("Generated " + this.classes.size() + " classes");
		generateOperations(this.nbrOperations);
		generateRestrictedOperations(this.nbrRestrictedOperations);
		generateOperationPermissionAssignments(this.minPermsPerOp, this.maxPermsPerOp);
		log.warn("Generated " + this.operations.size() + " Operations and " 
				+ this.restrictedOperations.size() + " Restricted Operations, ");
		generateActivities(this.nbrActivities, root);
		generateActivityPartitions(this.nbrActivityPartitions);
		log.warn("Generated " + this.activities.size() + " Activities and " 
				+ this.activityPartitions.size() + " Activity Partitions");
		generateGrantedActions(this.nbrGrantedActions);
		generateForbiddenActions(this.nbrForbiddenActions);
		log.warn("Generated " + this.grantedActions.size() + " Grande Actions " 
				+ this.forbiddenActions.size() + " Forbidden Actions");
	}
	
	/**
	 * Generate a give amount of activity partitions. These are randomly assigned 
	 * to activities that have already been created.
	 * PRECONDITION: this.activities.size() > 0
	 * @param nbrActivityPartitions the number of partitions to create
	 */
	private void generateActivityPartitions(int nbrActivityPartitions) {
		assert this.activities.size() > 0;
		log.debug("Entering generateActivityPartitions(int nbrActivityPartitions)");
		Random rdm = new Random();
		for (int i = 0; i < nbrActivityPartitions; i++) {
			Activity activity = this.activities.get(rdm.nextInt(this.activities.size()));
			User user = this.users.get(rdm.nextInt(this.users.size()));
			ActivityPartition partition = new ActivityPartition(user.getName(), activity, user);
			log.debug("Activity Partition " + partition.getName() 
					+ " created within Activity " + activity.getName());
			generateAPRoleAssignments(partition);
			activity.addActivityPartition(partition);
			this.activityPartitions.add(partition);
		}
		log.debug("Leaving generateActivityPartitions(int nbrActivityPartitions)");
	}
	
	/**
	 * Generates Partition->Role assignments for a partition. The assignments 
	 * are a random subset of the roles assigned to the user the partition 
	 * refers to. Assigned roles will not cause DSoD violations.
	 * @param partition the partition that needs roles assigned
	 */
	private void generateAPRoleAssignments(ActivityPartition partition) {
		log.debug("Entering generateAPRoleAssignments(ActivityPartition partition)");
		
		Random rdm = new Random();
		List<Role> roles = partition.getUser().getRoles();
		
		log.warn("Partition " + partition.getName() + "'s user has " 
				+ roles.size() + " role(s) assigned");
		
		if (roles.size() == 0) {
			log.warn("The user has no roles assigned, nothing to do here");
			return;
		}
		int nbrRoles = rdm.nextInt(roles.size() + 1);
		log.warn(nbrRoles + " roles will be activated on partition " + partition.getName());
		for (int i = 0; i < nbrRoles; i++) {
			Role role = roles.get(rdm.nextInt(roles.size()));
			log.warn("Selecting role " + role.getName());
			if (!role.hasDSoD(role)) {
				partition.addRole(role);
				log.warn("Added role " + role.getName() + " to partition " + partition.getName());
			}
			for (Role dsod: role.getDSoD()) {
				if (!partition.getRoles().contains(dsod)) {
					partition.addRole(role);
					log.warn("Added role " + role.getName() 
							+ " to partition " + partition.getName());
				}
				else {
					// TODO:Deal with this problem more elegantly - this reduces 
					// the number of assigned roles
					log.warn("Will not add role " + role.getName() 
							+ " to partition " + partition.getName() 
							+ " because of a DSoD conflict with role " 
							+ dsod.getName());
				}
			}
			
			log.debug("Added role " + role.getName() + " to partition " 
					+ partition.getName());
		}
		
	}
	
	/**
	 * Generate activities and adds them to the activities list
	 * @param nbrActivities the number of activities to generate
	 * @param parent the parent element for all the activities
	 */
	private void generateActivities(int nbrActivities, Element parent) {
		log.debug("Entering generateActivities(int nbrActivities, Element parent)");
		log.debug("About to generate " + nbrActivities + " activities");
		for (int i = 1; i <= nbrActivities; i++) {
			this.activities.add(new Activity("Activity" + i, parent));
			log.debug("created activity " + i);
		}
	}
	
	/**
	 * Generates a given number of «Forbidden» Actions, and assigns each to a 
	 * random Activity Partition.
	 * PRECONDITION: this.activityPartitions.size() > 0
	 * @param nbrForbiddenActions the number of «Forbidden» Actions to generate
	 */
	private void generateForbiddenActions(int nbrForbiddenActions) {
		assert this.activityPartitions.size() > 0;
		log.debug("Entering generateForbiddenActions(int nbrForbiddenActions)");
		log.debug("About to generate " + nbrForbiddenActions 
				+ " «Forbidden» Actions");
		Random rdm = new Random();
		for (int i = 1; i <= nbrForbiddenActions; i++) {
			ActivityPartition partition = this.activityPartitions.get(rdm.nextInt(this.activityPartitions.size()));
			ForbiddenAction action = new ForbiddenAction("ForbiddenAction" + i, partition);
			generateForbiddenActionOperationAssignments(action);
			this.forbiddenActions.add(action);
			((Activity)partition.getParent()).addAction(action);
			log.debug("Added action " + action.getName() + " to Partition " + partition.getName());
		}
	}
	
	/**
	 * Assigns operations to «Forbidden» Actions. The number of assigned actions
	 * is random within the specified limits, and at least one operation cannot
	 * be performed by the user, as required for the «Forbidden» verification
	 * @param action The «Forbidden» action to which operations should be assigned
	 */
	private void generateForbiddenActionOperationAssignments(
			ForbiddenAction action) {
		log.debug("Entering generateForbiddenActionOperationAssignments(ForbiddenAction action)");
		List<Permission> availablePermissions = getAvailablePermissions(action);
		log.debug("There are " + availablePermissions.size() + " permissions");
		
		// We list all operations that can't be performed by the user - we'll
		// need at least one of those
		List<RestrictedOperation> unexecutableOperations = new ArrayList<RestrictedOperation>();
		List<RestrictedOperation> executableOperations = new ArrayList<RestrictedOperation>();
		for (RestrictedOperation operation: this.restrictedOperations) {
			if (!availablePermissions.containsAll(operation.getPermissions())) {
				unexecutableOperations.add(operation);
			} else {
				executableOperations.add(operation);
			}
		}
		// TODO: handle the case where there's no unexecutable operation
		if (unexecutableOperations.size() == 0)
			return;
		log.debug("There are " + unexecutableOperations.size() 
				+ " operations that the user wouldn't be able to fulfill");
		
		// Randomly selecting the number of operations to add
		Random rdm = new Random();
		// we need at least one, hence the +1
		int nbrOperations = rdm.nextInt(this.maxRestOpsPerAction) + 1;
		// how many unexecutable operations should be lower than or equal 
		// to their number, but we need at least one, hence the +1
		int nbrUnexecutableOperations = Math.min(rdm.nextInt(unexecutableOperations.size()) + 1, nbrOperations);
		int nbrExecutableOperations = Math.min(nbrOperations - nbrUnexecutableOperations, executableOperations.size());
		List operations = GeneratorUtils.selectRandomElement(unexecutableOperations, nbrUnexecutableOperations);
		operations.addAll(GeneratorUtils.selectRandomElement(executableOperations, nbrExecutableOperations));
		
		for (Object operation:operations) {
			action.addOperation((Operation)operation);
			log.debug("Operation " + ((Operation)operation).getName() + " added to Action " + action.getName());
		}
	}
	
	/**
	 * Given an Action, finds all the permissions available to said Action through 
	 * the roles associated to its ActivityPartition
	 * @param action the action to consider
	 * @return a List of permissions that the user has activated
	 */
	private List<Permission> getAvailablePermissions(Action action) {
		List<Permission> permissions = new ArrayList<Permission>();
		for (Role role: ((ActivityPartition) action.getParent()).getRoles())
			permissions.addAll(role.getPermissions());
		return permissions;
	}
	
	/**
	 * Generates a given number of «Granted» Actions, and assigns each to a 
	 * random Activity Partition.
	 * PRECONDITION: this.activityPartitions.size() > 0
	 * @param nbrGrantedActions the number of «Granted» Actions to generate
	 */
	private void generateGrantedActions(int nbrGrantedActions) {
		assert this.activityPartitions.size() > 0;
		log.debug("Entering generateGrantedActions(int nbrGrantedActions)");
		Random rdm = new Random();
		for (int i = 1; i <= nbrGrantedActions; i++) {
			ActivityPartition partition = this.activityPartitions.get(rdm.nextInt(this.activityPartitions.size()));
			GrantedAction action = new GrantedAction("GrantedAction" + i, partition);
			log.debug("Generated Action " + action.getName() 
					+ " inside Partition " + partition.getName());
			if (generateGrantedActionOperationAssignments(action)) {
				this.grantedActions.add(action);
				((Activity)partition.getParent()).addAction(action);
				partition.addAction(action);
			} else {
				//TODO: this lowers the number of actions generated. We should 
				//instead try to create another one
				log.warn("Could not find suitable operations for this action, the action will be discarded");
			}
		}
	}
	
	private boolean generateGrantedActionOperationAssignments(GrantedAction action) {
		log.debug("Entering generateGrantedActionOperationAssignments(GrantedAction action)");
		List<Permission> availablePermissions = getAvailablePermissions(action);
		log.debug("There are " + availablePermissions.size() + " permissions available");
		// We compute the list of operations that could be performed with 
		// those permissions
		List<RestrictedOperation> executableOperations = new ArrayList<RestrictedOperation>();
		for (RestrictedOperation operation:this.restrictedOperations) {
			if (availablePermissions.containsAll(operation.getPermissions())) {
				executableOperations.add(operation);
			}
		}
		// If no operation can be executed by the user, we need to find another
		// strategy
		if (executableOperations.size() == 0)
			return false;
		log.debug("There are " + executableOperations.size() 
				+ " operations that the user can execute");
		// Finally, we randomly select some of these operations for the Action
		Random rdm = new Random();
		int max = Math.min(this.maxRestOpsPerAction, executableOperations.size());
		int nbrOperations = rdm.nextInt(max) + 1;
		List ops = GeneratorUtils.selectRandomElement(executableOperations, nbrOperations);
		for (Object op:ops) {
			action.addOperation((Operation)op);
		}
		return true;
	}
	
	private void generateUsers(int number, Element parent) {
		int nbr = this.users.size();
		for (int i = 0; i < number; i++) {
			nbr++;
			this.users.add(new User("User" + nbr, parent));
		}
	}
	
	private void generateRoles(int number, Element parent) {
		int nbr = this.roles.size();
		for (int i = 0; i < number; i++) {
			nbr++;
			this.roles.add(new Role("Role" + nbr, parent));
		}
	}
	
	private void generatePermissions(int number, Element parent) {
		int nbr = this.permissions.size();
		for (int i = 0; i < number; i++) {
			nbr++;
			this.permissions.add(new Permission("Permission" + nbr, parent));
		}
	}
	
	private void generateClasses(int number, Element parent) {
		int nbr = this.classes.size();
		for (int i = 0; i < number; i++) {
			nbr++;
			this.classes.add(new UMLClass("Class" + nbr, parent));
		}
	}
	
	private void generateOperations(int number) {
		Random rdm = new Random();
		for (int i = 0; i < number; i++) {
			UMLClass cl = this.classes.get(rdm.nextInt(this.classes.size()));
			Operation op = new Operation("operation" + i, cl);
			cl.addOperation(op);
			this.operations.add(op);
		}
	}
	
	private void generateRestrictedOperations(int number) {
		Random rdm = new Random();
		for (int i = 0; i < number; i++) {
			UMLClass cl = this.classes.get(rdm.nextInt(this.classes.size()));
			RestrictedOperation rop = new RestrictedOperation("restrictedOperation" + i, cl);
			cl.addOperation(rop);
			this.restrictedOperations.add(rop);
		}
	}
	
	private void generateOperationPermissionAssignments(int min, int max) {
		Random rdm = new Random();
		for (RestrictedOperation operation: this.restrictedOperations) {
			int number = rdm.nextInt(max - min) + min + 1;
			List permissions = GeneratorUtils.selectRandomElement(this.permissions, number);
			for (Object permission:permissions) {
				operation.addPermission((Permission)permission);
			}
		}
		
	}
	
	private void generateSSoD(int min, int max) {
		assert(min <= max);
		Random rdm = new Random();
		int nbr = rdm.nextInt(max - min) + min;
		int i = 0;
		while (i < nbr) {
			Role role1 = this.roles.get(rdm.nextInt(this.roles.size() - 1));
			Role role2 = this.roles.get(rdm.nextInt(this.roles.size() - 1));
			// Two roles shouldn't have more than 1 SoD relationship
			if (role1.hasSoD(role2)) {
				role1.addSSoD(role2);
				role2.addSSoD(role1);
				i++;
			}
		}
	}
	
	private void generateDSoD(int min, int max) {
		assert(min <= max);
		Random rdm = new Random();
		int nbr = rdm.nextInt(max - min) + min;
		int i = 0;
		while (i < nbr) {
			Role role1 = this.roles.get(rdm.nextInt(this.roles.size() - 1));
			Role role2 = this.roles.get(rdm.nextInt(this.roles.size() - 1));
			// Two roles shouldn't have more than 1 SoD relationship
			if (role1.hasSoD(role2)) {
				role1.addDSoD(role2);
				role2.addDSoD(role1);
				i++;
			}
		}
	}
	
	private void generateRoleHierarchies(int min, int max) {
		assert(min <= max);
		Random rdm = new Random();
		int nbr = rdm.nextInt(max - min) + min;
		int i = 0;
		// FIXME: potential infinite loop if no more parent-child relationships 
		// can be created without introducing cycles
		while (i < nbr) {
			Role role1 = this.roles.get(rdm.nextInt(this.roles.size() - 1));
			Role role2 = this.roles.get(rdm.nextInt(this.roles.size() - 1));
			if (!role1.hasAncestor(role2)) {
				role1.addParent(role2);
				i++;
			}
		}
	}
	
	/**
	 * Generates random user-roles assignments. The number of assignments is 
	 * between min and max
	 * @param min minimum number of assignments
	 * @param max maximum number of assignments
	 */
	private void generateUserRoleAssignments(int min, int max) {
		assert(min <= max);
		assert((min >= 0) && (max >= 0));
		// if there's no assignment needed, there's nothing to do here
		if (max == 0) return;
		for (int i = 0; i < this.users.size(); i++) {
			User user = this.users.get(i);
			// We randomly choose the number of assignments, between min and max
			Random rdm = new Random();
			int assignments = rdm.nextInt(max - min + 1) + min;
			for (int j = 0; j < assignments; j++) {
				List<Role> availableRoles = getSSoDCompliantRoles(user);
				if (availableRoles.size() == 0) {
					log.warn("There are no more suitable roles for user " + user.getName());
					//TODO: deal with this more elegantly
					break;
				}
				Role role = availableRoles.get(rdm.nextInt(availableRoles.size()));
				if(!user.addRoleSSoD(role)) {
					log.debug("This should not happen: SSoD violation detected when adding role " + role.getName() + " to user " + user.getName());
					throw new RuntimeException("RBACModel.generateUserRoleAssignments");
				}
				log.debug("Role " + role.getName() + " has been assigned to User " + user.getName());
			}
		}
	}
	
	/**
	 * For a specified user, returns a list of all the existing roles that could 
	 * be added to this user without violating any SSoD constraint, and that have 
	 * not already been assigned to the user
	 * @param user the user to consider
	 * @return a list of suitable roles
	 */
	private List<Role> getSSoDCompliantRoles(User user) {
		List<Role> compliantRoles = new ArrayList<Role>();
		for (Role role: this.roles) {
			if (!user.hasRole(role) && !user.causesSSoDViolation(role))
				compliantRoles.add(role);
		}
		return compliantRoles;
	}
	
	private void generateRolePermissionAssignments(int min, int max) {
		assert(min <= max);
		Random rdm = new Random();
		for (Role role:this.roles) {
			// Each role will get a random number of randomly chosen permissions
			role.addAllPermissions(GeneratorUtils.selectRandomElement(this.permissions, 
					rdm.nextInt(max - min) + 1));
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getWellFormed() {
		return wellFormed;
	}

	public void setWellFormed(Boolean wellFormed) {
		this.wellFormed = wellFormed;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public Boolean getFullCoverage() {
		return fullCoverage;
	}

	public void setFullCoverage(Boolean fullCoverage) {
		this.fullCoverage = fullCoverage;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Boolean getRedundant() {
		return redundant;
	}

	public void setRedundant(Boolean redundant) {
		this.redundant = redundant;
	}

	public int getNbrUsers() {
		return nbrUsers;
	}

	public void setNbrUsers(int nbrUsers) {
		this.nbrUsers = nbrUsers;
	}

	public int getNbrRoles() {
		return nbrRoles;
	}

	public void setNbrRoles(int nbrRoles) {
		this.nbrRoles = nbrRoles;
	}

	public int getNbrPermissions() {
		return nbrPermissions;
	}

	public void setNbrPermissions(int nbrPermissions) {
		this.nbrPermissions = nbrPermissions;
	}

	public int getNbrActivityPartitions() {
		return nbrActivityPartitions;
	}

	public void setNbrActivityPartitions(int nbrActivityPartitions) {
		this.nbrActivityPartitions = nbrActivityPartitions;
	}

	public int getNbrActions() {
		return nbrActions;
	}

	public void setNbrActions(int nbrActions) {
		this.nbrActions = nbrActions;
	}

	public int getNbrGrantedActions() {
		return nbrGrantedActions;
	}

	public void setNbrGrantedActions(int nbrGrantedActions) {
		this.nbrGrantedActions = nbrGrantedActions;
	}

	public int getNbrForbiddenActions() {
		return nbrForbiddenActions;
	}

	public void setNbrForbiddenActions(int nbrForbiddenActions) {
		this.nbrForbiddenActions = nbrForbiddenActions;
	}

	public int getNbrOperations() {
		return nbrOperations;
	}

	public void setNbrOperations(int nbrOperations) {
		this.nbrOperations = nbrOperations;
	}

	public int getNbrRestrictedOperations() {
		return nbrRestrictedOperations;
	}

	public void setNbrRestrictedOperations(int nbrRestrictedOperations) {
		this.nbrRestrictedOperations = nbrRestrictedOperations;
	}

	public int getNbrClasses() {
		return nbrClasses;
	}

	public void setNbrClasses(int nbrClasses) {
		this.nbrClasses = nbrClasses;
	}

	public int getNbrActivities() {
		return nbrActivities;
	}

	public void setNbrActivities(int nbrActivities) {
		this.nbrActivities = nbrActivities;
	}

	public int getMinPermsPerOp() {
		return minPermsPerOp;
	}

	public void setMinPermsPerOp(int minPermsPerOp) {
		this.minPermsPerOp = minPermsPerOp;
	}

	public int getMaxPermsPerOp() {
		return maxPermsPerOp;
	}

	public void setMaxPermsPerOp(int maxPermsPerOp) {
		this.maxPermsPerOp = maxPermsPerOp;
	}

	public int getMinOpsPerAction() {
		return minOpsPerAction;
	}

	public void setMinOpsPerAction(int minOpsPerAction) {
		this.minOpsPerAction = minOpsPerAction;
	}

	public int getMaxOpsPerAction() {
		return maxOpsPerAction;
	}

	public void setMaxOpsPerAction(int maxOpsPerAction) {
		this.maxOpsPerAction = maxOpsPerAction;
	}
	
	public String toString() {
		return "This model is named " + this.name + ".\t It has " 
		+ this.users.size() + " users, " + this.roles.size() + " roles and " 
		+ this.permissions.size() + " permissions.";
	}
	
	public Map<String, Integer> computeStats() {
		Map<String, Integer> map = new Hashtable<String, Integer>();
		int elements = 0;
		int relations = 0;
		int stereotypes = 0;
		
		if (this.correct)
			map.put("correct", 1);
		else
			map.put("correct", 0);
		
		map.put("Users", this.users.size());
		elements += this.users.size();
		stereotypes += this.users.size();
		
		map.put("Roles", this.roles.size());
		elements += this.roles.size();
		stereotypes += this.roles.size();
		
		map.put("Permissions", this.permissions.size());
		elements += this.permissions.size();
		stereotypes += this.permissions.size();
		
		map.put("Operations", this.operations.size());
		elements += this.operations.size();
		
		map.put("Restricted Operations", this.restrictedOperations.size());
		stereotypes += this.restrictedOperations.size();
		
		map.put("Activities", this.activities.size());
		elements += this.activities.size();
		
		map.put("User Partitions", this.activityPartitions.size());
		elements += this.activityPartitions.size();
		stereotypes += this.activityPartitions.size();
		
		map.put("Actions", this.actions.size());
		elements += this.actions.size();
		
		map.put("Granted Actions", this.grantedActions.size());
		stereotypes += this.grantedActions.size();
		
		map.put("Forbidden Actions", this.forbiddenActions.size());
		stereotypes += this.forbiddenActions.size();
		
		int associations = 0;
		for (GrantedAction action: this.grantedActions) {
			associations += action.getActivateRoles().size();
		}
		for (ForbiddenAction action: this.forbiddenActions) {
			associations += action.getActivateRoles().size();
		}
		map.put("ActivateRoles", associations);
		relations += associations;
		
		associations = 0;
		for (GrantedAction action: this.grantedActions) {
			associations += action.getDeactivateRoles().size();
		}
		for (ForbiddenAction action: this.forbiddenActions) {
			associations += action.getDeactivateRoles().size();
		}
		map.put("DeactivateRoles", associations);
		relations += associations;
		
		associations = 0;
		for (User user: this.users) {
			associations += user.getRoles().size();
		}
		map.put("User -> Role assignments", associations);
		relations += associations;
		
		int perm_assignments = 0;
		int hierarchies = 0;
		int ssod = 0;
		int dsod = 0;
		for (Role role: this.roles) {
			perm_assignments += role.getPermissions().size();
			hierarchies += role.getParents().size();
			ssod += role.getSSoD().size();
			dsod += role.getDSoD().size();
		}
		map.put("Role hierarchies", hierarchies);
		map.put("Role -> Permission assignments", perm_assignments);
		// we divide SSoD and DSoD by 2 since the constraints are counted twice 
		// (once on each end of the association)
		map.put("SSoD constraints", ssod / 2);
		map.put("DSoD constraints", dsod / 2);
		relations += hierarchies + perm_assignments + (ssod / 2) + (dsod / 2);
		
		associations = 0;
		for (RestrictedOperation op:this.restrictedOperations) {
			associations += op.getPermissions().size();
		}
		map.put("Restricted Operation -> Permission assignments", associations);
		relations += associations;
		
		associations = 0;
		for (GrantedAction action: this.grantedActions) {
			associations += action.getOperations().size();
		}
		for (ForbiddenAction action: this.forbiddenActions) {
			associations += action.getOperations().size();
		}
		map.put("Action -> Operation assignments", associations);
		relations += associations;
		
		associations = 0;
		for (ActivityPartition ap:this.activityPartitions) {
			associations += ap.getRoles().size();
		}
		map.put("Activity Partition -> Role assignments", associations);
		relations += associations;
		
		map.put("Classes", this.classes.size());
		elements += this.classes.size();
		
		map.put("Total elements", elements);
		map.put("Total associations", relations);
		map.put("Total Stereotypes", stereotypes);
		
		return map;
	}
	/**
	 * Computes statistics about the model and returns them as a list of Strings.
	 * Each element gives a piece of information about the model, such as the 
	 * number of users, the number of Actions, etc. Each element has the following
	 * form: 'key: value'
	 * @return a List of Strings providing stats about the model
	 */
	public List<String> stats() {
		int elements = 0;
		int relations = 0;
		int stereotypes = 0;
		List<String> stats = new ArrayList<String>();
		String item = "name: " + this.name;
		stats.add(item);
		item = "users: " + this.users.size();
		stats.add(item);
		elements += this.users.size();
		stereotypes += this.users.size();
		item = "roles: " + this.roles.size();
		stats.add(item);
		elements += this.roles.size();
		stereotypes += this.roles.size();
		item = "permissions: " + this.permissions.size();
		stats.add(item);
		elements += this.permissions.size();
		stereotypes += this.permissions.size();
		item = "operations: " + this.operations.size();
		stats.add(item);
		elements += this.operations.size();
		item = "«restricted» operations: " + this.restrictedOperations.size();
		stats.add(item);
		elements += this.restrictedOperations.size();
		stereotypes += this.restrictedOperations.size();
		item = "activities: " + this.activities.size();
		stats.add(item);
		elements += this.activities.size();
		item = "«User» partitions: " + this.activityPartitions.size();
		stats.add(item);
		elements += this.activityPartitions.size();
		stereotypes += this.activityPartitions.size();
		item = "actions: " + this.actions.size();
		stats.add(item);
		elements += this.actions.size();
		item = "«granted» actions: " + this.grantedActions.size();
		stats.add(item);
		stereotypes += this.grantedActions.size();
		item = "«forbidden» action: " + this.forbiddenActions.size();
		stats.add(item);
		stereotypes += this.forbiddenActions.size();
		int associations = 0;
		for (GrantedAction action: this.grantedActions) {
			associations += action.getActivateRoles().size();
		}
		for (ForbiddenAction action: this.forbiddenActions) {
			associations += action.getActivateRoles().size();
		}
		item = "ActivateRoles: " + associations;
		stats.add(item);
		relations += associations;
		associations = 0;
		for (GrantedAction action: this.grantedActions) {
			associations += action.getDeactivateRoles().size();
		}
		for (ForbiddenAction action: this.forbiddenActions) {
			associations += action.getDeactivateRoles().size();
		}
		item = "DeactivateRoles: " + associations;
		stats.add(item);
		relations += associations;
		associations = 0;
		for (User user: this.users) {
			associations += user.getRoles().size();
		}
		item = "user -> role assignments: " + associations;
		stats.add(item);
		relations += associations;
		int perm_assignments = 0;
		int hierarchies = 0;
		int ssod = 0;
		int dsod = 0;
		for (Role role: this.roles) {
			perm_assignments += role.getPermissions().size();
			hierarchies += role.getParents().size();
			ssod += role.getSSoD().size();
			dsod += role.getDSoD().size();
		}
		item = "role hierarchies: " + hierarchies;
		stats.add(item);
		item = "role -> permission assignments: " + perm_assignments;
		stats.add(item);
		// we divide SSoD and DSoD by 2 since the constraints are counted twice 
		// (once on each end of the association)
		item = "SSoD constraints: " + ssod / 2;
		stats.add(item);
		item = "DSoD constraints: " + dsod / 2;
		stats.add(item);
		relations += hierarchies + perm_assignments + (ssod / 2) + (dsod / 2);
		associations = 0;
		for (RestrictedOperation op:this.restrictedOperations) {
			associations += op.getPermissions().size();
		}
		item = "«restricted» operation -> permission assignments: " + associations;
		stats.add(item);
		relations += associations;
		associations = 0;
		for (GrantedAction action: this.grantedActions) {
			associations += action.getOperations().size();
		}
		for (ForbiddenAction action: this.forbiddenActions) {
			associations += action.getOperations().size();
		}
		item = "Action -> Operation assignments: " + associations;
		stats.add(item);
		associations = 0;
		relations += associations;
		for (ActivityPartition ap:this.activityPartitions) {
			associations += ap.getRoles().size();
		}
		item = "AP -> role assignments: " + associations;
		stats.add(item);
		relations += associations;
		item = "classes: " + this.classes.size();
		stats.add(item);
		elements += this.classes.size();
		item = "Total elements: " + elements;
		stats.add(item);
		item = "Total associations: " + relations;
		stats.add(item);
		item = "Total stereotypes: " + stereotypes;
		stats.add(item);
		return stats;
	}
	
	public void printModel() {
		for (User user: this.users)
			System.out.println(user.toString());
		for (Role role: this.roles)
			System.out.println(role.toString());
		for (Permission permission: this.permissions)
			System.out.println(permission.toString());
		for (RestrictedOperation operation: this.restrictedOperations)
			System.out.println(operation.toString());
		for (Activity activity:this.activities)
			System.out.println(activity.toString());
		for (ActivityPartition partition: this.activityPartitions)
			System.out.println(partition.toString());
		for (GrantedAction action: this.grantedActions)
			System.out.println(action.toString());
		for (ForbiddenAction action: this.forbiddenActions)
			System.out.println(action.toString());
	}
	public boolean toXMI(File emptyModel, File target) {
		// TODO Auto-generated method stub
		Document document = null;
		try {
			SAXBuilder builder = new SAXBuilder();
			document = 	builder.build(emptyModel);
		} catch(JDOMException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		Namespace umlNS = document.getRootElement().getNamespace("uml");
		Namespace xmiNS = document.getRootElement().getNamespace("xmi");
		Namespace rbacNS = document.getRootElement().getNamespace("rbacUML");
		org.jdom.Element root = document.getRootElement();
		org.jdom.Element umlPkg = document.getRootElement().getChild("Model", umlNS);
		for (User user:this.users) {
			umlPkg.addContent(user.toXmi(xmiNS));
			root.addContent(user.stereotypeToXmi(rbacNS, xmiNS));
//			if (user.hasAssignedRoles())
//				root.addContent(user.rolesToXmi(rbacNS, xmiNS));
		}
		for (Role role:this.roles) {
			umlPkg.addContent(role.toXmi(xmiNS));
			root.addContent(role.stereotypeToXmi(rbacNS, xmiNS));
		}
		for (Permission permission:this.permissions) {
			umlPkg.addContent(permission.toXmi(xmiNS));
			root.addContent(permission.stereotypeToXmi(rbacNS, xmiNS));
		}
		for (UMLClass umlClass: this.classes) {
			umlPkg.addContent(umlClass.toXmi(xmiNS));
			for (Operation op: umlClass.getOperations()) {
				if (op instanceof RestrictedOperation) {
					root.addContent(((RestrictedOperation)op).stereotypeToXmi(rbacNS, xmiNS));
				}
			}
		}
		for (Activity activity: this.activities) {
			umlPkg.addContent(activity.toXmi(xmiNS));
		}
		for (ActivityPartition partition: this.activityPartitions) {
			root.addContent(partition.stereotypeToXmi(rbacNS, xmiNS));
		}
		for (GrantedAction gAction: this.grantedActions) {
			root.addContent(gAction.stereotypeToXmi(rbacNS, xmiNS));
//			umlPkg.addContent(gAction.toXmi(xmiNS));
		}
		
		for (ForbiddenAction fAction: this.forbiddenActions) {
			root.addContent(fAction.stereotypeToXmi(rbacNS, xmiNS));
//			umlPkg.addContent(fAction.toXmi(xmiNS));
		}
		
		System.out.println(document.toString());
		
		try {
			// Pretty print please.
		    XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		    outputter.output(document, new FileOutputStream(target));
		} catch (java.io.IOException e) {
		    e.printStackTrace();
		}

		return true;
	}
}
