package uk.ac.open.rbacuml.dsml.plugin.research.fixing.change;

import org.eclipse.emf.common.command.Command;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;

import uk.ac.open.rbacuml.dsml.plugin.research.KeepSterHelper;
import uk.ac.open.rbacuml.dsml.plugin.research.RDNavHelper;
import uk.ac.open.rbacuml.dsml.plugin.research.RDSterHelper;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacNode;

import com.ibm.xtools.modeler.ui.UMLModeler;
import com.ibm.xtools.uml.core.type.UMLElementTypes;

public class StereotypeAssociationAddChange extends StereotypeAssociationChange {
	private Stereotype stereotype = null;
	private String name = null;
	private Association association;
	/**
	 * Creates a new Change that creates a new, undirected
	 * association between two classes
	 * @param end1 one end of the association
	 * @param end2 the other end of the association
	 * @param name the name of the association
	 * @param stereotype an optional stereotype to apply to the association
	 */
	private StereotypeAssociationAddChange(Class end1, Class end2, String name, 
			Stereotype stereotype) {
		super(Change.ADDITION, end1.getName(), end2.getName(), 
				name);
		handleKeepSter(end1, end2);
		this.command = new RBACAdditionChangeCommand(this, end1, end2, stereotype, name, 
				end1.getOwner(), UMLElementTypes.BIDIRECTIONAL_ASSOCIATION);
	}
	
	private StereotypeAssociationAddChange(Class end1, RDEltAddChange end2, 
			String name, Stereotype stereotype) {
		super(Change.ADDITION, end1.getName(), end2, 
				name);
		this.command = null;
		this.stereotype = stereotype;
		this.name = name;
	}
	
	private StereotypeAssociationAddChange(RDEltAddChange end1, Class end2, 
			String name, Stereotype stereotype) {
		super(Change.ADDITION, end1, end2.getName(), 
				name);
		this.command = null;
		this.stereotype = stereotype;
		this.name = name;
	}
	
	private StereotypeAssociationAddChange(RDEltAddChange end1, 
			RDEltAddChange end2, String name, Stereotype stereotype) {
		super(Change.ADDITION, end1, end2, 
				name);
		this.command = null;
		this.stereotype = stereotype;
		this.name = name;
	}
	
	public static StereotypeAssociationChange createAssociationAddChange(RbacNode node, Class end1, Class end2, String name, Stereotype stereotype) {
		RDEltAddChange end1Change, end2Change;
		end1Change = node.getClassAddition(end1.getName());
		end2Change = node.getClassAddition(end2.getName());
		if ((end1Change == null) && (end2Change == null))
			return new StereotypeAssociationAddChange(end1, end2, name, stereotype);
		else if ((end1Change != null) && (end2Change == null))
			return new StereotypeAssociationAddChange(end1Change, end2, name, stereotype);
		else if ((end1Change == null) && (end2Change != null))
			return new StereotypeAssociationAddChange(end1, end2Change, name, stereotype);
		else // end1Change != null && end2Change != null
			return new StereotypeAssociationAddChange(end1Change, end2Change, name, stereotype);
	}
	
	public static StereotypeAssociationChange createAssociationAddChange(RbacNode node, RDEltAddChange end1, Class end2, String name, Stereotype stereotype) {
		RDEltAddChange end2Change;
		end2Change = node.getClassAddition(end2.getName());
		if (end2Change == null)
			return new StereotypeAssociationAddChange(end1, end2, name, stereotype);
		else // end2Change != null
			return new StereotypeAssociationAddChange(end1, end2Change, name, stereotype);
	}
	
	public static StereotypeAssociationChange createAssociationAddChange(RbacNode node, Class end1, RDEltAddChange end2, String name, Stereotype stereotype) {
		RDEltAddChange end1Change;
		end1Change = node.getClassAddition(end1.getName());
		if (end1Change == null)
			return new StereotypeAssociationAddChange(end1, end2, name, stereotype);
		else // end1Change != null
			return new StereotypeAssociationAddChange(end1Change, end2, name, stereotype);
	}
	
