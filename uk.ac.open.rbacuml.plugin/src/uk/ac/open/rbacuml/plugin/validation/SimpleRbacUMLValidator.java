/**
 * 
 */
package uk.ac.open.rbacuml.plugin.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintFilter;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.uml2.uml.Element;

/**
 * @author Lionel Montrieux <L.M.C.Montrieux@open.ac.uk>
 *
 */
public class SimpleRbacUMLValidator implements IRbacUMLValidator, Runnable {
	static Logger log = Logger.getLogger(SimpleRbacUMLValidator.class);
	private Collection<? extends Element> elements = null;
	private Map<String,Boolean> types = new Hashtable<String, Boolean>();
	private boolean lazy = false;
	private IBatchValidator validator = null;
	private MultiStatus result = null;
	private long startTime;
	private long endTime;
	
	public SimpleRbacUMLValidator() {
		this.result = new MultiStatus("org.eclipse.emf.validation", 13, "default message", null);
		types.put("wf", false);
		types.put("ver", false);
		types.put("sat", false);
		types.put("cov", false);
		types.put("comp", false);
		types.put("red", false);
		lazy = false;
	}

	/* (non-Javadoc)
	 * @see uk.ac.open.rbacuml.plugin.validation.IRbacUMLValidator#setConstraintEvaluation(java.lang.String, boolean)
	 */
	public void setConstraintEvaluation(String type, boolean evaluate) {
		this.types.put(type, evaluate);
	}

	/* (non-Javadoc)
	 * @see uk.ac.open.rbacuml.plugin.validation.IRbacUMLValidator#getConstraintEvaluation(java.lang.String)
	 */
	public boolean getConstraintEvaluation(String type) {
		return this.types.get(type);
	}

	/* (non-Javadoc)
	 * @see uk.ac.open.rbacuml.plugin.validation.IRbacUMLValidator#evaluate(boolean)
	 */
	public IStatus evaluate(boolean lazy) {
		this.lazy = lazy;
		return evaluate();
	}

	public void setLazyEvaluation(boolean lazy) {
		this.lazy = lazy;
	}

	public IStatus evaluate() {
		validator = 
			(IBatchValidator)ModelValidationService.getInstance()
				.newValidator(EvaluationMode.BATCH);
		validator.setReportSuccesses(true);
		validator.setIncludeLiveConstraints(false);
		log.warn("Batch Validator configured");
		if (lazy) {
			return evaluateLazy();
		} else {
			return evaluateFull();
		}
	}
	
	private IStatus evaluateLazy() {
		log.warn("Starting lazy evaluation");
		this.result = new MultiStatus("org.eclipse.emf.validation", 13, "default message", null);
		List<String> types = null;
		this.startTime = System.currentTimeMillis();
		if (this.types.get("wf") == true) {
			types = new ArrayList<String>();
			types.add("WF");
			result.merge(validate(elements, validator, createCategoryFilter(types)));
		}
		if (result.isOK()) {
			// well-formedness succeeded, we can run VER, COMP, COV and RED
			// We first run VER, and if the result is KO then we run SAT. We 
			// then run COMP, COV and RED anyway, as long as they are required
			types = new ArrayList<String>();
			if (this.types.get("ver") == true)
				types.add("VER");
			result.merge(validate(elements, validator, createCategoryFilter(types)));
			if (!result.isOK()) {
				if (this.types.get("sat") == true) {
					// verification has failed and satisfiability is required
					types = new ArrayList<String>();
					types.add("SAT");
					result.merge(validate(elements, validator, createCategoryFilter(types)));
				}
			}
			types.clear();
			if (this.types.get("cov") == true)
				types.add("COV");
			if (this.types.get("comp") == true)
				types.add("COMP");
			if (this.types.get("red") == true)
				types.add("RED");
			// If at least one of cov, comp or red is required, we evaluate
			// it now, and we evaluate all the required types together since
			// their order doesn't matter.
			if (types.size() > 0) {
				result.merge(validate(elements, validator, createCategoryFilter(types)));
			}
		}
		this.endTime = System.currentTimeMillis();
		return result;
	}
	
	private IStatus evaluateFull() {
		log.warn("Starting full evaluation");
		this.result = new MultiStatus("org.eclipse.emf.validation", 13, "default message", null);
		List<String> types = new ArrayList<String>();
		this.startTime = System.currentTimeMillis();
		if (this.types.get("wf") == true) {
			types.add("WF");
			log.debug("Well-formedness constraints added to the evaluation filter");
		}
		if (this.types.get("ver") == true) {
			types.add("VER");
			log.debug("Verification constraints added to the evaluation filter");
		}
		if (this.types.get("sat") == true) {
			types.add("SAT");
			log.debug("Satifiability constraints added to the evaluation filter");
		}
		if (this.types.get("cov") == true) {
			types.add("COV");
			log.debug("Coverage constraints added to the evaluation filter");
		}
		if (this.types.get("comp") == true) {
			types.add("COMP");
			log.debug("Completeness constraints added to the evaluation filter");
		}
		if (this.types.get("red") == true) {
			types.add("RED");
			log.debug("Redundancy constraints added to the evaluation filter");
		}
		result.merge(validate(elements, validator, createCategoryFilter(types)));
		this.endTime = System.currentTimeMillis();
		return result;
	}

	public IStatus evaluate(Collection<? extends Element> elements) {
		this.elements = elements;
		return evaluate();
	}
	
	public long getTimeMilliseconds() {
		return this.endTime - this.startTime;
	}

	public IStatus evaluate(Collection<? extends Element> elements, boolean lazy) {
		this.elements = elements;
		this.lazy = lazy;
		return evaluate();
	}

	public boolean getLazyEvaluation() {
		return this.lazy;
	}

	public void setElements(Collection<? extends Element> elements) {
		this.elements = elements;
	}
	
	/**
	 * Adds a filter to the validator, runs the validator against the 
	 * collection of elements, and remove the filter.
	 * @param elements
	 * @param validator
	 * @param filter
	 * @return the validation results
	 */
	private IStatus validate(Collection<? extends Element> elements, 
			IBatchValidator validator, IConstraintFilter filter) {
		validator.addConstraintFilter(filter);
		IStatus results = validator.validate(elements);
		validator.removeConstraintFilter(filter);
		log.warn("Validation performed on " + elements.size() + " elements");
		return results;
	}
	
	private IConstraintFilter createCategoryFilter(List<String> types) {
		String regex = RbacUMLValidatorUtils.oclFilterPrefix;
		String cat = "(";
		for (String type: types) {
			cat += type + "|";
		}
		// removing trailing |
		cat = cat.substring(0, cat.length() - 1);
		// closing bracket
		cat += ")";
		if (!cat.equals("()"))
				regex += cat;
		regex += RbacUMLValidatorUtils.oclFilterSuffix;
		log.debug("Using constraint filter: " + regex);
		final String filter = regex;
		
		return new IConstraintFilter() {
			public boolean accept(IConstraintDescriptor constraint, EObject target) {
				// TODO: use id instead for better performance
				if (constraint.getName().matches(filter)) {
					log.trace("Constraint " + constraint.getName() + " (" + constraint.getId() + ") selected for validation");
					return true;
				}
				return false;
			}
		};
	}
	
	public IStatus getResult() {
		return this.result;
	}

	public void run() {
		this.evaluate();
	}

}
