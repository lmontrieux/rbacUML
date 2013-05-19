package uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.command.CommandStack;

import com.ibm.xtools.modeler.ui.UMLModeler;

import uk.ac.open.rbacuml.dsml.plugin.research.fixing.Fix;
import uk.ac.open.tree.Tree;
import uk.ac.open.tree.node.INode;
import uk.ac.open.tree.selection.IConstraintSelector.Type;
import uk.ac.open.tree.traversal.ITraversalStrategy.Traversal;

public class RbacTree extends Tree {
	Logger log = Logger.getLogger(this.getClass());
	private RDTreeStats stats;
	private CommandStack stack;
	private int nextNodeID;
	private int maxSol;

	public RbacTree(Traversal bfs, int maxH, String modelName, int maxSol) {
		super(bfs, maxH);
		stats = new RDTreeStats(modelName);
		stats.setStart(System.currentTimeMillis());
		this.stack = UMLModeler.getEditingDomain().getCommandStack();
		this.stack.addCommandStackListener(new RDCommandStackListener());
		this.nextNodeID = 0;
		this.maxSol = maxSol;
	}

	@Override
	public Collection<Fix> fixModel() {
		log.info("Starting fixing the model");
		if (root.evaluate(Type.SIMPLE))
			return null;
		fixNode(root);
		stats.setEnd(System.currentTimeMillis());
		log.info("Done fixing the model");
		log.info("I found " + this.solutions.size() + " solutions that fix the model");
		System.out.println(stats.toString());
		return null;
	}
	
	private void fixNode(INode node) {
		log.debug("Fixing node " + ((RbacNode)node).getID());
		node.applyFix();
		if (reachedMaxSolutions()) {
			log.debug("Maximum number of solutions found, stopping here");
			return;
		}
		node.fix();
		node.undoFix();
		log.debug("Node fixed: " + ((RbacNode)node).getID());
		stats.addNode((RbacNode)node);
		INode nextNode = this.traversal.nextNode();
		if (nextNode != null)
			fixNode(nextNode);
	}
	
	public List<List<Fix>> getSolutions() {
		return solutions;
	}
	
	public String getStats() {
		return this.stats.toString();
	}
	
	public RDTreeStats getStatsObject() {
		return this.stats;
	}
	
	public void countChild() {
		this.stats.addChild();
	}
	
	public CommandStack getCommandStack() {
		return this.stack;
	}

	public int getNextNodeId() {
		this.nextNodeID++;
		return (nextNodeID - 1);
	}
	
	public void addSolution(List<Fix> solution, int cost, long time) {
		this.stats.addSolution(cost, time);
		this.solutions.add(solution);
	}
	
	public boolean reachedMaxSolutions() {
		return this.maxSol <= this.solutions.size();
	}
}
