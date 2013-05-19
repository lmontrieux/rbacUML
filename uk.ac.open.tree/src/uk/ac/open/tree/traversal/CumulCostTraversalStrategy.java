package uk.ac.open.tree.traversal;

import java.util.PriorityQueue;

import uk.ac.open.tree.Tree;
import uk.ac.open.tree.node.CumulCostNodeComparator;
import uk.ac.open.tree.node.INode;

/**
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public class CumulCostTraversalStrategy implements ITraversalStrategy {
	Tree tree;
	PriorityQueue<INode> queue;
	
	CumulCostTraversalStrategy(Tree tree) {
		this.tree = tree;
		this.queue = new PriorityQueue<INode>(10, new CumulCostNodeComparator());
	}

	@Override
	public INode nextNode() {
		return queue.poll();
	}

	@Override
	public void enQueue(INode node) {
		queue.offer(node);
	}

}
