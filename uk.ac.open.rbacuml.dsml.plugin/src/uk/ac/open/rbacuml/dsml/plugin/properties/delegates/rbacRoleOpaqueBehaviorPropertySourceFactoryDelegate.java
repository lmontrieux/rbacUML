package uk.ac.open.rbacuml.dsml.plugin.properties.delegates;

import com.ibm.xtools.uml.ui.properties.stereotypes.ICollectionItemLabelProvider;
import com.ibm.xtools.uml.ui.properties.stereotypes.IStereotypePropertySourceDelegate;
import com.ibm.xtools.uml.ui.properties.stereotypes.IStereotypePropertySourceFactory;

import org.eclipse.gmf.runtime.common.core.command.CommandResult;

import org.eclipse.gmf.runtime.common.ui.services.properties.extended.PropertyId;

import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;

/**
 * @generated
 */
public class rbacRoleOpaqueBehaviorPropertySourceFactoryDelegate
    implements IStereotypePropertySourceDelegate {
    
    /**
 	 * @generated
 	 */
    public boolean filterStereotype(Stereotype stereotype) {    
        return !"rbacDSML::rbacRole".equals(stereotype.getQualifiedName()); //$NON-NLS-1$
    }

    /**
 	 * @generated
 	 */
    public boolean filterProperty(Property nextProperty) {
        return false;
    }

    /**
 	 * @generated
 	 */
    public boolean addSpecializedProperty(
            IStereotypePropertySourceFactory sourceFactory,
            Property nextProperty, String displayName, String modelId,
            PropertyId propertyId, Type type, boolean isReadOnly) {
        return false;
    }

    /**
 	 * @generated
 	 */
    public CommandResult handleSpecializedCollectionPropertyItemInsert(
            IStereotypePropertySourceFactory sourceFactory, Property property,
            String displayName, String modelId, String itemId) {
        return null;
    }
   
    /**
 	 * @generated
 	 */    
    public boolean useCategories() {
        return false;
    }
   
    /**
 	 * @generated
 	 */
    public ICollectionItemLabelProvider getCollectionLabelProvider(
            Property property, Type collectionType) {
        return null;
    }
}
