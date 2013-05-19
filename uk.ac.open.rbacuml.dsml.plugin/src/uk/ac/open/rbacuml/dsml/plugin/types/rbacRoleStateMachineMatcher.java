package uk.ac.open.rbacuml.dsml.plugin.types;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.emf.type.core.IElementMatcher;

import org.eclipse.uml2.uml.Element;

/**
 * @generated
 */
public class rbacRoleStateMachineMatcher
        implements IElementMatcher {
    
    /**
     * @generated
     */
    public boolean matches(EObject eObject) {
        return RbacDSMLElementTypes._RBACROLE__STATEMACHINE.getEClass() == eObject.eClass() &&
                ((Element)eObject).getAppliedStereotype(RbacDSMLElementTypes._RBACROLE__STATEMACHINE.getStereotypeName()) != null;
    }
}