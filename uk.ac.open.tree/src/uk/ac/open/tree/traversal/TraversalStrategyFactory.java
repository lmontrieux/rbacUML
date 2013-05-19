package uk.ac.open.tree.traversal;

import uk.ac.open.tree.Tree;
import uk.ac.open.tree.traversal.ITraversalStrategy.Traversal;

public class TraversalStrategyFactory {
	
	public static ITraversalStrategy createTraversalStrategy(Traversal type, 
			Tree tree) {
		if (type.equals(Traversal.BFS))
			return new BFSTraversalStrategy(tree);
		else if (type.equals(Traversal.DFS))
			return new DFSTraversalStrategy(tree);
		else if (type.equals(Traversal.NUM_ERRORS))
			return new NumErrorsTraversalStrategy(tree);
		else if (type.equals(Traversal.RED_ERRORS))
			return new RedErrorsTraversalStrategy(tree);
		else if (type.equals(Traversal.CUMUL_COST))
			return new CumulCostTraversalStrategy(tree);
		else if (type.equals(Traversal.AVG_COST))
			return new AvgCostTraversalStrategy(tree);
		else
			return null;
	}
}
