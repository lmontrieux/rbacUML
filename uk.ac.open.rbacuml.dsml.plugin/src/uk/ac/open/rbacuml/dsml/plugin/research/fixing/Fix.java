package uk.ac.open.rbacuml.dsml.plugin.research.fixing;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.command.CommandStack;

import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.Change;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.RDEltAddChange;

public class Fix {
	public List<Change> changes;
	private int appliedChanges;
	Logger log = Logger.getLogger(this.getClass());
	
	public Fix() {
		changes = new ArrayList<Change>();
		appliedChanges = 0;
	}
	
	public List<Change> getAllChanges() {
		return changes;
	}
	
	public void addChange(Change change) {
		changes.add(change);
	}
	
	public String toString() {
		String print = "Fix is made of " + changes.size() + " changes.";
		if (changes.size() > 0)
			print += System.getProperty("line.separator");
		for (Change change:changes) {
			print += change.toString() + System.getProperty("line.separator");
		}
		return print;
	}
	
	public int getCost() {
		int cost = 0;
		for (Change change:this.changes)
			cost += change.getCost();
		return cost;
	}

	/**
	 * Creates and returns a NEW fix that's the merge of the current one and
	 * the one specified as a parameter
	 * @param fix the second fix to merge
	 * @return the merged fix
	 */
	public Fix getMerge(Fix fix) {
		Fix merge = new Fix();
		List<Change> myChanges = this.changes;
		List<Change> theirChanges = fix.getAllChanges();
		for (Change myChange:myChanges) {
			for (Change theirChange:theirChanges)
				if (myChange.conflicts(theirChange))
					return null;
		}
		merge.addAllChanges(myChanges);
		merge.addAllChanges(theirChanges);
		return merge;
	}
	
	public void addAllChanges(List<Change> changes) {
		this.changes.addAll(changes);
	}

	/**
	 * Merges this fix with another one. The changes of the fix passed as a 
	 * parameter are added to this fix, after making sure that there are no
	 * conflicts
	 * @param fix the fix to merge with this one
	 * @return true if the merging has succeeded, false if a conflict has been
	 * detected
	 */
	public boolean merge(Fix fix) {
		for (Change newChange:fix.getAllChanges()) {
			for (Change existingChange:changes) {
				if (existingChange.conflicts(newChange))
					return false;
			}
		}
		// There are no conflicts, we can merge
		changes.addAll(fix.getAllChanges());
		return true;
	}
	
	/**
	 * Applies the fix on the model
	 */
	public void applyFix(CommandStack stack) {
		log.debug("Fix::::");
		for (Change change:this.changes) {
			log.debug("CHANGE:::::" + change.toString());
		}
		appliedChanges = changes.size();
		for (Change change:changes) {
			log.debug("Applying change " + change.toString());
			change.apply(stack);
			log.debug("Change applied: " + change.toString());
		}
	}
	
	/**
	 * Undoes the applied fix
	 * PRE: the fix must have been applied before
	 */
	public void undoFix(CommandStack stack) {
		log.debug("Undoing fix.");
		for (int i = this.changes.size() - 1; i >= 0; i--) {
			Change change = this.changes.get(i);
			log.debug("About to undo: " + change.toString());
			change.undo(stack);
			log.debug("Change undone");
		}
		appliedChanges = 0;
		log.debug("fix undone.");
	}
	
	public boolean contains(Change change) {
		return changes.contains(change);
	}
	
	/**
	 * Minimises a fix by removing the duplicate changes it may contain
	 */
	public void minimise() {
		log.debug("Minimising changes in fix " + this.toString());
		List<Change> minimised = new ArrayList<Change>();
		for (Change change:this.changes) {
			if (!minimised.contains(change))
				minimised.add(change);
		}
		log.debug("Went from " + changes.size() + " to " + minimised.size() 
				+ " after minimising");
		this.changes = minimised;
	}
	
	/**
	 * Determines whether this fix is a subset of another fix
	 * A fix A is a subset of a fix B iff all the changes in A
	 * are also in B
	 * @param fix the fix to compare this fix to
	 * @return true if this fix is a subset of the fix passed as a 
	 * parameter
	 */
	public boolean isSubsetOf(Fix fix) {
		for (Change change:this.changes) {
			if (!fix.contains(change))
				return false;
		}
		return true;
	}
	
	/**
	 * Determines whether this fix is a superset of another fix
	 * A fix A is a superset of a fix B iff all the changes in B
	 * are also in A
	 * @param fix the fix to compare this fix to
	 * @return true if this fix is a superset of the fix passed as a 
	 * parameter
	 */
	public boolean isSupersetOf(Fix fix) {
		for (Change change:fix.getAllChanges()) {
			if (!this.changes.contains(change))
				return false;
		}
		return true;
	}

	/**
	 * Returns a list of all the RDEltAddChange s in the fix
	 * @return
	 */
	public List<RDEltAddChange> getAddedClasses() {
		List<RDEltAddChange> additions = new ArrayList<RDEltAddChange>();
		for (Change change:this.changes) {
			if (change instanceof RDEltAddChange)
				additions.add((RDEltAddChange)change);
		}
		return additions;
	}
}
