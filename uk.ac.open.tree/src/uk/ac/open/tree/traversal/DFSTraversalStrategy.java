/**
 * 
 */
package uk.ac.open.tree.traversal;

import java.util.Stack;

import uk.ac.open.tree.Tree;
import uk.ac.open.tree.node.INode;

/**
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public class DFSTraversalStrategy implements ITraversalStrategy {
	private Tree tree;
	private Stack<INode> stack;
	
	DFSTraversalStrategy(Tree tree) {
		this.tree = tree;
		this.stack = new Stack<INode>();
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.open.tree.traversal.ITraversalStrategy#nextNode()
	 */
	@Override
	public INode nextNode() {
		if (stack.isEmpty())
			return null;
		return stack.pop();
	}

	/* (non-Javadoc)
	 * @see uk.ac.open.tree.traversal.ITraversalStrategy#enQueue(uk.ac.open.tree.node.INode)
	 */
	@Override
	public void enQueue(INode node) {
		assert(node != null);
		stack.push(node);
	}

}
