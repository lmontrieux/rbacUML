package uk.ac.open.rbacuml.dsml.plugin.popup.actions;

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

import uk.ac.open.rbacuml.dsml.plugin.l10n.RbacDSMLMessages;

import uk.ac.open.rbacuml.dsml.plugin.types.RbacDSMLElementTypes;

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
		
        if (actionId.equals("_Permission__ExecutionEnvironment")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._PERMISSION__EXECUTIONENVIRONMENT;
       			}
        		
        else if (actionId.equals("_Forbidden__ProtocolStateMachine")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._FORBIDDEN__PROTOCOLSTATEMACHINE;
       			}
        		
        else if (actionId.equals("_Granted__Node")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._GRANTED__NODE;
       			}
        		
        else if (actionId.equals("_Granted__Stereotype")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._GRANTED__STEREOTYPE;
       			}
        		
        else if (actionId.equals("_Forbidden__Activity")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._FORBIDDEN__ACTIVITY;
       			}
        		
        else if (actionId.equals("_rbacRole__Stereotype")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RBACROLE__STEREOTYPE;
       			}
        		
        else if (actionId.equals("_User__Component")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._USER__COMPONENT;
       			}
        		
        else if (actionId.equals("_User__Node")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._USER__NODE;
       			}
        		
        else if (actionId.equals("_rbacRole__ExecutionEnvironment")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RBACROLE__EXECUTIONENVIRONMENT;
       			}
        		
        else if (actionId.equals("_rbacRole__Node")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RBACROLE__NODE;
       			}
        		
        else if (actionId.equals("_Granted__Activity")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._GRANTED__ACTIVITY;
       			}
        		
        else if (actionId.equals("_User__Interaction")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._USER__INTERACTION;
       			}
        		
        else if (actionId.equals("_Forbidden__Stereotype")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._FORBIDDEN__STEREOTYPE;
       			}
        		
        else if (actionId.equals("_User__ProtocolStateMachine")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._USER__PROTOCOLSTATEMACHINE;
       			}
        		
        else if (actionId.equals("_Resource__Stereotype")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RESOURCE__STEREOTYPE;
       			}
        		
        else if (actionId.equals("_Resource__Device")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RESOURCE__DEVICE;
       			}
        		
        else if (actionId.equals("_Resource__Component")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RESOURCE__COMPONENT;
       			}
        		
        else if (actionId.equals("_Granted__OpaqueBehavior")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._GRANTED__OPAQUEBEHAVIOR;
       			}
        		
        else if (actionId.equals("_Forbidden__Node")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._FORBIDDEN__NODE;
       			}
        		
        else if (actionId.equals("_Permission__Component")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._PERMISSION__COMPONENT;
       			}
        		
        else if (actionId.equals("_User__Device")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._USER__DEVICE;
       			}
        		
        else if (actionId.equals("_Resource__Activity")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RESOURCE__ACTIVITY;
       			}
        		
        else if (actionId.equals("_User__ExecutionEnvironment")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._USER__EXECUTIONENVIRONMENT;
       			}
        		
        else if (actionId.equals("_Granted__Interaction")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._GRANTED__INTERACTION;
       			}
        		
        else if (actionId.equals("_Granted__ProtocolStateMachine")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._GRANTED__PROTOCOLSTATEMACHINE;
       			}
        		
        else if (actionId.equals("_rbacRole__OpaqueBehavior")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RBACROLE__OPAQUEBEHAVIOR;
       			}
        		
        else if (actionId.equals("_Forbidden__Class")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._FORBIDDEN__CLASS;
       			}
        		
        else if (actionId.equals("_Granted__Device")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._GRANTED__DEVICE;
       			}
        		
        else if (actionId.equals("_User__Activity")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._USER__ACTIVITY;
       			}
        		
        else if (actionId.equals("_Forbidden__FunctionBehavior")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._FORBIDDEN__FUNCTIONBEHAVIOR;
       			}
        		
        else if (actionId.equals("_Permission__Stereotype")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._PERMISSION__STEREOTYPE;
       			}
        		
        else if (actionId.equals("_rbacRole__Activity")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RBACROLE__ACTIVITY;
       			}
        		
        else if (actionId.equals("_Forbidden__Component")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._FORBIDDEN__COMPONENT;
       			}
        		
        else if (actionId.equals("_User__FunctionBehavior")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._USER__FUNCTIONBEHAVIOR;
       			}
        		
        else if (actionId.equals("_Permission__OpaqueBehavior")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._PERMISSION__OPAQUEBEHAVIOR;
       			}
        		
        else if (actionId.equals("_Permission__StateMachine")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._PERMISSION__STATEMACHINE;
       			}
        		
        else if (actionId.equals("_Resource__OpaqueBehavior")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RESOURCE__OPAQUEBEHAVIOR;
       			}
        		
        else if (actionId.equals("_Granted__StateMachine")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._GRANTED__STATEMACHINE;
       			}
        		
        else if (actionId.equals("_Permission__FunctionBehavior")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._PERMISSION__FUNCTIONBEHAVIOR;
       			}
        		
        else if (actionId.equals("_Permission__Node")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._PERMISSION__NODE;
       			}
        		
        else if (actionId.equals("_rbacRole__Component")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RBACROLE__COMPONENT;
       			}
        		
        else if (actionId.equals("_Permission__ProtocolStateMachine")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._PERMISSION__PROTOCOLSTATEMACHINE;
       			}
        		
        else if (actionId.equals("_User__OpaqueBehavior")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._USER__OPAQUEBEHAVIOR;
       			}
        		
        else if (actionId.equals("_Permission__Activity")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._PERMISSION__ACTIVITY;
       			}
        		
        else if (actionId.equals("_Resource__Class")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RESOURCE__CLASS;
       			}
        		
        else if (actionId.equals("_rbacRole__ProtocolStateMachine")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RBACROLE__PROTOCOLSTATEMACHINE;
       			}
        		
        else if (actionId.equals("_Forbidden__StateMachine")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._FORBIDDEN__STATEMACHINE;
       			}
        		
        else if (actionId.equals("_Resource__ProtocolStateMachine")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RESOURCE__PROTOCOLSTATEMACHINE;
       			}
        		
        else if (actionId.equals("_Granted__Component")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._GRANTED__COMPONENT;
       			}
        		
        else if (actionId.equals("_rbacRole__Device")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RBACROLE__DEVICE;
       			}
        		
        else if (actionId.equals("_Permission__Interaction")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._PERMISSION__INTERACTION;
       			}
        		
        else if (actionId.equals("_rbacRole__Class")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RBACROLE__CLASS;
       			}
        		
        else if (actionId.equals("_User__Stereotype")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._USER__STEREOTYPE;
       			}
        		
        else if (actionId.equals("_Granted__ExecutionEnvironment")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._GRANTED__EXECUTIONENVIRONMENT;
       			}
        		
        else if (actionId.equals("_Resource__Interaction")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RESOURCE__INTERACTION;
       			}
        		
        else if (actionId.equals("_rbacRole__FunctionBehavior")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RBACROLE__FUNCTIONBEHAVIOR;
       			}
        		
        else if (actionId.equals("_Forbidden__Interaction")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._FORBIDDEN__INTERACTION;
       			}
        		
        else if (actionId.equals("_Granted__Class")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._GRANTED__CLASS;
       			}
        		
        else if (actionId.equals("_rbacRole__StateMachine")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RBACROLE__STATEMACHINE;
       			}
        		
        else if (actionId.equals("_User__Class")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._USER__CLASS;
       			}
        		
        else if (actionId.equals("_Forbidden__ExecutionEnvironment")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._FORBIDDEN__EXECUTIONENVIRONMENT;
       			}
        		
        else if (actionId.equals("_Forbidden__OpaqueBehavior")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._FORBIDDEN__OPAQUEBEHAVIOR;
       			}
        		
        else if (actionId.equals("_Resource__Node")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RESOURCE__NODE;
       			}
        		
        else if (actionId.equals("_Resource__FunctionBehavior")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RESOURCE__FUNCTIONBEHAVIOR;
       			}
        		
        else if (actionId.equals("_Granted__FunctionBehavior")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._GRANTED__FUNCTIONBEHAVIOR;
       			}
        		
        else if (actionId.equals("_Resource__ExecutionEnvironment")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RESOURCE__EXECUTIONENVIRONMENT;
       			}
        		
        else if (actionId.equals("_rbacRole__Interaction")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RBACROLE__INTERACTION;
       			}
        		
        else if (actionId.equals("_Permission__Class")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._PERMISSION__CLASS;
       			}
        		
        else if (actionId.equals("_Resource__StateMachine")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._RESOURCE__STATEMACHINE;
       			}
        		
        else if (actionId.equals("_Forbidden__Device")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._FORBIDDEN__DEVICE;
       			}
        		
        else if (actionId.equals("_Permission__Device")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._PERMISSION__DEVICE;
       			}
        		
        else if (actionId.equals("_User__StateMachine")) { //$NON-NLS-1$
        	return RbacDSMLElementTypes._USER__STATEMACHINE;
       			}
        		
        
        else {
        	uk.ac.open.rbacuml.dsml.plugin.Activator.getDefault().getLog().log(new Status(IStatus.ERROR,
        		uk.ac.open.rbacuml.dsml.plugin.Activator.PLUGIN_ID, -1,
        		RbacDSMLMessages.Error_Cannot_Get_Element_Type + StringStatics.SPACE + actionId, null));
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