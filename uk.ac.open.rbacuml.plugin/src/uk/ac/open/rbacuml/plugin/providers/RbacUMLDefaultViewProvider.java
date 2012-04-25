package uk.ac.open.rbacuml.plugin.providers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.emf.transaction.util.TransactionUtil;

import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;

import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;

import org.eclipse.gmf.runtime.diagram.core.providers.IViewProvider;

import org.eclipse.gmf.runtime.diagram.core.services.ViewService;

import org.eclipse.gmf.runtime.diagram.core.services.view.CreateDiagramViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateEdgeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateNodeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateViewForKindOperation;

import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IMetamodelType;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationType;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.open.rbacuml.plugin.types.RbacUMLElementTypes;

import uk.ac.open.rbacuml.plugin.viewFactories.ActivateRolesCallBehaviorActionViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.ActivateRolesCallOperationActionViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.ActivateRolesOpaqueActionViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.DeactivateRolesCallBehaviorActionViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.DeactivateRolesCallOperationActionViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.DeactivateRolesOpaqueActionViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.ForbiddenCallBehaviorActionViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.ForbiddenCallOperationActionViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.ForbiddenOpaqueActionViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.GrantedCallBehaviorActionViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.GrantedCallOperationActionViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.GrantedOpaqueActionViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.PermissionClassViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.PermissionStereotypeViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACRoleClassViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACRoleStereotypeViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACUserActivityPartitionViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACUserClassViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACUserStereotypeViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.RestrictedMessageViewCustomizer;
import uk.ac.open.rbacuml.plugin.viewFactories.RestrictedOperationViewCustomizer;

/**
 * @generated
 */