	public static StereotypeAssociationChange createAssociationAddChange(RbacNode node, RDEltAddChange end1, RDEltAddChange end2, String name, Stereotype stereotype) {
		return new StereotypeAssociationAddChange(end1, end2, name, stereotype);
	}

	private void handleKeepSter(Class end1, Class end2) {
		if (end1.getAppliedStereotype(KeepSterHelper.NO_ADD) != null)
			keep = true;
		if (end2.getAppliedStereotype(KeepSterHelper.NO_ADD) != null)
			keep = true;
		
		if (end1.getAppliedStereotype(KeepSterHelper.NO_ADD_ROLE) != null)
			if (end2.getAppliedStereotype(RDSterHelper.ROLE) != null)
				keep = true;
		if (end2.getAppliedStereotype(KeepSterHelper.NO_ADD_ROLE) != null)
			if (end1.getAppliedStereotype(RDSterHelper.ROLE) != null)
				keep = true;
		
		if (end1.getAppliedStereotype(KeepSterHelper.NO_ADD_RESOURCE) != null)
			if (end2.getAppliedStereotype(RDSterHelper.RESOURCE) != null)
				keep = true;
		if (end2.getAppliedStereotype(KeepSterHelper.NO_ADD_RESOURCE) != null)
			if (end1.getAppliedStereotype(RDSterHelper.RESOURCE) != null)
				keep = true;
		
		if (end1.getAppliedStereotype(KeepSterHelper.NO_ADD_PERMISSION) != null)
			if (end2.getAppliedStereotype(RDSterHelper.PERMISSION) != null)
				keep = true;
		if (end2.getAppliedStereotype(KeepSterHelper.NO_ADD_PERMISSION) != null)
			if (end1.getAppliedStereotype(RDSterHelper.PERMISSION) != null)
				keep = true;
	}
	
	public Command getEMFCommand() {
		String end1, end2;
		log.debug("From:"  + this.from() + " To: " + this.to());
		if (this.from().split("::").length > 1)
			end1 = this.from().split("::")[1];
		else
			end1 = this.from();
		if (this.to().split("::").length > 1)
			end2 = this.to().split("::")[1];
		else
			end2 = this.to();
		Class class1 = null;
		Class class2 = null;
		NamedElement element = (NamedElement)UMLModeler.getUMLUIHelper().getSelectedElements().get(0);
		for (Element candidate:element.getOwner().allOwnedElements()) {
			if (candidate instanceof Class) {
				if (((Class)candidate).getName().equals(end1))
					class1 = (Class)candidate;
				if (((Class)candidate).getName().equals(end2))
					class2 = (Class)candidate;
			}
		}
		if (class1 == null)
			throw new RuntimeException("Can't find class named " + end1);
		if (class2 == null)
			throw new RuntimeException("Can't find class named " + end2);
		this.command = new RBACAdditionChangeCommand(this, class1, class2, stereotype, 
				this.toString(), class1.getOwner(), UMLElementTypes.BIDIRECTIONAL_ASSOCIATION);
		return this.command;
	}

	public void notify(String name, RDEltAddChange rdEltAddChange) {
		// TODO Auto-generated method stub
		
	}
	
	public void setElement(Association element) {
		this.association = element;
	}

	@Override
	public boolean isUndone() {
		Association association;
		if (stereotype == null)
			association = RDNavHelper.getAssociation(getClass(from()), getClass(to()), null);
		else {
			log.debug("There is a stereotype named " + stereotype.getName());
			association = RDNavHelper.getAssociation(getClass(from()), getClass(to()), stereotype.getName());
		}
		if (association == null)
			return true;
		return false;
	}

	public Command getUndoCommand() {
		if (association == null)
			log.debug("ASSOCIATION IS NULL");
		if (RDNavHelper.getAssociation(getClass(from()), getClass(to()), null) == null)
			log.debug("WTF is this association?");
		log.debug("Creating undo command for association " + RDNavHelper.getAssociation(getClass(from()), getClass(to()), null).getName());
		return new RBACDeleteChangeCommand("Undoing association creation change", RDNavHelper.getAssociation(getClass(from()), getClass(to()), null));
	}
}
