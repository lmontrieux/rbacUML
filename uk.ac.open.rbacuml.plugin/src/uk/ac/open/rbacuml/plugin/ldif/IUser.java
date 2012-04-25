package uk.ac.open.rbacuml.plugin.ldif;

import java.util.List;
import java.util.UUID;

public interface IUser {
	public String getName();
	
	public String getUuid();
	
	public List<IRole> getRoles();
	
	public void setName(String name);
	
	public void setUuid(UUID uuid);
	
	public void setUuid(String uuid);
	
	public void addRole(IRole role);
	
	public boolean hasRole(IRole role);
	
	public boolean hasRole(String name);
}
