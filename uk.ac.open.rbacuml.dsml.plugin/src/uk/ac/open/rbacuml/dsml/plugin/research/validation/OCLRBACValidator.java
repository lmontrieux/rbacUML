/**
 * 
 */
package uk.ac.open.rbacuml.dsml.plugin.research.validation;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.validation.marker.IMarkerConfigurator;
import org.eclipse.emf.validation.marker.MarkerUtil;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.lpg.BasicEnvironment;
import org.eclipse.ocl.lpg.ProblemHandler;
import org.eclipse.ocl.options.ProblemOption;
import org.eclipse.ocl.uml.OCL;
import org.eclipse.ocl.uml.OCL.Helper;
import org.eclipse.ocl.uml.OCL.Query;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.util.OCLUtil;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;

import com.ibm.xtools.modeler.ui.UMLModeler;

/**
 * @author Lionel Montrieux <L.M.C.Montrieux@open.ac.uk>
 *
 */
public class OCLRBACValidator implements IRBACValidator {
	private static OCLRBACValidator singleton;
	private OCL ocl;
	private Helper helper;
	private ResourceSet resourceSet;
	private IBatchValidator validator;
	Logger log = Logger.getLogger(this.getClass());
	
	public static OCLRBACValidator getValidator() {
		if (singleton == null) {
			EObject eObj = UMLModeler.getUMLUIHelper().getSelectedElements().get(0);
			if (eObj instanceof Element) {
				Element element = (Element) eObj;
				singleton = new OCLRBACValidator(element.eResource().getResourceSet());
			}
			return null;
		}
		return singleton;
	}
	
	/**
	 * Creates a new OCL validator from a ResourceSet.
	 * @param resourceSet
	 */
	private OCLRBACValidator(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
		ocl = OCL.newInstance(resourceSet);
		helper = ocl.createOCLHelper();
		
		BasicEnvironment benv = OCLUtil.getAdapter(ocl.getEnvironment(), 
				BasicEnvironment.class);
		benv.setOption(ProblemOption.CLOSURE_ITERATOR, 
				ProblemHandler.Severity.OK);
	}
	
	public void initValidator() {
		validator = (IBatchValidator)ModelValidationService.getInstance()
				.newValidator(EvaluationMode.BATCH);
		validator.setOption(IBatchValidator.OPTION_TRACK_RESOURCES, true);
	}
	
	/**
	 * Using the result of the evaluation of a model, selects the constraint
	 * that will be fixed.
	 * @pre status can't be null
	 * @TODO: implement and change return type in signature
	 * @param status the result of the evaluation of elements
	 */
	public IStatus selectConstraintToFix(IStatus status) {
		assert (status != null);
		
		if (status.isOK()) {
			log.info("Status was OK, nothing to fix here");
			return null;
		}
		if (status.isMultiStatus()) {
			for (IStatus child:status.getChildren()) {
				if (child.getSeverity() == IStatus.ERROR) {
					log.info("Status selected: " + child.getMessage() + "\t " +
							"from plugin " + child.getPlugin());
					return child;
				}
			}
			throw new RuntimeException("Can't find any constraint to fix");
		}
		log.info("Status " + status.getMessage() 
				+ " is not a MultiStatus and is not OK, selecting");
		return status;
	}
	
	/**
	 * Evaluates a collection of elements using the OCL rbacDSML constraints
	 * @pre validator must have been initialised properly
	 * @param elements the collection of elements to evaluate
	 * @return an IStatus with the result of the evaluation
	 */
	public IStatus evaluateElements(Collection<Element> elements) {
		assert(validator != null);
		initValidator();
		log.info("Track resources: " 
				+ validator.getOption(IBatchValidator.OPTION_TRACK_RESOURCES));
		log.debug("There are " + elements.size() + " elements to validate");
		return validator.validate(elements);
	}
	
	public Object evaluateQuery(String query, Collection<Element> elements) 
				throws ParserException {
		OCLExpression expression = helper.createQuery(query);
		Query queryEval = ocl.createQuery(expression);
		return queryEval.evaluate(elements);
	}
}
