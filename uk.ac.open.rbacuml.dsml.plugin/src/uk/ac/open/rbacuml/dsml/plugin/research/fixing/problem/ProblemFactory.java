/**
 * 
 */
package uk.ac.open.rbacuml.dsml.plugin.research.fixing.problem;

import org.apache.log4j.Logger;
import org.eclipse.emf.validation.model.ConstraintStatus;

import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacNode;


/**
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public class ProblemFactory {
	static Logger log = Logger.getLogger("ProblemFactory");
	
	public static Problem createProblem(ConstraintStatus status, RbacNode node) {
		assert(status != null);
		assert(status.getMessage() != null);
		Problem problem = null;
		String message = status.getMessage();
		log.debug("Attempting to create a Problem for constraint " + message);
		if (message.matches(".+(rbacDSML::User::SSoD).+"))
			problem = new SSoDProblem(status, node);
		else if (message.matches(".+(rbacDSML::Granted::VER).+"))
			problem = new GrantedProblem(status, node);
		else if (message.matches(".+(rbacDSML::Forbidden::VER).+"))
			problem = new ForbiddenProblem(status, node);
		else if (message.matches(".+(rbacDSML::Scenario::Activated).+"))
			problem = new ActivateRolesProblem(status, node);
		else if (message.matches(".+(rbacDSML::Scenario::DSoD).+"))
			problem = new DSoDProblem(status, node);
		else
			throw new IllegalArgumentException("Can't create Problem for constraint " + message);
		return problem;
	}
}
