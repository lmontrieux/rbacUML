package uk.ac.open.rbacuml.plugin.providers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IMetamodelType;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationType;

import org.eclipse.gmf.runtime.notation.View;

import uk.ac.open.rbacuml.plugin.types.RbacUMLElementTypes;

import uk.ac.open.rbacuml.plugin.utils.RbacUMLSemanticHints;

import uk.ac.open.rbacuml.plugin.viewFactories.ActivateRolesrBACRoleRBACRoleLabelViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.ActivateRolesrBACRoleRBACRoleTextViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.ActivateRolesrBACRoleRBACRoleViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.DeactivateRolesrBACRoleRBACRoleLabelViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.DeactivateRolesrBACRoleRBACRoleTextViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.DeactivateRolesrBACRoleRBACRoleViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.ForbiddeninteractionInteractionLabelViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.ForbiddeninteractionInteractionTextViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.ForbiddeninteractionInteractionViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.ForbiddenoperationOperationLabelViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.ForbiddenoperationOperationTextViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.ForbiddenoperationOperationViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.GrantedinteractionInteractionLabelViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.GrantedinteractionInteractionTextViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.GrantedinteractionInteractionViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.GrantedoperationOperationLabelViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.GrantedoperationOperationTextViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.GrantedoperationOperationViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACRoledsod1RBACRoleLabelViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACRoledsod1RBACRoleTextViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACRoledsod1RBACRoleViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACRolepermissionPermissionLabelViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACRolepermissionPermissionTextViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACRolepermissionPermissionViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACRolessod1RBACRoleLabelViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACRolessod1RBACRoleTextViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACRolessod1RBACRoleViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACUseraliasUserRBACUserLabelViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACUseraliasUserRBACUserTextViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACUseraliasUserRBACUserViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACUserrBACRoleRBACRoleLabelViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACUserrBACRoleRBACRoleTextViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RBACUserrBACRoleRBACRoleViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RestrictedpermissionPermissionLabelViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RestrictedpermissionPermissionTextViewFactory;
import uk.ac.open.rbacuml.plugin.viewFactories.RestrictedpermissionPermissionViewFactory;

/**
 * @generated
 */
