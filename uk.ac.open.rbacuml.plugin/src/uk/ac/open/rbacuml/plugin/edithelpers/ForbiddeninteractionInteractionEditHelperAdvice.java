 package uk.ac.open.rbacuml.plugin.edithelpers;

import com.ibm.xtools.uml.type.IStereotypedElementType;

import java.util.Collections;

import org.eclipse.core.commands.ExecutionException;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;

import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

import org.eclipse.gmf.runtime.emf.type.core.commands.GetEditContextCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;

import uk.ac.open.rbacuml.plugin.types.RbacUMLElementTypes;

/**
 * @generated
 */
public class ForbiddeninteractionInteractionEditHelperAdvice extends
        RbacUMLBaseEditHelperAdvice {


    /**
     * @generated
     */
    public ICommand getBeforeEditContextCommand(GetEditContextRequest request) {
        request.setEditContext(RbacUMLElementTypes.FORBIDDEN_INTERACTION);		
        return super.getBeforeEditContextCommand(request);
    }
    
    /**
     * @generated
     */
    protected ICommand getAfterEditContextCommand(GetEditContextRequest request) {
        return new GetEditContextCommand(request);
    }
    
    /**
     * @generated
     */
    protected ICommand getBeforeCreateRelationshipCommand(
            final CreateRelationshipRequest request) {
            
            
        Element source = (Element)request.getSource();
    	Object target = request.getTarget();

		// Ensure that association doesn't already exist
		if (source != null && target != null) {
			IStereotypedElementType sourceType =(IStereotypedElementType)RbacUMLElementTypes
		    	.getMatchingSource(request.getElementType(), source);	 
			Stereotype srcStereotype = source.getApplicableStereotype(sourceType.getStereotypeName());
			EObject sApp = source.getStereotypeApplication(srcStereotype);
			
			
			Object value = sApp.eGet(sApp.eClass().getEStructuralFeature("interaction")); //$NON-NLS-1$
			if (value != null) {
            	if (value.equals(target)) {			
					return UnexecutableCommand.INSTANCE;
				}
			}
		}
            
            
        return new AbstractTransactionalCommand(request.getEditingDomain(),
                request.getLabel(), Collections.EMPTY_LIST) {

            /**
             * @generated
             */
            protected CommandResult doExecuteWithResult(
                    IProgressMonitor monitor, IAdaptable info)
                    throws ExecutionException {
                Element source = (Element)request.getSource();
                IStereotypedElementType sourceType =(IStereotypedElementType)RbacUMLElementTypes
                        .getMatchingSource(request.getElementType(), request.getSource());
                Stereotype stereotype = source.getApplicableStereotype(sourceType.getStereotypeName());
                EObject sApp = source.getStereotypeApplication(stereotype);
 				EObject target = request.getTarget();
                
                sApp.eSet(sApp.eClass().getEStructuralFeature("interaction"), target); //$NON-NLS-1$
                return CommandResult.newOKCommandResult();
            }
            
        };
    }
    
}
