package uk.ac.open.rbacuml.dsml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.lpg.BasicEnvironment;
import org.eclipse.ocl.lpg.ProblemHandler;
import org.eclipse.ocl.options.ProblemOption;
import org.eclipse.ocl.util.OCLUtil;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;


public class RbacDsmlCli {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("USAGE: RbacDsmlCli <org.eclipse.uml2.uml.resources_*.jar> <model>");
		}
		String modelFile = args[1];
		
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		
		// .uml and .profile.uml file extensions registration
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.PROFILE_FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		
		Map uriMap = resourceSet.getURIConverter().getURIMap();
		
		URI uri = URI.createURI("jar:file://" + args[0] + "!/");
		
		uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uri.appendSegment("libraries").appendSegment(""));
		uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uri.appendSegment("metamodels").appendSegment(""));
		uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uri.appendSegment("profiles").appendSegment(""));
		
		Resource modelResource = resourceSet.getResource(URI.createFileURI(modelFile), true);
		List<Diagnostic> warnings = modelResource.getWarnings();
		List<Diagnostic> errors = modelResource.getErrors();
		try {
			modelResource.load(uriMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Element root = (Element) modelResource.getContents().get(0);
		
		List<Element> allElements = root.getNearestPackage().allOwnedElements();
		List<Element> elements = new ArrayList<Element>();
		for (Element element:allElements) {
			if (element.getAppliedStereotypes().size() > 0) {
				elements.add(element);
			}
		}
		
		OCL ocl = OCL.newInstance();
		
		//Setting up OCL evaluation environment
		BasicEnvironment benv = OCLUtil.getAdapter(ocl.getEnvironment(), BasicEnvironment.class);
		benv.setOption(ProblemOption.CLOSURE_ITERATOR, ProblemHandler.Severity.OK);
		
		
		IBatchValidator validator = (IBatchValidator) ModelValidationService.getInstance().newValidator(EvaluationMode.BATCH);
		validator.setOption(IBatchValidator.OPTION_TRACK_RESOURCES, true);
		validator.setOption(IBatchValidator.OPTION_REPORT_SUCCESSES, true);
		
		IStatus status = validator.validate(elements);
		
		if (status.isOK())
			System.out.println("[]");
		else {
			if (status.isMultiStatus()) {
				System.out.print("[");
				for (IStatus cStatus:status.getChildren()) {
					System.out.print("(" + ((ConstraintStatus)cStatus).getMessage() + "," + ((NamedElement)((ConstraintStatus)status).getTarget()).getName() + "),");
				}
				System.out.println("]");
			} else {
				System.out.println("[(" + ((ConstraintStatus)status).getMessage() + "," + ((NamedElement)((ConstraintStatus)status).getTarget()).getName() + ")]");
			}
		}
		
	}

}
