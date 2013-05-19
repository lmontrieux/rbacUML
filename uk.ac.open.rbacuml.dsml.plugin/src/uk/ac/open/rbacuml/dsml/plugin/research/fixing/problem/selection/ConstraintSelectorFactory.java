package uk.ac.open.rbacuml.dsml.plugin.research.fixing.problem.selection;

import org.eclipse.core.runtime.IStatus;

import uk.ac.open.tree.selection.IConstraintSelector;
import uk.ac.open.tree.selection.IConstraintSelector.Type;

public class ConstraintSelectorFactory {
	
	public static IConstraintSelector createConstraintSelector(IStatus status, Type type) {
		switch(type) {
		case SIMPLE:
			return new SimpleConstraintSelector(status);
		case OCLDEPENDENCIES:
			return new OCLDependencyConstraintSelector(status);
		default:
			return null;
		}
	}
}
