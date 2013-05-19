package uk.ac.open.tree.traversal;

import java.util.PriorityQueue;

import uk.ac.open.tree.Tree;
import uk.ac.open.tree.node.AvgCostNodeComparator;
import uk.ac.open.tree.node.INode;

public class AvgCostTraversalStrategy implements ITraversalStrategy {
	Tree tree;
	PriorityQueue<INode> queue;
	
	AvgCostTraversalStrategy(Tree tree) {
		this.tree = tree;
		this.queue = new PriorityQueue<INode>(10, new AvgCostNodeComparator());
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
