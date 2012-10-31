package uk.ac.open.rbacuml.modelgenerator.rbac.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jdom.Namespace;

public class UMLClass implements Element {
	private String name;
	private Element parent;
	private List<Operation> operations;
	private UUID baseUUID;
	private UUID sterUUID;
	
	public UMLClass(String name, Element parent) {
		this.name = name;
		this.parent = parent;
		this.operations = new ArrayList<Operation>();
		this.baseUUID = UUID.randomUUID();
		this.sterUUID = UUID.randomUUID();
	}
	
	public String getName() {
		return name;
	}
	
	public boolean addOperation(Operation operation) {
		if (this.operations.contains(operation))
			return false;
		this.operations.add(operation);
		return true;
	}
	
	public boolean hasOperation(Operation operation) {
		return this.operations.contains(operation);
	}
	
	public List<Operation> getOperations() {
		return this.operations;
	}

	@Override
	public Element getParent() {
		return this.parent;
	}
	
	public UUID getBaseUUID() {
		return this.baseUUID;
	}
	
	public UUID getSterUUID() {
		return this.sterUUID;
	}
	
	public org.jdom.Element toXmi(Namespace xmiNS) {
		org.jdom.Element element = new org.jdom.Element("packagedElement");
		element.setAttribute("type", "uml:Class", xmiNS);
		element.setAttribute("id", this.baseUUID.toString(), xmiNS);
		element.setAttribute("name", this.name);
		// creating Operations
		for (Operation operation:this.operations) {
			element.addContent(operation.toXmi(xmiNS));
		}
		return element;
	}
}
