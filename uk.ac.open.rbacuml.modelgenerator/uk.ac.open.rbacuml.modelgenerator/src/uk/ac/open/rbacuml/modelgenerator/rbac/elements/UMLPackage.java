package uk.ac.open.rbacuml.modelgenerator.rbac.elements;

import java.util.UUID;

public class UMLPackage implements Element {
	private Element parent;
	private String name;
	private UUID baseUUID;
	private UUID sterUUID;
	
	public UMLPackage(String name, Element parent) {
		this.name = name;
		this.parent = parent;
		this.baseUUID = UUID.randomUUID();
		this.sterUUID = UUID.randomUUID();
	}
	
	public Element getParent() {
		return parent;
	}
	
	public void setParent(Element parent) {
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public UUID getBaseUUID() {
		return this.baseUUID;
	}
	
	public UUID getSterUUID() {
		return this.sterUUID;
	}
}
