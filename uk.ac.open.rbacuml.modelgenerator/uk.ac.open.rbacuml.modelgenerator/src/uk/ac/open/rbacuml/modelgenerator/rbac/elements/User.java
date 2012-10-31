package uk.ac.open.rbacuml.modelgenerator.rbac.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jdom.Namespace;

public class User implements Element {
	private String name;
	private Element parent;
	private List<Role> roles = new ArrayList<Role>();
	private UUID baseUUID;
	private UUID sterUUID;
	
	public User(String name, Element parent) {
		this.name = name;
		this.parent = parent;
		this.baseUUID = UUID.randomUUID();
		this.sterUUID = UUID.randomUUID();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Element getParent() {
		return parent;
	}

	public void setParent(Element parent) {
		this.parent = parent;
	}

	public List<Role> getRoles() {
		return roles;
	}
	
	/**
	 * Assigns a new role to the user, regardless of possible SoD violations
	 * @param role the role to be added
	 * @return true if the role has been added, false if the role was already
	 * assigned to the user
	 */
	public boolean addRole(Role role) {
		if (this.roles.contains(role))
			return false;
		this.roles.add(role);
		return true;
	}
	
	/**
	 * Assigns a new role to the user, unless it would cause an SSoD violation
	 * @param role the role to be added
	 * @return true if the role has been added, false if it would cause an SSoD
	 * violation or if the role was already assigned to the user
	 */
	public boolean addRoleSSoD(Role role) {
		if (this.causesSSoDViolation(role)) {
			return false;
		}
		return addRole(role);
	}
	
	/**
	 * Assigns a new role to the user, unless it would cause a DSoD violation
	 * @param role the role to be added
	 * @return true if the role has been added, false if it would cause a DSoD
	 * violation or if the role was already assigned to the user
	 */
	public boolean addRoleDSoD(Role role) {
		if (this.causesDSoDViolation(role)) {
			return false;
		}
		return addRole(role);
	}
	
	/**
	 * Assigns a new role to the user, unless it would cause an SSoD or a DSoD 
	 * violation
	 * @param role the role to be added
	 * @return true if the role has been added, false if it would cause an SSoD, 
	 * a DSoD violation, or if the role was already assigned to the user
	 */
	public boolean addRoleSoD(Role role) {
		if (this.causesDSoDViolation(role) || this.causesSSoDViolation(role))
			return false;
		return addRole(role);
	}
	
	/** 
	 * Detects SSoD violations between a particular role and the roles 
	 * already assigned to the user.
	 * @param role a role with which any of the assigned roles could potentially
	 * conflict
	 * @return true if a SSoD violation would be create should role be assigned 
	 * to the user. False otherwise.
	 */
	public boolean causesSSoDViolation(Role role) {
		for (Role ssod: role.getSSoD()) {
			if (this.roles.contains(ssod)) {
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * Detects DSoD violations between a particular role and the roles 
	 * already assigned to the user.
	 * @param role a role with which any of the assigned roles could potentially
	 * conflict
	 * @return true if a DSoD violation would be create should role be assigned 
	 * to the user. False otherwise.
	 */
	public boolean causesDSoDViolation(Role role) {
		for (Role dsod: role.getDSoD()) {
			if (this.roles.contains(dsod)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return "User " + this.name + "\t roles: " + this.roles.size();
	}

	public boolean causesSoDViolation(Role role) {
		return this.causesDSoDViolation(role) && this.causesSSoDViolation(role);
	}

	public boolean hasRole(Role role) {
		for (Role candidate: this.roles) {
			if (candidate.equals(role))
				return true;
		}
		return false;
	}
	
	public boolean equals(Role role) {
		if (this.name.equals(role.getName()))
			return true;
		return false;
	}

	public org.jdom.Element toXmi(Namespace xmiNS) {
		org.jdom.Element element = new org.jdom.Element("packagedElement");
		element.setAttribute("type", "uml:Class", xmiNS);
		element.setAttribute("id", this.baseUUID.toString(), xmiNS);
		element.setAttribute("name", this.name);
		return element;
	}
	
	public org.jdom.Element stereotypeToXmi(Namespace rbacNS, Namespace xmiNS) {
		org.jdom.Element element = new org.jdom.Element("RBACUser", rbacNS);
		element.setAttribute("id", this.sterUUID.toString(), xmiNS);
		element.setAttribute("base_Class", this.baseUUID.toString());
		String refs = "";
		for (Role role: this.roles)
			refs += " " + role.getSterUUID().toString();
		if (!refs.equals(""))
			refs = refs.substring(1);
		element.setAttribute("rBACRole", refs);
		return element;
	}
	
	public boolean hasAssignedRoles() {
		if (this.roles.size() == 0)
			return false;
		return true;
	}
	
//	public org.jdom.Element rolesToXmi(Namespace rbacNS, Namespace xmiNS) {
//		assert(this.roles.size() > 0);
//		org.jdom.Element element = new org.jdom.Element("RBACUser", rbacNS);
//		element.setAttribute("id", UUID.randomUUID().toString(), xmiNS);
//		String references = "";
//		for (int i = 0; i < this.roles.size(); i++) {
//			references += " " + this.roles.get(i).getSterUUID().toString();
//		}
//		references = references.substring(1);
//		element.setAttribute("rBACRole", references);
//		element.setAttribute("base_Class", this.baseUUID.toString());
//		System.out.println(element.toString());
//		return element;
//	}
	
	public UUID getBaseUUID() {
		return this.baseUUID;
	}
	
	public UUID getSterUUID() {
		return this.sterUUID;
	}
}
