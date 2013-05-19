/**
 * 
 */
package uk.ac.open.rbacuml.dsml.plugin.research.fixing.problem;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;

import uk.ac.open.rbacuml.dsml.plugin.research.RDSterHelper;
import uk.ac.open.rbacuml.dsml.plugin.research.RDNavHelper;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.Fix;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.Change;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.StereotypeAssociationDeleteChange;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.path.Path;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacNode;


/**
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public class SSoDProblem extends Problem {

	public SSoDProblem(ConstraintStatus status, RbacNode node) {
		super(status, node);
		log.info("SSoD Problem created.");
	}

	/* (non-Javadoc)
	 * @see uk.ac.open.rbacuml.dsml.plugin.research.fixing.Problem#solve()
	 */
	@Override
	public boolean solve() {
		log.info("Solving Scenario::SSoD error");
		List<Path> cycles = findCycles();
		for (Path cycle:cycles) {
			this.solutions = mergeFixes(this.solutions, breakCycle(cycle));
		}
		removeNonMinimalSolutions();
		purgeKeptChanges();
		return true;
	}
	
	private List<Fix> breakCycle(Path cycle) {
		List<Fix> fixes = new ArrayList<Fix>();
		for (Association assoc:cycle.getAssociations()) {
			Fix fix = new Fix();
			Change change = StereotypeAssociationDeleteChange.createAssociationDeleteChange(node, assoc);
			fix.addChange(change);
			fixes.add(fix);
		}
		return fixes;
	}
	
	/** Finds all the SSoD cycles from the target.
	 * Eliminates duplicates.
	 * @return a list of Paths, each one representing a cycle. 
	 * Cycles are undirected and duplicates have been eliminated.
	 * 
	 */
	private List<Path> findCycles() {
		List<Path> cycles = new ArrayList<Path>();
		List<Class> roles = RDNavHelper.userGetRoles((Class)target);
		for (Class role:roles) {
			List<Class> ssods = RDNavHelper.roleGetSSoD(role);
			for (Class ssod:ssods) {
				if (roles.contains(ssod)) {
					Path path = new Path();
					path.addAssociation(RDNavHelper.getAssociation((Class)target, role, null));
					path.addAssociation(RDNavHelper.getAssociation(role, ssod, RDSterHelper.SSOD));
					path.addAssociation(RDNavHelper.getAssociation(ssod, (Class)target, null));
					cycles.add(path);
				}
			}
		}
		return eliminateDuplicates(cycles);
	}
	
	/**
	 * Eliminates duplicates from a list of paths. Path a-b-c is a 
	 * duplicate of path c-b-a.
	 * @param cycles The list of cycles, possibly containing duplicates
	 * @return a list where all the duplicates have been eliminated
	 */
	private List<Path> eliminateDuplicates(List<Path> cycles) {
		List<Path> noDup = new ArrayList<Path>();
		for (int i = 0; i < cycles.size(); i++) {
			boolean duplicate = false;
			for (int j = i + 1; j < cycles.size(); j++) {
				if (cycles.get(i).isDuplicate(cycles.get(j)))
					duplicate = true;
			}
			if (!duplicate)
				noDup.add(cycles.get(i));
		}
		return noDup;
	}
}
