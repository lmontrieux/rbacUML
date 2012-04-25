package uk.ac.open.rbacuml.plugin.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;

public class RBACStereotypeNavigationHelper {
	
	public static List<Class> getRBACRoles(Class user) {
		if (user.getAppliedStereotype("rbacUML::RBACUser") == null)
			throw new IllegalArgumentException("Argument 'user' must be a Class stereotyped with rbacUML::RBACUser");
		List<Class> rbacroles = new ArrayList<Class>();
		for (Object object:(List)navigate(user, user.getAppliedStereotype("rbacUML::RBACUser"), "rBACRole", "base_Class", true)) {
			rbacroles.add((Class)object);
		}
		return rbacroles;
	}
	
	public static List<Class> getPermissions(Class rbacrole) {
		if (rbacrole.getAppliedStereotype("rbacUML::RBACRole") == null)
			throw new IllegalArgumentException("Argument 'rbacrole' must be a Class stereeotyped with rbacUML::RBACRole");
		// casting the Objects in the list to org.eclipse.uml2.uml.Class
		List<Class> permissions = new ArrayList<Class>();
		for (Object object:(List)navigate(rbacrole, rbacrole.getAppliedStereotype("rbacUML::RBACRole"), "permission", "base_Class", true)) {
			permissions.add((Class)object);
		}
		return permissions;
	}
	
	private static Object navigate(Element element, Stereotype stereotype, String propertyName, String base, boolean collection) {
		if (collection) {
			List result = new ArrayList();
			Object value = element.getValue(stereotype, propertyName);
			if (value instanceof EcoreEList<?>) {
				EcoreEList<DynamicEObjectImpl> list = (EcoreEList<DynamicEObjectImpl>)value;
				for (DynamicEObjectImpl object:list) {
					EList<EStructuralFeature> features = object.eClass().getEAllStructuralFeatures();
					for (EStructuralFeature feature:features) {
						if (feature.getName().equals(base)) {
							Setting setting = object.eSetting(feature);
							result.add(setting.get(true));
						}
					}
				}
			}
			return result;
		} else {
			// TODO: implement this
			return false;
		}
	}
}
