package uk.ac.open.rbacuml.dsml.plugin.properties.filters;

import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.jface.viewers.IFilter;

import uk.ac.open.rbacuml.dsml.plugin.types.RbacDSMLElementTypes;

/**
 * @generated
 */
public class rbacRoleDevicePropertyFilter
    implements IFilter {

    /**
 	* @generated
 	*/
    public boolean select(Object toTest) {
        if (toTest instanceof IAdaptable) {
        	EObject eObject = (EObject)((IAdaptable)toTest).getAdapter(EObject.class);
        	if (eObject != null) {
           		return RbacDSMLElementTypes._RBACROLE__DEVICE.getMatcher().matches(eObject);
           	}
        }
        return false;
    }
}
