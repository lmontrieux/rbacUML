/**
 * 
 */
package uk.ac.open.rbacuml.dsml.plugin.research.fixing.problem;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.model.IModelConstraint;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;

import uk.ac.open.rbacuml.dsml.plugin.research.fixing.Fix;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.Change;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.RDEltAddChange;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.StereotypeAssociationAddChange;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.StereotypeAssociationDeleteChange;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacNode;

/**
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public abstract class Problem {
	protected ConstraintStatus problem;
	protected List<Fix> solutions;
	protected Element target;
	protected IModelConstraint constraint;
	protected RbacNode node;
	protected Logger log = Logger.getLogger("Problem");
	
	public Problem(ConstraintStatus status, RbacNode node) {
		log.trace("Creating a new Problem with status " + status.getMessage());
		this.problem = status;
		this.solutions = new ArrayList<Fix>();
		this.target = (Element)problem.getTarget();
		this.constraint = problem.getConstraint();
		this.node = node;
	}
	
	public abstract boolean solve();
	
	public List<Fix> getSolutions() {
		return solutions;
	}
	
	public boolean isSolved() {
		return solutions.size() > 0;
	}
	
	public ConstraintStatus getProblem() {
		return problem;
	}
	
	public String getMessage() {
		return problem.getMessage();
	}
	
	public Element getTarget() {
		return target;
	}

	public IModelConstraint getConstraint() {
		return constraint;
	}
	
	/**
	 * Merges two lists of fixes
	 * @param fixes the first list
	 * @param newFixes the second one
	 * @return a list of merged fixes, where each fix in the first list is 
	 * merged with one fix on the second list - for each fix on the second list.
	 */
	protected List<Fix> mergeFixes(List<Fix> fixes, List<Fix> newFixes) {
		log.debug("Merging two lists of fixes");
		if (fixes.size() == 0)
			return newFixes;
		if (newFixes.size() == 0)
			return fixes;
		List<Fix> results = new ArrayList<Fix>();
		for (Fix fix:fixes) {
			for (Fix newFix:newFixes) {
				Fix merged = fix.getMerge(newFix);
				merged.minimise();
				results.add(merged);
			}
		}
		log.debug("Lists of fixes merged, resulting in " + results.size() + " fixes");
		return results;
	}
	
	protected void minimiseFixes(List<Fix> fixes) {
		for (Fix fix:fixes)
			fix.minimise();
	}
	
	/**
	 * Removes the non-minimal solutions from the set of solutions.
	 * A Fix A is non-minimal if there exists in the set of solutions 
	 * another fix B that is a subset of A.
	 */
	protected void removeNonMinimalSolutions() {
		log.debug("Removing non-minimal solutions from fix");
		List<Fix> minimal = new ArrayList<Fix>();
		for (Fix fix:this.solutions) {
			boolean min = true;
			for (Fix otherFix:this.solutions) {
				if (fix.isSupersetOf(otherFix) && !fix.equals(otherFix))
					min = false;
			}
			if (min)
				minimal.add(fix);
		}
		log.debug("Went from " + this.solutions.size() + " fixes to " 
				+ minimal.size() + " minimal ones");
		this.solutions = minimal;
	}
	
	/**
	 * Removes from the list of solutions those that include changes that are
	 * in contradiction with stereotypes from the Keep profile
	 */
	protected void purgeKeptChanges() {
		List<Fix> purgedList = new ArrayList<Fix>();
		for (Fix fix:this.solutions) {
			boolean purge = false;
			for (Change change:fix.getAllChanges()) {
				if (change instanceof StereotypeAssociationDeleteChange) {
					StereotypeAssociationDeleteChange del = (StereotypeAssociationDeleteChange)change;
					if (del.hasKeepSter()) {
						purge = true;
						break;
					}
				}
				if (change instanceof StereotypeAssociationAddChange) {
					StereotypeAssociationAddChange add = (StereotypeAssociationAddChange)change;
					if (add.hasKeepSter()) {
						purge = true;
						break;
					}
				}
			}
			if (!purge) {
				purgedList.add(fix);
			}
		}
		this.solutions = purgedList;
	}
	
	/**
	 * Creates a brand new permission on the model
	 * @return a Change that creates the new permission
	 */
	protected RDEltAddChange createNewPermission(Class element) {
		return createNewClass(element, Change.NEW_PERMISSION);
	}
	
	protected RDEltAddChange createNewResource(Class element) {
		return createNewClass(element, Change.NEW_RESOURCE);
	}
	
	protected RDEltAddChange createNewUser(Class element) {
		return createNewClass(element, Change.NEW_USER);
	}
	
	protected RDEltAddChange createNewRole(Class element) {
		return createNewClass(element, Change.NEW_ROLE);
	}
	
	protected RDEltAddChange createNewClass(Class element, int type) {
		log.debug("Creating a new Class with type " + type);
		RDEltAddChange newClass = new RDEltAddChange(type, 
				"name", element.getOwner());
		return newClass;
	}
}
