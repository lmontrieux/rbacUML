package uk.ac.open.rbacuml.modelgenerator.rbac.elements;

import java.util.UUID;

import org.jdom.Namespace;

public class Permission implements Element {
	private String name;
	private Element parent;
	private UUID baseUUID;
	private UUID sterUUID;
	
	public Permission(String name, Element parent) {
		this.name = name;
		this.parent = parent;
		this.baseUUID = UUID.randomUUID();
		this.sterUUID = UUID.randomUUID();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setParent(Element parent) {
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}
	
	public Element getParent() {
		return parent;
	}
	
	public String toString() {
		return "Permission " + this.name;
	}
	
	public org.jdom.Element toXmi(Namespace xmiNS) {
		org.jdom.Element element = new org.jdom.Element("packagedElement");
		element.setAttribute("type", "uml:Class", xmiNS);
		element.setAttribute("id", this.baseUUID.toString(), xmiNS);
		element.setAttribute("name", this.name);
		return element;
	}
	
	public org.jdom.Element stereotypeToXmi(Namespace rbacNS, Namespace xmiNS) {
		org.jdom.Element element = new org.jdom.Element("Permission", rbacNS);
		element.setAttribute("id", this.sterUUID.toString(), xmiNS);
		element.setAttribute("base_Class", this.baseUUID.toString());
		return element;
	}
	
	public UUID getBaseUUID() {
		return this.baseUUID;
	}
	
	public UUID getSterUUID() {
		return this.sterUUID;
	}
	
}
