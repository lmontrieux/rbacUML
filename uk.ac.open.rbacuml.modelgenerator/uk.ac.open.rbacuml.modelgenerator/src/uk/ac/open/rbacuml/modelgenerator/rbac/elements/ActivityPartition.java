package uk.ac.open.rbacuml.modelgenerator.rbac.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jdom.Namespace;

public class ActivityPartition implements Element {
	private String name;
	private Element parent;
	private List<Action> actions;
	private User user;
	private List<Role> roles;
	private UUID baseUUID;
	private UUID sterUUID;
	
	public ActivityPartition(String name, Element parent, User user) {
		this.name = name;
		this.parent = parent;
		this.actions = new ArrayList<Action>();
		this.user = user;
		this.roles = new ArrayList<Role>();
		this.baseUUID = UUID.randomUUID();
		this.sterUUID = UUID.randomUUID();
	}
	
	public void addAction(Action action) {
		actions.add(action);
	}
	
	public void addRole(Role role) {
		roles.add(role);
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Action> getActions() {
		return actions;
	}

	public List<Role> getRoles() {
		return roles;
	}
	
	public String toString() {
		return "Activity Partition " + this.name + "\t parent: " 
		+ this.getParent().getName() + "\t Roles: " + this.roles.size() 
		+ "\t Actions: " + this.actions.size();
	}
	
	public UUID getBaseUUID() {
		return this.baseUUID;
	}
	
	public UUID getSterUUID() {
		return this.sterUUID;
	}

	public org.jdom.Element toXmi(Namespace xmiNS) {
		org.jdom.Element element = new org.jdom.Element("group");
		element.setAttribute("type", "uml:ActivityPartition", xmiNS);
		element.setAttribute("id", this.baseUUID.toString(), xmiNS);
		element.setAttribute("name", this.name);
		String actions = "";
		for (Action action: this.actions) {
			actions += " " + action.getBaseUUID();
		}
		if (!actions.equals(""))
			actions = actions.substring(1);
		element.setAttribute("node", actions);
		return element;
	}
	
	public org.jdom.Element stereotypeToXmi(Namespace rbacNS, Namespace xmiNS) {
		org.jdom.Element element = new org.jdom.Element("RBACUser", rbacNS);
		element.setAttribute("id", this.sterUUID.toString(), xmiNS);
		element.setAttribute("base_ActivityPartition", this.baseUUID.toString());
		String rBACRoles = "";
		for (Role role: this.roles) {
			rBACRoles += " " + role.getSterUUID();
		}
		if (!rBACRoles.equals(""))
			rBACRoles = rBACRoles.substring(1);
		element.setAttribute("rBACRole", rBACRoles);
		element.setAttribute("aliasUser", this.user.getSterUUID().toString());
		return element;
	}
}
