package uk.ac.open.rbacuml.dsml.plugin.utils;

import java.lang.reflect.Method;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.common.command.Command;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;

import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;

import org.eclipse.uml2.uml.util.UMLUtil;

import uk.ac.open.rbacuml.dsml.plugin.providers.RbacDSMLViewProvider;

import uk.ac.open.rbacuml.dsml.plugin.types.RbacDSMLElementTypes;

/**
 * @generated
 */
public class RbacDSMLResourceSetListener
    extends ResourceSetListenerImpl {

	/**
	 * @generated
	 */
    public RbacDSMLResourceSetListener() {
        super(NotificationFilter.createNotifierTypeFilter(EObject.class));
    }

	/**
	 * @generated
	 */
    public boolean isPrecommitOnly() {
        return true;
    }

	/**
	 * @generated
	 */
    public Command transactionAboutToCommit(ResourceSetChangeEvent event)
        throws RollbackException {

        for (Notification notification : event.getNotifications()) {
            if (notification.getNotifier() instanceof EObject && notification.getFeature() instanceof EReference) {
            	EObject stereotypeApplication = (EObject)notification.getNotifier();
            	Element baseElement = UMLUtil.getBaseElement(stereotypeApplication);
            	Stereotype stereotype = UMLUtil.getStereotype(stereotypeApplication);
            	EReference feature = (EReference)notification.getFeature();
            	
            	if (baseElement != null && stereotype != null && feature instanceof EReference) {
            		EObject oldValue = (EObject)notification.getOldValue();
            		if (oldValue != null) {
            			Element oldTargetElement = (oldValue instanceof Element) ? (Element)oldValue : UMLUtil.getBaseElement(oldValue);
            			
            			Collection baseElementReferences = EObjectUtil.getReferencers(
            					baseElement, new EReference[] {NotationPackage.eINSTANCE.getView_Element()});
            			
            			Collection oldTargetElementReferencers = EObjectUtil.getReferencers(
            					oldTargetElement, new EReference[] {NotationPackage.eINSTANCE.getView_Element()});

            			Collection<Edge> edges = new HashSet<Edge>();
            			for (Object baseElementReferencer : baseElementReferences) {
            				if (baseElementReferencer instanceof Node) {
            					Node baseElementReferencerNode = (Node)baseElementReferencer;
            					List sourceEdges = baseElementReferencerNode.getSourceEdges();
            					if (!sourceEdges.isEmpty()) {
              					for (Object oldTargetElementReferencer : oldTargetElementReferencers) {
              						if (oldTargetElementReferencer instanceof Node) {
              							Node oldTargetElementReferencerNode = (Node)oldTargetElementReferencer;
              							for (Object edge : sourceEdges) {
              								if (oldTargetElementReferencerNode.equals(((Edge)edge).getTarget())) {
              									edges.add((Edge)edge);
              								}
              							}	
              						}
              					}
            					}
            				}
            			}
          					
     				
          				
          				
          				// [31 Dec 08] MLC
          				// This for loop is only useful if there are links in the profile; otherwise, the
          				// generated code has errors.  Generate only if there are non-default edit parts.     
          					
            			for (Edge edge : edges) {
            				String edgeType = edge.getType();
            				Class edgeClass = (Class)RbacDSMLViewProvider.edgeMap.get(edgeType);
            				
              try {
                Method featureMethod = edgeClass.getDeclaredMethod("getSourceFeature", null);
              				Method elementTypeMethod = edgeClass.getDeclaredMethod("getElementType", null);
              				String edgeFeature = (String)featureMethod.invoke(null);
              				IElementType edgeElementType = (IElementType)elementTypeMethod.invoke(null);
              				
                if (feature.getName().equals(edgeFeature) && RbacDSMLElementTypes.matchesSource(edgeElementType, baseElement) && RbacDSMLElementTypes.matchesTarget(edgeElementType, oldTargetElement)) {
                  EObjectUtil.destroy(edge);
                }
              } catch (Exception e) {
                uk.ac.open.rbacuml.dsml.plugin.Activator.getDefault().getLog().log(new Status(IStatus.ERROR, "uk.ac.open.rbacuml.dsml.plugin", -1, e.getLocalizedMessage(), e)); //$NON-NLS-1$
              }	
            			}
            			
            			
            		}
            	}
            }
        }

        return null;
    }
}

