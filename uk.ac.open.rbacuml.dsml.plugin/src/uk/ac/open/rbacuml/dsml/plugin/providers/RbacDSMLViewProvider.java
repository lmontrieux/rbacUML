package uk.ac.open.rbacuml.dsml.plugin.providers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IMetamodelType;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationType;

import org.eclipse.gmf.runtime.notation.View;

import uk.ac.open.rbacuml.dsml.plugin.types.RbacDSMLElementTypes;

import uk.ac.open.rbacuml.dsml.plugin.utils.RbacDSMLSemanticHints;

import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourcepermissionPermissionLabelViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourcepermissionPermissionTextViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ResourcepermissionPermissionViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ScenariorbacRolerbacRoleLabelViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ScenariorbacRolerbacRoleTextViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ScenariorbacRolerbacRoleViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ScenarioresourceResourceLabelViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ScenarioresourceResourceTextViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ScenarioresourceResourceViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ScenariouserUserLabelViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ScenariouserUserTextViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.ScenariouserUserViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserrbacRolerbacRoleLabelViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserrbacRolerbacRoleTextViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.UserrbacRolerbacRoleViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoledsod1rbacRoleLabelViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoledsod1rbacRoleTextViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoledsod1rbacRoleViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleparentrbacRoleLabelViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleparentrbacRoleTextViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRoleparentrbacRoleViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRolepermissionPermissionLabelViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRolepermissionPermissionTextViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRolepermissionPermissionViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRolerbacRolerbacRoleTextViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRolessod1rbacRoleLabelViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRolessod1rbacRoleTextViewFactory;
import uk.ac.open.rbacuml.dsml.plugin.viewFactories.rbacRolessod1rbacRoleViewFactory;

/**
 * @generated
 */
public class RbacDSMLViewProvider
        extends AbstractViewProvider {
    
    /**
     * @generated
     */
    public static Map nodeMap = new HashMap();
    
    /**
     * @generated
     */
    static {
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLUSERRBACROLERBACROLELABELEDITPART, UserrbacRolerbacRoleLabelViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLUSERRBACROLERBACROLETEXTEDITPART, UserrbacRolerbacRoleTextViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEPERMISSIONPERMISSIONLABELEDITPART, rbacRolepermissionPermissionLabelViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEPERMISSIONPERMISSIONTEXTEDITPART, rbacRolepermissionPermissionTextViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLESSOD1RBACROLELABELEDITPART, rbacRolessod1rbacRoleLabelViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLESSOD1RBACROLETEXTEDITPART, rbacRolessod1rbacRoleTextViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEDSOD1RBACROLELABELEDITPART, rbacRoledsod1rbacRoleLabelViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEDSOD1RBACROLETEXTEDITPART, rbacRoledsod1rbacRoleTextViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEPARENTRBACROLELABELEDITPART, rbacRoleparentrbacRoleLabelViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEPARENTRBACROLETEXTEDITPART, rbacRoleparentrbacRoleTextViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRESOURCEPERMISSIONPERMISSIONLABELEDITPART, ResourcepermissionPermissionLabelViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRESOURCEPERMISSIONPERMISSIONTEXTEDITPART, ResourcepermissionPermissionTextViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIORBACROLERBACROLELABELEDITPART, ScenariorbacRolerbacRoleLabelViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIORBACROLERBACROLETEXTEDITPART, ScenariorbacRolerbacRoleTextViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIOUSERUSERLABELEDITPART, ScenariouserUserLabelViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIOUSERUSERTEXTEDITPART, ScenariouserUserTextViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIORESOURCERESOURCELABELEDITPART, ScenarioresourceResourceLabelViewFactory.class);
        nodeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIORESOURCERESOURCETEXTEDITPART, ScenarioresourceResourceTextViewFactory.class);
    }
    
    /**
     * @generated
     */
    public static Map edgeMap = new HashMap();
    
    /**
     * @generated
     */
    static {
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLUSERRBACROLERBACROLEEDITPART, UserrbacRolerbacRoleViewFactory.class);
        edgeMap.put(RbacDSMLElementTypes.USER_RBACROLE, UserrbacRolerbacRoleViewFactory.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEPERMISSIONPERMISSIONEDITPART, rbacRolepermissionPermissionViewFactory.class);
        edgeMap.put(RbacDSMLElementTypes.RBACROLE_PERMISSION, rbacRolepermissionPermissionViewFactory.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLESSOD1RBACROLEEDITPART, rbacRolessod1rbacRoleViewFactory.class);
        edgeMap.put(RbacDSMLElementTypes.RBACROLE_SSOD1, rbacRolessod1rbacRoleViewFactory.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEDSOD1RBACROLEEDITPART, rbacRoledsod1rbacRoleViewFactory.class);
        edgeMap.put(RbacDSMLElementTypes.RBACROLE_DSOD1, rbacRoledsod1rbacRoleViewFactory.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRBACROLEPARENTRBACROLEEDITPART, rbacRoleparentrbacRoleViewFactory.class);
        edgeMap.put(RbacDSMLElementTypes.RBACROLE_PARENT, rbacRoleparentrbacRoleViewFactory.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLRESOURCEPERMISSIONPERMISSIONEDITPART, ResourcepermissionPermissionViewFactory.class);
        edgeMap.put(RbacDSMLElementTypes.RESOURCE_PERMISSION, ResourcepermissionPermissionViewFactory.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIORBACROLERBACROLEEDITPART, ScenariorbacRolerbacRoleViewFactory.class);
        edgeMap.put(RbacDSMLElementTypes.SCENARIO_RBACROLE, ScenariorbacRolerbacRoleViewFactory.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIOUSERUSEREDITPART, ScenariouserUserViewFactory.class);
        edgeMap.put(RbacDSMLElementTypes.SCENARIO_USER, ScenariouserUserViewFactory.class);
        edgeMap.put(RbacDSMLSemanticHints.SH_RBACDSMLSCENARIORESOURCERESOURCEEDITPART, ScenarioresourceResourceViewFactory.class);
        edgeMap.put(RbacDSMLElementTypes.SCENARIO_RESOURCE, ScenarioresourceResourceViewFactory.class);
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
                for (int i = 0; i < RbacDSMLElementTypes.NODE_TYPES.length; ++i) {
                    elementType = RbacDSMLElementTypes.NODE_TYPES[i];
                    if (elementType instanceof ISpecializationType) {
                        if (((ISpecializationType)elementType).getMatcher().matches(eObject)) {
                            return (Class) nodeMap.get(elementType);
                        }
                    }
                }
                // next check metamodel type matches
                for (int i = 0; i < RbacDSMLElementTypes.NODE_TYPES.length; ++i) {
                    elementType = RbacDSMLElementTypes.NODE_TYPES[i];
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
                for (int i = 0; i < RbacDSMLElementTypes.RELATIONSHIP_TYPES.length; ++i) {
                    elementType = RbacDSMLElementTypes.RELATIONSHIP_TYPES[i];
                    if (elementType instanceof ISpecializationType) {
                        if (((ISpecializationType)elementType).getMatcher().matches(eObject)) {
                            return (Class) edgeMap.get(elementType);
                        }
                    }
                }
                // next check metamodel type matches
                for (int i = 0; i < RbacDSMLElementTypes.RELATIONSHIP_TYPES.length; ++i) {
                    elementType = RbacDSMLElementTypes.RELATIONSHIP_TYPES[i];
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