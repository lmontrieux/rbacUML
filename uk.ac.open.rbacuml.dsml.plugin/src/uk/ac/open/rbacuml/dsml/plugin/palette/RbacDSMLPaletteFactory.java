package uk.ac.open.rbacuml.dsml.plugin.palette;

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

import uk.ac.open.rbacuml.dsml.plugin.l10n.RbacDSMLMessages;

import uk.ac.open.rbacuml.dsml.plugin.types.RbacDSMLElementTypes;

import uk.ac.open.rbacuml.dsml.plugin.utils.RbacDSMLUtil;

/**
 * @generated
 */
public class RbacDSMLPaletteFactory {

    /**
     * @generated
     */
    private ResourceSet resourceSet;
    
    /**
     * @generated
     */
    public RbacDSMLPaletteFactory(DiagramEditor editor) {
        this.resourceSet = editor.getEditingDomain().getResourceSet();
    }
    
    /**
     * @generated
     */
    public void fillPalette(PaletteRoot paletteRoot) {
        paletteRoot.add(createRbacDSMLDrawer());
    }

    /**
     * @generated
     */
    private PaletteDrawer createRbacDSMLDrawer() {
        PaletteDrawer paletteDrawer = new PaletteDrawer(RbacDSMLMessages.PaletteDrawer_RbacDSML_name);
        paletteDrawer.setId("RbacDSML"); //$NON-NLS-1$
        paletteDrawer.setDescription(RbacDSMLMessages.PaletteDrawer_RbacDSML_description);
    paletteDrawer.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
        paletteDrawer.add(createScenario_resourceCreationTool());
        paletteDrawer.add(createRbacRole_permissionCreationTool());
        paletteDrawer.add(createResource_permissionCreationTool());
        paletteDrawer.add(createRbacRole_parentCreationTool());
        paletteDrawer.add(createScenario_rbacRoleCreationTool());
        paletteDrawer.add(createRbacRole_dsod1CreationTool());
        paletteDrawer.add(createUser_rbacRoleCreationTool());
        paletteDrawer.add(createRbacRole_ssod1CreationTool());
        paletteDrawer.add(createScenario_userCreationTool());
        paletteDrawer.add(createSSoDStack());
        paletteDrawer.add(createRbacRoleStack());
        paletteDrawer.add(createDSoDStack());
        paletteDrawer.add(createResourceStack());
        paletteDrawer.add(createGrantedStack());
        paletteDrawer.add(createForbiddenStack());
        paletteDrawer.add(createPermissionStack());
        paletteDrawer.add(createUserStack());
        return paletteDrawer;
    }

