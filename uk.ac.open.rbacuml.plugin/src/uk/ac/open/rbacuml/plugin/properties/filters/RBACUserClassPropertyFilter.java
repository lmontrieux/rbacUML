package uk.ac.open.rbacuml.plugin.properties.filters;

import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.jface.viewers.IFilter;

import uk.ac.open.rbacuml.plugin.types.RbacUMLElementTypes;

/**
 * @generated
 */
public class RBACUserClassPropertyFilter
    implements IFilter {

    /**
 	* @generated
 	*/
    public boolean select(Object toTest) {
        if (toTest instanceof IAdaptable) {
        	EObject eObject = (EObject)((IAdaptable)toTest).getAdapter(EObject.class);
        	if (eObject != null) {
           		return RbacUMLElementTypes._RBACUSER__CLASS.getMatcher().matches(eObject);
           	}
        }
        return false;
    }
}
