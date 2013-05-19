package uk.ac.open.rbacuml.dsml.plugin.research.fixing.path;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.uml2.uml.Association;

public class Path {
	private List<Association> path;
	
	public Path() {
		path = new ArrayList<Association>();
	}
	
	public void addAssociation(Association association) {
		this.path.add(association);
	}
	
	public int getLength() {
		return path.size();
	}
	
	public Association getAssociationAt(int position) {
		return path.get(position);
	}
	
	public boolean equals(Path otherPath) {
		if (path.size() != otherPath.getLength())
			return false;
		for (int i = 0; i < path.size(); i++) {
			if (path.get(i) != otherPath.getAssociationAt(i))
				return false;
		}
		return true;
	}
	
	public List<Association> getAssociations() {
		return path;
	}
	
	/**
	 * Determines if the path is a duplicate of another one.
	 * Two paths are duplicate if they have the same associations, 
	 * regardless of their order. This is a weaker condition than 
	 * equals, where all associations must be in the same order
	 * @param path the potential duplicate
	 * @return true if duplicate, false otherwise
	 */
	public boolean isDuplicate(Path path) {
		if (this.getLength() != path.getLength()) //trivial case
			return false;
		for(Association association:this.path) {
			if (!path.contains(association))
				return false;
		}
		return true; //work because both Paths are of the same length.
	}
	
	public boolean contains(Association association) {
		return path.contains(association);
	}
}
