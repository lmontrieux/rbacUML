package uk.ac.open.rbacuml.dsml.plugin.viewFactories;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.open.rbacuml.dsml.plugin.providers.RbacDSMLDefaultViewProvider.IViewCustomizer;

/**
 * 
 * @generated
 */
public class ResourceProtocolStateMachineViewCustomizer implements IViewCustomizer {

	/**
	 * @generated
	 */
	public static ResourceProtocolStateMachineViewCustomizer INSTANCE = new ResourceProtocolStateMachineViewCustomizer();
	
	/**
	 * @generated
	 */
	private ResourceProtocolStateMachineViewCustomizer() {
    // private constructor
  }
	
	/**
	 * @generated
	 */
	public void customizeView(View view) {
    EPackage umlnotationEPackage = EPackage.Registry.INSTANCE.getEPackage("http://www.ibm.com/xtools/1.5.3/Umlnotation"); //$NON-NLS-1$
    EFactory umlnotationEFactory = umlnotationEPackage.getEFactoryInstance();
      
    EClass umlshapestyleEClass = (EClass)umlnotationEPackage.getEClassifier("UMLShapeStyle"); //$NON-NLS-1$
    if(umlshapestyleEClass != null) {
      Style style = view.getStyle(umlshapestyleEClass);
      if(style == null) {
        style = (Style)umlnotationEFactory.create(umlshapestyleEClass);
        view.getStyles().add(style);
      }
      EStructuralFeature showStereotypeFeature = umlshapestyleEClass.getEStructuralFeature("showStereotype"); //$NON-NLS-1$
      if(showStereotypeFeature != null && showStereotypeFeature.getEType() instanceof EDataType) {
        EDataType showStereotypeFeatureType = (EDataType)showStereotypeFeature.getEType();
        style.eSet(showStereotypeFeature, showStereotypeFeatureType.getEPackage().getEFactoryInstance().createFromString(showStereotypeFeatureType, "Image")); //$NON-NLS-1$
      }
    }
    
  }
}