package uk.ac.open.rbacuml.plugin.types;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.emf.type.core.IElementMatcher;

import org.eclipse.uml2.uml.Element;

/**
 * @generated
 */
public class ActivateRolesCallOperationActionMatcher
        implements IElementMatcher {
    
    /**
     * @generated
     */
    public boolean matches(EObject eObject) {
        return RbacUMLElementTypes._ACTIVATEROLES__CALLOPERATIONACTION.getEClass() == eObject.eClass() &&
                ((Element)eObject).getAppliedStereotype(RbacUMLElementTypes._ACTIVATEROLES__CALLOPERATIONACTION.getStereotypeName()) != null;
    }
}