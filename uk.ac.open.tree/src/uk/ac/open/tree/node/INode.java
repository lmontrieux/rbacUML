package uk.ac.open.tree.node;

import java.util.List;

import uk.ac.open.tree.selection.IConstraintSelector;
import uk.ac.open.tree.selection.IConstraintSelector.Type;

/**
 * This is the node interface. Nodes are the elements in a tree. But you
 * probably know that already.
 * 
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public interface INode {
	public void applyFix();
	public void undoFix();
	public int getCost();
	public int getCombinedCost();
	public boolean isCorrect();
	public boolean isRoot();
	public int getLevel();
	public INode getParent();
	public void setParent(INode parent);
	public List<INode> getChildren();
	public boolean addChild(INode node);
	public INode getNextChild(INode node);
	public IConstraintSelector getConstraintSelector();
	public boolean evaluate(Type selector);
	public void fix();
	public List getFixes();
	public long getTime();
	
	/**
	 * Returns the number of errors in the evaluation of this node
	 * @return the number of errors (positive int)
	 */
	public int getNumErrors();
}
