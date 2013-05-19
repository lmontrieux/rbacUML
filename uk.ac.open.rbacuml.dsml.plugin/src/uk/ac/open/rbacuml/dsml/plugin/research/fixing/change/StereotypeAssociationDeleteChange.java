package uk.ac.open.rbacuml.dsml.plugin.research.fixing.change;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;

import uk.ac.open.rbacuml.dsml.plugin.research.KeepSterHelper;
import uk.ac.open.rbacuml.dsml.plugin.research.RDNavHelper;
import uk.ac.open.rbacuml.dsml.plugin.research.RDSterHelper;
import uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree.RbacNode;

import com.ibm.xtools.uml.core.type.UMLElementTypes;

public class StereotypeAssociationDeleteChange extends
		StereotypeAssociationChange {
	private String sterName;
	private Stereotype stereotype =  null;

	private StereotypeAssociationDeleteChange(Association association) {
		super(Change.DELETION, ((Class)association.getMemberEnds().get(0).getType()).getName(), 
				((Class)association.getMemberEnds().get(1).getType()).getName(), association.getName());
		handleKeepSter(association);
		this.command = new RBACDeleteChangeCommand(this.toString(), association);
		this.sterName = null;
		if (association.getAppliedStereotypes().size() > 0)
			this.stereotype = association.getAppliedStereotypes().get(0);
	}
	
	private StereotypeAssociationDeleteChange(Class end1, RDEltAddChange end2, String name) {
		super(Change.DELETION, end1.getName(), end2, name);
	}
	
	private StereotypeAssociationDeleteChange(RDEltAddChange end1, RDEltAddChange end2, String name) {
		super(Change.DELETION, end1, end2, name);
	}
	
	public static StereotypeAssociationDeleteChange createAssociationDeleteChange(RbacNode node, Association association) {
		RDEltAddChange end1Change = node.getClassAddition(association.getMemberEnds().get(0).getType().getName());
		RDEltAddChange end2Change = node.getClassAddition(association.getMemberEnds().get(1).getType().getName());
		if ((end1Change == null) && (end2Change == null))
			return new StereotypeAssociationDeleteChange(association);
		else if ((end1Change == null) && (end2Change != null))
			return new StereotypeAssociationDeleteChange((Class)association.getMemberEnds().get(0).getType(), end2Change, association.getName());
		else if ((end1Change != null) && (end2Change == null))
			return new StereotypeAssociationDeleteChange((Class)association.getMemberEnds().get(1).getType(), end1Change, association.getName());
		else // both != null
			return new StereotypeAssociationDeleteChange(end1Change, end2Change, association.getName());
	}
	
	public Association getAssociation() {
		String from = this.from();
		String to = this.to();
		Class fromClass = getClass(from);
		Class toClass = getClass(to);
		Association association = RDNavHelper.getAssociation(fromClass, toClass, sterName);
		return association;
	}
	
	public RecordingCommand getEMFCommand() {
		Association association = getAssociation();
		if (association == null)
			throw new RuntimeException("Couldn't find association between " + to() + " and " + from());
		this.command = new RBACDeleteChangeCommand(this.toString(), association);
		return this.command;
	}
	
	private void handleKeepSter(Association association) {
		if (association.getAppliedStereotype(KeepSterHelper.KEEP) != null)
			keep = true;
		Property prop0 = association.getMemberEnds().get(0);
		Property prop1 = association.getMemberEnds().get(1);
		
		if (prop0.getOwner().getAppliedStereotype(KeepSterHelper.NO_DELETE) != null)
			keep = true;
		if (prop1.getOwner().getAppliedStereotype(KeepSterHelper.NO_DELETE) != null)
			keep = true;
		
		if (prop0.getOwner().getAppliedStereotype(KeepSterHelper.NO_DEL_ROLE) != null)
			if (prop1.getOwner().getAppliedStereotype(RDSterHelper.ROLE) != null)
				keep = true;
		if (prop1.getOwner().getAppliedStereotype(KeepSterHelper.NO_DEL_ROLE) != null)
			if (prop0.getOwner().getAppliedStereotype(RDSterHelper.ROLE) != null)
				keep = true;
		
		if (prop0.getOwner().getAppliedStereotype(KeepSterHelper.NO_DEL_RESOURCE) != null)
			if (prop1.getOwner().getAppliedStereotype(RDSterHelper.RESOURCE) != null)
				keep = true;
		if (prop1.getOwner().getAppliedStereotype(KeepSterHelper.NO_DEL_RESOURCE) != null)
			if (prop0.getOwner().getAppliedStereotype(RDSterHelper.RESOURCE) != null)
				keep = true;
		
		if (prop0.getOwner().getAppliedStereotype(KeepSterHelper.NO_DEL_PERMISSION) != null)
			if (prop1.getOwner().getAppliedStereotype(RDSterHelper.PERMISSION) != null)
				keep = true;
		if (prop1.getOwner().getAppliedStereotype(KeepSterHelper.NO_DEL_PERMISSION) != null)
			if (prop0.getOwner().getAppliedStereotype(RDSterHelper.PERMISSION) != null)
				keep = true;
	}

	public void notify(String name, RDEltAddChange rdEltAddChange) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isUndone() {
		if (RDNavHelper.getAssociation(getClass(from()), getClass(to()), sterName) == null)
			return false;
		return true;
	}
	
	public Command getUndoCommand() {
		return new RBACAdditionChangeCommand(this, getClass(from()), getClass(to()), stereotype, "undo association delete", getClass(from()).getOwner(), UMLElementTypes.BIDIRECTIONAL_ASSOCIATION);
	}
}
