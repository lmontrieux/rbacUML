package uk.ac.open.rbacuml.plugin.providers;

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

import uk.ac.open.rbacuml.plugin.l10n.RbacUMLMessages;

import uk.ac.open.rbacuml.plugin.types.RbacUMLElementTypes;

import uk.ac.open.rbacuml.plugin.utils.RbacUMLUtil;

/**
 * @generated
 */
public class RbacUMLContributionItemProvider
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
        if (menuId.equals("Add_rbacUML")) //$NON-NLS-1$
            return new ActionMenuManager("Add_rbacUML", //$NON-NLS-1$
                    new MenuAction(RbacUMLMessages.MenuManager_AddAction_text),
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
            if (actionId.equals("_RBACUser__ActivityPartition")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._RBACUSER__ACTIVITYPARTITION,
                    "_RBACUser__ActivityPartition", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__RBACUser__ActivityPartition_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._RBACUSER__ACTIVITYPARTITION, resourceSet));                    
            }
            else if (actionId.equals("_DeactivateRoles__CallOperationAction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._DEACTIVATEROLES__CALLOPERATIONACTION,
                    "_DeactivateRoles__CallOperationAction", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__DeactivateRoles__CallOperationAction_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._DEACTIVATEROLES__CALLOPERATIONACTION, resourceSet));                    
            }
            else if (actionId.equals("_DeactivateRoles__OpaqueAction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._DEACTIVATEROLES__OPAQUEACTION,
                    "_DeactivateRoles__OpaqueAction", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__DeactivateRoles__OpaqueAction_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._DEACTIVATEROLES__OPAQUEACTION, resourceSet));                    
            }
            else if (actionId.equals("_ActivateRoles__OpaqueAction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._ACTIVATEROLES__OPAQUEACTION,
                    "_ActivateRoles__OpaqueAction", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__ActivateRoles__OpaqueAction_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._ACTIVATEROLES__OPAQUEACTION, resourceSet));                    
            }
            else if (actionId.equals("_RBACUser__Class")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._RBACUSER__CLASS,
                    "_RBACUser__Class", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__RBACUser__Class_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._RBACUSER__CLASS, resourceSet));                    
            }
            else if (actionId.equals("_Permission__Class")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._PERMISSION__CLASS,
                    "_Permission__Class", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__Permission__Class_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._PERMISSION__CLASS, resourceSet));                    
            }
            else if (actionId.equals("_RBACUser__Stereotype")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._RBACUSER__STEREOTYPE,
                    "_RBACUser__Stereotype", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__RBACUser__Stereotype_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._RBACUSER__STEREOTYPE, resourceSet));                    
            }
            else if (actionId.equals("_Granted__OpaqueAction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._GRANTED__OPAQUEACTION,
                    "_Granted__OpaqueAction", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__Granted__OpaqueAction_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._GRANTED__OPAQUEACTION, resourceSet));                    
            }
            else if (actionId.equals("_ActivateRoles__CallOperationAction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._ACTIVATEROLES__CALLOPERATIONACTION,
                    "_ActivateRoles__CallOperationAction", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__ActivateRoles__CallOperationAction_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._ACTIVATEROLES__CALLOPERATIONACTION, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__CallOperationAction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._FORBIDDEN__CALLOPERATIONACTION,
                    "_Forbidden__CallOperationAction", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__Forbidden__CallOperationAction_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._FORBIDDEN__CALLOPERATIONACTION, resourceSet));                    
            }
            else if (actionId.equals("_Granted__CallBehaviorAction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._GRANTED__CALLBEHAVIORACTION,
                    "_Granted__CallBehaviorAction", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__Granted__CallBehaviorAction_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._GRANTED__CALLBEHAVIORACTION, resourceSet));                    
            }
            else if (actionId.equals("_Restricted__Operation")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._RESTRICTED__OPERATION,
                    "_Restricted__Operation", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__Restricted__Operation_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._RESTRICTED__OPERATION, resourceSet));                    
            }
            else if (actionId.equals("_DeactivateRoles__CallBehaviorAction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._DEACTIVATEROLES__CALLBEHAVIORACTION,
                    "_DeactivateRoles__CallBehaviorAction", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__DeactivateRoles__CallBehaviorAction_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._DEACTIVATEROLES__CALLBEHAVIORACTION, resourceSet));                    
            }
            else if (actionId.equals("_Granted__CallOperationAction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._GRANTED__CALLOPERATIONACTION,
                    "_Granted__CallOperationAction", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__Granted__CallOperationAction_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._GRANTED__CALLOPERATIONACTION, resourceSet));                    
            }
            else if (actionId.equals("_ActivateRoles__CallBehaviorAction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._ACTIVATEROLES__CALLBEHAVIORACTION,
                    "_ActivateRoles__CallBehaviorAction", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__ActivateRoles__CallBehaviorAction_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._ACTIVATEROLES__CALLBEHAVIORACTION, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__CallBehaviorAction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._FORBIDDEN__CALLBEHAVIORACTION,
                    "_Forbidden__CallBehaviorAction", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__Forbidden__CallBehaviorAction_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._FORBIDDEN__CALLBEHAVIORACTION, resourceSet));                    
            }
            else if (actionId.equals("_Forbidden__OpaqueAction")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._FORBIDDEN__OPAQUEACTION,
                    "_Forbidden__OpaqueAction", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__Forbidden__OpaqueAction_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._FORBIDDEN__OPAQUEACTION, resourceSet));                    
            }
            else if (actionId.equals("_RBACRole__Stereotype")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._RBACROLE__STEREOTYPE,
                    "_RBACRole__Stereotype", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__RBACRole__Stereotype_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._RBACROLE__STEREOTYPE, resourceSet));                    
            }
            else if (actionId.equals("_RBACRole__Class")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._RBACROLE__CLASS,
                    "_RBACRole__Class", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__RBACRole__Class_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._RBACROLE__CLASS, resourceSet));                    
            }
            else if (actionId.equals("_Permission__Stereotype")) { //$NON-NLS-1$
                return new CreateElementAction(workbenchPage,
                    RbacUMLElementTypes._PERMISSION__STEREOTYPE,
                    "_Permission__Stereotype", //$NON-NLS-1$
                    RbacUMLMessages.MenuManager_CreateAction__Permission__Stereotype_name,
                    RbacUMLUtil.getSmallImage(RbacUMLElementTypes._PERMISSION__STEREOTYPE, resourceSet));                    
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