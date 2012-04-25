package uk.ac.open.rbacuml.plugin.ldif;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimpleUser implements IUser {
	private String name;
	private String uuid;
	private List<IRole> roles;
	
	public SimpleUser(String name, String uuid) {
		this.name = name;
		this.uuid = uuid;
		this.roles = new ArrayList<IRole>();
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
		
	}


	public boolean hasRole(IRole thisRole) {
		for (IRole role: roles) {
			if (role.equals(thisRole))
				return true;
		}
		return false;
		
	}


	public boolean hasRole(String name) {
		for (IRole role: roles) {
			if (role.getName().equals(name))
				return true;
		}
		return false;
		
	}


	public String getName() {
		return this.name;
	}


	public String getUuid() {
		return this.uuid;
	}


	public void setUuid(UUID uuid) {
		this.uuid = uuid.toString();
		
	}


	public void addRole(IRole newRole) {
		for (IRole role: this.roles) {
			// If there's already a role with that name, we don't add it again
			if (role.getName().equals(newRole.getName()))
				return;
		}
		this.roles.add(newRole);
	}


	public List<IRole> getRoles() {
		return this.roles;
	}
}
