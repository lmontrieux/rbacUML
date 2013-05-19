package uk.ac.open.rbacuml.dsml.plugin.research.fixing.change;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Stereotype;

import com.ibm.xtools.modeler.ui.UMLModeler;
import com.ibm.xtools.uml.core.type.UMLElementTypes;

public class RBACAdditionChangeCommand extends RecordingCommand {
	private Class from, to;
	private Stereotype stereotype;
	private IElementType type;
	private Logger log = Logger.getLogger(this.getClass());
	private Change change;
	
	public RBACAdditionChangeCommand(Change change, Class from, Class to, Stereotype stereotype,
			String label, EObject container, IElementType type) {
		super(UMLModeler.getEditingDomain(), label);
		this.change = change;
		this.from = from;
		this.to = to;
		this.stereotype = stereotype;
		this.type = type;
	}

	@Override
	protected void doExecute() {
		assert(type.equals(UMLElementTypes.BIDIRECTIONAL_ASSOCIATION));
		Association association = from.createAssociation(true, AggregationKind.NONE_LITERAL, 
				StereotypeAssociationChange.getNextProperty(), 1, 1, 
				to, true, AggregationKind.NONE_LITERAL, StereotypeAssociationChange.getNextProperty(), 
				1, 1);
		if (stereotype != null)
			association.applyStereotype(stereotype);
		if (this.change instanceof StereotypeAssociationAddChange) {
			((StereotypeAssociationAddChange)this.change).setElement(association);
		}
	}
}
