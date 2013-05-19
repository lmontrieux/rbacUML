package uk.ac.open.tree.traversal;

import java.util.ArrayList;
import java.util.List;

import uk.ac.open.tree.Tree;
import uk.ac.open.tree.node.INode;

public class BFSTraversalStrategy implements ITraversalStrategy {
	private Tree tree;
	private List<INode> queue;

	BFSTraversalStrategy(Tree tree) {
		this.tree = tree;
		this.queue = new ArrayList<INode>();
	}
	
	@Override
	public INode nextNode() {
		if (queue.isEmpty())
			return null;
		return(queue.remove(0));
	}

	@Override
	public void enQueue(INode node) {
		this.queue.add(node);
	}

}
