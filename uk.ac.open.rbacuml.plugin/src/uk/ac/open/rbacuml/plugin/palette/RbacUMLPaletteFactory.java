package uk.ac.open.rbacuml.plugin.palette;

import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.gef.Tool;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.palette.ToolEntry;

import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

import org.eclipse.gmf.runtime.diagram.ui.tools.ConnectionCreationTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.CreationTool;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import org.eclipse.jface.resource.ImageDescriptor;

import uk.ac.open.rbacuml.plugin.l10n.RbacUMLMessages;

import uk.ac.open.rbacuml.plugin.types.RbacUMLElementTypes;

import uk.ac.open.rbacuml.plugin.utils.RbacUMLUtil;

/**
 * @generated
 */
public class RbacUMLPaletteFactory {

    /**
     * @generated
     */
    private ResourceSet resourceSet;
    
    /**
     * @generated
     */
    public RbacUMLPaletteFactory(DiagramEditor editor) {
        this.resourceSet = editor.getEditingDomain().getResourceSet();
    }
    
    /**
     * @generated
     */
    public void fillPalette(PaletteRoot paletteRoot) {
        paletteRoot.add(createRbacUMLDrawer());
    }

    /**
     * @generated
     */
    private PaletteDrawer createRbacUMLDrawer() {
        PaletteDrawer paletteDrawer = new PaletteDrawer(RbacUMLMessages.PaletteDrawer_RbacUML_name);
        paletteDrawer.setId("RbacUML"); //$NON-NLS-1$
        paletteDrawer.setDescription(RbacUMLMessages.PaletteDrawer_RbacUML_description);
		paletteDrawer.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
        paletteDrawer.add(createActivateRoles_rBACRoleCreationTool());
        paletteDrawer.add(createRBACRole_dsod1CreationTool());
        paletteDrawer.add(createDeactivateRoles_rBACRoleCreationTool());
        paletteDrawer.add(createRBACUser_rBACRoleCreationTool());
        paletteDrawer.add(createRBACRole_permissionCreationTool());
        paletteDrawer.add(createForbidden_operationCreationTool());
        paletteDrawer.add(createForbidden_interactionCreationTool());
        paletteDrawer.add(createGranted_interactionCreationTool());
        paletteDrawer.add(createRBACRole_ssod1CreationTool());
        paletteDrawer.add(createGranted_operationCreationTool());
        paletteDrawer.add(createRestricted_permissionCreationTool());
        paletteDrawer.add(createRBACUser_aliasUserCreationTool());
        paletteDrawer.add(createForbiddenStack());
        paletteDrawer.add(createActivateRolesStack());
        paletteDrawer.add(createGrantedStack());
        paletteDrawer.add(createRestrictedStack());
        paletteDrawer.add(createRBACRoleStack());
        paletteDrawer.add(createDeactivateRolesStack());
        paletteDrawer.add(createPermissionStack());
        paletteDrawer.add(createRBACUserStack());
        return paletteDrawer;
    }

