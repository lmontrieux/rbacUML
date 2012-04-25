package uk.ac.open.rbacuml.plugin.types;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.emf.type.core.IElementMatcher;

import org.eclipse.uml2.uml.Element;

/**
 * @generated
 */
public class RestrictedOperationMatcher
        implements IElementMatcher {
    
    /**
     * @generated
     */
    public boolean matches(EObject eObject) {
        return RbacUMLElementTypes._RESTRICTED__OPERATION.getEClass() == eObject.eClass() &&
                ((Element)eObject).getAppliedStereotype(RbacUMLElementTypes._RESTRICTED__OPERATION.getStereotypeName()) != null;
    }
}