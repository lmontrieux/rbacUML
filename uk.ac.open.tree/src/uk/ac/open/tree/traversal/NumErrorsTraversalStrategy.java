package uk.ac.open.tree.traversal;

import java.util.PriorityQueue;

import uk.ac.open.tree.Tree;
import uk.ac.open.tree.node.INode;
import uk.ac.open.tree.node.NumErrorsNodeComparator;

public class NumErrorsTraversalStrategy implements ITraversalStrategy {
	Tree tree;
	PriorityQueue<INode> queue;
	
	NumErrorsTraversalStrategy(Tree tree) {
		this.tree = tree;
		this.queue = new PriorityQueue<INode>(10, new NumErrorsNodeComparator());
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
