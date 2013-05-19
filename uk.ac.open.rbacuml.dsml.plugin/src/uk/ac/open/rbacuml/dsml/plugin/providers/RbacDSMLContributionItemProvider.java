package uk.ac.open.rbacuml.dsml.plugin.providers;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.emf.transaction.util.TransactionUtil;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

import org.eclipse.gef.commands.Command;

import org.eclipse.gef.requests.CreateRequest;

import org.eclipse.gmf.runtime.common.ui.action.ActionMenuManager;

import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;

import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;

import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;

import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;

import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;

import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;

import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;

import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import org.eclipse.gmf.runtime.emf.type.ui.ElementTypeImageDescriptor;

import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;

import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.jface.viewers.StructuredSelection;

import org.eclipse.swt.widgets.Display;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

import org.eclipse.uml2.uml.util.UMLUtil;

import uk.ac.open.rbacuml.dsml.plugin.l10n.RbacDSMLMessages;

import uk.ac.open.rbacuml.dsml.plugin.types.RbacDSMLElementTypes;

import uk.ac.open.rbacuml.dsml.plugin.utils.RbacDSMLUtil;

/**
 * @generated
 */
public class RbacDSMLContributionItemProvider
        extends AbstractContributionItemProvider {
    
    /**
     * @generated
     */
    private static class MenuAction extends Action {
        public MenuAction(String text) {
            setText(text);
        }
    }
    
    /**
     * @generated
     */
    protected IMenuManager createMenuManager(
            String menuId,
            IWorkbenchPartDescriptor partDescriptor) {
        if (menuId.equals("Add_rbacDSML")) //$NON-NLS-1$
            return new ActionMenuManager("Add_rbacDSML", //$NON-NLS-1$
                    new MenuAction(RbacDSMLMessages.MenuManager_AddAction_text),
                    true);
        return super.createMenuManager(menuId, partDescriptor);
    }   

    /**
     * @generated
     */
    protected IAction createAction(
            String actionId,
            IWorkbenchPartDescriptor partDescriptor) {

        IWorkbenchPage workbenchPage = partDescriptor.getPartPage();
        IEditorPart editorPart = workbenchPage.getActiveEditor();
        if (editorPart instanceof IDiagramWorkbenchPart) {
            ResourceSet resourceSet =
                TransactionUtil.getEditingDomain(
                        ((IDiagramWorkbenchPart)editorPart).getDiagram()).getResourceSet();
            if (actionId.equals("_Permission__ExecutionEnvironment")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._PERMISSION__EXECUTIONENVIRONMENT,
                    "_Permission__ExecutionEnvironment", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Permission__ExecutionEnvironment_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__EXECUTIONENVIRONMENT, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__ProtocolStateMachine")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._FORBIDDEN__PROTOCOLSTATEMACHINE,
                    "_Forbidden__ProtocolStateMachine", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Forbidden__ProtocolStateMachine_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__PROTOCOLSTATEMACHINE, resourceSet));                    
            }
            else if (actionId.equals("_Granted__Node")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._GRANTED__NODE,
                    "_Granted__Node", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Granted__Node_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__NODE, resourceSet));                    
            }
            else if (actionId.equals("_Granted__Stereotype")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._GRANTED__STEREOTYPE,
                    "_Granted__Stereotype", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Granted__Stereotype_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__STEREOTYPE, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__Activity")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._FORBIDDEN__ACTIVITY,
                    "_Forbidden__Activity", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Forbidden__Activity_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__ACTIVITY, resourceSet));                    
            }
            else if (actionId.equals("_rbacRole__Stereotype")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RBACROLE__STEREOTYPE,
                    "_rbacRole__Stereotype", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__rbacRole__Stereotype_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__STEREOTYPE, resourceSet));                    
            }
            else if (actionId.equals("_User__Component")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._USER__COMPONENT,
                    "_User__Component", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__User__Component_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__COMPONENT, resourceSet));                    
            }
            else if (actionId.equals("_User__Node")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._USER__NODE,
                    "_User__Node", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__User__Node_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__NODE, resourceSet));                    
            }
            else if (actionId.equals("_rbacRole__ExecutionEnvironment")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RBACROLE__EXECUTIONENVIRONMENT,
                    "_rbacRole__ExecutionEnvironment", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__rbacRole__ExecutionEnvironment_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__EXECUTIONENVIRONMENT, resourceSet));                    
            }
            else if (actionId.equals("_rbacRole__Node")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RBACROLE__NODE,
                    "_rbacRole__Node", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__rbacRole__Node_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__NODE, resourceSet));                    
            }
            else if (actionId.equals("_Granted__Activity")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._GRANTED__ACTIVITY,
                    "_Granted__Activity", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Granted__Activity_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__ACTIVITY, resourceSet));                    
            }
            else if (actionId.equals("_User__Interaction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._USER__INTERACTION,
                    "_User__Interaction", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__User__Interaction_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__INTERACTION, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__Stereotype")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._FORBIDDEN__STEREOTYPE,
                    "_Forbidden__Stereotype", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Forbidden__Stereotype_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__STEREOTYPE, resourceSet));                    
            }
            else if (actionId.equals("_User__ProtocolStateMachine")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._USER__PROTOCOLSTATEMACHINE,
                    "_User__ProtocolStateMachine", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__User__ProtocolStateMachine_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__PROTOCOLSTATEMACHINE, resourceSet));                    
            }
            else if (actionId.equals("_Resource__Stereotype")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RESOURCE__STEREOTYPE,
                    "_Resource__Stereotype", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Resource__Stereotype_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__STEREOTYPE, resourceSet));                    
            }
            else if (actionId.equals("_Resource__Device")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RESOURCE__DEVICE,
                    "_Resource__Device", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Resource__Device_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__DEVICE, resourceSet));                    
            }
            else if (actionId.equals("_Resource__Component")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RESOURCE__COMPONENT,
                    "_Resource__Component", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Resource__Component_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__COMPONENT, resourceSet));                    
            }
            else if (actionId.equals("_Granted__OpaqueBehavior")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._GRANTED__OPAQUEBEHAVIOR,
                    "_Granted__OpaqueBehavior", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Granted__OpaqueBehavior_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__OPAQUEBEHAVIOR, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__Node")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._FORBIDDEN__NODE,
                    "_Forbidden__Node", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Forbidden__Node_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__NODE, resourceSet));                    
            }
            else if (actionId.equals("_Permission__Component")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._PERMISSION__COMPONENT,
                    "_Permission__Component", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Permission__Component_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__COMPONENT, resourceSet));                    
            }
            else if (actionId.equals("_User__Device")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._USER__DEVICE,
                    "_User__Device", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__User__Device_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__DEVICE, resourceSet));                    
            }
            else if (actionId.equals("_Resource__Activity")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RESOURCE__ACTIVITY,
                    "_Resource__Activity", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Resource__Activity_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__ACTIVITY, resourceSet));                    
            }
            else if (actionId.equals("_User__ExecutionEnvironment")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._USER__EXECUTIONENVIRONMENT,
                    "_User__ExecutionEnvironment", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__User__ExecutionEnvironment_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__EXECUTIONENVIRONMENT, resourceSet));                    
            }
            else if (actionId.equals("_Granted__Interaction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._GRANTED__INTERACTION,
                    "_Granted__Interaction", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Granted__Interaction_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__INTERACTION, resourceSet));                    
            }
            else if (actionId.equals("_Granted__ProtocolStateMachine")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._GRANTED__PROTOCOLSTATEMACHINE,
                    "_Granted__ProtocolStateMachine", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Granted__ProtocolStateMachine_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__PROTOCOLSTATEMACHINE, resourceSet));                    
            }
            else if (actionId.equals("_rbacRole__OpaqueBehavior")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RBACROLE__OPAQUEBEHAVIOR,
                    "_rbacRole__OpaqueBehavior", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__rbacRole__OpaqueBehavior_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__OPAQUEBEHAVIOR, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__Class")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._FORBIDDEN__CLASS,
                    "_Forbidden__Class", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Forbidden__Class_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__CLASS, resourceSet));                    
            }
            else if (actionId.equals("_Granted__Device")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._GRANTED__DEVICE,
                    "_Granted__Device", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Granted__Device_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__DEVICE, resourceSet));                    
            }
            else if (actionId.equals("_User__Activity")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._USER__ACTIVITY,
                    "_User__Activity", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__User__Activity_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__ACTIVITY, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__FunctionBehavior")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._FORBIDDEN__FUNCTIONBEHAVIOR,
                    "_Forbidden__FunctionBehavior", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Forbidden__FunctionBehavior_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__FUNCTIONBEHAVIOR, resourceSet));                    
            }
            else if (actionId.equals("_Permission__Stereotype")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._PERMISSION__STEREOTYPE,
                    "_Permission__Stereotype", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Permission__Stereotype_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__STEREOTYPE, resourceSet));                    
            }
            else if (actionId.equals("_rbacRole__Activity")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RBACROLE__ACTIVITY,
                    "_rbacRole__Activity", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__rbacRole__Activity_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__ACTIVITY, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__Component")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._FORBIDDEN__COMPONENT,
                    "_Forbidden__Component", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Forbidden__Component_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__COMPONENT, resourceSet));                    
            }
            else if (actionId.equals("_User__FunctionBehavior")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._USER__FUNCTIONBEHAVIOR,
                    "_User__FunctionBehavior", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__User__FunctionBehavior_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__FUNCTIONBEHAVIOR, resourceSet));                    
            }
            else if (actionId.equals("_Permission__OpaqueBehavior")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._PERMISSION__OPAQUEBEHAVIOR,
                    "_Permission__OpaqueBehavior", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Permission__OpaqueBehavior_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__OPAQUEBEHAVIOR, resourceSet));                    
            }
            else if (actionId.equals("_Permission__StateMachine")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._PERMISSION__STATEMACHINE,
                    "_Permission__StateMachine", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Permission__StateMachine_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__STATEMACHINE, resourceSet));                    
            }
            else if (actionId.equals("_Resource__OpaqueBehavior")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RESOURCE__OPAQUEBEHAVIOR,
                    "_Resource__OpaqueBehavior", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Resource__OpaqueBehavior_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__OPAQUEBEHAVIOR, resourceSet));                    
            }
            else if (actionId.equals("_Granted__StateMachine")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._GRANTED__STATEMACHINE,
                    "_Granted__StateMachine", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Granted__StateMachine_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__STATEMACHINE, resourceSet));                    
            }
            else if (actionId.equals("_Permission__FunctionBehavior")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._PERMISSION__FUNCTIONBEHAVIOR,
                    "_Permission__FunctionBehavior", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Permission__FunctionBehavior_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__FUNCTIONBEHAVIOR, resourceSet));                    
            }
            else if (actionId.equals("_Permission__Node")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._PERMISSION__NODE,
                    "_Permission__Node", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Permission__Node_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__NODE, resourceSet));                    
            }
            else if (actionId.equals("_rbacRole__Component")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RBACROLE__COMPONENT,
                    "_rbacRole__Component", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__rbacRole__Component_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__COMPONENT, resourceSet));                    
            }
            else if (actionId.equals("_Permission__ProtocolStateMachine")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._PERMISSION__PROTOCOLSTATEMACHINE,
                    "_Permission__ProtocolStateMachine", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Permission__ProtocolStateMachine_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__PROTOCOLSTATEMACHINE, resourceSet));                    
            }
            else if (actionId.equals("_User__OpaqueBehavior")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._USER__OPAQUEBEHAVIOR,
                    "_User__OpaqueBehavior", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__User__OpaqueBehavior_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__OPAQUEBEHAVIOR, resourceSet));                    
            }
            else if (actionId.equals("_Permission__Activity")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._PERMISSION__ACTIVITY,
                    "_Permission__Activity", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Permission__Activity_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__ACTIVITY, resourceSet));                    
            }
            else if (actionId.equals("_Resource__Class")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RESOURCE__CLASS,
                    "_Resource__Class", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Resource__Class_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__CLASS, resourceSet));                    
            }
            else if (actionId.equals("_rbacRole__ProtocolStateMachine")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RBACROLE__PROTOCOLSTATEMACHINE,
                    "_rbacRole__ProtocolStateMachine", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__rbacRole__ProtocolStateMachine_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__PROTOCOLSTATEMACHINE, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__StateMachine")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._FORBIDDEN__STATEMACHINE,
                    "_Forbidden__StateMachine", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Forbidden__StateMachine_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__STATEMACHINE, resourceSet));                    
            }
            else if (actionId.equals("_Resource__ProtocolStateMachine")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RESOURCE__PROTOCOLSTATEMACHINE,
                    "_Resource__ProtocolStateMachine", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Resource__ProtocolStateMachine_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__PROTOCOLSTATEMACHINE, resourceSet));                    
            }
            else if (actionId.equals("_Granted__Component")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._GRANTED__COMPONENT,
                    "_Granted__Component", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Granted__Component_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__COMPONENT, resourceSet));                    
            }
            else if (actionId.equals("_rbacRole__Device")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RBACROLE__DEVICE,
                    "_rbacRole__Device", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__rbacRole__Device_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__DEVICE, resourceSet));                    
            }
            else if (actionId.equals("_Permission__Interaction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._PERMISSION__INTERACTION,
                    "_Permission__Interaction", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Permission__Interaction_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__INTERACTION, resourceSet));                    
            }
            else if (actionId.equals("_rbacRole__Class")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RBACROLE__CLASS,
                    "_rbacRole__Class", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__rbacRole__Class_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__CLASS, resourceSet));                    
            }
            else if (actionId.equals("_User__Stereotype")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._USER__STEREOTYPE,
                    "_User__Stereotype", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__User__Stereotype_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__STEREOTYPE, resourceSet));                    
            }
            else if (actionId.equals("_Granted__ExecutionEnvironment")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._GRANTED__EXECUTIONENVIRONMENT,
                    "_Granted__ExecutionEnvironment", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Granted__ExecutionEnvironment_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__EXECUTIONENVIRONMENT, resourceSet));                    
            }
            else if (actionId.equals("_Resource__Interaction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RESOURCE__INTERACTION,
                    "_Resource__Interaction", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Resource__Interaction_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__INTERACTION, resourceSet));                    
            }
            else if (actionId.equals("_rbacRole__FunctionBehavior")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RBACROLE__FUNCTIONBEHAVIOR,
                    "_rbacRole__FunctionBehavior", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__rbacRole__FunctionBehavior_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__FUNCTIONBEHAVIOR, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__Interaction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._FORBIDDEN__INTERACTION,
                    "_Forbidden__Interaction", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Forbidden__Interaction_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__INTERACTION, resourceSet));                    
            }
            else if (actionId.equals("_Granted__Class")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._GRANTED__CLASS,
                    "_Granted__Class", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Granted__Class_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__CLASS, resourceSet));                    
            }
            else if (actionId.equals("_rbacRole__StateMachine")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RBACROLE__STATEMACHINE,
                    "_rbacRole__StateMachine", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__rbacRole__StateMachine_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__STATEMACHINE, resourceSet));                    
            }
            else if (actionId.equals("_User__Class")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._USER__CLASS,
                    "_User__Class", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__User__Class_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__CLASS, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__ExecutionEnvironment")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._FORBIDDEN__EXECUTIONENVIRONMENT,
                    "_Forbidden__ExecutionEnvironment", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Forbidden__ExecutionEnvironment_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__EXECUTIONENVIRONMENT, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__OpaqueBehavior")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._FORBIDDEN__OPAQUEBEHAVIOR,
                    "_Forbidden__OpaqueBehavior", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Forbidden__OpaqueBehavior_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__OPAQUEBEHAVIOR, resourceSet));                    
            }
            else if (actionId.equals("_Resource__Node")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RESOURCE__NODE,
                    "_Resource__Node", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Resource__Node_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__NODE, resourceSet));                    
            }
            else if (actionId.equals("_Resource__FunctionBehavior")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RESOURCE__FUNCTIONBEHAVIOR,
                    "_Resource__FunctionBehavior", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Resource__FunctionBehavior_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__FUNCTIONBEHAVIOR, resourceSet));                    
            }
            else if (actionId.equals("_Granted__FunctionBehavior")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._GRANTED__FUNCTIONBEHAVIOR,
                    "_Granted__FunctionBehavior", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Granted__FunctionBehavior_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__FUNCTIONBEHAVIOR, resourceSet));                    
            }
            else if (actionId.equals("_Resource__ExecutionEnvironment")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RESOURCE__EXECUTIONENVIRONMENT,
                    "_Resource__ExecutionEnvironment", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Resource__ExecutionEnvironment_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__EXECUTIONENVIRONMENT, resourceSet));                    
            }
            else if (actionId.equals("_rbacRole__Interaction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RBACROLE__INTERACTION,
                    "_rbacRole__Interaction", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__rbacRole__Interaction_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__INTERACTION, resourceSet));                    
            }
            else if (actionId.equals("_Permission__Class")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._PERMISSION__CLASS,
                    "_Permission__Class", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Permission__Class_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__CLASS, resourceSet));                    
            }
            else if (actionId.equals("_Resource__StateMachine")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._RESOURCE__STATEMACHINE,
                    "_Resource__StateMachine", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Resource__StateMachine_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__STATEMACHINE, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__Device")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._FORBIDDEN__DEVICE,
                    "_Forbidden__Device", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Forbidden__Device_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__DEVICE, resourceSet));                    
            }
            else if (actionId.equals("_Permission__Device")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._PERMISSION__DEVICE,
                    "_Permission__Device", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__Permission__Device_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__DEVICE, resourceSet));                    
            }
            else if (actionId.equals("_User__StateMachine")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacDSMLElementTypes._USER__STATEMACHINE,
                    "_User__StateMachine", //$NON-NLS-1$
                    RbacDSMLMessages.MenuManager_CreateAction__User__StateMachine_name,
                    RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__STATEMACHINE, resourceSet));                    
            }
        }

        return super.createAction(actionId, partDescriptor);
    }

    /**
     * @generated
     */
    private class CreateElementAction
            extends DiagramAction {
    
        /**
         * @generated
         */
        private Element umlElement;
        
        /**
         * @generated
         */
        private IElementType elementType;
        
        /**
         * @generated
         */
        public CreateElementAction(
                IWorkbenchPage workbenchPage,
                String qualifiedName,
                String id,
                String label,
                ImageDescriptor imageDescriptor) {
                super(workbenchPage);
    
           setId(id);
           setText(label);
           setToolTipText(label);
                
           assert workbenchPage.getActiveEditor() instanceof DiagramEditor;
           DiagramEditor editor = (DiagramEditor) workbenchPage.getActiveEditor();
                
           Collection<? extends NamedElement> elements = UMLUtil.findNamedElements(editor.getDiagram().eResource().getResourceSet(), qualifiedName);
           if (elements.size() > 0) {
               this.umlElement = (Element) elements.iterator().next();
           }
                
           if (imageDescriptor == null) {
               setImageDescriptor(new ElementTypeImageDescriptor(ElementTypeRegistry.getInstance().getElementType(umlElement)));
           }
         else {
             setImageDescriptor(imageDescriptor);
         }
        }
        
        /**
         * @generated
         */
        public CreateElementAction(
                IWorkbenchPage workbenchPage,
                IElementType elementType,
                String id,
                String label,
                ImageDescriptor imageDescriptor) {
           super(workbenchPage);
    
           this.elementType = elementType;
    
           setId(id);
           setText(label);
           setToolTipText(label);
                
           if (imageDescriptor == null) {
             setImageDescriptor(new ElementTypeImageDescriptor(elementType));
         }
         else {
             setImageDescriptor(imageDescriptor);
         }
        }    
        
        /**
         * @generated
         */
        protected void doRun(IProgressMonitor progressMonitor) {
            Command command = getCommand();
    
            if (command != null) {
                getDiagramGraphicalViewer()
                    .getEditDomain()
                    .getCommandStack()
                    .execute(
                    command);
                selectNewObject();
            }
        }
        
        /**
         * @generated
         */
        protected Request createTargetRequest() {
            if (umlElement != null) {
                return new CreateViewRequest(new CreateViewRequest.ViewDescriptor(new EObjectAdapter(umlElement), PreferencesHint.USE_DEFAULTS));
            }
            if (elementType != null) {
                return new CreateViewAndElementRequest(elementType, getPreferencesHint());
            }
            return null;
        }
        
        /**
         * Selects the newly added shape view.
         *
         * @generated
         */
        private void selectNewObject() {
    
            final IDiagramGraphicalViewer viewer = getDiagramGraphicalViewer();
            if (viewer != null) {
            
                final Object newObject =
                    ((CreateRequest) getTargetRequest()).getNewObject();
            
                
                assert newObject instanceof Collection;
                assert ((Collection<?>) newObject).size() == 1;
                
                IAdaptable viewAdapter = (IAdaptable) ((Collection<?>) newObject).iterator().next();
                
                final EditPart editPart =
                    (EditPart) viewer.getEditPartRegistry().get(
                        viewAdapter.getAdapter(View.class));
                
                if (editPart != null) {
                    viewer.setSelection(new StructuredSelection(editPart));
                    Display.getCurrent().asyncExec(new Runnable() {
                        public void run() {
                            editPart.performRequest(
                                new Request(RequestConstants.REQ_DIRECT_EDIT));
                        }
                    });
                    
                }
            }
            
        }
        
        /**
         * @generated
         */
        protected boolean isSelectionListener() {
            return true;
        }        
    }
}