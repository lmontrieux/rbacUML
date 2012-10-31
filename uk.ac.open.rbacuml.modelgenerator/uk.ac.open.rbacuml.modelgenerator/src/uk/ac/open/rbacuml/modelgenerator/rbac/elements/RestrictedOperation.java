package uk.ac.open.rbacuml.modelgenerator.rbac.elements;

import java.util.ArrayList;
import java.util.List;
import org.jdom.Namespace;

public class RestrictedOperation extends Operation {
	private List<Permission> permissions;

	public RestrictedOperation(String name, Element parent) {
		super(name, parent);
		this.permissions = new ArrayList<Permission>();
	}

	public List<Permission> getPermissions() {
		return permissions;
	}
	
	public void addPermission(Permission permission) {
		permissions.add(permission);
	}
	
	public String toString() {
		return "Restricted Operation " + this.name + "\t Permissions: " 
		+ this.permissions.size();
	}
	
	public org.jdom.Element stereotypeToXmi(Namespace rbacNS, Namespace xmiNS) {
		org.jdom.Element element = new org.jdom.Element("Restricted", rbacNS);
		element.setAttribute("id", this.sterUUID.toString(), xmiNS);
		element.setAttribute("base_Operation", this.baseUUID.toString());
		// Creating permission assignments
		String permAssignments = "";
		for (Permission permission: this.permissions) {
			permAssignments += " " + permission.getSterUUID().toString();
		}
		if (!permAssignments.equals("")) {
			permAssignments = permAssignments.substring(1);
		}
		element.setAttribute("permission", permAssignments);
		return element;
	}
}
