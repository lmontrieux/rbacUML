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
public class DSoDProblem extends Problem {

	public DSoDProblem(ConstraintStatus status, RbacNode node) {
		super(status, node);
		log.info("DSoD Problem created.");
	}

	@Override
	public boolean solve() {
		log.info("Solving Scenario::DSoD error");
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
	
	/** Finds all the DSoD cycles from the target.
	 * Eliminates duplicates.
	 * @return a list of Paths, each one representing a cycle. 
	 * Cycles are undirected and duplicates have been eliminated.
	 */
	private List<Path> findCycles() {
		List<Path> cycles = new ArrayList<Path>();
		List<Class> roles = RDNavHelper.scenarioGetRoles((Class)target);
		log.debug("Found " + roles.size() + " roles from the scenario " + ((Class)target).getName());
		for (Class role:roles) {
			List<Class> dsods = RDNavHelper.roleGetDSoD(role);
			log.debug("Found " + dsods.size() + " dsod relationships from role " + role.getName());
			for (Class dsod:dsods) {
				if (roles.contains(dsod)) {
					Path path = new Path();
					path.addAssociation(RDNavHelper.getAssociation((Class)target, role, null));
					path.addAssociation(RDNavHelper.getAssociation(role, dsod, RDSterHelper.DSOD));
					path.addAssociation(RDNavHelper.getAssociation(dsod, (Class)target, null));
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
		log.info("Eliminating duplicates from a list of " + cycles.size() + " paths");
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
		log.info("There are " + noDup.size() + " paths left after eliminating duplicates");
		return noDup;
	}
}
