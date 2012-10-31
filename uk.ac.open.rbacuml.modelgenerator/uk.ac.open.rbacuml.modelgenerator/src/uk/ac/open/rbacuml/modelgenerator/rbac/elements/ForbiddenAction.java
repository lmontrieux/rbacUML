package uk.ac.open.rbacuml.modelgenerator.rbac.elements;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Namespace;

public class ForbiddenAction extends Action {
	private List<Operation> operations;
	private List<Role> activateRoles;
	private List<Role> deactivateRoles;
	
	public ForbiddenAction(String name, Element action) {
		super(name, action);
		this.operations = new ArrayList<Operation>();
		this.activateRoles = new ArrayList<Role>();
		this.deactivateRoles = new ArrayList<Role>();
	}

	public List<Operation> getOperations() {
		return operations;
	}
	
	public void addOperation(Operation op) {
		operations.add(op);
	}
	
	public List<Role> getActivateRoles() {
		return this.activateRoles;
	}
	
	public void addActivateRole(Role role) {
		this.activateRoles.add(role);
	}
	
	public List<Role> getDeactivateRoles() {
		return this.deactivateRoles;
	}
	
	public void addDeactivateRole(Role role) {
		this.deactivateRoles.add(role);
	}
	
	public String toString() {
		return "Forbidden Action " + this.name + "\t Operations: " 
		+ this.operations.size() + "\t ActivateRoles: " 
		+ this.activateRoles.size() + "\t DeactivateRoles: " 
		+ this.deactivateRoles.size();
	}
	
	public org.jdom.Element stereotypeToXmi(Namespace rbacNS, Namespace xmiNS) {
		org.jdom.Element element = new org.jdom.Element("Forbidden", rbacNS);
		element.setAttribute("id", this.sterUUID.toString(), xmiNS);
		element.setAttribute("base_Action", this.baseUUID.toString());
		String ops = "";
		for (Operation operation: this.operations) {
			ops += " " + operation.getBaseUUID();
		}
		if (!ops.equals(""))
			ops = ops.substring(1);
		element.setAttribute("operation", ops);
		return element;
	}
}
