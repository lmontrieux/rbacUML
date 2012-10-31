package uk.ac.open.rbacuml.modelgenerator.rbac.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jdom.Namespace;

public class Role implements Element {
	private String name;
	private Element parent;
	private List<Role> parents = new ArrayList<Role>();
	private List<Permission> permissions = new ArrayList<Permission>();
	private List<Role> ssod = new ArrayList<Role>();
	private List<Role> dsod = new ArrayList<Role>();
	private UUID baseUUID;
	private UUID sterUUID;
	
	public Role(String name, Element parent) {
		this.name = name;
		this.parent = parent;
		this.baseUUID = UUID.randomUUID();
		this.sterUUID = UUID.randomUUID();
	}
	
	public boolean addParent(Role parent) {
		if (this.parents.contains(parent))
			return false;
		this.parents.add(parent);
		return true;
	}
	
	public boolean addPermission(Permission permission) {
		if (this.parents.contains(permission))
			return false;
		this.permissions.add(permission);
		return true;
	}
	
	/**
	 * Adds a new Role to the list of SSoD.
	 * @param role the role to add as a SSoD
	 * @return false if the role is already in the DSoD or the SSoD list. true 
	 * if the role has been added to the list
	 */
	public boolean addSSoD(Role role) {
		if (this.hasSSoD(role) || this.hasDSoD(role))
			return false;
		this.ssod.add(role);
		return true;
	}
	
	public List<Role> getSSoD() {
		List<Role> ssod = new ArrayList<Role>();
		for (Role role: this.parents) {
			ssod.addAll(role.getSSoD());
		}
		ssod.addAll(this.ssod);
		return ssod;
	}
	
	public boolean hasSSoD(Role role) {
		for (Role parent:this.parents) {
			if (parent.hasSSoD(role))
				return true;
		}
		return this.ssod.contains(role);
	}
	
	public boolean hasSoD(Role role) {
		return this.hasSSoD(role) || this.hasDSoD(role);
	}
	
	/**
	 * Adds a new Role to the list of SSoD.
	 * @param role the role to add as a SSoD
	 * @return false if the role is already in the DSoD or the SSoD list. true 
	 * if the role has been added to the list
	 */
	public boolean addDSoD(Role role) {
		if (this.hasSSoD(role) || this.hasDSoD(role))
			return false;
		this.dsod.add(role);
		return true;
	}
	
	public List<Role> getDSoD() {
		List<Role> dsod = new ArrayList<Role>();
		for (Role role: this.parents) {
			dsod.addAll(role.getDSoD());
		}
		dsod.addAll(this.dsod);
		return dsod;
	}
	
	public boolean hasDSoD(Role role) {
		for (Role parent:this.parents) {
			if (parent.hasDSoD(role))
				return true;
		}
		return this.dsod.contains(role);
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

	public List<Role> getParents() {
		return parents;
	}
	
	/**
	 * Collects all (both direct and indirect) parents of the role.
	 * @return a List of Roles, which is the list of ancestors
	 * @pre: there are no cycles in the role hierarchy
	 */
	public List<Role> getAllParents() {
		List<Role> allParents = this.parents;
		for (Role role:this.parents) {
			allParents.addAll(role.getAllParents());
		}
		return allParents;
	}
	
	public List<Role> getAncestors() {
		return this.getAllParents();
	}
	
	public boolean hasParent(Role role) {
		if (this.parents.contains(role))
			return true;
		else
			return false;
	}
	
	public boolean hasAncestor(Role role) {
		if (this.hasParent(role))
			return true;
		else {
			for (Role parent:this.parents) {
				if (parent.hasAncestor(role))
					return true;
			}
		}
		return false;
	}
	
	public List<Permission> getPermissions() {
		return permissions;
	}
	
	public String toString() {
		return "Role " + this.name + "\t permissions: " 
		+ this.permissions.size() + "\t parents: " + this.parents.size() 
		+ "\t SSoD: " + this.ssod.size() + "\t DSoD: " + this.dsod.size();
	}
	
	public org.jdom.Element toXmi(Namespace xmiNS) {
		org.jdom.Element element = new org.jdom.Element("packagedElement");
		element.setAttribute("type", "uml:Class", xmiNS);
		element.setAttribute("id", this.baseUUID.toString(), xmiNS);
		element.setAttribute("name", this.name);
		// Generating role hierarchies - each parent is a 'generalization' child
		for (Role parent: this.parents) {
			org.jdom.Element generalization = new org.jdom.Element("generalization");
			generalization.setAttribute("id", UUID.randomUUID().toString(), xmiNS);
			generalization.setAttribute("general", parent.getBaseUUID().toString());
			element.addContent(generalization);
		}
		return element;
	}
	
	public org.jdom.Element stereotypeToXmi(Namespace rbacNS, Namespace xmiNS) {
		org.jdom.Element element = new org.jdom.Element("RBACRole", rbacNS);
		element.setAttribute("id", this.sterUUID.toString(), xmiNS);
		element.setAttribute("base_Class", this.baseUUID.toString());
		String refs = "";
		for (Permission permission: this.permissions)
			refs += " " + permission.getSterUUID().toString();
		if (!refs.equals(""))
			refs = refs.substring(1);
		element.setAttribute("permission", refs);
		return element;
	}

	public UUID getBaseUUID() {
		return this.baseUUID;
	}
	
	public UUID getSterUUID() {
		return this.sterUUID;
	}
	
	public boolean hasAssignedPermissions() {
		if (this.permissions.size() == 0)
			return false;
		return true;
	}

	/** Adds all the permissions from a list
	 * 
	 * @param permissions the list of permissions to add. The list must only 
	 * contain Permission objects
	 */
	public void addAllPermissions(List permissions) {
		for (Object permission:permissions) {
			assert(permission instanceof Permission);
			if (!this.permissions.contains((Permission)permission))
				this.permissions.add((Permission)permission);
		}
	}
	
//	public org.jdom.Element permissionsToXmi(Namespace rbacNS, Namespace xmiNS) {
//		assert(this.permissions.size() > 0);
//		org.jdom.Element element = new org.jdom.Element("RBACRole", rbacNS);
//		element.setAttribute("id", UUID.randomUUID().toString(), xmiNS);
//		String references = "";
//		for (int i = 0; i < this.permissions.size(); i++) {
//			references += " " + this.permissions.get(i).getSterUUID().toString();
//		}
//		references = references.substring(1);
//		element.setAttribute("permission", references);
//		element.setAttribute("base_Class", this.baseUUID.toString());
//		System.out.println(element.toString());
//		return element;
//	}
}
