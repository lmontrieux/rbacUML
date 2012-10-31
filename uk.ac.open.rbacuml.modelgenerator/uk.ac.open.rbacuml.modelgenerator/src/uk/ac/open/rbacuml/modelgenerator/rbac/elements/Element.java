package uk.ac.open.rbacuml.modelgenerator.rbac.elements;

import java.util.UUID;

public interface Element {
	public String getName();
	public Element getParent();
	public UUID getBaseUUID();
	public UUID getSterUUID();
	
}
