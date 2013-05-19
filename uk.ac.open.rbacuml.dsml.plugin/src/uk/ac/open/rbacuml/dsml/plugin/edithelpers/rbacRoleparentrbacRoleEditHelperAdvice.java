 package uk.ac.open.rbacuml.dsml.plugin.edithelpers;

import com.ibm.xtools.uml.type.IStereotypedElementType;

import java.util.Collections;

import org.eclipse.core.commands.ExecutionException;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;

import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import org.eclipse.gmf.runtime.emf.type.core.commands.GetEditContextCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;

import uk.ac.open.rbacuml.dsml.plugin.types.RbacDSMLElementTypes;

/**
 * @generated
 */
public class rbacRoleparentrbacRoleEditHelperAdvice extends
        RbacDSMLBaseEditHelperAdvice {


    /**
     * @generated
     */
    public ICommand getBeforeEditContextCommand(GetEditContextRequest request) {
        request.setEditContext(RbacDSMLElementTypes.RBACROLE_PARENT);		
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
			IStereotypedElementType sourceType =(IStereotypedElementType)RbacDSMLElementTypes
		    	.getMatchingSource(request.getElementType(), source);	 
			Stereotype srcStereotype = source.getApplicableStereotype(sourceType.getStereotypeName());
			EObject sApp = source.getStereotypeApplication(srcStereotype);
			
            if (target instanceof Element) {
                IElementType targetType =(IElementType)RbacDSMLElementTypes
                    .getMatchingTarget(request.getElementType(), (Element)target);
                Stereotype targetStereotype = ((Element)target).getApplicableStereotype(((IStereotypedElementType)targetType).getStereotypeName());
                target = ((Element)target).getStereotypeApplication(targetStereotype);   
            }     
			
			Object value = sApp.eGet(sApp.eClass().getEStructuralFeature("parent")); //$NON-NLS-1$
			if (value != null) {
            	if (((EList)value).contains(target)) {			
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
                IStereotypedElementType sourceType =(IStereotypedElementType)RbacDSMLElementTypes
                        .getMatchingSource(request.getElementType(), request.getSource());
                Stereotype stereotype = source.getApplicableStereotype(sourceType.getStereotypeName());
                EObject sApp = source.getStereotypeApplication(stereotype);
 				EObject target = request.getTarget();
                
                if (target instanceof Element) {
                    IElementType targetType =(IElementType)RbacDSMLElementTypes
                        .getMatchingTarget(request.getElementType(), target);
                    Stereotype targetStereotype = ((Element)target).getApplicableStereotype(((IStereotypedElementType)targetType).getStereotypeName());
                    target = ((Element)target).getStereotypeApplication(targetStereotype);   
                }     
                ((EList)sApp.eGet(sApp.eClass().getEStructuralFeature("parent"))).add(target); //$NON-NLS-1$
                return CommandResult.newOKCommandResult();
            }
            
        };
    }
    
}
