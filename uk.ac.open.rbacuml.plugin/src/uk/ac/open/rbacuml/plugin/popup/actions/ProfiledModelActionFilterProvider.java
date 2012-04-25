package uk.ac.open.rbacuml.plugin.popup.actions;

import com.ibm.xtools.uml.navigator.ModelServerElement;

import com.ibm.xtools.umlnotation.UMLDiagram;

import org.eclipse.gmf.runtime.common.core.service.IOperation;

import org.eclipse.gmf.runtime.common.ui.services.action.filter.AbstractActionFilterProvider;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Profile;

/**
 * @generated
 */
public class ProfiledModelActionFilterProvider extends AbstractActionFilterProvider {
	
	private static final String IS_PROFILED_MODEL = "isProfiledModel"; //$NON-NLS-1$
	
	private static final String IS_ENABLED = "isEnabled"; //$NON-NLS-1$
	
	private static final String APPLIEDPROFILENAME = "rbacUML"; //$NON-NLS-1$
	
	public boolean testAttribute(Object target, String name, String value) {
		
		// We only want to show the profile tooling on elements that are
		// in a model with the profile attached.  And even then, not on all
		// elements.  This method is recursive; if we find a classifier, we
		// follow its owner up the chain until we come to the owning Package;
		// then check that for the applied profile.
		if (name.equals(IS_PROFILED_MODEL)) {
			
			if (target instanceof Package) {
				return checkForAppliedProfile((Package) target);
			}
			
			if (target instanceof Classifier) {
				return testAttribute(((Classifier) target).getOwner(), name, value);
			}
			
			if (target instanceof ModelServerElement) {
				ModelServerElement mse = (ModelServerElement) target;
			
				// We do not want to show the menu for certain elements, e.g.,
				// diagrams, primitive types, etc.
				if (mse.getElement() instanceof UMLDiagram
						|| mse.getElement() instanceof PrimitiveType
						|| mse.getElement() instanceof PackageImport) {
					return false;
				} else if (mse.getElement() instanceof Package) {
					return checkForAppliedProfile((Package) mse.getElement());
				} else if (mse.getElement() instanceof Classifier) {
					Classifier classImpl = (Classifier) mse.getElement();
					return testAttribute(classImpl.getOwner(), name, value);
				}
			}
			
			return false;
			
		}
		
		// Some entries in the popup menu should be disabled.  This is just a quick
		// and dirty way to permit this.
		if (name.equals(IS_ENABLED)) {
			if (value.equalsIgnoreCase("no")) { //$NON-NLS-1$
				return false;
			} else {
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * This method checks if the profile from the profile tooling has been 
	 * applied to the incoming package.
	 * 
	 * @param pkg Package to check for profile tooling profile
	 * @return true if profile has been applied to package
	 */
	private boolean checkForAppliedProfile(Package pkg) {
	
		for (Profile profile : pkg.getAllAppliedProfiles()) {
			if (profile.getName().equals(APPLIEDPROFILENAME)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean provides(IOperation operation) {
		// Never called?
		return false;
	}
	
}