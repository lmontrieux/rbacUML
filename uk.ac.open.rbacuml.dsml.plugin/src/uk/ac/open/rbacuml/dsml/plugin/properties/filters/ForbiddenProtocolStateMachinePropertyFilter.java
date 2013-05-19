package uk.ac.open.rbacuml.dsml.plugin.properties.filters;

import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.jface.viewers.IFilter;

import uk.ac.open.rbacuml.dsml.plugin.types.RbacDSMLElementTypes;

/**
 * @generated
 */
public class ForbiddenProtocolStateMachinePropertyFilter
    implements IFilter {

    /**
 	* @generated
 	*/
    public boolean select(Object toTest) {
        if (toTest instanceof IAdaptable) {
        	EObject eObject = (EObject)((IAdaptable)toTest).getAdapter(EObject.class);
        	if (eObject != null) {
           		return RbacDSMLElementTypes._FORBIDDEN__PROTOCOLSTATEMACHINE.getMatcher().matches(eObject);
           	}
        }
        return false;
    }
}
