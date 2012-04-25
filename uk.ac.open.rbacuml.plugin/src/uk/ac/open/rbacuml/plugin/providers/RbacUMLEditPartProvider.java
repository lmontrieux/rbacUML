package uk.ac.open.rbacuml.plugin.providers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IMetamodelType;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationType;

import org.eclipse.gmf.runtime.notation.View;

import uk.ac.open.rbacuml.plugin.editparts.ActivateRolesrBACRoleRBACRoleEditPart;
import uk.ac.open.rbacuml.plugin.editparts.ActivateRolesrBACRoleRBACRoleLabelEditPart;
import uk.ac.open.rbacuml.plugin.editparts.ActivateRolesrBACRoleRBACRoleTextEditPart;
import uk.ac.open.rbacuml.plugin.editparts.DeactivateRolesrBACRoleRBACRoleEditPart;
import uk.ac.open.rbacuml.plugin.editparts.DeactivateRolesrBACRoleRBACRoleLabelEditPart;
import uk.ac.open.rbacuml.plugin.editparts.DeactivateRolesrBACRoleRBACRoleTextEditPart;
import uk.ac.open.rbacuml.plugin.editparts.ForbiddeninteractionInteractionEditPart;
import uk.ac.open.rbacuml.plugin.editparts.ForbiddeninteractionInteractionLabelEditPart;
import uk.ac.open.rbacuml.plugin.editparts.ForbiddeninteractionInteractionTextEditPart;
import uk.ac.open.rbacuml.plugin.editparts.ForbiddenoperationOperationEditPart;
import uk.ac.open.rbacuml.plugin.editparts.ForbiddenoperationOperationLabelEditPart;
import uk.ac.open.rbacuml.plugin.editparts.ForbiddenoperationOperationTextEditPart;
import uk.ac.open.rbacuml.plugin.editparts.GrantedinteractionInteractionEditPart;
import uk.ac.open.rbacuml.plugin.editparts.GrantedinteractionInteractionLabelEditPart;
import uk.ac.open.rbacuml.plugin.editparts.GrantedinteractionInteractionTextEditPart;
import uk.ac.open.rbacuml.plugin.editparts.GrantedoperationOperationEditPart;
import uk.ac.open.rbacuml.plugin.editparts.GrantedoperationOperationLabelEditPart;
import uk.ac.open.rbacuml.plugin.editparts.GrantedoperationOperationTextEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACRoledsod1RBACRoleEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACRoledsod1RBACRoleLabelEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACRoledsod1RBACRoleTextEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACRolepermissionPermissionEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACRolepermissionPermissionLabelEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACRolepermissionPermissionTextEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACRolessod1RBACRoleEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACRolessod1RBACRoleLabelEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACRolessod1RBACRoleTextEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACUseraliasUserRBACUserEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACUseraliasUserRBACUserLabelEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACUseraliasUserRBACUserTextEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACUserrBACRoleRBACRoleEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACUserrBACRoleRBACRoleLabelEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RBACUserrBACRoleRBACRoleTextEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RestrictedpermissionPermissionEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RestrictedpermissionPermissionLabelEditPart;
import uk.ac.open.rbacuml.plugin.editparts.RestrictedpermissionPermissionTextEditPart;

import uk.ac.open.rbacuml.plugin.types.RbacUMLElementTypes;

import uk.ac.open.rbacuml.plugin.utils.RbacUMLSemanticHints;

/**
 * @generated
 */
