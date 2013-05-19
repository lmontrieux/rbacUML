package uk.ac.open.rbacuml.dsml.plugin.research;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;

public class RDNavHelper {
	private static Logger log = Logger.getLogger("RDNavHelper");

	/**
	 * Gets all users from a role
	 * 
	 * @param user the user
	 * @return a list of roles
	 */
	public static List<Class> userGetRoles(Class user) {
		List<Class> roles = new ArrayList<Class>();
		
		for (Property prop:user.getAllAttributes()) {
			Class role = (Class)prop.getType();
			if (role.getAppliedStereotype(RDSterHelper.ROLE) != null)
				roles.add(role);
		}
		
		return roles;
	}
	
	/**
	 * Gets all SSoD roles from a given role
	 * 
	 * @param role the source role
	 * @return a list of roles involved in SSoD with the input role
	 */
	public static List<Class> roleGetSSoD(Class role) {
		List<Class> ssods = new ArrayList<Class>();
		for (Property prop:((Class)role).getAllAttributes()) {
			Class opposite = (Class)prop.getType();
			Association association = prop.getAssociation();
			if ((opposite.getAppliedStereotype(RDSterHelper.ROLE) != null)
					&& (association.getAppliedStereotype(RDSterHelper.SSOD) != null))
				ssods.add(opposite);
		}
		
		return ssods;
	}
	
	public static List<Class> roleGetDSoD(Class role) {
		List<Class> dsods = new ArrayList<Class>();
		for (Property prop:((Class)role).getAllAttributes()) {
			Class opposite = (Class)prop.getType();
			Association association = prop.getAssociation();
			if ((opposite.getAppliedStereotype(RDSterHelper.ROLE) != null)
					&& (association.getAppliedStereotype(RDSterHelper.DSOD) != null))
				dsods.add(opposite);
		}
		
		return dsods;
	}
	
	public static List<Class> scenarioGetResources(Class scenario) {
		List<Class> resources = new ArrayList<Class>();
		for (Property prop:((Class) scenario).getAllAttributes()) {
			Class resource = (Class)prop.getType();
			if (resource.getAppliedStereotype(RDSterHelper.RESOURCE) != null)
				resources.add(resource);
		}
		return resources;
	}
	
	public static List<Class> resourceGetPermissions(Class resource) {
		List<Class> permissions = new ArrayList<Class>();
		for (Property prop:((Class)resource).getAllAttributes()) {
			Class permission = (Class)prop.getType();
			if (permission.getAppliedStereotype(RDSterHelper.PERMISSION) != null)
				permissions.add(permission);
		}
		return permissions;
	}

	public static List<Class> scenarioGetRoles(Class scenario) {
		List<Class> roles = new ArrayList<Class>();
		for (Property prop:((Class)scenario).getAllAttributes()) {
			Class role = (Class)prop.getType();
			if (role.getAppliedStereotype(RDSterHelper.ROLE) != null)
				roles.add(role);
		}
		return roles;
	}

	public static List<Class> roleGetPermissions(Class role) {
		List<Class> permissions = new ArrayList<Class>();
		for (Property prop:((Class)role).getAllAttributes()) {
			Class permission = (Class)prop.getType();
			if (permission.getAppliedStereotype(RDSterHelper.PERMISSION) != null)
				permissions.add(permission);
		}
		return permissions;
	}

	public static Class scenarioGetUser(Class scenario) {
		for (Property prop:((Class)scenario).getAllAttributes()) {
			Class user = (Class)prop.getType();
			if (user.getAppliedStereotype(RDSterHelper.USER) != null) {
				return user;
			}
		}
		log.fatal("Didn't find any user");
		throw new RuntimeException("Didn't find user for scenario " + scenario.toString());
	}

	public static List<Class> permissionGetRoles(Class permission) {
		List<Class> roles = new ArrayList<Class>();
		for (Property prop:((Class)permission).getAllAttributes()) {
			Class role = (Class)prop.getType();
			if ((role.getAppliedStereotype(RDSterHelper.ROLE) != null) 
					&& (!roles.contains(role)))
				roles.add(role);			
		}
		return roles;
	}
	
