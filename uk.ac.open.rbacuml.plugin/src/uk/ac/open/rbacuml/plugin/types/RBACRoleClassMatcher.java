package uk.ac.open.rbacuml.plugin.types;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.emf.type.core.IElementMatcher;

import org.eclipse.uml2.uml.Element;

/**
 * @generated
 */
public class RBACRoleClassMatcher
        implements IElementMatcher {
    
    /**
     * @generated
     */
    public boolean matches(EObject eObject) {
        return RbacUMLElementTypes._RBACROLE__CLASS.getEClass() == eObject.eClass() &&
                ((Element)eObject).getAppliedStereotype(RbacUMLElementTypes._RBACROLE__CLASS.getStereotypeName()) != null;
    }
}