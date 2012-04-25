package uk.ac.open.rbacuml.plugin.viewFactories;

import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;

import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractLabelViewFactory;

import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.open.rbacuml.plugin.utils.RbacUMLSemanticHints;

/**
 * @generated
 */
public class GrantedoperationOperationLabelViewFactory
        extends AbstractLabelViewFactory {
    
    /**
     * @generated
     */
    protected void decorateView(View containerView, View view,
            IAdaptable semanticAdapter, String semanticHint, int index,
            boolean persisted) {

        super.decorateView(containerView, view, semanticAdapter, semanticHint, index,
                persisted);

        getViewService().createNode(semanticAdapter, view, RbacUMLSemanticHints.SH_RBACUMLGRANTEDOPERATIONOPERATIONTEXTEDITPART, ViewUtil.APPEND, persisted, getPreferencesHint());

        LayoutConstraint layoutConstraint = ((Node) view).getLayoutConstraint();
        if (layoutConstraint instanceof Size) {
            Size size = (Size) layoutConstraint;
            size.setWidth(-1);
            size.setHeight(-1);
        }
    }
    
}