public class RbacUMLViewProvider
        extends AbstractViewProvider {
    
    /**
     * @generated
     */
    public static Map nodeMap = new HashMap();
    
    /**
     * @generated
     */
    static {
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACUSERRBACROLERBACROLELABELEDITPART, RBACUserrBACRoleRBACRoleLabelViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACUSERRBACROLERBACROLETEXTEDITPART, RBACUserrBACRoleRBACRoleTextViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACUSERALIASUSERRBACUSERLABELEDITPART, RBACUseraliasUserRBACUserLabelViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACUSERALIASUSERRBACUSERTEXTEDITPART, RBACUseraliasUserRBACUserTextViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLEPERMISSIONPERMISSIONLABELEDITPART, RBACRolepermissionPermissionLabelViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLEPERMISSIONPERMISSIONTEXTEDITPART, RBACRolepermissionPermissionTextViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLESSOD1RBACROLELABELEDITPART, RBACRolessod1RBACRoleLabelViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLESSOD1RBACROLETEXTEDITPART, RBACRolessod1RBACRoleTextViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLEDSOD1RBACROLELABELEDITPART, RBACRoledsod1RBACRoleLabelViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLEDSOD1RBACROLETEXTEDITPART, RBACRoledsod1RBACRoleTextViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRESTRICTEDPERMISSIONPERMISSIONLABELEDITPART, RestrictedpermissionPermissionLabelViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLRESTRICTEDPERMISSIONPERMISSIONTEXTEDITPART, RestrictedpermissionPermissionTextViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLGRANTEDOPERATIONOPERATIONLABELEDITPART, GrantedoperationOperationLabelViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLGRANTEDOPERATIONOPERATIONTEXTEDITPART, GrantedoperationOperationTextViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLGRANTEDINTERACTIONINTERACTIONLABELEDITPART, GrantedinteractionInteractionLabelViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLGRANTEDINTERACTIONINTERACTIONTEXTEDITPART, GrantedinteractionInteractionTextViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLFORBIDDENOPERATIONOPERATIONLABELEDITPART, ForbiddenoperationOperationLabelViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLFORBIDDENOPERATIONOPERATIONTEXTEDITPART, ForbiddenoperationOperationTextViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLFORBIDDENINTERACTIONINTERACTIONLABELEDITPART, ForbiddeninteractionInteractionLabelViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLFORBIDDENINTERACTIONINTERACTIONTEXTEDITPART, ForbiddeninteractionInteractionTextViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLACTIVATEROLESRBACROLERBACROLELABELEDITPART, ActivateRolesrBACRoleRBACRoleLabelViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLACTIVATEROLESRBACROLERBACROLETEXTEDITPART, ActivateRolesrBACRoleRBACRoleTextViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLDEACTIVATEROLESRBACROLERBACROLELABELEDITPART, DeactivateRolesrBACRoleRBACRoleLabelViewFactory.class);
        nodeMap.put(RbacUMLSemanticHints.SH_RBACUMLDEACTIVATEROLESRBACROLERBACROLETEXTEDITPART, DeactivateRolesrBACRoleRBACRoleTextViewFactory.class);
    }
    
    /**
     * @generated
     */
    public static Map edgeMap = new HashMap();
    
    /**
     * @generated
     */
    static {
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACUSERRBACROLERBACROLEEDITPART, RBACUserrBACRoleRBACRoleViewFactory.class);
        edgeMap.put(RbacUMLElementTypes.RBACUSER_RBACROLE, RBACUserrBACRoleRBACRoleViewFactory.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACUSERALIASUSERRBACUSEREDITPART, RBACUseraliasUserRBACUserViewFactory.class);
        edgeMap.put(RbacUMLElementTypes.RBACUSER_ALIASUSER, RBACUseraliasUserRBACUserViewFactory.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLEPERMISSIONPERMISSIONEDITPART, RBACRolepermissionPermissionViewFactory.class);
        edgeMap.put(RbacUMLElementTypes.RBACROLE_PERMISSION, RBACRolepermissionPermissionViewFactory.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLESSOD1RBACROLEEDITPART, RBACRolessod1RBACRoleViewFactory.class);
        edgeMap.put(RbacUMLElementTypes.RBACROLE_SSOD1, RBACRolessod1RBACRoleViewFactory.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLRBACROLEDSOD1RBACROLEEDITPART, RBACRoledsod1RBACRoleViewFactory.class);
        edgeMap.put(RbacUMLElementTypes.RBACROLE_DSOD1, RBACRoledsod1RBACRoleViewFactory.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLRESTRICTEDPERMISSIONPERMISSIONEDITPART, RestrictedpermissionPermissionViewFactory.class);
        edgeMap.put(RbacUMLElementTypes.RESTRICTED_PERMISSION, RestrictedpermissionPermissionViewFactory.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLGRANTEDOPERATIONOPERATIONEDITPART, GrantedoperationOperationViewFactory.class);
        edgeMap.put(RbacUMLElementTypes.GRANTED_OPERATION, GrantedoperationOperationViewFactory.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLGRANTEDINTERACTIONINTERACTIONEDITPART, GrantedinteractionInteractionViewFactory.class);
        edgeMap.put(RbacUMLElementTypes.GRANTED_INTERACTION, GrantedinteractionInteractionViewFactory.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLFORBIDDENOPERATIONOPERATIONEDITPART, ForbiddenoperationOperationViewFactory.class);
        edgeMap.put(RbacUMLElementTypes.FORBIDDEN_OPERATION, ForbiddenoperationOperationViewFactory.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLFORBIDDENINTERACTIONINTERACTIONEDITPART, ForbiddeninteractionInteractionViewFactory.class);
        edgeMap.put(RbacUMLElementTypes.FORBIDDEN_INTERACTION, ForbiddeninteractionInteractionViewFactory.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLACTIVATEROLESRBACROLERBACROLEEDITPART, ActivateRolesrBACRoleRBACRoleViewFactory.class);
        edgeMap.put(RbacUMLElementTypes.ACTIVATEROLES_RBACROLE, ActivateRolesrBACRoleRBACRoleViewFactory.class);
        edgeMap.put(RbacUMLSemanticHints.SH_RBACUMLDEACTIVATEROLESRBACROLERBACROLEEDITPART, DeactivateRolesrBACRoleRBACRoleViewFactory.class);
        edgeMap.put(RbacUMLElementTypes.DEACTIVATEROLES_RBACROLE, DeactivateRolesrBACRoleRBACRoleViewFactory.class);
    }

    /**
     * @generated
     */
    protected Class getNodeViewClass(IAdaptable semanticAdapter,
            View containerView, String semanticHint) {
        if (semanticHint != null && semanticHint.length() > 0) {
            return (Class)nodeMap.get(semanticHint);
        }
        IElementType elementType = (IElementType)semanticAdapter.getAdapter(IElementType.class);
        if (elementType != null) {
            return (Class)nodeMap.get(elementType);
        } else {
            EObject eObject = getSemanticElement(semanticAdapter);
            if (eObject != null) {
                // first check specialization type matches
                for (int i = 0; i < RbacUMLElementTypes.NODE_TYPES.length; ++i) {
                    elementType = RbacUMLElementTypes.NODE_TYPES[i];
                    if (elementType instanceof ISpecializationType) {
                        if (((ISpecializationType)elementType).getMatcher().matches(eObject)) {
                            return (Class) nodeMap.get(elementType);
                        }
                    }
                }
                // next check metamodel type matches
                for (int i = 0; i < RbacUMLElementTypes.NODE_TYPES.length; ++i) {
                    elementType = RbacUMLElementTypes.NODE_TYPES[i];
                    if (elementType instanceof IMetamodelType) {
                        if (eObject.eClass() == elementType.getEClass()) {
                            return (Class) nodeMap.get(elementType);
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
    protected Class getEdgeViewClass(IAdaptable semanticAdapter,
            View containerView, String semanticHint) {
        if (semanticHint != null && semanticHint.length() > 0) {
            return (Class)edgeMap.get(semanticHint);
        }
        IElementType elementType = (IElementType)semanticAdapter.getAdapter(IElementType.class);
        if (elementType != null) {
            return (Class)edgeMap.get(elementType);
        } else {
            EObject eObject = getSemanticElement(semanticAdapter);
            if (eObject != null) {
                // first check specialization type matches
                for (int i = 0; i < RbacUMLElementTypes.RELATIONSHIP_TYPES.length; ++i) {
                    elementType = RbacUMLElementTypes.RELATIONSHIP_TYPES[i];
                    if (elementType instanceof ISpecializationType) {
                        if (((ISpecializationType)elementType).getMatcher().matches(eObject)) {
                            return (Class) edgeMap.get(elementType);
                        }
                    }
                }
                // next check metamodel type matches
                for (int i = 0; i < RbacUMLElementTypes.RELATIONSHIP_TYPES.length; ++i) {
                    elementType = RbacUMLElementTypes.RELATIONSHIP_TYPES[i];
                    if (elementType instanceof IMetamodelType) {
                        if (eObject.eClass() == elementType.getEClass()) {
                            return (Class) edgeMap.get(elementType);
                        }
                    }
                }
            }
        }
        return null;       
    }

}