    /**
     * @generated
     */
    private ToolEntry createActivateRoles_rBACRoleCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes.ACTIVATEROLES_RBACROLE, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes.ACTIVATEROLES_RBACROLE, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacUML.ActivateRoles_rBACRole", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool_ActivateRoles_rBACRole_name,
                RbacUMLMessages.PaletteTool_ActivateRoles_rBACRole_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes.ACTIVATEROLES_RBACROLE);
    }

    /**
     * @generated
     */
    private ToolEntry createRBACRole_dsod1CreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes.RBACROLE_DSOD1, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes.RBACROLE_DSOD1, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacUML.RBACRole_dsod1", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool_RBACRole_dsod1_name,
                RbacUMLMessages.PaletteTool_RBACRole_dsod1_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes.RBACROLE_DSOD1);
    }

    /**
     * @generated
     */
    private ToolEntry createDeactivateRoles_rBACRoleCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes.DEACTIVATEROLES_RBACROLE, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes.DEACTIVATEROLES_RBACROLE, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacUML.DeactivateRoles_rBACRole", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool_DeactivateRoles_rBACRole_name,
                RbacUMLMessages.PaletteTool_DeactivateRoles_rBACRole_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes.DEACTIVATEROLES_RBACROLE);
    }

    /**
     * @generated
     */
    private ToolEntry createRBACUser_rBACRoleCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes.RBACUSER_RBACROLE, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes.RBACUSER_RBACROLE, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacUML.RBACUser_rBACRole", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool_RBACUser_rBACRole_name,
                RbacUMLMessages.PaletteTool_RBACUser_rBACRole_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes.RBACUSER_RBACROLE);
    }

    /**
     * @generated
     */
    private ToolEntry createRBACRole_permissionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes.RBACROLE_PERMISSION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes.RBACROLE_PERMISSION, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacUML.RBACRole_permission", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool_RBACRole_permission_name,
                RbacUMLMessages.PaletteTool_RBACRole_permission_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes.RBACROLE_PERMISSION);
    }

    /**
     * @generated
     */
    private ToolEntry createForbidden_operationCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes.FORBIDDEN_OPERATION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes.FORBIDDEN_OPERATION, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacUML.Forbidden_operation", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool_Forbidden_operation_name,
                RbacUMLMessages.PaletteTool_Forbidden_operation_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes.FORBIDDEN_OPERATION);
    }

    /**
     * @generated
     */
    private ToolEntry createForbidden_interactionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes.FORBIDDEN_INTERACTION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes.FORBIDDEN_INTERACTION, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacUML.Forbidden_interaction", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool_Forbidden_interaction_name,
                RbacUMLMessages.PaletteTool_Forbidden_interaction_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes.FORBIDDEN_INTERACTION);
    }

    /**
     * @generated
     */
    private ToolEntry createGranted_interactionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes.GRANTED_INTERACTION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes.GRANTED_INTERACTION, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacUML.Granted_interaction", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool_Granted_interaction_name,
                RbacUMLMessages.PaletteTool_Granted_interaction_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes.GRANTED_INTERACTION);
    }

    /**
     * @generated
     */
    private ToolEntry createRBACRole_ssod1CreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes.RBACROLE_SSOD1, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes.RBACROLE_SSOD1, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacUML.RBACRole_ssod1", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool_RBACRole_ssod1_name,
                RbacUMLMessages.PaletteTool_RBACRole_ssod1_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes.RBACROLE_SSOD1);
    }

    /**
     * @generated
     */
    private ToolEntry createGranted_operationCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes.GRANTED_OPERATION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes.GRANTED_OPERATION, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacUML.Granted_operation", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool_Granted_operation_name,
                RbacUMLMessages.PaletteTool_Granted_operation_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes.GRANTED_OPERATION);
    }

    /**
     * @generated
     */
    private ToolEntry createRestricted_permissionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes.RESTRICTED_PERMISSION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes.RESTRICTED_PERMISSION, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacUML.Restricted_permission", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool_Restricted_permission_name,
                RbacUMLMessages.PaletteTool_Restricted_permission_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes.RESTRICTED_PERMISSION);
    }

    /**
     * @generated
     */
    private ToolEntry createRBACUser_aliasUserCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes.RBACUSER_ALIASUSER, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes.RBACUSER_ALIASUSER, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacUML.RBACUser_aliasUser", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool_RBACUser_aliasUser_name,
                RbacUMLMessages.PaletteTool_RBACUser_aliasUser_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes.RBACUSER_ALIASUSER);
    }

    /**
     * @generated
     */
    private PaletteStack createForbiddenStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacUMLMessages.PaletteStack_Forbidden_name, RbacUMLMessages.PaletteStack_Forbidden_description, smallImage);
        paletteStack.setId("Forbidden"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_Forbidden__OpaqueActionCreationTool());
        paletteStack.add(create_Forbidden__CallOperationActionCreationTool());
        paletteStack.add(create_Forbidden__CallBehaviorActionCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__OpaqueActionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._FORBIDDEN__OPAQUEACTION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._FORBIDDEN__OPAQUEACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._Forbidden__OpaqueAction", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__Forbidden__OpaqueAction_name,
                RbacUMLMessages.PaletteTool__Forbidden__OpaqueAction_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._FORBIDDEN__OPAQUEACTION);
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__CallOperationActionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._FORBIDDEN__CALLOPERATIONACTION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._FORBIDDEN__CALLOPERATIONACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._Forbidden__CallOperationAction", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__Forbidden__CallOperationAction_name,
                RbacUMLMessages.PaletteTool__Forbidden__CallOperationAction_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._FORBIDDEN__CALLOPERATIONACTION);
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__CallBehaviorActionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._FORBIDDEN__CALLBEHAVIORACTION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._FORBIDDEN__CALLBEHAVIORACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._Forbidden__CallBehaviorAction", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__Forbidden__CallBehaviorAction_name,
                RbacUMLMessages.PaletteTool__Forbidden__CallBehaviorAction_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._FORBIDDEN__CALLBEHAVIORACTION);
    }

    /**
     * @generated
     */
    private PaletteStack createActivateRolesStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacUMLMessages.PaletteStack_ActivateRoles_name, RbacUMLMessages.PaletteStack_ActivateRoles_description, smallImage);
        paletteStack.setId("ActivateRoles"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_ActivateRoles__OpaqueActionCreationTool());
        paletteStack.add(create_ActivateRoles__CallOperationActionCreationTool());
        paletteStack.add(create_ActivateRoles__CallBehaviorActionCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_ActivateRoles__OpaqueActionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._ACTIVATEROLES__OPAQUEACTION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._ACTIVATEROLES__OPAQUEACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._ActivateRoles__OpaqueAction", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__ActivateRoles__OpaqueAction_name,
                RbacUMLMessages.PaletteTool__ActivateRoles__OpaqueAction_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._ACTIVATEROLES__OPAQUEACTION);
    }

    /**
     * @generated
     */
    private ToolEntry create_ActivateRoles__CallOperationActionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._ACTIVATEROLES__CALLOPERATIONACTION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._ACTIVATEROLES__CALLOPERATIONACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._ActivateRoles__CallOperationAction", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__ActivateRoles__CallOperationAction_name,
                RbacUMLMessages.PaletteTool__ActivateRoles__CallOperationAction_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._ACTIVATEROLES__CALLOPERATIONACTION);
    }

    /**
     * @generated
     */
    private ToolEntry create_ActivateRoles__CallBehaviorActionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._ACTIVATEROLES__CALLBEHAVIORACTION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._ACTIVATEROLES__CALLBEHAVIORACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._ActivateRoles__CallBehaviorAction", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__ActivateRoles__CallBehaviorAction_name,
                RbacUMLMessages.PaletteTool__ActivateRoles__CallBehaviorAction_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._ACTIVATEROLES__CALLBEHAVIORACTION);
    }

    /**
     * @generated
     */
    private PaletteStack createGrantedStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacUMLMessages.PaletteStack_Granted_name, RbacUMLMessages.PaletteStack_Granted_description, smallImage);
        paletteStack.setId("Granted"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_Granted__OpaqueActionCreationTool());
        paletteStack.add(create_Granted__CallOperationActionCreationTool());
        paletteStack.add(create_Granted__CallBehaviorActionCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__OpaqueActionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._GRANTED__OPAQUEACTION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._GRANTED__OPAQUEACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._Granted__OpaqueAction", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__Granted__OpaqueAction_name,
                RbacUMLMessages.PaletteTool__Granted__OpaqueAction_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._GRANTED__OPAQUEACTION);
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__CallOperationActionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._GRANTED__CALLOPERATIONACTION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._GRANTED__CALLOPERATIONACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._Granted__CallOperationAction", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__Granted__CallOperationAction_name,
                RbacUMLMessages.PaletteTool__Granted__CallOperationAction_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._GRANTED__CALLOPERATIONACTION);
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__CallBehaviorActionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._GRANTED__CALLBEHAVIORACTION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._GRANTED__CALLBEHAVIORACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._Granted__CallBehaviorAction", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__Granted__CallBehaviorAction_name,
                RbacUMLMessages.PaletteTool__Granted__CallBehaviorAction_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._GRANTED__CALLBEHAVIORACTION);
    }

    /**
     * @generated
     */
    private PaletteStack createRestrictedStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacUMLMessages.PaletteStack_Restricted_name, RbacUMLMessages.PaletteStack_Restricted_description, smallImage);
        paletteStack.setId("Restricted"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_Restricted__OperationCreationTool());
        paletteStack.add(create_Restricted__MessageCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_Restricted__OperationCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._RESTRICTED__OPERATION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._RESTRICTED__OPERATION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._Restricted__Operation", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__Restricted__Operation_name,
                RbacUMLMessages.PaletteTool__Restricted__Operation_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._RESTRICTED__OPERATION);
    }

    /**
     * @generated
     */
    private ToolEntry create_Restricted__MessageCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._RESTRICTED__MESSAGE, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._RESTRICTED__MESSAGE, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacUML._Restricted__Message", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__Restricted__Message_name,
                RbacUMLMessages.PaletteTool__Restricted__Message_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._RESTRICTED__MESSAGE);
    }

    /**
     * @generated
     */
    private PaletteStack createRBACRoleStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacUMLMessages.PaletteStack_RBACRole_name, RbacUMLMessages.PaletteStack_RBACRole_description, smallImage);
        paletteStack.setId("RBACRole"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_RBACRole__ClassCreationTool());
        paletteStack.add(create_RBACRole__StereotypeCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_RBACRole__ClassCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._RBACROLE__CLASS, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._RBACROLE__CLASS, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._RBACRole__Class", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__RBACRole__Class_name,
                RbacUMLMessages.PaletteTool__RBACRole__Class_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._RBACROLE__CLASS);
    }

    /**
     * @generated
     */
    private ToolEntry create_RBACRole__StereotypeCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._RBACROLE__STEREOTYPE, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._RBACROLE__STEREOTYPE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._RBACRole__Stereotype", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__RBACRole__Stereotype_name,
                RbacUMLMessages.PaletteTool__RBACRole__Stereotype_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._RBACROLE__STEREOTYPE);
    }

    /**
     * @generated
     */
    private PaletteStack createDeactivateRolesStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacUMLMessages.PaletteStack_DeactivateRoles_name, RbacUMLMessages.PaletteStack_DeactivateRoles_description, smallImage);
        paletteStack.setId("DeactivateRoles"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_DeactivateRoles__OpaqueActionCreationTool());
        paletteStack.add(create_DeactivateRoles__CallOperationActionCreationTool());
        paletteStack.add(create_DeactivateRoles__CallBehaviorActionCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_DeactivateRoles__OpaqueActionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._DEACTIVATEROLES__OPAQUEACTION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._DEACTIVATEROLES__OPAQUEACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._DeactivateRoles__OpaqueAction", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__DeactivateRoles__OpaqueAction_name,
                RbacUMLMessages.PaletteTool__DeactivateRoles__OpaqueAction_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._DEACTIVATEROLES__OPAQUEACTION);
    }

    /**
     * @generated
     */
    private ToolEntry create_DeactivateRoles__CallOperationActionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._DEACTIVATEROLES__CALLOPERATIONACTION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._DEACTIVATEROLES__CALLOPERATIONACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._DeactivateRoles__CallOperationAction", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__DeactivateRoles__CallOperationAction_name,
                RbacUMLMessages.PaletteTool__DeactivateRoles__CallOperationAction_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._DEACTIVATEROLES__CALLOPERATIONACTION);
    }

    /**
     * @generated
     */
    private ToolEntry create_DeactivateRoles__CallBehaviorActionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._DEACTIVATEROLES__CALLBEHAVIORACTION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._DEACTIVATEROLES__CALLBEHAVIORACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._DeactivateRoles__CallBehaviorAction", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__DeactivateRoles__CallBehaviorAction_name,
                RbacUMLMessages.PaletteTool__DeactivateRoles__CallBehaviorAction_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._DEACTIVATEROLES__CALLBEHAVIORACTION);
    }

    /**
     * @generated
     */
    private PaletteStack createPermissionStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacUMLMessages.PaletteStack_Permission_name, RbacUMLMessages.PaletteStack_Permission_description, smallImage);
        paletteStack.setId("Permission"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_Permission__ClassCreationTool());
        paletteStack.add(create_Permission__StereotypeCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__ClassCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._PERMISSION__CLASS, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._PERMISSION__CLASS, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._Permission__Class", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__Permission__Class_name,
                RbacUMLMessages.PaletteTool__Permission__Class_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._PERMISSION__CLASS);
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__StereotypeCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._PERMISSION__STEREOTYPE, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._PERMISSION__STEREOTYPE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._Permission__Stereotype", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__Permission__Stereotype_name,
                RbacUMLMessages.PaletteTool__Permission__Stereotype_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._PERMISSION__STEREOTYPE);
    }

    /**
     * @generated
     */
    private PaletteStack createRBACUserStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacUMLMessages.PaletteStack_RBACUser_name, RbacUMLMessages.PaletteStack_RBACUser_description, smallImage);
        paletteStack.setId("RBACUser"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_RBACUser__ActivityPartitionCreationTool());
        paletteStack.add(create_RBACUser__ClassCreationTool());
        paletteStack.add(create_RBACUser__StereotypeCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_RBACUser__ActivityPartitionCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._RBACUSER__ACTIVITYPARTITION, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._RBACUSER__ACTIVITYPARTITION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._RBACUser__ActivityPartition", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__RBACUser__ActivityPartition_name,
                RbacUMLMessages.PaletteTool__RBACUser__ActivityPartition_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._RBACUSER__ACTIVITYPARTITION);
    }

    /**
     * @generated
     */
    private ToolEntry create_RBACUser__ClassCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._RBACUSER__CLASS, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._RBACUSER__CLASS, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._RBACUser__Class", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__RBACUser__Class_name,
                RbacUMLMessages.PaletteTool__RBACUser__Class_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._RBACUSER__CLASS);
    }

    /**
     * @generated
     */
    private ToolEntry create_RBACUser__StereotypeCreationTool() {
        ImageDescriptor smallImage = RbacUMLUtil.getSmallImage(RbacUMLElementTypes._RBACUSER__STEREOTYPE, resourceSet);
        ImageDescriptor largeImage = RbacUMLUtil.getLargeImage(RbacUMLElementTypes._RBACUSER__STEREOTYPE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacUML._RBACUser__Stereotype", //$NON-NLS-1$
                RbacUMLMessages.PaletteTool__RBACUser__Stereotype_name,
                RbacUMLMessages.PaletteTool__RBACUser__Stereotype_description,
                smallImage,
                largeImage,
                RbacUMLElementTypes._RBACUSER__STEREOTYPE);
    }      

    /**
     * @generated
     */
    private static class NodeToolEntry
        extends ToolEntry {

        /**
         * @generated
         */
        private final IElementType elementType;

        /**
         * @generated
         */
        private NodeToolEntry(String id, String title, String description,
                ImageDescriptor smallIcon, ImageDescriptor largeIcon,
                IElementType elementType) {
            super(title, description, smallIcon, largeIcon);
            setId(id);
            this.elementType = elementType;
        }

        /**
         * @generated
         */
        public Tool createTool() {
            Tool tool = new CreationTool(elementType);
            return tool;
        }
        
    }

    /**
     * @generated
     */
    private static class ConnectionToolEntry
        extends ToolEntry {

        /**
         * @generated
         */
        private final IElementType elementType;

        /**
         * @generated
         */
        private ConnectionToolEntry(String id, String title, String description,
                ImageDescriptor smallIcon, ImageDescriptor largeIcon,
                IElementType elementType) {
            super(title, description, smallIcon, largeIcon);
            setId(id);
            this.elementType = elementType;
        }

        /**
         * @generated
         */
        public Tool createTool() {
            Tool tool = new ConnectionCreationTool(elementType);
            tool.setProperties(getToolProperties());
            return tool;
        }
    }
}