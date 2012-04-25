package uk.ac.open.rbacuml.plugin.ldif;

import java.util.List;
import java.util.UUID;

public interface IRole {
	public String getName();
	
	public String getUuid();
	
	public List<IRole> getParents();
	
	public boolean hasParent(IRole role);
	
	public boolean hasParent(String name);
	
	public void setName(String name);
	
	public void setUuid(String uuid);
	
	public void setUuid(UUID uuid);
	
	public void addParent(IRole parent);
}
