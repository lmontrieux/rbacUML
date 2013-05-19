package uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

import com.ibm.xtools.modeler.ui.UMLModeler;

import uk.ac.open.rbacuml.dsml.plugin.research.fixing.Fix;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.change.RDEltAddChange;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.problem.Problem;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.problem.ProblemFactory;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.problem.selection.ConstraintSelectorFactory;
import uk.ac.open.rbacuml.dsml.plugin.research.validation.OCLRBACValidator;
import uk.ac.open.tree.Tree;
import uk.ac.open.tree.node.AbstractNode;
import uk.ac.open.tree.node.INode;
import uk.ac.open.tree.selection.IConstraintSelector.Type;

/**
 * Creates a new RbacNode. A RbacNode has a Fix, which is the fix that 
 * produced the associated model once applied to the parent's model. If the node 
 * is the root (i.e. has no parent), then it has no fix.
 * 
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 */
public class RbacNode extends AbstractNode {
	Logger log = Logger.getLogger(this.getClass());
	private Fix fix;
	private boolean fixApplied;
	private Problem problem;
	private long start;
	private long end;
	private List<RDEltAddChange> addedClasses;
	private int id;
	
	public RbacNode(Fix fix, INode parent, Tree tree) {
		super();
		this.id = ((RbacTree)tree).getNextNodeId();
		if (((fix == null) && (parent != null)) || ((fix != null) && (parent == null))) {
			throw new IllegalArgumentException("Either both fix and parent are null, or they both aren't.");
		}
		this.fixApplied = false;
		this.fix = fix;
		this.children = new ArrayList<INode>();
		this.parent = parent;
		this.tree = tree;
		if (fix == null) {
			log.debug("Creating new root RbacNode");
		}
		else {
			log.debug("Create new RbacNode of level " + getLevel() + "with fix " + fix.toString());
			this.cost = fix.getCost();
			this.addedClasses = fix.getAddedClasses();
		}
		log.debug("Created node with ID " + this.id);
		if (fix != null)
			log.debug("Node ID " + this.id + " has Fix " + fix.toString());
	}
	
	public boolean evaluate(Type constraintSelector) {
		this.constraintSelector = ConstraintSelectorFactory.createConstraintSelector(
				evaluateModel(), constraintSelector);
		return isCorrect();
	}
	
	public void fix() {
		this.start = System.currentTimeMillis();
		generateChildren();
		this.end = System.currentTimeMillis();
	}
	
	public long getTime() {
		return this.end - this.start;
	}
	
	private void generateChildren() {
		assert(this.constraintSelector != null);
		assert(fixApplied);
		log.info("Generating children from height " + this.getLevel());
		if (getLevel() >= tree.getMaxH()) {
			log.info("Reached the tree's maximum height, not generating and more children");
			return;
		}
		ConstraintStatus nextConstraint = this.constraintSelector.next();
		if (nextConstraint != null) {
			problem = ProblemFactory.createProblem(nextConstraint, this);
			problem.solve();
			for (Fix solution:problem.getSolutions()) {
				addChild(new RbacNode(solution, this, tree));
				if (((RbacTree)tree).reachedMaxSolutions()) {
					log.debug("Reached max number of solutions");
					return;
				}
			}
			log.info("Done generating " + children.size() + " children");
		} else {
			problem = null;
			log.info("There are no more constraints to select");
		}		
	}
	
	@Override
	public boolean addChild(INode child) {
		children.add(child);
		((RbacNode)child).applyOneFix();
		child.evaluate(Type.SIMPLE);
		if (child.isCorrect()) {
			((RbacTree)this.tree).addSolution(child.getFixes(), child.getCombinedCost(), System.currentTimeMillis());
		}
		((RbacNode)child).undoOneFix();
		tree.enQueue(child);
		tree.countChild();
		return true;
		
	}

	/**
	 * Evaluates the model
	 * @return
	 */
	private IStatus evaluateModel() {
		assert(fixApplied);
		log.info("Evaluating model...");
		List<Element> elements = new ArrayList<Element>();
		org.eclipse.uml2.uml.Package pkg = ((Element)UMLModeler.getUMLUIHelper().getSelectedElements().get(0)).getNearestPackage();
		List<Element> allElements = pkg.allOwnedElements();
		for(EObject object:allElements) {
			if (object instanceof Class)
				elements.add((Element) object);
		}
		log.info("Evaluating constraints on " + elements.size() + " elements");
		IStatus evaluation = OCLRBACValidator.getValidator().evaluateElements(elements);
		log.debug("Evaluation result: " + evaluation.getMessage());
		this.correct = evaluation.isOK();
		return evaluation;
	}
	
	@Override
	public void applyFix() {
		assert(!fixApplied);
		log.info("Entering RbacNode.applyFix() for node " + this.id);
		if (getParent() != null) {
			getParent().applyFix();
			log.info("Applying fix on node " + this.id + " of level " + getLevel());
			fix.applyFix(((RbacTree)tree).getCommandStack());
			log.debug("Fix applied");
		} else {
			// If there's no parent, we're at the root, and there's no fix to apply
			log.debug("We're at the root, no fix to apply");
		}
		fixApplied = true;
	}
	
	public void applyOneFix() {
		assert(!fixApplied);
		log.info("Applying one fix");
		if (fix != null) {
			fix.applyFix(((RbacTree)tree).getCommandStack());
			log.debug("One fix applied");
		} else {
			log.debug("This must be the root");
		}
		fixApplied = true;
	}
	
	public Fix getFix() {
		return fix;
	}

	@Override
	public void undoFix() {
		assert(fixApplied);
		// undo the parents if they exist
		if (!isRoot()) {
			log.info("Undoing fix from node " + this.id + " on Level " + getLevel());
			fix.undoFix(((RbacTree)tree).getCommandStack());
			log.debug("Fix undone on node " + this.id + " of level " + getLevel());
			getParent().undoFix();
		} else {
			log.debug("Root reached, no fix to undo");
		}
		fixApplied = false;
	}
	
	public void undoOneFix() {
		assert(fixApplied);
		log.debug("Undoing one fix");
		if (fix != null) {
			fix.undoFix(((RbacTree)tree).getCommandStack());
			log.debug("One fix undone");
		} else {
			log.debug("Must be the root, no fix to undo");
		}
	}

	/**
	 * Returns a list of fixes from the root to the current node.
	 */
	@Override
	public List<Fix> getFixes() {
		List<Fix> fixes = new ArrayList<Fix>();
		if (isRoot()) {
			return fixes;
		} else {
			fixes.addAll(parent.getFixes());
			fixes.add(this.fix);
			return fixes;
		}
	}

	public int getNumErrors() {
		return getConstraintSelector().getNumErrors();
	}
	
	public RDEltAddChange getClassAddition(String name) {
		if (this.addedClasses == null)
			return null;
		for (RDEltAddChange change:this.addedClasses) {
			if (change.getName().equals(name))
				return change;
		}
		return ((RbacNode)parent).getClassAddition(name);
	}

	public int getID() {
		return this.id;
	}
	
	public String toString() {
		String res = "Node " + this.id;
		if (parent != null)
			res += " with parent " + ((RbacNode)parent).getID();
		else
			res += " is the root. ";
		res += " level " + this.getLevel();
		if (this.problem != null) {
			res += " Constraint " + this.problem.getConstraint().getDescriptor().getName(); 
			res += " on target " + ((NamedElement)this.problem.getTarget()).getName();
		}
		if (this.fix != null)
			res += " fix " + this.fix.toString() + ".";
		if (this.correct)
			res += " The node is a solution";
		return res;
	}
}
