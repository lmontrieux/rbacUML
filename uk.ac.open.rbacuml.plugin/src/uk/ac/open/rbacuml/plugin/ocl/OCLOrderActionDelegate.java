package uk.ac.open.rbacuml.plugin.ocl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;

import com.ibm.xtools.modeler.ui.UMLModeler;

public class OCLOrderActionDelegate extends AbstractActionDelegate implements
		IViewActionDelegate {

	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		Element elt = (Element)UMLModeler.getUMLUIHelper().getSelectedElements().get(0);
		EList<Package> pkgs = elt.getNearestPackage().allOwningPackages();
		List<Element> elements = null;
		for (Package pkg:pkgs) {
			if (pkg.getNearestPackage().equals(pkg)) {
				//it's the root package
				elements = pkg.allOwnedElements();
			}
		}
		List<Element> elts = new ArrayList<Element>();
		for (Element candidate:elements) {
			if ((candidate instanceof Class)) {
				if (((Class)candidate).getAppliedStereotypes().size() > 0)
					elts.add(candidate);
			}
		}
		OCLOrderedEvaluation.evaluate(elts);
	}

}
