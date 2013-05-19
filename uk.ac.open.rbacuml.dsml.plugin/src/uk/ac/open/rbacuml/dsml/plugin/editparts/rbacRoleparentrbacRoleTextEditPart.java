package uk.ac.open.rbacuml.dsml.plugin.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;

import org.eclipse.gmf.runtime.diagram.ui.editparts.TextCompartmentEditPart;

import org.eclipse.gmf.runtime.diagram.ui.label.ILabelDelegate;
import org.eclipse.gmf.runtime.diagram.ui.label.WrappingLabelDelegate;

import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;

import org.eclipse.gmf.runtime.notation.View;

import uk.ac.open.rbacuml.dsml.plugin.l10n.RbacDSMLMessages;

/**
 * @generated
 */
public class rbacRoleparentrbacRoleTextEditPart
        extends TextCompartmentEditPart {

    /**
     * @generated
     */
    public rbacRoleparentrbacRoleTextEditPart(View view) {
        super(view);
    }
    
    /**
     * @generated
     */
    protected String getLabelText() {
        return RbacDSMLMessages.RbacRoleparentrbacRoleTextEditPart_text;
    }
    
    /**
     * @generated
     */
    public void setFigure(IFigure figure) {
        super.setFigure(figure);
    }
    
    /**
     * @generated
     */
    protected ILabelDelegate createLabelDelegate() {
        ILabelDelegate newLabelDelegate = new WrappingLabelDelegate(
                (WrappingLabel) getFigure());
        
        newLabelDelegate.setTextJustification(PositionConstants.CENTER);
        newLabelDelegate.setAlignment(PositionConstants.CENTER);
        newLabelDelegate.setTextAlignment(PositionConstants.TOP);
        return newLabelDelegate;
    }
}