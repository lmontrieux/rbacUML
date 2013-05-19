package uk.ac.open.rbacuml.dsml.plugin.providers;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gef.EditPart;

import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationType;

import uk.ac.open.rbacuml.dsml.plugin.types.RbacDSMLElementTypes;

/**
 * @generated
 */
public class RbacDSMLEditPolicyProvider extends AbstractProvider
	implements IEditPolicyProvider {

    /**
     * @generated
     */
	private final static String PROFILE_ASSOCIATIONS_SEMANTIC_ROLE = "ProfileAssociationsSemanticRole"; //$NON-NLS-1$
	
	/**
 	 * @generated
     */
	public void createEditPolicies(EditPart editPart) {
    editPart.installEditPolicy(PROFILE_ASSOCIATIONS_SEMANTIC_ROLE,
      new RbacDSMLAssociationEditPolicy());
  }

	/**
 	 * @generated
     */	
	public boolean provides(IOperation operation) {
    if (operation instanceof CreateEditPoliciesOperation) {
      EditPart ep = ((CreateEditPoliciesOperation) operation)
        .getEditPart();
      
      if (ep instanceof IGraphicalEditPart) {
        IGraphicalEditPart gep = (IGraphicalEditPart)ep;
        EObject element = gep.getNotationView().getElement();
        if (element != null) {
          for (IElementType elementType : RbacDSMLElementTypes.NODE_TYPES) {
            if (elementType instanceof ISpecializationType) {
              if (((ISpecializationType)elementType).getMatcher().matches(element)) {
                return true;
              }
            }
          }
        }
      }
    }
    return false;
  }
}

