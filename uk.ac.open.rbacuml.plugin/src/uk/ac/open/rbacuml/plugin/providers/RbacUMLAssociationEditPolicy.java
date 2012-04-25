package uk.ac.open.rbacuml.plugin.providers;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;

import org.eclipse.gef.commands.Command;

import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import org.eclipse.gef.requests.ReconnectRequest;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class RbacUMLAssociationEditPolicy extends AbstractEditPolicy
		implements EditPolicy {

	/**
 	 * @generated
     */
	public Command getCommand(Request request) {
		if (request instanceof ReconnectRequest) {
			ReconnectRequest reconnectRequest = (ReconnectRequest)request;
			ConnectionEditPart connectionEP = reconnectRequest.getConnectionEditPart();
			if (connectionEP instanceof IProfileAssociationConnectionEditPart) {
				IProfileAssociationConnectionEditPart associationEP = (IProfileAssociationConnectionEditPart)connectionEP;
				request.getExtendedData().put("SOURCE_FEATURE", associationEP.getSourceFeature()); //$NON-NLS-1$
				request.getExtendedData().put("ELEMENT_TYPE", associationEP.getElementType()); //$NON-NLS-1$
			}
		}
		
		return super.getCommand(request);
	}
	
	/**
 	 * @generated
     */	
	public interface IProfileAssociationConnectionEditPart {
		public String getSourceFeature();
		public IElementType getElementType();
	}
}


