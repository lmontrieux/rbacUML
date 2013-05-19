package uk.ac.open.rbacuml.dsml.plugin.providers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IMetamodelType;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationType;

import org.eclipse.gmf.runtime.notation.View;

import uk.ac.open.rbacuml.dsml.plugin.editparts.ResourcepermissionPermissionEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.ResourcepermissionPermissionLabelEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.ResourcepermissionPermissionTextEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.ScenariorbacRolerbacRoleEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.ScenariorbacRolerbacRoleLabelEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.ScenariorbacRolerbacRoleTextEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.ScenarioresourceResourceEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.ScenarioresourceResourceLabelEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.ScenarioresourceResourceTextEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.ScenariouserUserEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.ScenariouserUserLabelEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.ScenariouserUserTextEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.UserrbacRolerbacRoleEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.UserrbacRolerbacRoleLabelEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.UserrbacRolerbacRoleTextEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.rbacRoledsod1rbacRoleEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.rbacRoledsod1rbacRoleLabelEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.rbacRoledsod1rbacRoleTextEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.rbacRoleparentrbacRoleEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.rbacRoleparentrbacRoleLabelEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.rbacRoleparentrbacRoleTextEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.rbacRolepermissionPermissionEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.rbacRolepermissionPermissionLabelEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.rbacRolepermissionPermissionTextEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.rbacRolerbacRolerbacRoleLabelEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.rbacRolessod1rbacRoleEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.rbacRolessod1rbacRoleLabelEditPart;
import uk.ac.open.rbacuml.dsml.plugin.editparts.rbacRolessod1rbacRoleTextEditPart;

import uk.ac.open.rbacuml.dsml.plugin.types.RbacDSMLElementTypes;

import uk.ac.open.rbacuml.dsml.plugin.utils.RbacDSMLSemanticHints;

/**
 * @generated
 */
public class RbacDSMLEditPartProvider
        extends AbstractEditPartProvider {

    /**
     * @generated
     */
    private static Map nodeMap = new HashMap();
    
    /**
     * @generated
     */
    static {
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLUSERRBACROLERBACROLELABELEDITPART, UserrbacRolerbacRoleLabelEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLUSERRBACROLERBACROLETEXTEDITPART, UserrbacRolerbacRoleTextEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEPERMISSIONPERMISSIONLABELEDITPART, rbacRolepermissionPermissionLabelEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEPERMISSIONPERMISSIONTEXTEDITPART, rbacRolepermissionPermissionTextEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLESSOD1RBACROLELABELEDITPART, rbacRolessod1rbacRoleLabelEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLESSOD1RBACROLETEXTEDITPART, rbacRolessod1rbacRoleTextEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEDSOD1RBACROLELABELEDITPART, rbacRoledsod1rbacRoleLabelEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEDSOD1RBACROLETEXTEDITPART, rbacRoledsod1rbacRoleTextEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEPARENTRBACROLELABELEDITPART, rbacRoleparentrbacRoleLabelEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEPARENTRBACROLETEXTEDITPART, rbacRoleparentrbacRoleTextEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRESOURCEPERMISSIONPERMISSIONLABELEDITPART, ResourcepermissionPermissionLabelEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRESOURCEPERMISSIONPERMISSIONTEXTEDITPART, ResourcepermissionPermissionTextEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIORBACROLERBACROLELABELEDITPART, ScenariorbacRolerbacRoleLabelEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIORBACROLERBACROLETEXTEDITPART, ScenariorbacRolerbacRoleTextEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIOUSERUSERLABELEDITPART, ScenariouserUserLabelEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIOUSERUSERTEXTEDITPART, ScenariouserUserTextEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIORESOURCERESOURCELABELEDITPART, ScenarioresourceResourceLabelEditPart.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIORESOURCERESOURCETEXTEDITPART, ScenarioresourceResourceTextEditPart.class);
    }
    
    /**
     * @generated
     */
    private static Map edgeMap = new HashMap();
    
    /**
     * @generated
     */
    static {
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLUSERRBACROLERBACROLEEDITPART, UserrbacRolerbacRoleEditPart.class);
        edgeMap.put(RbacDSMLElementTypes.USER_RBACROLE, UserrbacRolerbacRoleEditPart.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEPERMISSIONPERMISSIONEDITPART, rbacRolepermissionPermissionEditPart.class);
        edgeMap.put(RbacDSMLElementTypes.RBACROLE_PERMISSION, rbacRolepermissionPermissionEditPart.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLESSOD1RBACROLEEDITPART, rbacRolessod1rbacRoleEditPart.class);
        edgeMap.put(RbacDSMLElementTypes.RBACROLE_SSOD1, rbacRolessod1rbacRoleEditPart.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEDSOD1RBACROLEEDITPART, rbacRoledsod1rbacRoleEditPart.class);
        edgeMap.put(RbacDSMLElementTypes.RBACROLE_DSOD1, rbacRoledsod1rbacRoleEditPart.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEPARENTRBACROLEEDITPART, rbacRoleparentrbacRoleEditPart.class);
        edgeMap.put(RbacDSMLElementTypes.RBACROLE_PARENT, rbacRoleparentrbacRoleEditPart.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRESOURCEPERMISSIONPERMISSIONEDITPART, ResourcepermissionPermissionEditPart.class);
        edgeMap.put(RbacDSMLElementTypes.RESOURCE_PERMISSION, ResourcepermissionPermissionEditPart.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIORBACROLERBACROLEEDITPART, ScenariorbacRolerbacRoleEditPart.class);
        edgeMap.put(RbacDSMLElementTypes.SCENARIO_RBACROLE, ScenariorbacRolerbacRoleEditPart.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIOUSERUSEREDITPART, ScenariouserUserEditPart.class);
        edgeMap.put(RbacDSMLElementTypes.SCENARIO_USER, ScenariouserUserEditPart.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIORESOURCERESOURCEEDITPART, ScenarioresourceResourceEditPart.class);
        edgeMap.put(RbacDSMLElementTypes.SCENARIO_RESOURCE, ScenarioresourceResourceEditPart.class);
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
            for (int i = 0; i < RbacDSMLElementTypes.NODE_TYPES.length; ++i) {
                IElementType elementType = RbacDSMLElementTypes.NODE_TYPES[i];
                if (elementType instanceof ISpecializationType) {
                    if (((ISpecializationType)elementType).getMatcher().matches(eObject)) {
                        return (Class) nodeMap.get(elementType);
                    }
                }
            }
            // next check metamodel type matches
            for (int i = 0; i < RbacDSMLElementTypes.NODE_TYPES.length; ++i) {
                IElementType elementType = RbacDSMLElementTypes.NODE_TYPES[i];
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
            for (int i = 0; i < RbacDSMLElementTypes.RELATIONSHIP_TYPES.length; ++i) {
                IElementType elementType = RbacDSMLElementTypes.RELATIONSHIP_TYPES[i];
                if (elementType instanceof ISpecializationType) {
                    if (((ISpecializationType)elementType).getMatcher().matches(eObject)) {
                        return (Class) edgeMap.get(elementType);
                    }
                }
            }
            // next check metamodel type matches
            for (int i = 0; i < RbacDSMLElementTypes.RELATIONSHIP_TYPES.length; ++i) {
                IElementType elementType = RbacDSMLElementTypes.RELATIONSHIP_TYPES[i];
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