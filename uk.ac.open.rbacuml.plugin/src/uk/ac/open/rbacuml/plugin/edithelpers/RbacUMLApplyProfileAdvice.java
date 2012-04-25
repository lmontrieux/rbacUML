    package uk.ac.open.rbacuml.plugin.edithelpers;

import com.ibm.xtools.common.ui.reduction.util.EditingCapabilitiesUtil;

import com.ibm.xtools.uml.type.UMLElementTypes;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;

import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import org.eclipse.ui.IPluginContribution;
import org.eclipse.ui.PlatformUI;

import org.eclipse.ui.activities.ITriggerPoint;
import org.eclipse.ui.activities.ITriggerPointManager;
import org.eclipse.ui.activities.WorkbenchActivityHelper;

import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.ProfileApplication;

import uk.ac.open.rbacuml.plugin.l10n.RbacUMLMessages;

import uk.ac.open.rbacuml.plugin.utils.RbacUMLUtil;

/**
 * @generated
 */
public class RbacUMLApplyProfileAdvice
        extends AbstractEditHelperAdvice {

    /**
     * @generated
     */
    protected ICommand getAfterCreateRelationshipCommand(
            final CreateRelationshipRequest request) {
        if (request.getElementType() == UMLElementTypes.PROFILE_APPLICATION) {
            return new AbstractTransactionalCommand(request.getEditingDomain(),
                    RbacUMLMessages.CommandLabel_setProfileEditingCapabilities, null) {

                /**
                 * @generated
                 */
                protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
                        IAdaptable info) throws ExecutionException {
                    ProfileApplication profileApplication = (ProfileApplication)request.getNewElement();
                    if (profileApplication.getAppliedProfile().equals(RbacUMLUtil.getProfile())) {
						EObject root = EcoreUtil.getRootContainer(profileApplication.getApplyingPackage());
                        if (root instanceof Package) {
                        	Package rootPackage = (Package)root;
   
	                        boolean hasEnabledEditingCapabilities = EditingCapabilitiesUtil.hasEnabledEditingCapabilities(rootPackage);
	                        if (hasEnabledEditingCapabilities ||
	                                EditingCapabilitiesUtil.hasDisabledEditingCapabilities(rootPackage)) {
	                            Set<String> requiredActivityIds = new HashSet<String>();
	                            Set<String> modelRequiredActivityIds = EditingCapabilitiesUtil.getRequiredActivityIds(rootPackage);
	                            if (modelRequiredActivityIds != null) {
	                                requiredActivityIds.addAll(modelRequiredActivityIds);
	                            }
	                            requiredActivityIds.add(RbacUMLUtil.RBACUML_TOOLING_ACTIVITY_ID);
	                            EditingCapabilitiesUtil.setRequiredActivityIds(rootPackage, requiredActivityIds);
	                        }
	                        
	                        if (hasEnabledEditingCapabilities) {
	                            EditingCapabilitiesUtil.reenableActivities();
	                        } else {
	                            ITriggerPoint point = PlatformUI.getWorkbench()
	                                    .getActivitySupport().getTriggerPointManager()
	                                    .getTriggerPoint(ITriggerPointManager.UNKNOWN_TRIGGER_POINT_ID);
	                            IPluginContribution contribution = new IPluginContribution() {
	                                
	                                public String getLocalId() {
	                                    return RbacUMLUtil.RBACUML_TOOLING_ACTIVITY_ID;
	                                }
	                                
	                                public String getPluginId() {
	                                    return uk.ac.open.rbacuml.plugin.Activator.PLUGIN_ID;
	                                }
	                            };
	                            WorkbenchActivityHelper.allowUseOf(point, contribution);
	                        }
	                    }
                    }
                    return CommandResult.newOKCommandResult();
                }
            };
        }
        return super.getAfterCreateRelationshipCommand(request);
    }
    
    /**
     * @generated
     */
    protected ICommand getBeforeDestroyElementCommand(
            final DestroyElementRequest request) {
        final EObject elementToDestroy = request.getElementToDestroy();
        if (elementToDestroy instanceof ProfileApplication) {
            return new AbstractTransactionalCommand(request.getEditingDomain(),
                    RbacUMLMessages.CommandLabel_unsetProfileEditingCapabilities, null) {

                /**
                 * @generated
                 */
                protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
                        IAdaptable info) throws ExecutionException {
                    ProfileApplication profileApplication = (ProfileApplication)elementToDestroy;
                    if (profileApplication.getAppliedProfile().equals(RbacUMLUtil.getProfile())) {
                       EObject root = EcoreUtil.getRootContainer(profileApplication.getApplyingPackage());
                       if (root instanceof Package) {
                       		Package rootPackage = (Package)root;
							if (EditingCapabilitiesUtil.hasEnabledEditingCapabilities(rootPackage) ||
                           		EditingCapabilitiesUtil.hasDisabledEditingCapabilities(rootPackage)) {
                            	Set<String> requiredActivityIds = new HashSet<String>(EditingCapabilitiesUtil.getRequiredActivityIds(rootPackage));
                            	requiredActivityIds.remove(RbacUMLUtil.RBACUML_TOOLING_ACTIVITY_ID);
                            	EditingCapabilitiesUtil.setRequiredActivityIds(rootPackage, requiredActivityIds);
                            	EditingCapabilitiesUtil.reenableActivities();
                            }
                        }
                    }
                    return CommandResult.newOKCommandResult();
                }
            };
        }
        return super.getAfterDestroyElementCommand(request);
    }
}