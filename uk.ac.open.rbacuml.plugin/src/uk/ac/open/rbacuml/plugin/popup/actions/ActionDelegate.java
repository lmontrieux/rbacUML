package uk.ac.open.rbacuml.plugin.popup.actions;

import com.ibm.xtools.uml.navigator.factory.UMLNavigatorConstants;

import com.ibm.xtools.uml.ui.internal.utils.NavigatorInlineEditUtil;

import org.eclipse.core.commands.operations.ICompositeOperation;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;

import org.eclipse.gmf.runtime.common.core.util.StringStatics;

import org.eclipse.gmf.runtime.common.ui.util.DisplayUtils;

import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;

import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import org.eclipse.gmf.runtime.emf.ui.action.AbstractModelActionDelegate;

import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;

import uk.ac.open.rbacuml.plugin.l10n.RbacUMLMessages;

import uk.ac.open.rbacuml.plugin.types.RbacUMLElementTypes;

/**
 * @generated
 */
public class ActionDelegate extends 
	AbstractModelActionDelegate implements IObjectActionDelegate {
	
	private ICompositeOperation openComposite;
	private CommandResult lastResult;
	
	@Override
	protected TransactionalEditingDomain getEditingDomain() {
		return MEditingDomain.INSTANCE;
	}
	
	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
	
		ICommand command = getCommand();
		
		if (command == null || !command.canExecute()) {
			// Don't log or throw an exception here because can happen when
			// the user has cancelled the action.
			return;
		}
		
		execute(command, progressMonitor, null);
		
		lastResult = command.getCommandResult();
		if (openComposite == null) {
			doPostExecutionFollowup();
		}
	}
	
	private void doPostExecutionFollowup() {
		// do the post-creation after the superclass has completed its
		// WriteCommand open composite
		if ((lastResult != null) && (lastResult.getReturnValue() instanceof EObject)) {
			EObject newElement = (EObject) lastResult.getReturnValue();
			
			if (newElement.eResource() != null) {
				postElementCreation(newElement);
			}
		}
		
		lastResult = null;
	}
	
	protected void postElementCreation(final EObject newElement) {
		// Once the new element is created, set it up for inline editing
		// of its name.	
		IWorkbenchPart part = getWorkbenchPart();
		if (part != null) {
			final IWorkbenchPartSite site = part.getSite();
			if (site != null && site.getId().equals(UMLNavigatorConstants.PROJECT_EXPLORER)) {
				
				DisplayUtils.getDisplay().asyncExec(new Runnable() {
				
					public void run() {
						NavigatorInlineEditUtil.startInlineEdit(newElement);
					}
				});
			}
		}
	}
	
	protected ICommand getCommand() {
		ICommand result = null;
		
		CreateElementRequest request = getCreateElementRequest();
		
		if (request != null) {
			IElementType contextType = ElementTypeRegistry.getInstance().getElementType(request.getEditHelperContext());
			
			if (contextType != null) {
				ICommand createCommand = contextType.getEditCommand(request);
				
				if (createCommand != null && createCommand.canExecute()) {
					result = createCommand;
				}
			}
		}
		
		return result;
	}
	
	protected CreateElementRequest getCreateElementRequest() {
	
		IElementType elementType= getElementType();
		
		if (elementType != null) {
			return new CreateElementRequest(getSelectedElement(), elementType);
		}
		return null;
	}
	
	protected IElementType getElementType() {
	
		/*
		 * The type of element to create depends on what the user
		 * clicks on.  The action ids are set up to be the same as
		 * the profile element type.
		 */
		
		String actionId = getActionId();
		
        if (actionId.equals("_RBACUser__ActivityPartition")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._RBACUSER__ACTIVITYPARTITION;
       			}
        		
        else if (actionId.equals("_DeactivateRoles__CallOperationAction")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._DEACTIVATEROLES__CALLOPERATIONACTION;
       			}
        		
        else if (actionId.equals("_DeactivateRoles__OpaqueAction")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._DEACTIVATEROLES__OPAQUEACTION;
       			}
        		
        else if (actionId.equals("_ActivateRoles__OpaqueAction")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._ACTIVATEROLES__OPAQUEACTION;
       			}
        		
        else if (actionId.equals("_RBACUser__Class")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._RBACUSER__CLASS;
       			}
        		
        else if (actionId.equals("_Permission__Class")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._PERMISSION__CLASS;
       			}
        		
        else if (actionId.equals("_RBACUser__Stereotype")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._RBACUSER__STEREOTYPE;
       			}
        		
        else if (actionId.equals("_Granted__OpaqueAction")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._GRANTED__OPAQUEACTION;
       			}
        		
        else if (actionId.equals("_ActivateRoles__CallOperationAction")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._ACTIVATEROLES__CALLOPERATIONACTION;
       			}
        		
        else if (actionId.equals("_Forbidden__CallOperationAction")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._FORBIDDEN__CALLOPERATIONACTION;
       			}
        		
        else if (actionId.equals("_Granted__CallBehaviorAction")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._GRANTED__CALLBEHAVIORACTION;
       			}
        		
        else if (actionId.equals("_Restricted__Operation")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._RESTRICTED__OPERATION;
       			}
        		
        else if (actionId.equals("_DeactivateRoles__CallBehaviorAction")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._DEACTIVATEROLES__CALLBEHAVIORACTION;
       			}
        		
        else if (actionId.equals("_Granted__CallOperationAction")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._GRANTED__CALLOPERATIONACTION;
       			}
        		
        else if (actionId.equals("_ActivateRoles__CallBehaviorAction")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._ACTIVATEROLES__CALLBEHAVIORACTION;
       			}
        		
        else if (actionId.equals("_Forbidden__CallBehaviorAction")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._FORBIDDEN__CALLBEHAVIORACTION;
       			}
        		
        else if (actionId.equals("_Forbidden__OpaqueAction")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._FORBIDDEN__OPAQUEACTION;
       			}
        		
        else if (actionId.equals("_RBACRole__Stereotype")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._RBACROLE__STEREOTYPE;
       			}
        		
        else if (actionId.equals("_RBACRole__Class")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._RBACROLE__CLASS;
       			}
        		
        else if (actionId.equals("_Permission__Stereotype")) { //$NON-NLS-1$
        	return RbacUMLElementTypes._PERMISSION__STEREOTYPE;
       			}
        		
        
        else {
        	uk.ac.open.rbacuml.plugin.Activator.getDefault().getLog().log(new Status(IStatus.ERROR,
        		uk.ac.open.rbacuml.plugin.Activator.PLUGIN_ID, -1,
        		RbacUMLMessages.Error_Cannot_Get_Element_Type + StringStatics.SPACE + actionId, null));
	        return null;
        } //else
    }
	
	protected String getActionId() {
		return getAction().getId();
	}
	
	protected EObject getSelectedElement() {
		EObject selection = (EObject) ((IAdaptable) getStructuredSelection().getFirstElement()).getAdapter(EObject.class);
		return selection;
	}

}