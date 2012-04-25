package uk.ac.open.rbacuml.plugin.ldif;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public abstract class AbstractUserDirectory {
	private Hashtable<String, IUser> users = null;
	private Hashtable<String, IRole> roles = null;
	
	private Namespace rbacumlNS;
	private Namespace xmiNS;
	
	private Document xmiFile;
	

	public AbstractUserDirectory() {
		this.users = new Hashtable<String, IUser>();
		this.roles = new Hashtable<String, IRole>();
		this.xmiFile = null;
	}
	
	public abstract boolean populate(File source) throws FileNotFoundException;
	
	private void addAssignment(String user, String role) {
		this.users.get(user).addRole(this.roles.get(role));
	}
	
	/**
	 * Adds a user to the list of users. If the user is already in the list, 
	 * checks for roles that have not yet been assigned and add them
	 * 
	 * @param user the User to add to the list. Can't be null
	 */
	protected void addUser(IUser user) {
		assert(user != null);
		if (users.containsKey(user.getName())) {
			// the user is already there
			IUser current = users.get(user.getName());
			// but does it have all the roles?
			for (IRole newRole:user.getRoles()) {
				current.addRole(newRole);
			}
		} else {
			users.put(user.getName(), user);
		}
	}
	
	protected boolean hasUser(String name) {
		assert (name != null);
		if (users.containsKey(name))
			return true;
		return false;
	}
	
	protected IUser getUser(String name) {
		assert(name != null);
		
		return users.get(name);
	}
	
	protected boolean hasRole(String name) {
		assert (name != null);
		if (roles.containsKey(name))
			return true;
		return false;
	}
	
	protected IRole getRole(String name) {
		assert(name != null);
		
		return roles.get(name);
	}
	
	protected void addRole(IRole role) {
		assert(role != null);
		if (roles.containsKey(role.getName())) {
			// the role is already there
			IRole current = roles.get(role.getName());
		} else {
			roles.put(role.getName(), role);
		}
	}
	
	public int getNumberOfUsers() {
		return this.users.size();
	}
	
	public int getNumberOfRoles() {
		return this.roles.size();
	}
	
	public boolean openXMI(String file) {
		SAXBuilder builder = new SAXBuilder();
		try {
			this.xmiFile = builder.build(new File(file));
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean writeXMI(String file) {
		XMLOutputter outputter = new XMLOutputter();
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			outputter.output(xmiFile, writer);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void addToXMI() {
		rbacumlNS = xmiFile.getRootElement().getNamespace("rbacUML");
		Namespace ns = xmiFile.getRootElement().getNamespace();
		xmiNS = ns;
		List packages = xmiFile.getRootElement().getChildren();
		Element newClass = null;
		Element newRole = null;
		System.out.println("Here");
		try {
		for (Iterator<Element> iterNS = packages.iterator(); iterNS.hasNext(); ) {
			Element elt = iterNS.next();
			if (elt.getNamespacePrefix().equals("uml")) {
				// let's start with the roles
				for (IRole role: this.roles.values()) {
					newRole = createClass(role.getName(), role.getUuid());
					elt.addContent(newRole);
					Element rbacRole = createRole(newRole.getAttribute("id", xmiNS).getValue());
					xmiFile.getRootElement().addContent(rbacRole);
				}
				// then the users and user-role associations
				for (IUser user: this.users.values()) {
					newClass = createClass(user.getName(), user.getUuid());
					elt.addContent(newClass);
					Element rbacUser = createUser(newClass.getAttribute("id", xmiNS).getValue());
					addAllUserRoleAssignments(rbacUser, user.getRoles());
					xmiFile.getRootElement().addContent(rbacUser);
				}
			}
		}
		}
		catch (ConcurrentModificationException cme1) {
			System.out.println(cme1.getMessage() + cme1.getCause());
		}
	}
	
	private org.jdom.Element createUser(String baseUuid) {
		return createRBACUMLStereotype("User", "base_Class", baseUuid);
	}
	
	private org.jdom.Element createRole(String baseUuid) {
		return createRBACUMLStereotype("RBACRole", "base_Class", baseUuid);
	}
	
	private org.jdom.Element createRBACUMLStereotype(String stereotype, String base, String baseUuid) {
		org.jdom.Element ster = new org.jdom.Element(stereotype, rbacumlNS);
		ster.setAttribute("id", UUID.randomUUID().toString(), xmiNS);
		ster.setAttribute(base, baseUuid);
		return ster;
	}
	
	private void addUserRoleAssignment(org.jdom.Element user, String roleUuid) {
		String rbacRole = user.getAttribute("rBACRole").toString();
		user.setAttribute("rBACRole", rbacRole + " " + roleUuid);
	}
	
	private void addAllUserRoleAssignments(Element user, List<IRole> roles) {
		String rbacRole = "";
		if (user.getAttribute("rBACRole") != null)
			rbacRole = user.getAttribute("rBACRole").toString();
		for (IRole role: roles) {
			rbacRole = rbacRole + " " + role.getUuid();
		}
	}
	
	private org.jdom.Element createClass(String name, String uuid) {
		org.jdom.Element elt = new org.jdom.Element("packagedElement");
		elt.setAttribute("type", "uml:Class", xmiNS);
		elt.setAttribute("id", uuid, xmiNS);
		elt.setAttribute("name", name);
		return elt;
	}
}
