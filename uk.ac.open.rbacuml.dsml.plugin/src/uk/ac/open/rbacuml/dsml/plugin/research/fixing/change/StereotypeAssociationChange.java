package uk.ac.open.rbacuml.dsml.plugin.research.fixing.change;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

import com.ibm.xtools.modeler.ui.UMLModeler;


public abstract class StereotypeAssociationChange implements Change {
	private int type;
	protected String end1;
	protected RDEltAddChange end1Change;
	protected String end2;
	protected RDEltAddChange end2Change;
	private String name;
	protected RecordingCommand command;
	protected boolean keep;
	Logger log = Logger.getLogger(this.getClass());
	private static int property = 1;
	
	/**
	 * Creates a new Change that adds or remove an association. To use only
	 * by subclasses
	 * @param type the type of Change
	 * @param end1 one end of the association
	 * @param end2 the other end of the association
	 * @param name the name of the association
	 */
	protected StereotypeAssociationChange(int type, String end1, String end2, 
			String name) {
		assert(end1 != null);
		assert(end2 != null);
		assert(name != null);
		this.type = type;
		this.end1 = end1;
		this.end2 = end2;
		this.name = name;
		keep = false;
	}
	
	protected StereotypeAssociationChange(int type, RDEltAddChange end1, String end2, String name) {
		this.type = type;
		this.end1Change = end1;
		this.end2 = end2;
		this.name = name;
		keep = false;
	}
	
	protected StereotypeAssociationChange(int type, String end1, RDEltAddChange end2, String name) {
		this.type = type;
		this.end1 = end1;
		this.end2Change = end2;
		this.name = name;
		keep = false;
	}
	
	protected StereotypeAssociationChange(int type, RDEltAddChange end1, RDEltAddChange end2, String name) {
		this.type = type;
		this.end1Change = end1;
		this.end2Change = end2;
		this.name = name;
		keep = false;
	}
	
	public int getType() {
		return type;
	}
	
	public String from() {
		if (end1 != null)
			return end1;
		else
			return end1Change.getName();
	}
	
	public String to() {
		if (end2 != null)
			return end2;
		else
			return end2Change.getName();
	}
	
	public String getName() {
		return name;
	}

	public boolean conflicts(Change change) {
		if (!(change instanceof StereotypeAssociationChange)) {
			return false;
		}
		StereotypeAssociationChange sachange = (StereotypeAssociationChange) change;
		if (((this.type == Change.ADDITION) && (sachange.getType() == Change.DELETION))
		|| ((this.type == Change.DELETION) && (sachange.getType() == Change.ADDITION))) {
			if (this.from().equals(sachange.from()) && this.to().equals(sachange.to()) 
					&& this.name.equals(sachange.getName()))
				return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object change) {
		if (!(change instanceof StereotypeAssociationChange))
			return false;
		StereotypeAssociationChange sachange = (StereotypeAssociationChange) change;
		if (this.type != sachange.getType())
			return false;
		if ((this.end1 != null) && (!this.end1.equals(sachange.from())))
			return false;
		if (this.end1Change == null)
		if ((this.end1Change != null) && (!this.end1Change.getName().equals(sachange.from())))
			return false;
		if ((this.end2 != null) && (!this.end2.equals(sachange.to())))
			return false;
		if ((this.end2Change != null) && (!this.end2Change.equals(sachange.to())))
			return false;
		if ((this.end1 == null) && (this.end1Change == null)) {
			throw new RuntimeException("There is no end1 in change " + this.toString());
		}
		if ((this.end2 == null) && (this.end2Change == null)) {
			throw new RuntimeException("There is no end2 in change " + this.toString());
		}
		if ((this.name == null) && (sachange.name == null)) {
			return true;
		}
		if ((this.name == null) || (sachange.name == null))
			return false;
		if (!this.name.equals(sachange.getName()))
			return false;
		return true;
	}
	
	public String toString() {
		String end1, end2;
		if (this.end1 == null)
			end1 = this.end1Change.getName();
		else
			end1 = this.end1;
		if (this.end2 == null)
			end2 = this.end2Change.getName();
		else
			end2 = this.end2;
		String print = "Stereotype association change: ";
		if (type == Change.ADDITION)
			print += "Addition";
		else
			print += "Deletion";
		print += " of an association between " + end1 + " and " + end2 + " named " + name;
		return print;
	}

	public Command getEMFCommand() {
		return command;
	}
	
	public boolean hasKeepSter() {
		return keep;
	}
	
	public int getCost() {
		return 1;
	}
	
	public Class getClass(String name) {
		Class target = null;
		NamedElement element = (NamedElement)UMLModeler.getUMLUIHelper().getSelectedElements().get(0);
		for (Element candidate:element.getOwner().allOwnedElements()) {
			if (candidate instanceof Class) {
				if (((Class)candidate).getName().equals(name))
					target = (Class)candidate;
			}
		}
		if (target == null)
			throw new RuntimeException("Can't find class named " + end1);
		return target;
	}
	
	public abstract boolean isUndone();
	
	public boolean apply(CommandStack stack) {
		log.debug("Applying...");
		stack.execute(this.getEMFCommand());
		if (isUndone())
			throw new RuntimeException("Could not apply change: " + this.toString());
		log.debug("Applied!");
		return true;
	}
	
	public boolean undo(CommandStack stack) {
		log.debug("Undoing...");
		stack.execute(this.getUndoCommand());
		log.debug("Undone!");
		return true;
	}
	
	public static String getNextProperty() {
		String prop = "property" + StereotypeAssociationChange.property;
		StereotypeAssociationChange.property++;
		return prop;
	}
}
