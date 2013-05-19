package uk.ac.open.tree.selection;

import org.eclipse.emf.validation.model.ConstraintStatus;

public interface IConstraintSelector {
	public static enum Type {SIMPLE, OCLDEPENDENCIES};
	/**
	 * Selects the next violated constraint to fix
	 * @return an IStatus representing the constraint to fix, or null if there
	 * aren't any.
	 */
	public ConstraintStatus next();
	
	/**
	 * Gives the number of errors in the model evaluation
	 * @return the number of errors (>= 0)
	 */
	public int getNumErrors();
}