    /**
     * @generated
     */
    private ToolEntry createScenario_resourceCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes.SCENARIO_RESOURCE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes.SCENARIO_RESOURCE, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML.Scenario_resource", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool_Scenario_resource_name,
                RbacDSMLMessages.PaletteTool_Scenario_resource_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes.SCENARIO_RESOURCE);
    }

    /**
     * @generated
     */
    private ToolEntry createRbacRole_permissionCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes.RBACROLE_PERMISSION, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes.RBACROLE_PERMISSION, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML.RbacRole_permission", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool_RbacRole_permission_name,
                RbacDSMLMessages.PaletteTool_RbacRole_permission_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes.RBACROLE_PERMISSION);
    }

    /**
     * @generated
     */
    private ToolEntry createResource_permissionCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes.RESOURCE_PERMISSION, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes.RESOURCE_PERMISSION, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML.Resource_permission", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool_Resource_permission_name,
                RbacDSMLMessages.PaletteTool_Resource_permission_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes.RESOURCE_PERMISSION);
    }

    /**
     * @generated
     */
    private ToolEntry createRbacRole_parentCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes.RBACROLE_PARENT, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes.RBACROLE_PARENT, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML.RbacRole_parent", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool_RbacRole_parent_name,
                RbacDSMLMessages.PaletteTool_RbacRole_parent_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes.RBACROLE_PARENT);
    }

    /**
     * @generated
     */
    private ToolEntry createScenario_rbacRoleCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes.SCENARIO_RBACROLE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes.SCENARIO_RBACROLE, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML.Scenario_rbacRole", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool_Scenario_rbacRole_name,
                RbacDSMLMessages.PaletteTool_Scenario_rbacRole_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes.SCENARIO_RBACROLE);
    }

    /**
     * @generated
     */
    private ToolEntry createRbacRole_dsod1CreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes.RBACROLE_DSOD1, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes.RBACROLE_DSOD1, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML.RbacRole_dsod1", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool_RbacRole_dsod1_name,
                RbacDSMLMessages.PaletteTool_RbacRole_dsod1_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes.RBACROLE_DSOD1);
    }

    /**
     * @generated
     */
    private ToolEntry createUser_rbacRoleCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes.USER_RBACROLE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes.USER_RBACROLE, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML.User_rbacRole", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool_User_rbacRole_name,
                RbacDSMLMessages.PaletteTool_User_rbacRole_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes.USER_RBACROLE);
    }

    /**
     * @generated
     */
    private ToolEntry createRbacRole_ssod1CreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes.RBACROLE_SSOD1, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes.RBACROLE_SSOD1, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML.RbacRole_ssod1", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool_RbacRole_ssod1_name,
                RbacDSMLMessages.PaletteTool_RbacRole_ssod1_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes.RBACROLE_SSOD1);
    }

    /**
     * @generated
     */
    private ToolEntry createScenario_userCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes.SCENARIO_USER, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes.SCENARIO_USER, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML.Scenario_user", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool_Scenario_user_name,
                RbacDSMLMessages.PaletteTool_Scenario_user_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes.SCENARIO_USER);
    }

    /**
     * @generated
     */
    private PaletteStack createSSoDStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacDSMLMessages.PaletteStack_SSoD_name, RbacDSMLMessages.PaletteStack_SSoD_description, smallImage);
        paletteStack.setId("SSoD"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_SSoD__AssociationCreationTool());
        paletteStack.add(create_SSoD__ExtensionCreationTool());
        paletteStack.add(create_SSoD__CommunicationPathCreationTool());
        paletteStack.add(create_SSoD__AssociationClassCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_SSoD__AssociationCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._SSOD__ASSOCIATION, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._SSOD__ASSOCIATION, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML._SSoD__Association", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__SSoD__Association_name,
                RbacDSMLMessages.PaletteTool__SSoD__Association_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._SSOD__ASSOCIATION);
    }

    /**
     * @generated
     */
    private ToolEntry create_SSoD__ExtensionCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._SSOD__EXTENSION, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._SSOD__EXTENSION, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML._SSoD__Extension", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__SSoD__Extension_name,
                RbacDSMLMessages.PaletteTool__SSoD__Extension_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._SSOD__EXTENSION);
    }

    /**
     * @generated
     */
    private ToolEntry create_SSoD__CommunicationPathCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._SSOD__COMMUNICATIONPATH, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._SSOD__COMMUNICATIONPATH, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML._SSoD__CommunicationPath", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__SSoD__CommunicationPath_name,
                RbacDSMLMessages.PaletteTool__SSoD__CommunicationPath_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._SSOD__COMMUNICATIONPATH);
    }

    /**
     * @generated
     */
    private ToolEntry create_SSoD__AssociationClassCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._SSOD__ASSOCIATIONCLASS, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._SSOD__ASSOCIATIONCLASS, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML._SSoD__AssociationClass", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__SSoD__AssociationClass_name,
                RbacDSMLMessages.PaletteTool__SSoD__AssociationClass_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._SSOD__ASSOCIATIONCLASS);
    }

    /**
     * @generated
     */
    private PaletteStack createRbacRoleStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacDSMLMessages.PaletteStack_RbacRole_name, RbacDSMLMessages.PaletteStack_RbacRole_description, smallImage);
        paletteStack.setId("RbacRole"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_rbacRole__ClassCreationTool());
        paletteStack.add(create_rbacRole__ProtocolStateMachineCreationTool());
        paletteStack.add(create_rbacRole__StateMachineCreationTool());
        paletteStack.add(create_rbacRole__StereotypeCreationTool());
        paletteStack.add(create_rbacRole__OpaqueBehaviorCreationTool());
        paletteStack.add(create_rbacRole__FunctionBehaviorCreationTool());
        paletteStack.add(create_rbacRole__ActivityCreationTool());
        paletteStack.add(create_rbacRole__InteractionCreationTool());
        paletteStack.add(create_rbacRole__ComponentCreationTool());
        paletteStack.add(create_rbacRole__NodeCreationTool());
        paletteStack.add(create_rbacRole__DeviceCreationTool());
        paletteStack.add(create_rbacRole__ExecutionEnvironmentCreationTool());
        paletteStack.add(create_rbacRole__AssociationClassCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_rbacRole__ClassCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__CLASS, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RBACROLE__CLASS, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._rbacRole__Class", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__rbacRole__Class_name,
                RbacDSMLMessages.PaletteTool__rbacRole__Class_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RBACROLE__CLASS);
    }

    /**
     * @generated
     */
    private ToolEntry create_rbacRole__ProtocolStateMachineCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__PROTOCOLSTATEMACHINE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RBACROLE__PROTOCOLSTATEMACHINE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._rbacRole__ProtocolStateMachine", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__rbacRole__ProtocolStateMachine_name,
                RbacDSMLMessages.PaletteTool__rbacRole__ProtocolStateMachine_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RBACROLE__PROTOCOLSTATEMACHINE);
    }

    /**
     * @generated
     */
    private ToolEntry create_rbacRole__StateMachineCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__STATEMACHINE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RBACROLE__STATEMACHINE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._rbacRole__StateMachine", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__rbacRole__StateMachine_name,
                RbacDSMLMessages.PaletteTool__rbacRole__StateMachine_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RBACROLE__STATEMACHINE);
    }

    /**
     * @generated
     */
    private ToolEntry create_rbacRole__StereotypeCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__STEREOTYPE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RBACROLE__STEREOTYPE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._rbacRole__Stereotype", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__rbacRole__Stereotype_name,
                RbacDSMLMessages.PaletteTool__rbacRole__Stereotype_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RBACROLE__STEREOTYPE);
    }

    /**
     * @generated
     */
    private ToolEntry create_rbacRole__OpaqueBehaviorCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__OPAQUEBEHAVIOR, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RBACROLE__OPAQUEBEHAVIOR, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._rbacRole__OpaqueBehavior", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__rbacRole__OpaqueBehavior_name,
                RbacDSMLMessages.PaletteTool__rbacRole__OpaqueBehavior_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RBACROLE__OPAQUEBEHAVIOR);
    }

    /**
     * @generated
     */
    private ToolEntry create_rbacRole__FunctionBehaviorCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__FUNCTIONBEHAVIOR, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RBACROLE__FUNCTIONBEHAVIOR, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._rbacRole__FunctionBehavior", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__rbacRole__FunctionBehavior_name,
                RbacDSMLMessages.PaletteTool__rbacRole__FunctionBehavior_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RBACROLE__FUNCTIONBEHAVIOR);
    }

    /**
     * @generated
     */
    private ToolEntry create_rbacRole__ActivityCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__ACTIVITY, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RBACROLE__ACTIVITY, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._rbacRole__Activity", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__rbacRole__Activity_name,
                RbacDSMLMessages.PaletteTool__rbacRole__Activity_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RBACROLE__ACTIVITY);
    }

    /**
     * @generated
     */
    private ToolEntry create_rbacRole__InteractionCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__INTERACTION, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RBACROLE__INTERACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._rbacRole__Interaction", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__rbacRole__Interaction_name,
                RbacDSMLMessages.PaletteTool__rbacRole__Interaction_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RBACROLE__INTERACTION);
    }

    /**
     * @generated
     */
    private ToolEntry create_rbacRole__ComponentCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__COMPONENT, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RBACROLE__COMPONENT, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._rbacRole__Component", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__rbacRole__Component_name,
                RbacDSMLMessages.PaletteTool__rbacRole__Component_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RBACROLE__COMPONENT);
    }

    /**
     * @generated
     */
    private ToolEntry create_rbacRole__NodeCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__NODE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RBACROLE__NODE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._rbacRole__Node", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__rbacRole__Node_name,
                RbacDSMLMessages.PaletteTool__rbacRole__Node_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RBACROLE__NODE);
    }

    /**
     * @generated
     */
    private ToolEntry create_rbacRole__DeviceCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__DEVICE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RBACROLE__DEVICE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._rbacRole__Device", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__rbacRole__Device_name,
                RbacDSMLMessages.PaletteTool__rbacRole__Device_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RBACROLE__DEVICE);
    }

    /**
     * @generated
     */
    private ToolEntry create_rbacRole__ExecutionEnvironmentCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__EXECUTIONENVIRONMENT, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RBACROLE__EXECUTIONENVIRONMENT, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._rbacRole__ExecutionEnvironment", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__rbacRole__ExecutionEnvironment_name,
                RbacDSMLMessages.PaletteTool__rbacRole__ExecutionEnvironment_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RBACROLE__EXECUTIONENVIRONMENT);
    }

    /**
     * @generated
     */
    private ToolEntry create_rbacRole__AssociationClassCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RBACROLE__ASSOCIATIONCLASS, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RBACROLE__ASSOCIATIONCLASS, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML._rbacRole__AssociationClass", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__rbacRole__AssociationClass_name,
                RbacDSMLMessages.PaletteTool__rbacRole__AssociationClass_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RBACROLE__ASSOCIATIONCLASS);
    }

    /**
     * @generated
     */
    private PaletteStack createDSoDStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacDSMLMessages.PaletteStack_DSoD_name, RbacDSMLMessages.PaletteStack_DSoD_description, smallImage);
        paletteStack.setId("DSoD"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_DSoD__AssociationCreationTool());
        paletteStack.add(create_DSoD__ExtensionCreationTool());
        paletteStack.add(create_DSoD__CommunicationPathCreationTool());
        paletteStack.add(create_DSoD__AssociationClassCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_DSoD__AssociationCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._DSOD__ASSOCIATION, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._DSOD__ASSOCIATION, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML._DSoD__Association", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__DSoD__Association_name,
                RbacDSMLMessages.PaletteTool__DSoD__Association_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._DSOD__ASSOCIATION);
    }

    /**
     * @generated
     */
    private ToolEntry create_DSoD__ExtensionCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._DSOD__EXTENSION, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._DSOD__EXTENSION, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML._DSoD__Extension", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__DSoD__Extension_name,
                RbacDSMLMessages.PaletteTool__DSoD__Extension_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._DSOD__EXTENSION);
    }

    /**
     * @generated
     */
    private ToolEntry create_DSoD__CommunicationPathCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._DSOD__COMMUNICATIONPATH, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._DSOD__COMMUNICATIONPATH, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML._DSoD__CommunicationPath", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__DSoD__CommunicationPath_name,
                RbacDSMLMessages.PaletteTool__DSoD__CommunicationPath_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._DSOD__COMMUNICATIONPATH);
    }

    /**
     * @generated
     */
    private ToolEntry create_DSoD__AssociationClassCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._DSOD__ASSOCIATIONCLASS, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._DSOD__ASSOCIATIONCLASS, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML._DSoD__AssociationClass", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__DSoD__AssociationClass_name,
                RbacDSMLMessages.PaletteTool__DSoD__AssociationClass_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._DSOD__ASSOCIATIONCLASS);
    }

    /**
     * @generated
     */
    private PaletteStack createResourceStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacDSMLMessages.PaletteStack_Resource_name, RbacDSMLMessages.PaletteStack_Resource_description, smallImage);
        paletteStack.setId("Resource"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_Resource__ClassCreationTool());
        paletteStack.add(create_Resource__ProtocolStateMachineCreationTool());
        paletteStack.add(create_Resource__StateMachineCreationTool());
        paletteStack.add(create_Resource__StereotypeCreationTool());
        paletteStack.add(create_Resource__OpaqueBehaviorCreationTool());
        paletteStack.add(create_Resource__FunctionBehaviorCreationTool());
        paletteStack.add(create_Resource__ActivityCreationTool());
        paletteStack.add(create_Resource__InteractionCreationTool());
        paletteStack.add(create_Resource__ComponentCreationTool());
        paletteStack.add(create_Resource__NodeCreationTool());
        paletteStack.add(create_Resource__DeviceCreationTool());
        paletteStack.add(create_Resource__ExecutionEnvironmentCreationTool());
        paletteStack.add(create_Resource__AssociationClassCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_Resource__ClassCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__CLASS, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RESOURCE__CLASS, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Resource__Class", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Resource__Class_name,
                RbacDSMLMessages.PaletteTool__Resource__Class_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RESOURCE__CLASS);
    }

    /**
     * @generated
     */
    private ToolEntry create_Resource__ProtocolStateMachineCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__PROTOCOLSTATEMACHINE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RESOURCE__PROTOCOLSTATEMACHINE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Resource__ProtocolStateMachine", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Resource__ProtocolStateMachine_name,
                RbacDSMLMessages.PaletteTool__Resource__ProtocolStateMachine_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RESOURCE__PROTOCOLSTATEMACHINE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Resource__StateMachineCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__STATEMACHINE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RESOURCE__STATEMACHINE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Resource__StateMachine", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Resource__StateMachine_name,
                RbacDSMLMessages.PaletteTool__Resource__StateMachine_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RESOURCE__STATEMACHINE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Resource__StereotypeCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__STEREOTYPE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RESOURCE__STEREOTYPE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Resource__Stereotype", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Resource__Stereotype_name,
                RbacDSMLMessages.PaletteTool__Resource__Stereotype_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RESOURCE__STEREOTYPE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Resource__OpaqueBehaviorCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__OPAQUEBEHAVIOR, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RESOURCE__OPAQUEBEHAVIOR, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Resource__OpaqueBehavior", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Resource__OpaqueBehavior_name,
                RbacDSMLMessages.PaletteTool__Resource__OpaqueBehavior_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RESOURCE__OPAQUEBEHAVIOR);
    }

    /**
     * @generated
     */
    private ToolEntry create_Resource__FunctionBehaviorCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__FUNCTIONBEHAVIOR, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RESOURCE__FUNCTIONBEHAVIOR, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Resource__FunctionBehavior", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Resource__FunctionBehavior_name,
                RbacDSMLMessages.PaletteTool__Resource__FunctionBehavior_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RESOURCE__FUNCTIONBEHAVIOR);
    }

    /**
     * @generated
     */
    private ToolEntry create_Resource__ActivityCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__ACTIVITY, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RESOURCE__ACTIVITY, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Resource__Activity", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Resource__Activity_name,
                RbacDSMLMessages.PaletteTool__Resource__Activity_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RESOURCE__ACTIVITY);
    }

    /**
     * @generated
     */
    private ToolEntry create_Resource__InteractionCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__INTERACTION, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RESOURCE__INTERACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Resource__Interaction", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Resource__Interaction_name,
                RbacDSMLMessages.PaletteTool__Resource__Interaction_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RESOURCE__INTERACTION);
    }

    /**
     * @generated
     */
    private ToolEntry create_Resource__ComponentCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__COMPONENT, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RESOURCE__COMPONENT, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Resource__Component", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Resource__Component_name,
                RbacDSMLMessages.PaletteTool__Resource__Component_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RESOURCE__COMPONENT);
    }

    /**
     * @generated
     */
    private ToolEntry create_Resource__NodeCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__NODE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RESOURCE__NODE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Resource__Node", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Resource__Node_name,
                RbacDSMLMessages.PaletteTool__Resource__Node_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RESOURCE__NODE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Resource__DeviceCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__DEVICE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RESOURCE__DEVICE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Resource__Device", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Resource__Device_name,
                RbacDSMLMessages.PaletteTool__Resource__Device_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RESOURCE__DEVICE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Resource__ExecutionEnvironmentCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__EXECUTIONENVIRONMENT, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RESOURCE__EXECUTIONENVIRONMENT, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Resource__ExecutionEnvironment", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Resource__ExecutionEnvironment_name,
                RbacDSMLMessages.PaletteTool__Resource__ExecutionEnvironment_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RESOURCE__EXECUTIONENVIRONMENT);
    }

    /**
     * @generated
     */
    private ToolEntry create_Resource__AssociationClassCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._RESOURCE__ASSOCIATIONCLASS, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._RESOURCE__ASSOCIATIONCLASS, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML._Resource__AssociationClass", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Resource__AssociationClass_name,
                RbacDSMLMessages.PaletteTool__Resource__AssociationClass_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._RESOURCE__ASSOCIATIONCLASS);
    }

    /**
     * @generated
     */
    private PaletteStack createGrantedStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacDSMLMessages.PaletteStack_Granted_name, RbacDSMLMessages.PaletteStack_Granted_description, smallImage);
        paletteStack.setId("Granted"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_Granted__ClassCreationTool());
        paletteStack.add(create_Granted__ProtocolStateMachineCreationTool());
        paletteStack.add(create_Granted__StateMachineCreationTool());
        paletteStack.add(create_Granted__StereotypeCreationTool());
        paletteStack.add(create_Granted__OpaqueBehaviorCreationTool());
        paletteStack.add(create_Granted__FunctionBehaviorCreationTool());
        paletteStack.add(create_Granted__ActivityCreationTool());
        paletteStack.add(create_Granted__InteractionCreationTool());
        paletteStack.add(create_Granted__ComponentCreationTool());
        paletteStack.add(create_Granted__NodeCreationTool());
        paletteStack.add(create_Granted__DeviceCreationTool());
        paletteStack.add(create_Granted__ExecutionEnvironmentCreationTool());
        paletteStack.add(create_Granted__AssociationClassCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__ClassCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__CLASS, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._GRANTED__CLASS, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Granted__Class", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Granted__Class_name,
                RbacDSMLMessages.PaletteTool__Granted__Class_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._GRANTED__CLASS);
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__ProtocolStateMachineCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__PROTOCOLSTATEMACHINE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._GRANTED__PROTOCOLSTATEMACHINE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Granted__ProtocolStateMachine", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Granted__ProtocolStateMachine_name,
                RbacDSMLMessages.PaletteTool__Granted__ProtocolStateMachine_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._GRANTED__PROTOCOLSTATEMACHINE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__StateMachineCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__STATEMACHINE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._GRANTED__STATEMACHINE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Granted__StateMachine", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Granted__StateMachine_name,
                RbacDSMLMessages.PaletteTool__Granted__StateMachine_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._GRANTED__STATEMACHINE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__StereotypeCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__STEREOTYPE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._GRANTED__STEREOTYPE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Granted__Stereotype", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Granted__Stereotype_name,
                RbacDSMLMessages.PaletteTool__Granted__Stereotype_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._GRANTED__STEREOTYPE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__OpaqueBehaviorCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__OPAQUEBEHAVIOR, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._GRANTED__OPAQUEBEHAVIOR, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Granted__OpaqueBehavior", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Granted__OpaqueBehavior_name,
                RbacDSMLMessages.PaletteTool__Granted__OpaqueBehavior_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._GRANTED__OPAQUEBEHAVIOR);
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__FunctionBehaviorCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__FUNCTIONBEHAVIOR, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._GRANTED__FUNCTIONBEHAVIOR, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Granted__FunctionBehavior", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Granted__FunctionBehavior_name,
                RbacDSMLMessages.PaletteTool__Granted__FunctionBehavior_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._GRANTED__FUNCTIONBEHAVIOR);
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__ActivityCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__ACTIVITY, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._GRANTED__ACTIVITY, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Granted__Activity", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Granted__Activity_name,
                RbacDSMLMessages.PaletteTool__Granted__Activity_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._GRANTED__ACTIVITY);
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__InteractionCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__INTERACTION, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._GRANTED__INTERACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Granted__Interaction", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Granted__Interaction_name,
                RbacDSMLMessages.PaletteTool__Granted__Interaction_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._GRANTED__INTERACTION);
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__ComponentCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__COMPONENT, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._GRANTED__COMPONENT, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Granted__Component", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Granted__Component_name,
                RbacDSMLMessages.PaletteTool__Granted__Component_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._GRANTED__COMPONENT);
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__NodeCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__NODE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._GRANTED__NODE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Granted__Node", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Granted__Node_name,
                RbacDSMLMessages.PaletteTool__Granted__Node_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._GRANTED__NODE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__DeviceCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__DEVICE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._GRANTED__DEVICE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Granted__Device", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Granted__Device_name,
                RbacDSMLMessages.PaletteTool__Granted__Device_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._GRANTED__DEVICE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__ExecutionEnvironmentCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__EXECUTIONENVIRONMENT, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._GRANTED__EXECUTIONENVIRONMENT, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Granted__ExecutionEnvironment", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Granted__ExecutionEnvironment_name,
                RbacDSMLMessages.PaletteTool__Granted__ExecutionEnvironment_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._GRANTED__EXECUTIONENVIRONMENT);
    }

    /**
     * @generated
     */
    private ToolEntry create_Granted__AssociationClassCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._GRANTED__ASSOCIATIONCLASS, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._GRANTED__ASSOCIATIONCLASS, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML._Granted__AssociationClass", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Granted__AssociationClass_name,
                RbacDSMLMessages.PaletteTool__Granted__AssociationClass_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._GRANTED__ASSOCIATIONCLASS);
    }

    /**
     * @generated
     */
    private PaletteStack createForbiddenStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacDSMLMessages.PaletteStack_Forbidden_name, RbacDSMLMessages.PaletteStack_Forbidden_description, smallImage);
        paletteStack.setId("Forbidden"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_Forbidden__ClassCreationTool());
        paletteStack.add(create_Forbidden__ProtocolStateMachineCreationTool());
        paletteStack.add(create_Forbidden__StateMachineCreationTool());
        paletteStack.add(create_Forbidden__StereotypeCreationTool());
        paletteStack.add(create_Forbidden__OpaqueBehaviorCreationTool());
        paletteStack.add(create_Forbidden__FunctionBehaviorCreationTool());
        paletteStack.add(create_Forbidden__ActivityCreationTool());
        paletteStack.add(create_Forbidden__InteractionCreationTool());
        paletteStack.add(create_Forbidden__ComponentCreationTool());
        paletteStack.add(create_Forbidden__NodeCreationTool());
        paletteStack.add(create_Forbidden__DeviceCreationTool());
        paletteStack.add(create_Forbidden__ExecutionEnvironmentCreationTool());
        paletteStack.add(create_Forbidden__AssociationClassCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__ClassCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__CLASS, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._FORBIDDEN__CLASS, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Forbidden__Class", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Forbidden__Class_name,
                RbacDSMLMessages.PaletteTool__Forbidden__Class_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._FORBIDDEN__CLASS);
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__ProtocolStateMachineCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__PROTOCOLSTATEMACHINE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._FORBIDDEN__PROTOCOLSTATEMACHINE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Forbidden__ProtocolStateMachine", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Forbidden__ProtocolStateMachine_name,
                RbacDSMLMessages.PaletteTool__Forbidden__ProtocolStateMachine_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._FORBIDDEN__PROTOCOLSTATEMACHINE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__StateMachineCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__STATEMACHINE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._FORBIDDEN__STATEMACHINE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Forbidden__StateMachine", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Forbidden__StateMachine_name,
                RbacDSMLMessages.PaletteTool__Forbidden__StateMachine_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._FORBIDDEN__STATEMACHINE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__StereotypeCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__STEREOTYPE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._FORBIDDEN__STEREOTYPE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Forbidden__Stereotype", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Forbidden__Stereotype_name,
                RbacDSMLMessages.PaletteTool__Forbidden__Stereotype_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._FORBIDDEN__STEREOTYPE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__OpaqueBehaviorCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__OPAQUEBEHAVIOR, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._FORBIDDEN__OPAQUEBEHAVIOR, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Forbidden__OpaqueBehavior", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Forbidden__OpaqueBehavior_name,
                RbacDSMLMessages.PaletteTool__Forbidden__OpaqueBehavior_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._FORBIDDEN__OPAQUEBEHAVIOR);
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__FunctionBehaviorCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__FUNCTIONBEHAVIOR, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._FORBIDDEN__FUNCTIONBEHAVIOR, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Forbidden__FunctionBehavior", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Forbidden__FunctionBehavior_name,
                RbacDSMLMessages.PaletteTool__Forbidden__FunctionBehavior_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._FORBIDDEN__FUNCTIONBEHAVIOR);
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__ActivityCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__ACTIVITY, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._FORBIDDEN__ACTIVITY, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Forbidden__Activity", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Forbidden__Activity_name,
                RbacDSMLMessages.PaletteTool__Forbidden__Activity_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._FORBIDDEN__ACTIVITY);
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__InteractionCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__INTERACTION, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._FORBIDDEN__INTERACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Forbidden__Interaction", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Forbidden__Interaction_name,
                RbacDSMLMessages.PaletteTool__Forbidden__Interaction_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._FORBIDDEN__INTERACTION);
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__ComponentCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__COMPONENT, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._FORBIDDEN__COMPONENT, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Forbidden__Component", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Forbidden__Component_name,
                RbacDSMLMessages.PaletteTool__Forbidden__Component_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._FORBIDDEN__COMPONENT);
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__NodeCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__NODE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._FORBIDDEN__NODE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Forbidden__Node", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Forbidden__Node_name,
                RbacDSMLMessages.PaletteTool__Forbidden__Node_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._FORBIDDEN__NODE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__DeviceCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__DEVICE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._FORBIDDEN__DEVICE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Forbidden__Device", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Forbidden__Device_name,
                RbacDSMLMessages.PaletteTool__Forbidden__Device_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._FORBIDDEN__DEVICE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__ExecutionEnvironmentCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__EXECUTIONENVIRONMENT, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._FORBIDDEN__EXECUTIONENVIRONMENT, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Forbidden__ExecutionEnvironment", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Forbidden__ExecutionEnvironment_name,
                RbacDSMLMessages.PaletteTool__Forbidden__ExecutionEnvironment_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._FORBIDDEN__EXECUTIONENVIRONMENT);
    }

    /**
     * @generated
     */
    private ToolEntry create_Forbidden__AssociationClassCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._FORBIDDEN__ASSOCIATIONCLASS, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._FORBIDDEN__ASSOCIATIONCLASS, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML._Forbidden__AssociationClass", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Forbidden__AssociationClass_name,
                RbacDSMLMessages.PaletteTool__Forbidden__AssociationClass_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._FORBIDDEN__ASSOCIATIONCLASS);
    }

    /**
     * @generated
     */
    private PaletteStack createPermissionStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacDSMLMessages.PaletteStack_Permission_name, RbacDSMLMessages.PaletteStack_Permission_description, smallImage);
        paletteStack.setId("Permission"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_Permission__ClassCreationTool());
        paletteStack.add(create_Permission__ProtocolStateMachineCreationTool());
        paletteStack.add(create_Permission__StateMachineCreationTool());
        paletteStack.add(create_Permission__StereotypeCreationTool());
        paletteStack.add(create_Permission__OpaqueBehaviorCreationTool());
        paletteStack.add(create_Permission__FunctionBehaviorCreationTool());
        paletteStack.add(create_Permission__ActivityCreationTool());
        paletteStack.add(create_Permission__InteractionCreationTool());
        paletteStack.add(create_Permission__ComponentCreationTool());
        paletteStack.add(create_Permission__NodeCreationTool());
        paletteStack.add(create_Permission__DeviceCreationTool());
        paletteStack.add(create_Permission__ExecutionEnvironmentCreationTool());
        paletteStack.add(create_Permission__AssociationClassCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__ClassCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__CLASS, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._PERMISSION__CLASS, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Permission__Class", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Permission__Class_name,
                RbacDSMLMessages.PaletteTool__Permission__Class_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._PERMISSION__CLASS);
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__ProtocolStateMachineCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__PROTOCOLSTATEMACHINE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._PERMISSION__PROTOCOLSTATEMACHINE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Permission__ProtocolStateMachine", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Permission__ProtocolStateMachine_name,
                RbacDSMLMessages.PaletteTool__Permission__ProtocolStateMachine_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._PERMISSION__PROTOCOLSTATEMACHINE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__StateMachineCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__STATEMACHINE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._PERMISSION__STATEMACHINE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Permission__StateMachine", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Permission__StateMachine_name,
                RbacDSMLMessages.PaletteTool__Permission__StateMachine_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._PERMISSION__STATEMACHINE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__StereotypeCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__STEREOTYPE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._PERMISSION__STEREOTYPE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Permission__Stereotype", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Permission__Stereotype_name,
                RbacDSMLMessages.PaletteTool__Permission__Stereotype_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._PERMISSION__STEREOTYPE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__OpaqueBehaviorCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__OPAQUEBEHAVIOR, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._PERMISSION__OPAQUEBEHAVIOR, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Permission__OpaqueBehavior", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Permission__OpaqueBehavior_name,
                RbacDSMLMessages.PaletteTool__Permission__OpaqueBehavior_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._PERMISSION__OPAQUEBEHAVIOR);
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__FunctionBehaviorCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__FUNCTIONBEHAVIOR, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._PERMISSION__FUNCTIONBEHAVIOR, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Permission__FunctionBehavior", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Permission__FunctionBehavior_name,
                RbacDSMLMessages.PaletteTool__Permission__FunctionBehavior_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._PERMISSION__FUNCTIONBEHAVIOR);
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__ActivityCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__ACTIVITY, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._PERMISSION__ACTIVITY, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Permission__Activity", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Permission__Activity_name,
                RbacDSMLMessages.PaletteTool__Permission__Activity_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._PERMISSION__ACTIVITY);
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__InteractionCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__INTERACTION, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._PERMISSION__INTERACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Permission__Interaction", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Permission__Interaction_name,
                RbacDSMLMessages.PaletteTool__Permission__Interaction_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._PERMISSION__INTERACTION);
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__ComponentCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__COMPONENT, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._PERMISSION__COMPONENT, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Permission__Component", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Permission__Component_name,
                RbacDSMLMessages.PaletteTool__Permission__Component_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._PERMISSION__COMPONENT);
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__NodeCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__NODE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._PERMISSION__NODE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Permission__Node", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Permission__Node_name,
                RbacDSMLMessages.PaletteTool__Permission__Node_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._PERMISSION__NODE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__DeviceCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__DEVICE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._PERMISSION__DEVICE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Permission__Device", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Permission__Device_name,
                RbacDSMLMessages.PaletteTool__Permission__Device_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._PERMISSION__DEVICE);
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__ExecutionEnvironmentCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__EXECUTIONENVIRONMENT, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._PERMISSION__EXECUTIONENVIRONMENT, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._Permission__ExecutionEnvironment", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Permission__ExecutionEnvironment_name,
                RbacDSMLMessages.PaletteTool__Permission__ExecutionEnvironment_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._PERMISSION__EXECUTIONENVIRONMENT);
    }

    /**
     * @generated
     */
    private ToolEntry create_Permission__AssociationClassCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._PERMISSION__ASSOCIATIONCLASS, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._PERMISSION__ASSOCIATIONCLASS, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML._Permission__AssociationClass", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__Permission__AssociationClass_name,
                RbacDSMLMessages.PaletteTool__Permission__AssociationClass_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._PERMISSION__ASSOCIATIONCLASS);
    }

    /**
     * @generated
     */
    private PaletteStack createUserStack() {
        ImageDescriptor smallImage = null;
        ImageDescriptor largeImage = null;
        PaletteStack paletteStack = new PaletteStack(RbacDSMLMessages.PaletteStack_User_name, RbacDSMLMessages.PaletteStack_User_description, smallImage);
        paletteStack.setId("User"); //$NON-NLS-1$

        paletteStack.setLargeIcon(largeImage);
        paletteStack.add(create_User__ClassCreationTool());
        paletteStack.add(create_User__ProtocolStateMachineCreationTool());
        paletteStack.add(create_User__StateMachineCreationTool());
        paletteStack.add(create_User__StereotypeCreationTool());
        paletteStack.add(create_User__OpaqueBehaviorCreationTool());
        paletteStack.add(create_User__FunctionBehaviorCreationTool());
        paletteStack.add(create_User__ActivityCreationTool());
        paletteStack.add(create_User__InteractionCreationTool());
        paletteStack.add(create_User__ComponentCreationTool());
        paletteStack.add(create_User__NodeCreationTool());
        paletteStack.add(create_User__DeviceCreationTool());
        paletteStack.add(create_User__ExecutionEnvironmentCreationTool());
        paletteStack.add(create_User__AssociationClassCreationTool());
        return paletteStack;
    }

    /**
     * @generated
     */
    private ToolEntry create_User__ClassCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__CLASS, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._USER__CLASS, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._User__Class", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__User__Class_name,
                RbacDSMLMessages.PaletteTool__User__Class_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._USER__CLASS);
    }

    /**
     * @generated
     */
    private ToolEntry create_User__ProtocolStateMachineCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__PROTOCOLSTATEMACHINE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._USER__PROTOCOLSTATEMACHINE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._User__ProtocolStateMachine", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__User__ProtocolStateMachine_name,
                RbacDSMLMessages.PaletteTool__User__ProtocolStateMachine_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._USER__PROTOCOLSTATEMACHINE);
    }

    /**
     * @generated
     */
    private ToolEntry create_User__StateMachineCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__STATEMACHINE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._USER__STATEMACHINE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._User__StateMachine", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__User__StateMachine_name,
                RbacDSMLMessages.PaletteTool__User__StateMachine_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._USER__STATEMACHINE);
    }

    /**
     * @generated
     */
    private ToolEntry create_User__StereotypeCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__STEREOTYPE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._USER__STEREOTYPE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._User__Stereotype", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__User__Stereotype_name,
                RbacDSMLMessages.PaletteTool__User__Stereotype_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._USER__STEREOTYPE);
    }

    /**
     * @generated
     */
    private ToolEntry create_User__OpaqueBehaviorCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__OPAQUEBEHAVIOR, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._USER__OPAQUEBEHAVIOR, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._User__OpaqueBehavior", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__User__OpaqueBehavior_name,
                RbacDSMLMessages.PaletteTool__User__OpaqueBehavior_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._USER__OPAQUEBEHAVIOR);
    }

    /**
     * @generated
     */
    private ToolEntry create_User__FunctionBehaviorCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__FUNCTIONBEHAVIOR, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._USER__FUNCTIONBEHAVIOR, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._User__FunctionBehavior", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__User__FunctionBehavior_name,
                RbacDSMLMessages.PaletteTool__User__FunctionBehavior_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._USER__FUNCTIONBEHAVIOR);
    }

    /**
     * @generated
     */
    private ToolEntry create_User__ActivityCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__ACTIVITY, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._USER__ACTIVITY, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._User__Activity", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__User__Activity_name,
                RbacDSMLMessages.PaletteTool__User__Activity_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._USER__ACTIVITY);
    }

    /**
     * @generated
     */
    private ToolEntry create_User__InteractionCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__INTERACTION, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._USER__INTERACTION, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._User__Interaction", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__User__Interaction_name,
                RbacDSMLMessages.PaletteTool__User__Interaction_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._USER__INTERACTION);
    }

    /**
     * @generated
     */
    private ToolEntry create_User__ComponentCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__COMPONENT, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._USER__COMPONENT, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._User__Component", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__User__Component_name,
                RbacDSMLMessages.PaletteTool__User__Component_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._USER__COMPONENT);
    }

    /**
     * @generated
     */
    private ToolEntry create_User__NodeCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__NODE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._USER__NODE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._User__Node", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__User__Node_name,
                RbacDSMLMessages.PaletteTool__User__Node_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._USER__NODE);
    }

    /**
     * @generated
     */
    private ToolEntry create_User__DeviceCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__DEVICE, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._USER__DEVICE, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._User__Device", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__User__Device_name,
                RbacDSMLMessages.PaletteTool__User__Device_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._USER__DEVICE);
    }

    /**
     * @generated
     */
    private ToolEntry create_User__ExecutionEnvironmentCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__EXECUTIONENVIRONMENT, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._USER__EXECUTIONENVIRONMENT, resourceSet);    
    
        return new NodeToolEntry(
                "RbacDSML._User__ExecutionEnvironment", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__User__ExecutionEnvironment_name,
                RbacDSMLMessages.PaletteTool__User__ExecutionEnvironment_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._USER__EXECUTIONENVIRONMENT);
    }

    /**
     * @generated
     */
    private ToolEntry create_User__AssociationClassCreationTool() {
        ImageDescriptor smallImage = RbacDSMLUtil.getSmallImage(RbacDSMLElementTypes._USER__ASSOCIATIONCLASS, resourceSet);
        ImageDescriptor largeImage = RbacDSMLUtil.getLargeImage(RbacDSMLElementTypes._USER__ASSOCIATIONCLASS, resourceSet);    
    
        return new ConnectionToolEntry(
                "RbacDSML._User__AssociationClass", //$NON-NLS-1$
                RbacDSMLMessages.PaletteTool__User__AssociationClass_name,
                RbacDSMLMessages.PaletteTool__User__AssociationClass_description,
                smallImage,
                largeImage,
                RbacDSMLElementTypes._USER__ASSOCIATIONCLASS);
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