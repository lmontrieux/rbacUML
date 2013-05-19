package uk.ac.open.rbacuml.dsml.plugin.research.fixing.problem.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.model.ConstraintStatus;

import uk.ac.open.tree.selection.IConstraintSelector;

/**
 * This class implements a constraint selector based on the dependency 
 * relationship between OCL constraints. The selector examines the dependency
 * graph and goes from the root to the nodes when selecting the next constraint
 * 
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public class OCLDependencyConstraintSelector implements IConstraintSelector {
	private int totalErrors;
	private PriorityQueue<ConstraintStatus> queue;
	Logger log = Logger.getLogger(this.getClass());
	
	OCLDependencyConstraintSelector(IStatus results) {
		this.queue = new PriorityQueue<ConstraintStatus>();
		List<ConstraintStatus> errors = extractErrors(results);
		for (ConstraintStatus error:errors) {
			this.queue.offer(error);
		}
		this.totalErrors = errors.size();
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

	/**
	 * Selects the next constraint to solve. The order in which constraints
	 * are selected depends on their category. Constraints in the same 
	 * category are selected in an unspecified order.
	 */
	public ConstraintStatus next() {
		return queue.poll();
	}

	public int getNumErrors() {
		return totalErrors;
	}

}
