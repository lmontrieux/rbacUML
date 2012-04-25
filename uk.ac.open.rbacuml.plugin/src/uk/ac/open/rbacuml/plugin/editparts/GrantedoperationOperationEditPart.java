package uk.ac.open.rbacuml.plugin.editparts;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolylineDecoration;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;

import org.eclipse.gef.commands.Command;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;

import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;

import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;

import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;

import org.eclipse.gmf.runtime.notation.View;

import uk.ac.open.rbacuml.plugin.providers.RbacUMLAssociationEditPolicy.IProfileAssociationConnectionEditPart;

import uk.ac.open.rbacuml.plugin.types.RbacUMLElementTypes;

/**
 * @generated
 */
public class GrantedoperationOperationEditPart
        extends ConnectionNodeEditPart implements IProfileAssociationConnectionEditPart {

    /**
     * @generated
     */
    public GrantedoperationOperationEditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected Connection createConnectionFigure() {
        PolylineConnectionEx connection = new PolylineConnectionEx();
        PolylineDecoration targetDecoration = new PolylineDecoration();
        IMapMode mm = getMapMode();
        targetDecoration.setScale(mm.DPtoLP(10),mm.DPtoLP(5));
        connection.setTargetDecoration(targetDecoration);
        return connection;
    }
    
    /**
     * @generated
     */
    public Command getCommand(Request request) {
		if (request instanceof EditCommandRequestWrapper) {
			EditCommandRequestWrapper requestWrapper = (EditCommandRequestWrapper)request;
			IEditCommandRequest editRequest = requestWrapper.getEditCommandRequest();
			if (editRequest instanceof DestroyElementRequest) {
				editRequest.setParameter("SOURCE_FEATURE", getSourceFeature()); //$NON-NLS-1$
				editRequest.setParameter("ELEMENT_TYPE", getElementType()); //$NON-NLS-1$
			} else if (editRequest instanceof ReorientReferenceRelationshipRequest) {
				new Object();
			}
		}
	
		return super.getCommand(request);
    }

    /**
     * @generated
     */
	public String getSourceFeature() {
		return "operation"; //$NON-NLS-1$
	}
	
    /**
     * @generated
     */
	public IElementType getElementType() {
		return RbacUMLElementTypes.GRANTED_OPERATION;
	}
}