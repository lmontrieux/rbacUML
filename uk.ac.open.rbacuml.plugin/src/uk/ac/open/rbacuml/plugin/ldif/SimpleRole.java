package uk.ac.open.rbacuml.plugin.ldif;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimpleRole implements IRole {	
	private String name;
	private String uuid;
	private List<IRole> parents;

	public SimpleRole(String name, String uuid) {
		this.name = name;
		this.uuid = uuid;
		this.parents = new ArrayList<IRole>();
	}

	
	public String getName() {
		return this.name;
	}


	public String getUuid() {
		return this.uuid;
	}


	public List<IRole> getParents() {
		return this.parents;
	}


	public boolean hasParent(IRole role) {
		for (IRole parent: this.parents) {
			if (parent.equals(role))
				return true;
		}
		return false;
	}


	public boolean hasParent(String name) {
		for (IRole parent: this.parents) {
			if (parent.getName().equals(name))
				return true;
		}
		return false;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public void setUuid(UUID uuid) {
		this.uuid = uuid.toString();
	}


	public void addParent(IRole role) {
		for (IRole parent: this.parents) {
			// If the parent is already there, there's no need to add it again
			if (parent.equals(role))
				return;
		}
		this.parents.add(role);
	}

	/**
	 * Two Roles are equal if they have the same name
	 * @param role the role
	 * @return true if both roles have the same name
	 */
	public boolean equals(IRole role) {
		assert(role != null);

		if (this.name.equals(role.getName()))
			return true;
		return false;
	}

}
