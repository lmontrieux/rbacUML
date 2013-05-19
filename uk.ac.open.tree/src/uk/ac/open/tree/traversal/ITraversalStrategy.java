package uk.ac.open.tree.traversal;

import uk.ac.open.tree.node.INode;

public interface ITraversalStrategy {
	public static enum Traversal { DFS, BFS, NUM_ERRORS, RED_ERRORS, 
		CUMUL_COST, AVG_COST};
	
	public INode nextNode();
	
	public void enQueue(INode node);
}
