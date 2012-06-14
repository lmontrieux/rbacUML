package uk.ac.open.rbacuml.plugin.ldif;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

public class SimpleUser implements IUser {
	private String name;
	private String uuid;
	private List<IRole> roles;
	private List<String> duplicates;
	
	static Logger log = Logger.getLogger(SimpleUser.class);
	
	public SimpleUser(String name, String uuid) {
		this.name = name;
		this.uuid = uuid;
		this.roles = new ArrayList<IRole>();
		this.duplicates = new ArrayList<String>();
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
			if (role.getName().equals(newRole.getName())) {
				log.debug("addRole: this role already exists, we don't add it again");
				return;
			}
		}
		this.roles.add(newRole);
		log.debug("addRole: added new role: " + newRole.getName());
	}


	public List<IRole> getRoles() {
		return this.roles;
	}


	/**
	 * Returns a list of duplicate users. Duplicate users have exactly the same 
	 * role assignments, and therefore they can be safely aggregated without 
	 * any impact on the security of the model. If there are no duplicates, 
	 * returns an empty list.
	 */
	public List<String> getDuplicates() {
		return this.duplicates;
	}


	/**
	 * Adds a user name to the list of duplicates. Duplicate users have exactly 
	 * the same role assignments, and therefore they can be safely aggregated 
	 * without any impact on the security of the model.
	 */
	public void addDuplicate(String name) {
		assert(name != null);
		this.duplicates.add(name);
	}
}