	public static List<Class> roleGetScenarios(Class role) {
		List<Class> scenarios = new ArrayList<Class>();
		for (Property prop:((Class)role).getAllAttributes()) {
			Class scenario = (Class)prop.getType();
			if (((scenario.getAppliedStereotype(RDSterHelper.GRANTED) != null) 
					|| (scenario.getAppliedStereotype(RDSterHelper.FORBIDDEN) != null))
					&& (!scenarios.contains(scenario))) {
				scenarios.add(scenario);
			}
		}
		return scenarios;
	}
	
	public static List<Class> roleGetUsers(Class role) {
		List<Class> users = new ArrayList<Class>();
		for (Property prop:((Class)role).getAllAttributes()) {
			Class user = (Class)prop.getType();
			if ((user.getAppliedStereotype(RDSterHelper.USER) != null)
					&& (!users.contains(user))) {
				users.add(user);
			}
		}
		return users;
	}
	

	public static List<Class> userGetScenarios(Class user) {
		List<Class> scenarios = new ArrayList<Class>();
		for (Property prop:((Class)user).getAllAttributes()) {
			Class scenario = (Class)prop.getType();
			if (((scenario.getAppliedStereotype(RDSterHelper.GRANTED) != null) 
					|| (scenario.getAppliedStereotype(RDSterHelper.FORBIDDEN) != null))
					&& (!scenarios.contains(scenario))) {
				scenarios.add(scenario);
			}
		}
		return scenarios;
	}
	
	/**
	 * Returns the association between two classes. The association can have a 
	 * particular stereotype. Only returns one association, even if there are 
	 * several ones.
	 * @param end1 the first end of the association
	 * @param end2 the other end of the association
	 * @param stereotype not null if the association must be stereotyped with a 
	 * particular stereotype
	 * @return an association if one exists, or null
	 */
	public static Association getAssociation(Class end1, Class end2, 
			String stereotype) {
		for (Property prop:end1.getAllAttributes()) {
			if (((Class)prop.getType()).equals(end2)) {
				if (((stereotype != null) 
						&& (prop.getAssociation().getAppliedStereotype(stereotype) != null)) 
						|| (stereotype == null)) {
					return prop.getAssociation();
				}
			}
		}
		for (Property prop:end2.getAllAttributes()) {
			if (((Class)prop.getType()).equals(end1)) {
				if (((stereotype != null) 
						&& (prop.getAssociation().getAppliedStereotype(stereotype) != null)) 
						|| (stereotype == null)) {
					return prop.getAssociation();
				}
			}
		}
		return null;
	}

	public static List<Class> getAllRoles(Class element) {
		List<Class> roles = new ArrayList<Class>();
		for (Element elt:element.getNearestPackage().getOwnedElements()) {
			if (elt.getAppliedStereotype(RDSterHelper.ROLE) != null)
				roles.add((Class)elt);
		}
		return roles;
	}

	public static List<Class> getAllUsers(Class element) {
		List<Class> users = new ArrayList<Class>();
		for (Element elt:element.getNearestPackage().getOwnedElements()) {
			if (elt.getAppliedStereotype(RDSterHelper.USER) != null)
				users.add((Class)elt);
		}
		return users;
	}

	public static List<Class> getAllPermissions(Class element) {
		List<Class> permissions = new ArrayList<Class>();
		for (Element elt:element.getNearestPackage().getOwnedElements()) {
			if (elt.getAppliedStereotype(RDSterHelper.PERMISSION) != null)
				permissions.add((Class)elt);
		}
		return permissions;
	}
	
	public static List<Class> getAllResources(Class element) {
		List<Class> resources = new ArrayList<Class>();
		for (Element elt:element.getNearestPackage().getOwnedElements()) {
			if (elt.getAppliedStereotype(RDSterHelper.RESOURCE) != null)
				resources.add((Class)elt);
		}
		return resources;
	}
}
