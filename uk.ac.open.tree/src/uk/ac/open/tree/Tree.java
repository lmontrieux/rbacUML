/**
 * This class implements a Tree, more or less tailored to our needs for the 
 * rbacDSML fixes generation algorithm, so it has a few "surprising" features.
 * 
 * - Nodes can be anything as long as they implement the INode interface.
 * - Nodes can be added. Nodes can NOT be removed
 * - The tree MUST have a maximum height set. This value can be increased using 
 * the setter, but not decreased.
 * - Cycles are not allowed (this is a tree, not a graph)
 * - Each node can have an arbitrary number of children, but a node has only
 * one parent (this is a tree, not a graph)
 * 
 * @author Lionel Montrieux<Lionel.Montrieux@open.ac.uk>
 */
package uk.ac.open.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uk.ac.open.tree.node.INode;
import uk.ac.open.tree.traversal.ITraversalStrategy;
import uk.ac.open.tree.traversal.ITraversalStrategy.Traversal;
import uk.ac.open.tree.traversal.TraversalStrategyFactory;

public abstract class Tree {

	protected int maxH;
	protected INode root;
	protected ITraversalStrategy traversal;
	protected List solutions;
	
	/**
	 * Creates a new Tree with a root and a max height of 0, which isn't 
	 * very helpful.
	 * Unless, for some reason, you need to set the max height later.
	 * @param root the root node
	 * @param traversal the traversal strategy to use. DFS strategy used by
	 * default
	 */
	public Tree(Traversal traversal) {
		this.maxH = 0;
		this.traversal = TraversalStrategyFactory.createTraversalStrategy(traversal, this);
		this.solutions = new ArrayList();
	}
	
	public void setRoot(INode root) {
		assert(this.root == null);
		this.root = root;
	}
	
	/**
	 * Creates a tree with a max height and a root.
	 * @param root the root node
	 * @param maxH the maximum height of the tree
	 */
	public Tree(INode root, Traversal traversal, int maxH) {
		this(traversal);
		this.setRoot(root);
		this.maxH = maxH;
	}
	
	/**
	 * Creates a tree with a max height.
	 * @param maxH the maximum height of the tree
	 */
	public Tree(Traversal traversal, int maxH) {
		this(traversal);
		this.maxH = maxH;
	}
	
	public INode getRoot() {
		return this.root;
	}
	
	/**
	 * Sets the max height of the Tree. it is only possible to _increase_ the 
	 * maximum height of the tree
	 * @param max the new maximum height for the tree
	 * @return true if height updated, false if not
	 */
	public boolean setMaxH(int max) {
		if (this.maxH <= max) {
			this.maxH = max;
			return true;
		} else
			return false;
	}
	
	public int getMaxH() {
		return maxH;
	}
	
	public INode nextNode() {
		return traversal.nextNode();
	}
	
	public abstract Collection fixModel();
	
	public void enQueue(INode node) {
		this.traversal.enQueue(node);
	}
	
	public void countChild() {
		return;
	}
}
