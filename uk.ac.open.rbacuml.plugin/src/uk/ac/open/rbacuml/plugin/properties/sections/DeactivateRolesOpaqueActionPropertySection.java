package uk.ac.open.rbacuml.plugin.properties.sections;

import com.ibm.xtools.modeler.ui.properties.sections.StereotypeContributedPropertiesSection;

import com.ibm.xtools.uml.ui.properties.stereotypes.IStereotypePropertySourceDelegate;

import uk.ac.open.rbacuml.plugin.properties.delegates.DeactivateRolesOpaqueActionPropertySourceFactoryDelegate;

/**
 * @generated
 */
public class DeactivateRolesOpaqueActionPropertySection
    extends StereotypeContributedPropertiesSection {

	/**
 	 * @generated
 	 */
    protected String getTableLabel() {
        return null;
    }  

	/**
 	 * @generated
 	 */
    protected IStereotypePropertySourceDelegate getStereotypePropertySourceDelegate() {
        return new DeactivateRolesOpaqueActionPropertySourceFactoryDelegate();
    }
}
