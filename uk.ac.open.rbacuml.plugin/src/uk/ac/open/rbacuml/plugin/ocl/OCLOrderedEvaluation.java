package uk.ac.open.rbacuml.plugin.ocl;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintFilter;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.uml2.uml.Element;

public class OCLOrderedEvaluation {
	private static final String profilePrefix = "rbacUML";
	private static final String wellFormedness = "WF";
	private static final String validation = "VAL";
	private static final String coverage = "COV";
	private static final String completeness = "COMP";
	private static final String redundancy = "RED";
	private static final String satisfiability = "SAT";
	
	public static void evaluate(Collection<? extends Element> elements) {
		IBatchValidator validator = 
			(IBatchValidator)ModelValidationService.getInstance()
				.newValidator(EvaluationMode.BATCH);
		validator.setReportSuccesses(true);
		IStatus result;
		result = validate(elements, validator, createCategoryFilter("^" + profilePrefix + "::\\w+::" + wellFormedness + "[a-zA-Z ]+$"));
		if (result.isOK()) {
			System.out.println("The model is well-formed");
			result = validate(elements, validator, createCategoryFilter("^" + profilePrefix + "::\\w+::" + validation + "[a-zA-Z ]+$"));
			if (result.isOK()) {
				System.out.println("The model is valid");
				result = validate(elements, validator, createCategoryFilter("^" + profilePrefix + "::\\w+::" + coverage + "[a-zA-Z ]+$"));
				validate(elements, validator, createCategoryFilter("^" + profilePrefix + "::\\w+::" + completeness + "[a-zA-Z ]+$"));
				validate(elements, validator, createCategoryFilter("^" + profilePrefix + "::\\w+::" + redundancy + "[a-zA-Z ]+$"));
			} else {
				System.out.println("The model is invalid");
				validate(elements, validator, createCategoryFilter("^" + profilePrefix + "::\\w+::" + satisfiability + "[a-zA-Z ]+$"));
			}
		} else {
			System.out.println("The model is not well-formed");
		}
	}
	
	/**
	 * Adds a filter to the validator, runs the validator against the collection of elements, and remove the filter.
	 * @param elements
	 * @param validator
	 * @param filter
	 * @return the validation results
	 */
	private static IStatus validate(Collection<? extends Element> elements, IBatchValidator validator, IConstraintFilter filter) {
		validator.addConstraintFilter(filter);
		IStatus results = validator.validate(elements);
		validator.removeConstraintFilter(filter);
		System.out.println("Validation performed on " + elements.size() + " elements");
		return results;
	}
	
	private static IConstraintFilter createCategoryFilter(final String regex) {
		return new IConstraintFilter() {
			public boolean accept(IConstraintDescriptor constraint, EObject target) {
				// TODO: use id instead for better performance
				if (constraint.getName().matches(regex)) {
					System.out.println("Constraint " + constraint.getName() + " (" + constraint.getId() + ") selected for validation");
					return true;
				}
				return false;
			}
		};
	}
}
