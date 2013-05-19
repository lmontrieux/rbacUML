package uk.ac.open.rbacuml.dsml.plugin.types;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.emf.type.core.IElementMatcher;

import org.eclipse.uml2.uml.Element;

/**
 * @generated
 */
public class GrantedNodeMatcher
        implements IElementMatcher {
    
    /**
     * @generated
     */
    public boolean matches(EObject eObject) {
        return RbacDSMLElementTypes._GRANTED__NODE.getEClass() == eObject.eClass() &&
                ((Element)eObject).getAppliedStereotype(RbacDSMLElementTypes._GRANTED__NODE.getStereotypeName()) != null;
    }
}