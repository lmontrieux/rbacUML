package uk.ac.open.rbacuml.dsml.plugin.providers;

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

import uk.ac.open.rbacuml.dsml.plugin.types.RbacDSMLElementTypes;

import uk.ac.open.rbacuml.dsml.plugin.viewFactories.DSoDAssociationClassViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.DSoDAssociationViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.DSoDCommunicationPathViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.DSoDExtensionViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ForbiddenActivityViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ForbiddenAssociationClassViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ForbiddenClassViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ForbiddenComponentViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ForbiddenDeviceViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ForbiddenExecutionEnvironmentViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ForbiddenFunctionBehaviorViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ForbiddenInteractionViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ForbiddenNodeViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ForbiddenOpaqueBehaviorViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ForbiddenProtocolStateMachineViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ForbiddenStateMachineViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ForbiddenStereotypeViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.GrantedActivityViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.GrantedAssociationClassViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.GrantedClassViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.GrantedComponentViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.GrantedDeviceViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.GrantedExecutionEnvironmentViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.GrantedFunctionBehaviorViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.GrantedInteractionViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.GrantedNodeViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.GrantedOpaqueBehaviorViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.GrantedProtocolStateMachineViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.GrantedStateMachineViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.GrantedStereotypeViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.PermissionActivityViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.PermissionAssociationClassViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.PermissionClassViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.PermissionComponentViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.PermissionDeviceViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.PermissionExecutionEnvironmentViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.PermissionFunctionBehaviorViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.PermissionInteractionViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.PermissionNodeViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.PermissionOpaqueBehaviorViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.PermissionProtocolStateMachineViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.PermissionStateMachineViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.PermissionStereotypeViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourceActivityViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourceAssociationClassViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourceClassViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourceComponentViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourceDeviceViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourceExecutionEnvironmentViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourceFunctionBehaviorViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourceInteractionViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourceNodeViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourceOpaqueBehaviorViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourceProtocolStateMachineViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourceStateMachineViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourceStereotypeViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.SSoDAssociationClassViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.SSoDAssociationViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.SSoDCommunicationPathViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.SSoDExtensionViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserActivityViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserAssociationClassViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserClassViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserComponentViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserDeviceViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserExecutionEnvironmentViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserFunctionBehaviorViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserInteractionViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserNodeViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserOpaqueBehaviorViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserProtocolStateMachineViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserStateMachineViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserStereotypeViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleActivityViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleAssociationClassViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleClassViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleComponentViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleDeviceViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleExecutionEnvironmentViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleFunctionBehaviorViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleInteractionViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleNodeViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleOpaqueBehaviorViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleProtocolStateMachineViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleStateMachineViewCustomizer;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleStereotypeViewCustomizer;

/**
 * @generated
 */
