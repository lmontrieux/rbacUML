package uk.ac.open.rbacuml.dsml.plugin.properties.sections;

import com.ibm.xtools.modeler.ui.properties.sections.StereotypeContributedPropertiesSection;

import com.ibm.xtools.uml.ui.properties.stereotypes.IStereotypePropertySourceDelegate;

import uk.ac.open.rbacuml.dsml.plugin.properties.delegates.SSoDExtensionPropertySourceFactoryDelegate;

/**
 * @generated
 */
public class SSoDExtensionPropertySection
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
        return new SSoDExtensionPropertySourceFactoryDelegate();
    }
}