public class RbacUMLDefaultViewProvider
        extends AbstractProvider implements IViewProvider {
    
	/**
	 * @generated
	 */
	public static interface IViewCustomizer {
		/**
		 * @generated
		 */
		public void customizeView(View view);
	}
	
	/**
	 * @generated
	 */
	private class WrapperAdaptable implements IAdaptable {
		/**
		 * @generated
		 */
		IAdaptable baseAdaptable = null;

		/**
		 * @generated
		 */
		public WrapperAdaptable(IAdaptable wrapped) {
			baseAdaptable = wrapped;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			return baseAdaptable.getAdapter(adapter);
		}
	}
	
    /**
     * @generated
     */
    private static Map<IElementType, IViewCustomizer> nodeMap = new HashMap<IElementType, IViewCustomizer>();
    
    /**
     * @generated
     */
    static {
	        nodeMap.put(RbacUMLElementTypes._RBACUSER__ACTIVITYPARTITION, RBACUserActivityPartitionViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._RBACUSER__CLASS, RBACUserClassViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._RBACUSER__STEREOTYPE, RBACUserStereotypeViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._RBACROLE__CLASS, RBACRoleClassViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._RBACROLE__STEREOTYPE, RBACRoleStereotypeViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._PERMISSION__CLASS, PermissionClassViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._PERMISSION__STEREOTYPE, PermissionStereotypeViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._RESTRICTED__OPERATION, RestrictedOperationViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._RESTRICTED__MESSAGE, RestrictedMessageViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._GRANTED__OPAQUEACTION, GrantedOpaqueActionViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._GRANTED__CALLOPERATIONACTION, GrantedCallOperationActionViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._GRANTED__CALLBEHAVIORACTION, GrantedCallBehaviorActionViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._FORBIDDEN__OPAQUEACTION, ForbiddenOpaqueActionViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._FORBIDDEN__CALLOPERATIONACTION, ForbiddenCallOperationActionViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._FORBIDDEN__CALLBEHAVIORACTION, ForbiddenCallBehaviorActionViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._ACTIVATEROLES__OPAQUEACTION, ActivateRolesOpaqueActionViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._ACTIVATEROLES__CALLOPERATIONACTION, ActivateRolesCallOperationActionViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._ACTIVATEROLES__CALLBEHAVIORACTION, ActivateRolesCallBehaviorActionViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._DEACTIVATEROLES__OPAQUEACTION, DeactivateRolesOpaqueActionViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._DEACTIVATEROLES__CALLOPERATIONACTION, DeactivateRolesCallOperationActionViewCustomizer.INSTANCE);
	        nodeMap.put(RbacUMLElementTypes._DEACTIVATEROLES__CALLBEHAVIORACTION, DeactivateRolesCallBehaviorActionViewCustomizer.INSTANCE);
	}
  
	/**
	 * @generated
	 */
    public Diagram createDiagram(IAdaptable semanticAdapter,
			String diagramKind, PreferencesHint preferencesHint) {
		return ViewService.getInstance().createDiagram(new WrapperAdaptable(semanticAdapter), diagramKind, preferencesHint);
	}

	/**
	 * @generated
	 */
	public Edge createEdge(IAdaptable semanticAdapter, View containerView,
			String semanticHint, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Edge umlEdge = ViewService.getInstance().createEdge(new WrapperAdaptable(semanticAdapter), containerView, semanticHint, index, persisted, preferencesHint);
		IViewCustomizer customizer = getEdgeViewCustomizer(semanticAdapter, containerView, semanticHint);
		if(customizer != null && umlEdge != null) {
			customizer.customizeView(umlEdge);
		}
		return umlEdge;
	}

	/**
	 * @generated
	 */
	public Node createNode(IAdaptable semanticAdapter, View containerView,
			String semanticHint, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Node umlNode = ViewService.getInstance().createNode(new WrapperAdaptable(semanticAdapter), containerView, semanticHint, index, persisted, preferencesHint);
		IViewCustomizer customizer = getNodeViewCustomizer(semanticAdapter, containerView, semanticHint);
		if(customizer != null && umlNode != null) {
			customizer.customizeView(umlNode);
		}
		return umlNode;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		/* if this is the CreateViewForKindOperation operation */
		if (operation instanceof CreateViewForKindOperation)
			return provides((CreateViewForKindOperation) operation);

		/* call the specific provides method */
		if (operation instanceof CreateDiagramViewOperation)
			return provides((CreateDiagramViewOperation) operation);
		else if (operation instanceof CreateEdgeViewOperation)
			return provides((CreateEdgeViewOperation) operation);
		else if (operation instanceof CreateNodeViewOperation)
			return provides((CreateNodeViewOperation) operation);
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean provides(CreateViewForKindOperation op) {
		if (op.getViewKind() == Node.class)
			return getNodeViewCustomizer(op.getSemanticAdapter(), op
				.getContainerView(), op.getSemanticHint()) != null;
		if (op.getViewKind() == Edge.class)
			return getEdgeViewCustomizer(op.getSemanticAdapter(), op
				.getContainerView(), op.getSemanticHint()) != null;
		return true;
	}

	/**
	 * @generated
	 */
	protected boolean provides(CreateDiagramViewOperation operation) {
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean provides(CreateEdgeViewOperation operation) {
		return (getEdgeViewCustomizer(operation.getSemanticAdapter(), operation
				.getContainerView(), operation.getSemanticHint()) != null);
	}

	/**
	 * @generated
	 */
	protected boolean provides(CreateNodeViewOperation operation) {
		return (getNodeViewCustomizer(operation.getSemanticAdapter(), operation
				.getContainerView(), operation.getSemanticHint()) != null);
	}


    /**
     * @generated
     */
    protected IViewCustomizer getNodeViewCustomizer(IAdaptable semanticAdapter,
            View containerView, String semanticHint) {
		if(semanticAdapter instanceof WrapperAdaptable)
			return null;

        if (semanticHint != null && semanticHint.length() > 0) {
            return (IViewCustomizer)nodeMap.get(semanticHint);
        }
        IElementType elementType = (IElementType)semanticAdapter.getAdapter(IElementType.class);
        if (elementType != null) {
            return (IViewCustomizer)nodeMap.get(elementType);
        } else {
            EObject eObject = getSemanticElement(semanticAdapter);
            if (eObject != null) {
                // first check specialization type matches
                for (int i = 0; i < RbacUMLElementTypes.NODE_TYPES.length; ++i) {
                    elementType = RbacUMLElementTypes.NODE_TYPES[i];
                    if (elementType instanceof ISpecializationType) {
                        if (((ISpecializationType)elementType).getMatcher().matches(eObject)) {
                            return (IViewCustomizer) nodeMap.get(elementType);
                        }
                    }
                }
                // next check metamodel type matches
                for (int i = 0; i < RbacUMLElementTypes.NODE_TYPES.length; ++i) {
                    elementType = RbacUMLElementTypes.NODE_TYPES[i];
                    if (elementType instanceof IMetamodelType) {
                        if (eObject.eClass() == elementType.getEClass()) {
                            return (IViewCustomizer) nodeMap.get(elementType);
                        }
                    }
                }
            }
        }
        return null;
    }
        
    /**
     * @generated
     */
    protected IViewCustomizer getEdgeViewCustomizer(IAdaptable semanticAdapter,
            View containerView, String semanticHint) {
		if(semanticAdapter instanceof WrapperAdaptable)
			return null;
            
        if (semanticHint != null && semanticHint.length() > 0) {
            return (IViewCustomizer)nodeMap.get(semanticHint);
        }
        IElementType elementType = (IElementType)semanticAdapter.getAdapter(IElementType.class);
        if (elementType != null) {
            return (IViewCustomizer)nodeMap.get(elementType);
        } else {
            EObject eObject = getSemanticElement(semanticAdapter);
            if (eObject != null) {
                // first check specialization type matches
                for (int i = 0; i < RbacUMLElementTypes.RELATIONSHIP_TYPES.length; ++i) {
                    elementType = RbacUMLElementTypes.RELATIONSHIP_TYPES[i];
                    if (elementType instanceof ISpecializationType) {
                        if (((ISpecializationType)elementType).getMatcher().matches(eObject)) {
                            return (IViewCustomizer) nodeMap.get(elementType);
                        }
                    }
                }
                // next check metamodel type matches
                for (int i = 0; i < RbacUMLElementTypes.RELATIONSHIP_TYPES.length; ++i) {
                    elementType = RbacUMLElementTypes.RELATIONSHIP_TYPES[i];
                    if (elementType instanceof IMetamodelType) {
                        if (eObject.eClass() == elementType.getEClass()) {
                            return (IViewCustomizer) nodeMap.get(elementType);
                        }
                    }
                }
            }
        }
        return null;       
    }

	/**
	 * @generated
	 */
	protected EObject getSemanticElement(IAdaptable semanticAdapter) {
		if (semanticAdapter == null)
			return null;
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject != null)
			return EMFCoreUtil.resolve(TransactionUtil.getEditingDomain(eObject), eObject);
		return null;
	}

	/**
	 * @generated
	 */
	protected EObject getSemanticElement(IAdaptable semanticAdapter,
			TransactionalEditingDomain domain) {
		if (semanticAdapter == null)
			return null;
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject != null)
			return EMFCoreUtil.resolve(domain, eObject);
		return null;
	}
}