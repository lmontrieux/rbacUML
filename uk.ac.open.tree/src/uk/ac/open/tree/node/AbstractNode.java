package uk.ac.open.tree.node;

import java.util.List;

import uk.ac.open.tree.Tree;
import uk.ac.open.tree.selection.IConstraintSelector;

public abstract class AbstractNode implements INode {
	protected boolean correct;
	protected int cost;
	protected INode parent;
	protected List<INode> children;
	protected IConstraintSelector constraintSelector;
	protected Tree tree;
	
	@Override
	public abstract void applyFix();
	
	@Override
	public abstract void undoFix();

	@Override
	public int getCost() {
		return cost;
	}
	
	@Override
	public int getCombinedCost() {
		if (parent == null)
			return cost;
		else
			return parent.getCombinedCost() + cost;
	}

	@Override
	public boolean isCorrect() {
		return correct;
	}

	@Override
	public int getLevel() {
		if (parent == null)
			return 1;
		else
			return parent.getLevel() + 1;
	}

	@Override
	public INode getParent() {
		return parent;
	}

	@Override
	public void setParent(INode parent) {
		this.parent = parent;
	}

	@Override
	public List<INode> getChildren() {
		return children;
	}

	/**
	 * Adds a child to the list of children.
	 * @FIXME: should check for duplicates in the tree before addition
	 */
	@Override
	public boolean addChild(INode node) {
		children.add(node);
		tree.enQueue(node);
		return true;
	}
	
	@Override
	public boolean isRoot() {
		if (this.parent == null)
			return true;
		return false;
	}
	
	@Override
	public INode getNextChild(INode node) {
		if (this.children.isEmpty())
			return null;
		if (node == null)
			return children.get(0);
		for (INode child:children) {
			if (child.equals(node)) {
				int nodeIdx = children.indexOf(child);
				if (nodeIdx + 1 < children.size())
					return children.get(nodeIdx + 1);
				else {// this was the last of the children. Now to the grandchildren.
					return getFirstGrandChild();
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns the first grandchild of the current Node
	 * @return the first grandchild, or null if there are no grandchildren
	 */
	protected INode getFirstGrandChild() {
		for (INode child:children) {
			INode grandChild = child.getNextChild(null);
			if (grandChild != null)
				return grandChild;
		}
		return null;
	}
	
	@Override
	public IConstraintSelector getConstraintSelector() {
		return this.constraintSelector;
	}

	public abstract List getFixes();

}