public class RbacUMLEditPartProvider
        extends AbstractEditPartProvider {

    /**
     * @generated
     */
    private static Map nodeMap = new HashMap();
    
    /**
     * @generated
     */
    static {
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACUSERRBACROLERBACROLELABELEDITPART, RBACUserrBACRoleRBACRoleLabelEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACUSERRBACROLERBACROLETEXTEDITPART, RBACUserrBACRoleRBACRoleTextEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACUSERALIASUSERRBACUSERLABELEDITPART, RBACUseraliasUserRBACUserLabelEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACUSERALIASUSERRBACUSERTEXTEDITPART, RBACUseraliasUserRBACUserTextEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLEPERMISSIONPERMISSIONLABELEDITPART, RBACRolepermissionPermissionLabelEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLEPERMISSIONPERMISSIONTEXTEDITPART, RBACRolepermissionPermissionTextEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLESSOD1RBACROLELABELEDITPART, RBACRolessod1RBACRoleLabelEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLESSOD1RBACROLETEXTEDITPART, RBACRolessod1RBACRoleTextEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLEDSOD1RBACROLELABELEDITPART, RBACRoledsod1RBACRoleLabelEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLEDSOD1RBACROLETEXTEDITPART, RBACRoledsod1RBACRoleTextEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRESTRICTEDPERMISSIONPERMISSIONLABELEDITPART, RestrictedpermissionPermissionLabelEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRESTRICTEDPERMISSIONPERMISSIONTEXTEDITPART, RestrictedpermissionPermissionTextEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLGRANTEDOPERATIONOPERATIONLABELEDITPART, GrantedoperationOperationLabelEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLGRANTEDOPERATIONOPERATIONTEXTEDITPART, GrantedoperationOperationTextEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLGRANTEDINTERACTIONINTERACTIONLABELEDITPART, GrantedinteractionInteractionLabelEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLGRANTEDINTERACTIONINTERACTIONTEXTEDITPART, GrantedinteractionInteractionTextEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLFORBIDDENOPERATIONOPERATIONLABELEDITPART, ForbiddenoperationOperationLabelEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLFORBIDDENOPERATIONOPERATIONTEXTEDITPART, ForbiddenoperationOperationTextEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLFORBIDDENINTERACTIONINTERACTIONLABELEDITPART, ForbiddeninteractionInteractionLabelEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLFORBIDDENINTERACTIONINTERACTIONTEXTEDITPART, ForbiddeninteractionInteractionTextEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLACTIVATEROLESRBACROLERBACROLELABELEDITPART, ActivateRolesrBACRoleRBACRoleLabelEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLACTIVATEROLESRBACROLERBACROLETEXTEDITPART, ActivateRolesrBACRoleRBACRoleTextEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLDEACTIVATEROLESRBACROLERBACROLELABELEDITPART, DeactivateRolesrBACRoleRBACRoleLabelEditPart.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLDEACTIVATEROLESRBACROLERBACROLETEXTEDITPART, DeactivateRolesrBACRoleRBACRoleTextEditPart.class);
    }
    
    /**
     * @generated
     */
    private static Map edgeMap = new HashMap();
    
    /**
     * @generated
     */
    static {
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACUSERRBACROLERBACROLEEDITPART, RBACUserrBACRoleRBACRoleEditPart.class);
        edgeMap.put(RbacUMLElementTypes.RBACUSER_RBACROLE, RBACUserrBACRoleRBACRoleEditPart.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACUSERALIASUSERRBACUSEREDITPART, RBACUseraliasUserRBACUserEditPart.class);
        edgeMap.put(RbacUMLElementTypes.RBACUSER_ALIASUSER, RBACUseraliasUserRBACUserEditPart.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLEPERMISSIONPERMISSIONEDITPART, RBACRolepermissionPermissionEditPart.class);
        edgeMap.put(RbacUMLElementTypes.RBACROLE_PERMISSION, RBACRolepermissionPermissionEditPart.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLESSOD1RBACROLEEDITPART, RBACRolessod1RBACRoleEditPart.class);
        edgeMap.put(RbacUMLElementTypes.RBACROLE_SSOD1, RBACRolessod1RBACRoleEditPart.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLEDSOD1RBACROLEEDITPART, RBACRoledsod1RBACRoleEditPart.class);
        edgeMap.put(RbacUMLElementTypes.RBACROLE_DSOD1, RBACRoledsod1RBACRoleEditPart.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLRESTRICTEDPERMISSIONPERMISSIONEDITPART, RestrictedpermissionPermissionEditPart.class);
        edgeMap.put(RbacUMLElementTypes.RESTRICTED_PERMISSION, RestrictedpermissionPermissionEditPart.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLGRANTEDOPERATIONOPERATIONEDITPART, GrantedoperationOperationEditPart.class);
        edgeMap.put(RbacUMLElementTypes.GRANTED_OPERATION, GrantedoperationOperationEditPart.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLGRANTEDINTERACTIONINTERACTIONEDITPART, GrantedinteractionInteractionEditPart.class);
        edgeMap.put(RbacUMLElementTypes.GRANTED_INTERACTION, GrantedinteractionInteractionEditPart.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLFORBIDDENOPERATIONOPERATIONEDITPART, ForbiddenoperationOperationEditPart.class);
        edgeMap.put(RbacUMLElementTypes.FORBIDDEN_OPERATION, ForbiddenoperationOperationEditPart.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLFORBIDDENINTERACTIONINTERACTIONEDITPART, ForbiddeninteractionInteractionEditPart.class);
        edgeMap.put(RbacUMLElementTypes.FORBIDDEN_INTERACTION, ForbiddeninteractionInteractionEditPart.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLACTIVATEROLESRBACROLERBACROLEEDITPART, ActivateRolesrBACRoleRBACRoleEditPart.class);
        edgeMap.put(RbacUMLElementTypes.ACTIVATEROLES_RBACROLE, ActivateRolesrBACRoleRBACRoleEditPart.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLDEACTIVATEROLESRBACROLERBACROLEEDITPART, DeactivateRolesrBACRoleRBACRoleEditPart.class);
        edgeMap.put(RbacUMLElementTypes.DEACTIVATEROLES_RBACROLE, DeactivateRolesrBACRoleRBACRoleEditPart.class);
    }
    
    /**
     * @generated
     */
    protected Class getNodeEditPartClass(View view) {
        String type = view.getType();
        if (type != null && type.length() > 0) {
            return (Class) nodeMap.get(view.getType());
        }
        EObject eObject = view.getElement();
        if (eObject != null) {
            // first check specialization type matches
            for (int i = 0; i < RbacUMLElementTypes.NODE_TYPES.length; ++i) {
                IElementType elementType = RbacUMLElementTypes.NODE_TYPES[i];
                if (elementType instanceof ISpecializationType) {
                    if (((ISpecializationType)elementType).getMatcher().matches(eObject)) {
                        return (Class) nodeMap.get(elementType);
                    }
                }
            }
            // next check metamodel type matches
            for (int i = 0; i < RbacUMLElementTypes.NODE_TYPES.length; ++i) {
                IElementType elementType = RbacUMLElementTypes.NODE_TYPES[i];
                if (elementType instanceof IMetamodelType) {
                    if (eObject.eClass() == elementType.getEClass()) {
                        return (Class) nodeMap.get(elementType);
                    }
                }
            }
        }
        return null;
    }

    /**
     * @generated
     */
    protected Class getEdgeEditPartClass(View view) {
        String type = view.getType();
        if (type != null && type.length() > 0) {
            return (Class) edgeMap.get(view.getType());
        }
        EObject eObject = view.getElement();
        if (eObject != null) {
            // first check specialization type matches
            for (int i = 0; i < RbacUMLElementTypes.RELATIONSHIP_TYPES.length; ++i) {
                IElementType elementType = RbacUMLElementTypes.RELATIONSHIP_TYPES[i];
                if (elementType instanceof ISpecializationType) {
                    if (((ISpecializationType)elementType).getMatcher().matches(eObject)) {
                        return (Class) edgeMap.get(elementType);
                    }
                }
            }
            // next check metamodel type matches
            for (int i = 0; i < RbacUMLElementTypes.RELATIONSHIP_TYPES.length; ++i) {
                IElementType elementType = RbacUMLElementTypes.RELATIONSHIP_TYPES[i];
                if (elementType instanceof IMetamodelType) {
                    if (eObject.eClass() == elementType.getEClass()) {
                        return (Class) edgeMap.get(elementType);
                    }
                }
            }
        }
        return null;
    }
}