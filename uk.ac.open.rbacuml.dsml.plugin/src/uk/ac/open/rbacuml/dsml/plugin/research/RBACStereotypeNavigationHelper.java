package uk.ac.open.rbacuml.dsml.plugin.research;

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
	
	public static List<Class> getUserRBACRoles(Class user) {
		if (user.getAppliedStereotype("rbacDSML::User") == null) 
			throw new IllegalArgumentException("Argument 'user' must be a Class stereotyped with rbacDSML::User");
		List<Class> rbacroles = new ArrayList<Class>();
		for (Object object:(List)navigate(user, user.getAppliedStereotype("rbacDSML::User"), "rbacRole", "base_Class", true)) {
			rbacroles.add((Class)object);
		}
		return rbacroles;
	}
	
	public static List<Class> getScenarioRBACRoles(Class scenario) {
		if (scenario.getAppliedStereotype("rbacDSML::Scenario") == null) 
			throw new IllegalArgumentException("Argument 'scenario' must be a Class stereotyped with rbacDSML::Scenario");
		List<Class> rbacroles = new ArrayList<Class>();
		for (Object object:(List)navigate(scenario, scenario.getAppliedStereotype("rbacDSML::Scenario"), "rbacRole", "base_Class", true)) {
			rbacroles.add((Class)object);
		}
		return rbacroles;
	}
	
	public static List<Class> getPermissions(Class user) {
		if ((user.getAppliedStereotype("rbacDSML::Resource") == null) 
				&& (user.getAppliedStereotype("rbacDSML::rbacRole") == null))
			throw new IllegalArgumentException("Argument 'user' must be a Class stereotyped with rbacDSML::Resource or rbacDSML::rbacRole");
		List<Class> permissions = new ArrayList<Class>();
		for (Object object:(List)navigate(user, user.getAppliedStereotype("rbacDSML::Resource"), "permission", "base_Class", true)) {
			permissions.add((Class)object);
		}
		return permissions;
	}
	
	public static Class getUser(Class scenario) {
		if (scenario.getAppliedStereotype("rbacDSML::Scenario") == null)
			throw new IllegalArgumentException("Argument 'scenario' must be a Class stereotyped with rbacDSML::Scenario");
		Class user = (Class)navigate(scenario, scenario.getAppliedStereotype("rbacDSML::Scenario"), "rbacRole", "base_Class", false);
		return user;
	}
	
	public static List<Class> getResources(Class scenario) {
		if (scenario.getAppliedStereotype("rbacDSML::Scenario") == null)
			throw new IllegalArgumentException("Argument 'scenario' must be a Class stereotyped with rbacDSML::Scenario");
		List<Class> resources = new ArrayList<Class>();
		for (Object object:(List)navigate(scenario, scenario.getAppliedStereotype("rbacDSML::Scenario"), "resource", "base_Class", true)) {
			resources.add((Class)object);
		}
		return resources;
	}
	
	public static List<Class> getSSoD(Class role) {
		if (role.getAppliedStereotype("rbacDSML::rbacRole") == null)
			throw new IllegalArgumentException("Argument 'role' must be a Class stereotyped with rbacDSML::rbacRole");
		List<Class> rbacroles = new ArrayList<Class>();
		for (Object object:(List)navigate(role, role.getAppliedStereotype("rbacDSML::rbacRole"), "ssod1", "base_Class", true)) {
			rbacroles.add((Class)object);
		}
		return rbacroles;
	}
	
	public static List<Class> getDSoD(Class role) {
		if (role.getAppliedStereotype("rbacDSML::rbacRole") == null)
			throw new IllegalArgumentException("Argument 'role' must be a Class stereotyped with rbacDSML::rbacRole");
		List<Class> rbacroles = new ArrayList<Class>();
		for (Object object:(List)navigate(role, role.getAppliedStereotype("rbacDSML::rbacRole"), "dsod1", "base_Class", true)) {
			rbacroles.add((Class)object);
		}
		return rbacroles;
	}
	
	private static Object navigate(Element element, Stereotype stereotype, String propertyName, String base, boolean collection) {
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
		if (collection)
			return result;
		else if (result.size() > 0)
			return result.get(0);
		else
			return null;
	}

}
