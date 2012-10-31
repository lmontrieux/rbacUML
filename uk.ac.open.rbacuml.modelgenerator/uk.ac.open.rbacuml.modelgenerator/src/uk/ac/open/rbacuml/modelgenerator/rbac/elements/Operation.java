package uk.ac.open.rbacuml.modelgenerator.rbac.elements;

import java.util.UUID;

import org.jdom.Namespace;

public class Operation implements Element {
	protected String name;
	protected Element parent;
	protected UUID baseUUID;
	protected UUID sterUUID;
	
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

	public Operation(String name, Element parent) {
		this.name = name;
		this.parent = parent;
		this.baseUUID = UUID.randomUUID();
		this.sterUUID = UUID.randomUUID();
	}
	
	public String toString() {
		return "Operation " + this.name;
	}
	
	public UUID getBaseUUID() {
		return this.baseUUID;
	}
	
	public UUID getSterUUID() {
		return this.sterUUID;
	}
	
	public org.jdom.Element toXmi(Namespace xmiNS) {
		org.jdom.Element element = new org.jdom.Element("ownedOperation");
		element.setAttribute("id", this.baseUUID.toString(), xmiNS);
		element.setAttribute("name", this.name);
		return element;
	}

}
