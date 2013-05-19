package uk.ac.open.rbacuml.dsml.plugin.research.fixing.problem.selection;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.uml2.uml.NamedElement;

import uk.ac.open.tree.selection.IConstraintSelector;

public class SimpleConstraintSelector implements IConstraintSelector {
	private List<ConstraintStatus> errors;
	private int next;
	Logger log = Logger.getLogger(this.getClass());
	
	SimpleConstraintSelector(IStatus results) {
		this.errors = extractErrors(results);
		this.next = 0;
	}
	
	public int getNumErrors() {
		return errors.size();
	}

	/**
	 * Selects the next constraint to solve. This version simply uses
	 * the next one in the list
	 */
	public ConstraintStatus next() {
		if (errors.size() <= 0)
			return null;
		ConstraintStatus current = errors.get(0);
		for (ConstraintStatus candidate:errors) {
			current = getFirst(current, candidate);
		}
		return current;
	}
	
	private ConstraintStatus getFirst(ConstraintStatus one, ConstraintStatus two) {
		if (getScore(one.getConstraint().getDescriptor().getName()) > getScore(two.getConstraint().getDescriptor().getName()))
			return one;
		else if (getScore(one.getConstraint().getDescriptor().getName()) < getScore(two.getConstraint().getDescriptor().getName()))
			return two;
		else {
			int compare = ((NamedElement)one.getTarget()).getName().compareTo(((NamedElement)two.getTarget()).getName());
			if (compare > 0)
				return one;
			else
				return two;
		}
	}
	
	private int getScore(String name) {
		if (name.startsWith("rbacDSML::Granted::VER Granted"))
			return 1;//1
		else if (name.startsWith("rbacDSML::Forbidden::VER Forbidden"))
			return 1;//1
		else if (name.startsWith("rbacDSML::Scenario::Activated"))
			return 2;//2
		else if (name.startsWith("rbacDSML::Scenario::DSoD"))
			return 2;//2
		else if (name.startsWith("rbacDSML::User::SSoD"))
			return 2;//2
		else
			throw new RuntimeException("unsupported constraint: " + name);
	}
	
	private List<ConstraintStatus> extractErrors(IStatus status) {
		log.trace("Extracting constraint substatus from status " + status.getMessage());
		List<ConstraintStatus> errors = new ArrayList<ConstraintStatus>();
		for (IStatus error:status.getChildren()) {
			if (error instanceof ConstraintStatus) {
				if (!((ConstraintStatus)error).isOK()) {
					errors.add((ConstraintStatus)error);
					log.trace("Added constraint substatus: " + error.getMessage());
				}
			}
		}
		return errors;
	}
}