public class RbacDSMLDefaultViewProvider
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
          nodeMap.put(RbacDSMLElementTypes._USER__CLASS, UserClassViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._USER__PROTOCOLSTATEMACHINE, UserProtocolStateMachineViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._USER__STATEMACHINE, UserStateMachineViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._USER__STEREOTYPE, UserStereotypeViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._USER__OPAQUEBEHAVIOR, UserOpaqueBehaviorViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._USER__FUNCTIONBEHAVIOR, UserFunctionBehaviorViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._USER__ACTIVITY, UserActivityViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._USER__INTERACTION, UserInteractionViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._USER__COMPONENT, UserComponentViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._USER__NODE, UserNodeViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._USER__DEVICE, UserDeviceViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._USER__EXECUTIONENVIRONMENT, UserExecutionEnvironmentViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._USER__ASSOCIATIONCLASS, UserAssociationClassViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RBACROLE__CLASS, rbacRoleClassViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RBACROLE__PROTOCOLSTATEMACHINE, rbacRoleProtocolStateMachineViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RBACROLE__STATEMACHINE, rbacRoleStateMachineViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RBACROLE__STEREOTYPE, rbacRoleStereotypeViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RBACROLE__OPAQUEBEHAVIOR, rbacRoleOpaqueBehaviorViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RBACROLE__FUNCTIONBEHAVIOR, rbacRoleFunctionBehaviorViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RBACROLE__ACTIVITY, rbacRoleActivityViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RBACROLE__INTERACTION, rbacRoleInteractionViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RBACROLE__COMPONENT, rbacRoleComponentViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RBACROLE__NODE, rbacRoleNodeViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RBACROLE__DEVICE, rbacRoleDeviceViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RBACROLE__EXECUTIONENVIRONMENT, rbacRoleExecutionEnvironmentViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RBACROLE__ASSOCIATIONCLASS, rbacRoleAssociationClassViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._PERMISSION__CLASS, PermissionClassViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._PERMISSION__PROTOCOLSTATEMACHINE, PermissionProtocolStateMachineViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._PERMISSION__STATEMACHINE, PermissionStateMachineViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._PERMISSION__STEREOTYPE, PermissionStereotypeViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._PERMISSION__OPAQUEBEHAVIOR, PermissionOpaqueBehaviorViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._PERMISSION__FUNCTIONBEHAVIOR, PermissionFunctionBehaviorViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._PERMISSION__ACTIVITY, PermissionActivityViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._PERMISSION__INTERACTION, PermissionInteractionViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._PERMISSION__COMPONENT, PermissionComponentViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._PERMISSION__NODE, PermissionNodeViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._PERMISSION__DEVICE, PermissionDeviceViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._PERMISSION__EXECUTIONENVIRONMENT, PermissionExecutionEnvironmentViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._PERMISSION__ASSOCIATIONCLASS, PermissionAssociationClassViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RESOURCE__CLASS, ResourceClassViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RESOURCE__PROTOCOLSTATEMACHINE, ResourceProtocolStateMachineViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RESOURCE__STATEMACHINE, ResourceStateMachineViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RESOURCE__STEREOTYPE, ResourceStereotypeViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RESOURCE__OPAQUEBEHAVIOR, ResourceOpaqueBehaviorViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RESOURCE__FUNCTIONBEHAVIOR, ResourceFunctionBehaviorViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RESOURCE__ACTIVITY, ResourceActivityViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RESOURCE__INTERACTION, ResourceInteractionViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RESOURCE__COMPONENT, ResourceComponentViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RESOURCE__NODE, ResourceNodeViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RESOURCE__DEVICE, ResourceDeviceViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RESOURCE__EXECUTIONENVIRONMENT, ResourceExecutionEnvironmentViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._RESOURCE__ASSOCIATIONCLASS, ResourceAssociationClassViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._GRANTED__CLASS, GrantedClassViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._GRANTED__PROTOCOLSTATEMACHINE, GrantedProtocolStateMachineViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._GRANTED__STATEMACHINE, GrantedStateMachineViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._GRANTED__STEREOTYPE, GrantedStereotypeViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._GRANTED__OPAQUEBEHAVIOR, GrantedOpaqueBehaviorViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._GRANTED__FUNCTIONBEHAVIOR, GrantedFunctionBehaviorViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._GRANTED__ACTIVITY, GrantedActivityViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._GRANTED__INTERACTION, GrantedInteractionViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._GRANTED__COMPONENT, GrantedComponentViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._GRANTED__NODE, GrantedNodeViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._GRANTED__DEVICE, GrantedDeviceViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._GRANTED__EXECUTIONENVIRONMENT, GrantedExecutionEnvironmentViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._GRANTED__ASSOCIATIONCLASS, GrantedAssociationClassViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._FORBIDDEN__CLASS, ForbiddenClassViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._FORBIDDEN__PROTOCOLSTATEMACHINE, ForbiddenProtocolStateMachineViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._FORBIDDEN__STATEMACHINE, ForbiddenStateMachineViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._FORBIDDEN__STEREOTYPE, ForbiddenStereotypeViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._FORBIDDEN__OPAQUEBEHAVIOR, ForbiddenOpaqueBehaviorViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._FORBIDDEN__FUNCTIONBEHAVIOR, ForbiddenFunctionBehaviorViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._FORBIDDEN__ACTIVITY, ForbiddenActivityViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._FORBIDDEN__INTERACTION, ForbiddenInteractionViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._FORBIDDEN__COMPONENT, ForbiddenComponentViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._FORBIDDEN__NODE, ForbiddenNodeViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._FORBIDDEN__DEVICE, ForbiddenDeviceViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._FORBIDDEN__EXECUTIONENVIRONMENT, ForbiddenExecutionEnvironmentViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._FORBIDDEN__ASSOCIATIONCLASS, ForbiddenAssociationClassViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._SSOD__ASSOCIATION, SSoDAssociationViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._SSOD__EXTENSION, SSoDExtensionViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._SSOD__COMMUNICATIONPATH, SSoDCommunicationPathViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._SSOD__ASSOCIATIONCLASS, SSoDAssociationClassViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._DSOD__ASSOCIATION, DSoDAssociationViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._DSOD__EXTENSION, DSoDExtensionViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._DSOD__COMMUNICATIONPATH, DSoDCommunicationPathViewCustomizer.INSTANCE);
          nodeMap.put(RbacDSMLElementTypes._DSOD__ASSOCIATIONCLASS, DSoDAssociationClassViewCustomizer.INSTANCE);
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
                for (int i = 0; i < RbacDSMLElementTypes.NODE_TYPES.length; ++i) {
                    elementType = RbacDSMLElementTypes.NODE_TYPES[i];
                    if (elementType instanceof ISpecializationType) {
                        if (((ISpecializationType)elementType).getMatcher().matches(eObject)) {
                            return (IViewCustomizer) nodeMap.get(elementType);
                        }
                    }
                }
                // next check metamodel type matches
                for (int i = 0; i < RbacDSMLElementTypes.NODE_TYPES.length; ++i) {
                    elementType = RbacDSMLElementTypes.NODE_TYPES[i];
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
                for (int i = 0; i < RbacDSMLElementTypes.RELATIONSHIP_TYPES.length; ++i) {
                    elementType = RbacDSMLElementTypes.RELATIONSHIP_TYPES[i];
                    if (elementType instanceof ISpecializationType) {
                        if (((ISpecializationType)elementType).getMatcher().matches(eObject)) {
                            return (IViewCustomizer) nodeMap.get(elementType);
                        }
                    }
                }
                // next check metamodel type matches
                for (int i = 0; i < RbacDSMLElementTypes.RELATIONSHIP_TYPES.length; ++i) {
                    elementType = RbacDSMLElementTypes.RELATIONSHIP_TYPES[i];
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