package uk.ac.open.rbacuml.plugin.types;

import com.ibm.xtools.uml.type.IStereotypedElementType;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.emf.type.core.AbstractElementTypeEnumerator;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IMetamodelType;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationType;

/**
 * @generated
 */
public class RbacUMLElementTypes extends AbstractElementTypeEnumerator {

    /**
     * @generated
     */
    public static final IStereotypedElementType _RESTRICTED__MESSAGE = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.RestrictedMessage"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _RBACUSER__ACTIVITYPARTITION = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.RBACUserActivityPartition"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _RBACUSER__CLASS = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.RBACUserClass"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _RBACUSER__STEREOTYPE = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.RBACUserStereotype"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _RBACROLE__CLASS = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.RBACRoleClass"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _RBACROLE__STEREOTYPE = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.RBACRoleStereotype"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _PERMISSION__CLASS = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.PermissionClass"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _PERMISSION__STEREOTYPE = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.PermissionStereotype"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _RESTRICTED__OPERATION = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.RestrictedOperation"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _GRANTED__OPAQUEACTION = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.GrantedOpaqueAction"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _GRANTED__CALLOPERATIONACTION = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.GrantedCallOperationAction"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _GRANTED__CALLBEHAVIORACTION = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.GrantedCallBehaviorAction"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _FORBIDDEN__OPAQUEACTION = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.ForbiddenOpaqueAction"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _FORBIDDEN__CALLOPERATIONACTION = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.ForbiddenCallOperationAction"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _FORBIDDEN__CALLBEHAVIORACTION = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.ForbiddenCallBehaviorAction"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _ACTIVATEROLES__OPAQUEACTION = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.ActivateRolesOpaqueAction"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _ACTIVATEROLES__CALLOPERATIONACTION = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.ActivateRolesCallOperationAction"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _ACTIVATEROLES__CALLBEHAVIORACTION = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.ActivateRolesCallBehaviorAction"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _DEACTIVATEROLES__OPAQUEACTION = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.DeactivateRolesOpaqueAction"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _DEACTIVATEROLES__CALLOPERATIONACTION = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.DeactivateRolesCallOperationAction"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IStereotypedElementType _DEACTIVATEROLES__CALLBEHAVIORACTION = (IStereotypedElementType)getElementType("uk.ac.open.rbacuml.plugin.DeactivateRolesCallBehaviorAction"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final ISpecializationType RBACUSER_RBACROLE = (ISpecializationType)getElementType("uk.ac.open.rbacuml.plugin.RBACUserrBACRoleRBACRole"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final ISpecializationType RBACUSER_ALIASUSER = (ISpecializationType)getElementType("uk.ac.open.rbacuml.plugin.RBACUseraliasUserRBACUser"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final ISpecializationType RBACROLE_PERMISSION = (ISpecializationType)getElementType("uk.ac.open.rbacuml.plugin.RBACRolepermissionPermission"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final ISpecializationType RBACROLE_SSOD1 = (ISpecializationType)getElementType("uk.ac.open.rbacuml.plugin.RBACRolessod1RBACRole"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final ISpecializationType RBACROLE_DSOD1 = (ISpecializationType)getElementType("uk.ac.open.rbacuml.plugin.RBACRoledsod1RBACRole"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final ISpecializationType RESTRICTED_PERMISSION = (ISpecializationType)getElementType("uk.ac.open.rbacuml.plugin.RestrictedpermissionPermission"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final ISpecializationType ACTIVATEROLES_RBACROLE = (ISpecializationType)getElementType("uk.ac.open.rbacuml.plugin.ActivateRolesrBACRoleRBACRole"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final ISpecializationType DEACTIVATEROLES_RBACROLE = (ISpecializationType)getElementType("uk.ac.open.rbacuml.plugin.DeactivateRolesrBACRoleRBACRole"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final ISpecializationType GRANTED_OPERATION = (ISpecializationType)getElementType("uk.ac.open.rbacuml.plugin.GrantedoperationOperation"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final ISpecializationType GRANTED_INTERACTION = (ISpecializationType)getElementType("uk.ac.open.rbacuml.plugin.GrantedinteractionInteraction"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final ISpecializationType FORBIDDEN_OPERATION = (ISpecializationType)getElementType("uk.ac.open.rbacuml.plugin.ForbiddenoperationOperation"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final ISpecializationType FORBIDDEN_INTERACTION = (ISpecializationType)getElementType("uk.ac.open.rbacuml.plugin.ForbiddeninteractionInteraction"); //$NON-NLS-1$

    /**
     * @generated
     */
    public static final IElementType[] NODE_TYPES = {
            _RBACUSER__ACTIVITYPARTITION,
            _RBACUSER__CLASS,
            _RBACUSER__STEREOTYPE,
            _RBACROLE__CLASS,
            _RBACROLE__STEREOTYPE,
            _PERMISSION__CLASS,
            _PERMISSION__STEREOTYPE,
            _RESTRICTED__OPERATION,
            _GRANTED__OPAQUEACTION,
            _GRANTED__CALLOPERATIONACTION,
            _GRANTED__CALLBEHAVIORACTION,
            _FORBIDDEN__OPAQUEACTION,
            _FORBIDDEN__CALLOPERATIONACTION,
            _FORBIDDEN__CALLBEHAVIORACTION,
            _ACTIVATEROLES__OPAQUEACTION,
            _ACTIVATEROLES__CALLOPERATIONACTION,
            _ACTIVATEROLES__CALLBEHAVIORACTION,
            _DEACTIVATEROLES__OPAQUEACTION,
            _DEACTIVATEROLES__CALLOPERATIONACTION,
            _DEACTIVATEROLES__CALLBEHAVIORACTION
        };

    /**
     * @generated
     */
    public static final IElementType[] RELATIONSHIP_TYPES = {
            _RESTRICTED__MESSAGE,
            RBACUSER_RBACROLE,
            RBACUSER_ALIASUSER,
            RBACROLE_PERMISSION,
            RBACROLE_SSOD1,
            RBACROLE_DSOD1,
            RESTRICTED_PERMISSION,
            ACTIVATEROLES_RBACROLE,
            DEACTIVATEROLES_RBACROLE,
            GRANTED_OPERATION,
            GRANTED_INTERACTION,
            FORBIDDEN_OPERATION,
            FORBIDDEN_INTERACTION
        };
    
    /**
     * @generated
     */
    private static Map<IElementType, IElementType[]> sources = new HashMap<IElementType, IElementType[]>();
    
    /**
     * @generated
     */
    static {
            IElementType[] types = new IElementType[0];
            sources.put(RbacUMLElementTypes._RESTRICTED__MESSAGE, types);
            types = new IElementType[3];
            types[0] = RbacUMLElementTypes._RBACUSER__STEREOTYPE;
            types[1] = RbacUMLElementTypes._RBACUSER__ACTIVITYPARTITION;
            types[2] = RbacUMLElementTypes._RBACUSER__CLASS;
            sources.put(RbacUMLElementTypes.RBACUSER_RBACROLE, types);
            types = new IElementType[3];
            types[0] = RbacUMLElementTypes._RBACUSER__STEREOTYPE;
            types[1] = RbacUMLElementTypes._RBACUSER__ACTIVITYPARTITION;
            types[2] = RbacUMLElementTypes._RBACUSER__CLASS;
            sources.put(RbacUMLElementTypes.RBACUSER_ALIASUSER, types);
            types = new IElementType[2];
            types[0] = RbacUMLElementTypes._RBACROLE__CLASS;
            types[1] = RbacUMLElementTypes._RBACROLE__STEREOTYPE;
            sources.put(RbacUMLElementTypes.RBACROLE_PERMISSION, types);
            types = new IElementType[2];
            types[0] = RbacUMLElementTypes._RBACROLE__CLASS;
            types[1] = RbacUMLElementTypes._RBACROLE__STEREOTYPE;
            sources.put(RbacUMLElementTypes.RBACROLE_SSOD1, types);
            types = new IElementType[2];
            types[0] = RbacUMLElementTypes._RBACROLE__CLASS;
            types[1] = RbacUMLElementTypes._RBACROLE__STEREOTYPE;
            sources.put(RbacUMLElementTypes.RBACROLE_DSOD1, types);
            types = new IElementType[2];
            types[0] = RbacUMLElementTypes._RESTRICTED__MESSAGE;
            types[1] = RbacUMLElementTypes._RESTRICTED__OPERATION;
            sources.put(RbacUMLElementTypes.RESTRICTED_PERMISSION, types);
            types = new IElementType[3];
            types[0] = RbacUMLElementTypes._ACTIVATEROLES__CALLBEHAVIORACTION;
            types[1] = RbacUMLElementTypes._ACTIVATEROLES__OPAQUEACTION;
            types[2] = RbacUMLElementTypes._ACTIVATEROLES__CALLOPERATIONACTION;
            sources.put(RbacUMLElementTypes.ACTIVATEROLES_RBACROLE, types);
            types = new IElementType[3];
            types[0] = RbacUMLElementTypes._DEACTIVATEROLES__CALLOPERATIONACTION;
            types[1] = RbacUMLElementTypes._DEACTIVATEROLES__CALLBEHAVIORACTION;
            types[2] = RbacUMLElementTypes._DEACTIVATEROLES__OPAQUEACTION;
            sources.put(RbacUMLElementTypes.DEACTIVATEROLES_RBACROLE, types);
            types = new IElementType[3];
            types[0] = RbacUMLElementTypes._GRANTED__CALLOPERATIONACTION;
            types[1] = RbacUMLElementTypes._GRANTED__OPAQUEACTION;
            types[2] = RbacUMLElementTypes._GRANTED__CALLBEHAVIORACTION;
            sources.put(RbacUMLElementTypes.GRANTED_OPERATION, types);
            types = new IElementType[3];
            types[0] = RbacUMLElementTypes._GRANTED__CALLOPERATIONACTION;
            types[1] = RbacUMLElementTypes._GRANTED__OPAQUEACTION;
            types[2] = RbacUMLElementTypes._GRANTED__CALLBEHAVIORACTION;
            sources.put(RbacUMLElementTypes.GRANTED_INTERACTION, types);
            types = new IElementType[3];
            types[0] = RbacUMLElementTypes._FORBIDDEN__CALLBEHAVIORACTION;
            types[1] = RbacUMLElementTypes._FORBIDDEN__OPAQUEACTION;
            types[2] = RbacUMLElementTypes._FORBIDDEN__CALLOPERATIONACTION;
            sources.put(RbacUMLElementTypes.FORBIDDEN_OPERATION, types);
            types = new IElementType[3];
            types[0] = RbacUMLElementTypes._FORBIDDEN__CALLBEHAVIORACTION;
            types[1] = RbacUMLElementTypes._FORBIDDEN__OPAQUEACTION;
            types[2] = RbacUMLElementTypes._FORBIDDEN__CALLOPERATIONACTION;
            sources.put(RbacUMLElementTypes.FORBIDDEN_INTERACTION, types);
        };
    
    /**
     * @generated
     */
    private static Map<IElementType, IElementType[]> targets = new HashMap<IElementType, IElementType[]>();
    
    /**
     * @generated
     */
    static {
            IElementType[] types = new IElementType[0];
            targets.put(RbacUMLElementTypes._RESTRICTED__MESSAGE, types);
            types = new IElementType[2];
            types[0] = RbacUMLElementTypes._RBACROLE__CLASS;
            types[1] = RbacUMLElementTypes._RBACROLE__STEREOTYPE;
            targets.put(RbacUMLElementTypes.RBACUSER_RBACROLE, types);
            types = new IElementType[3];
            types[0] = RbacUMLElementTypes._RBACUSER__STEREOTYPE;
            types[1] = RbacUMLElementTypes._RBACUSER__ACTIVITYPARTITION;
            types[2] = RbacUMLElementTypes._RBACUSER__CLASS;
            targets.put(RbacUMLElementTypes.RBACUSER_ALIASUSER, types);
            types = new IElementType[2];
            types[0] = RbacUMLElementTypes._PERMISSION__STEREOTYPE;
            types[1] = RbacUMLElementTypes._PERMISSION__CLASS;
            targets.put(RbacUMLElementTypes.RBACROLE_PERMISSION, types);
            types = new IElementType[2];
            types[0] = RbacUMLElementTypes._RBACROLE__CLASS;
            types[1] = RbacUMLElementTypes._RBACROLE__STEREOTYPE;
            targets.put(RbacUMLElementTypes.RBACROLE_SSOD1, types);
            types = new IElementType[2];
            types[0] = RbacUMLElementTypes._RBACROLE__CLASS;
            types[1] = RbacUMLElementTypes._RBACROLE__STEREOTYPE;
            targets.put(RbacUMLElementTypes.RBACROLE_DSOD1, types);
            types = new IElementType[2];
            types[0] = RbacUMLElementTypes._PERMISSION__STEREOTYPE;
            types[1] = RbacUMLElementTypes._PERMISSION__CLASS;
            targets.put(RbacUMLElementTypes.RESTRICTED_PERMISSION, types);
            types = new IElementType[2];
            types[0] = RbacUMLElementTypes._RBACROLE__CLASS;
            types[1] = RbacUMLElementTypes._RBACROLE__STEREOTYPE;
            targets.put(RbacUMLElementTypes.ACTIVATEROLES_RBACROLE, types);
            types = new IElementType[2];
            types[0] = RbacUMLElementTypes._RBACROLE__CLASS;
            types[1] = RbacUMLElementTypes._RBACROLE__STEREOTYPE;
            targets.put(RbacUMLElementTypes.DEACTIVATEROLES_RBACROLE, types);
            types = new IElementType[1];
            types[0] = ElementTypeRegistry.getInstance().getType("com.ibm.xtools.uml.operation"); //$NON-NLS-1$
            targets.put(RbacUMLElementTypes.GRANTED_OPERATION, types);
            types = new IElementType[1];
            types[0] = ElementTypeRegistry.getInstance().getType("com.ibm.xtools.uml.interaction"); //$NON-NLS-1$
            targets.put(RbacUMLElementTypes.GRANTED_INTERACTION, types);
            types = new IElementType[1];
            types[0] = ElementTypeRegistry.getInstance().getType("com.ibm.xtools.uml.operation"); //$NON-NLS-1$
            targets.put(RbacUMLElementTypes.FORBIDDEN_OPERATION, types);
            types = new IElementType[1];
            types[0] = ElementTypeRegistry.getInstance().getType("com.ibm.xtools.uml.interaction"); //$NON-NLS-1$
            targets.put(RbacUMLElementTypes.FORBIDDEN_INTERACTION, types);
        };
    
    /**
     * @generated
     */
    public static final IElementType[] getSources(IElementType elementType) {
        return sources.get(elementType);
    }
    
    /**
     * @generated
     */
    public static final IElementType[] getTargets(IElementType elementType) {
        return targets.get(elementType);
    }
    
    /**
     * @generated
     */
    public static IElementType getMatchingSource(IElementType elementType, EObject source) {
        return matches(getSources(elementType), source);
    }
    
    /**
     * @generated
     */
    public static IElementType getMatchingTarget(IElementType elementType, EObject target) {
        return matches(getTargets(elementType), target);
    }
    
    /**
     * @generated
     */
    public static boolean matchesSource(IElementType elementType, EObject source) {
        IElementType[] sourceTypes = getSources(elementType);
        if (sourceTypes != null && sourceTypes.length == 0) {
            return true;
        }
        return matches(sourceTypes, source) != null;
    }
    
    /**
     * @generated
     */
    public static boolean matchesTarget(IElementType elementType, EObject target) {
        IElementType[] targetTypes = getTargets(elementType);
        if (targetTypes != null && targetTypes.length == 0) {
            return true;
        }
        return matches(targetTypes, target) != null;
    }
    
    /**
     * @generated
     */
    private static IElementType matches(IElementType[] elementTypes, EObject eObject) {
        if (elementTypes == null) {
            return null;
        }
        for (int i = 0; i < elementTypes.length; ++i) {
            IElementType elementType = elementTypes[i];
            if (elementType instanceof ISpecializationType) {
                if (((ISpecializationType)elementType).getMatcher() != null &&
                        ((ISpecializationType)elementType).getMatcher().matches(eObject)) {
                    return elementType;
                }
            } 
        }
        for (int i = 0; i < elementTypes.length; ++i) {
            IElementType elementType = elementTypes[i];
            if (elementType instanceof ISpecializationType) {
                if (((ISpecializationType)elementType).getMatcher() == null &&
                        elementType.getEClass().isSuperTypeOf(eObject.eClass())) {
                    return elementType;
                }
            } else if (elementType instanceof IMetamodelType) {
                if (elementType.getEClass().isSuperTypeOf(eObject.eClass())) {
                    return elementType;
                }
            }
        }
        return null;
    }
}