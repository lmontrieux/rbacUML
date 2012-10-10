package uk.ac.open.rbacuml.plugin.validation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionDelegate;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;

import com.almworks.sqlite4java.SQLiteException;
import com.ibm.xtools.modeler.ui.UMLModeler;

public class BulkEvaluationAction extends AbstractActionDelegate implements
		IViewActionDelegate {
	
	static Logger log = Logger.getLogger("BulkEvaluationAction");

	private Map<Integer, IStatus> status = new Hashtable<Integer, IStatus>();
	private Map<Integer, Long> timing = new Hashtable<Integer, Long>();
	private long elements = 0;
	private SQLitePerformanceExport sqlite = null;
	private String name = null;
	
	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		System.out.println("-------Test starting here------");
		BulkEvaluationDialog dialog = new BulkEvaluationDialog(new Shell());
		if(dialog.open() == Window.OK) {
			File modelFile = new File(dialog.getModel());
			if (modelFile.isFile()) {
				// Just one file to evaluate
				runModel(modelFile.getPath(), dialog);
				clean();
			}
			else if (modelFile.isDirectory()) {
				// If it is a directory, we evaluate all the files in said 
				//directory. We do NOT do it recursively
				File[] models = modelFile.listFiles();
				for (int i = 0; i < models.length; i++) {
					// We only evaluate emx files that are readable.
					if (models[i].isFile() && models[i].canRead() 
							&& models[i].getPath().endsWith(".emx")) {
						runModel(models[i].getPath(), dialog);
						clean();
					}
				}
			}
		}
		dialog.close();
	}
	
	private void clean() {
		this.name = null;
		this.elements = 0;
		this.status = new Hashtable<Integer, IStatus>();
		this.timing = new Hashtable<Integer, Long>();
	}
	
	private void runModel(String model, BulkEvaluationDialog dialog) {
		assert(new File(model).canRead() && model.endsWith(".emx"));
		assert(dialog != null);
		log.warn("Preparing to evaluation model " + model);
		Element root = null;
		root = openModel(model);
		if (root == null) {
			log.error("Could not open model " + model + " . Aborting.");
			MessageDialog.openError(null, "Model opening error", 
					"Could not open model " + model + ". Aborting evaluation.");
			return;
		}
		String[] splitPath = model.split(File.separator);
		this.name = splitPath[splitPath.length - 1].substring(0, 
				splitPath[splitPath.length - 1].length() - 4);
		log.warn("Model name is " + this.name);
		Collection<? extends Element> elements = computeElementsList(root);
		runEvaluation(elements, dialog.getEvaluationSettings());
		//MessageDialog.openInformation(null, "Evaluation statistics", stats());
		exportToDb(dialog.getDatabase(), dialog.getTable());
	}
	
	private void runEvaluation(Collection<? extends Element> elements,
			Map<Integer, Boolean> evaluationSettings) {
		if (evaluationSettings.get(RbacUMLValidatorUtils.EVAL_FULL) == true)
			evaluateFull(elements);
		if (evaluationSettings.get(RbacUMLValidatorUtils.EVAL_FULL_LAZY) == true)
			evaluateFullLazy(elements);
		if (evaluationSettings.get(RbacUMLValidatorUtils.EVAL_WF) == true)
			evaluateWF(elements);
		if (evaluationSettings.get(RbacUMLValidatorUtils.EVAL_WF_VER) == true)
			evaluateWfVer(elements);
		if (evaluationSettings.get(RbacUMLValidatorUtils.EVAL_WF_VER_LAZY) == true)
			evaluateVERLazy(elements);
		if (evaluationSettings.get(RbacUMLValidatorUtils.EVAL_VER) == true)
			evaluateVer(elements);
		if (evaluationSettings.get(RbacUMLValidatorUtils.EVAL_SAT) == true)
			evaluateSat(elements);
		if (evaluationSettings.get(RbacUMLValidatorUtils.EVAL_COV) == true)
			evaluateCov(elements);
		if (evaluationSettings.get(RbacUMLValidatorUtils.EVAL_COMP) == true)
			evaluateComp(elements);
		if (evaluationSettings.get(RbacUMLValidatorUtils.EVAL_RED) == true)
			evaluateRed(elements);
		if (evaluationSettings.get(RbacUMLValidatorUtils.EVAL_FULL_MULTI) == true) {
			evaluateFullMulti(elements);
		}
	}

	private Element openModel(final String filename) {
		log.warn("Trying to open model " + filename);
		Element root = null;
		try {
			root = UMLModeler.openModelResource(filename);
			log.warn("Root element: " + root.toString());
		} catch (IOException e) {
			e.printStackTrace();
			log.error("IOException when opening model: " + e.getMessage());
		}
		return root;
	}
	
	private void evaluateFull(Collection<? extends Element> elements) {
		log.warn("Preparing 3 rouds of FULL evaluation");
		long time1, time2, time3;
		IRbacUMLValidator validator = new SimpleRbacUMLValidator();
		validator.setElements(elements);
		validator.setLazyEvaluation(false);
		validator.setConstraintEvaluation("wf", true);
		validator.setConstraintEvaluation("ver", true);
		validator.setConstraintEvaluation("sat", true);
		validator.setConstraintEvaluation("cov", true);
		validator.setConstraintEvaluation("comp", true);
		validator.setConstraintEvaluation("red", true);
		IStatus status = validator.evaluate();
		this.status.put(RbacUMLValidatorUtils.EVAL_FULL, status);
		this.timing.put(RbacUMLValidatorUtils.EVAL_FULL, validator.getTimeMilliseconds());
		time1 = validator.getTimeMilliseconds();
		log.warn("FULL evaluation round 1 took " + time1 + " ms");
		validator.evaluate();
		time2 = validator.getTimeMilliseconds();
		log.warn("FULL evaluation round 2 took " + time2 + " ms");
		validator.evaluate();
		time3 = validator.getTimeMilliseconds();
		log.warn("FULL evaluation round 3 took " + time3 + " ms");
		log.warn("Fastest FULL evaluation tool " 
				+ Math.min(time1, Math.min(time2, time3)) + " ms.");
		if (status.isOK())
			log.warn("FULL evaluation raised no errors and no warnings");
		if (status.getSeverity() == Status.WARNING)
			log.warn("FULL evaluation raised warnings");
		if (status.getSeverity() == Status.ERROR)
			log.warn("FULL evaluation raised errors");
		this.timing.put(RbacUMLValidatorUtils.EVAL_FULL, 
				Math.min(time1, Math.min(time2, time3)));
	}
	
	private void evaluateVer(Collection<? extends Element> elements) {
		log.warn("Preparing 3 rounds of VER evaluation");
		long time1, time2, time3;
		IRbacUMLValidator validator = new SimpleRbacUMLValidator();
		validator.setElements(elements);
		validator.setConstraintEvaluation("wf", false);
		validator.setConstraintEvaluation("ver", true);
		validator.setConstraintEvaluation("sat", false);
		validator.setConstraintEvaluation("cov", false);
		validator.setConstraintEvaluation("comp", false);
		validator.setConstraintEvaluation("red", false);
		this.status.put(RbacUMLValidatorUtils.EVAL_VER, validator.evaluate());
		time1 = validator.getTimeMilliseconds();
		log.warn("VER evaluation round 1 took " + time1 + " ms");
		validator.evaluate();
		time2 = validator.getTimeMilliseconds();
		log.warn("VER evaluation round 2 took " + time2 + " ms");
		validator.evaluate();
		time3 = validator.getTimeMilliseconds();
		log.warn("VER evaluation round 1 took " + time3 + " ms");
		log.warn("Fastest VER evaluation took " 
				+ Math.min(time1, Math.min(time2, time3)));
		this.timing.put(RbacUMLValidatorUtils.EVAL_VER, 
				Math.min(time1, Math.min(time2, time3)));
	}
	
	private void evaluateSat(Collection<? extends Element> elements) {
		log.warn("Preparing 3 rounds of SAT evaluation");
		long time1, time2, time3;
		IRbacUMLValidator validator = new SimpleRbacUMLValidator();
		validator.setElements(elements);
		validator.setConstraintEvaluation("wf", false);
		validator.setConstraintEvaluation("ver", false);
		validator.setConstraintEvaluation("sat", true);
		validator.setConstraintEvaluation("cov", false);
		validator.setConstraintEvaluation("comp", false);
		validator.setConstraintEvaluation("red", false);
		this.status.put(RbacUMLValidatorUtils.EVAL_SAT, validator.evaluate());
		time1 = validator.getTimeMilliseconds();
		log.warn("SAT evaluation round 1 took " + time1 + " ms");
		validator.evaluate();
		time2 = validator.getTimeMilliseconds();
		log.warn("SAT evaluation round 2 took " + time2 + " ms");
		validator.evaluate();
		time3 = validator.getTimeMilliseconds();
		log.warn("SAT evaluation round 1 took " + time3 + " ms");
		log.warn("Fastest SAT evaluation took " 
				+ Math.min(time1, Math.min(time2, time3)));
		this.timing.put(RbacUMLValidatorUtils.EVAL_SAT, 
				Math.min(time1, Math.min(time2, time3)));
	}
	
	private void evaluateCov(Collection<? extends Element> elements) {
		log.warn("Preparing 3 rounds of COV evaluation");
		long time1, time2, time3;
		IRbacUMLValidator validator = new SimpleRbacUMLValidator();
		validator.setElements(elements);
		validator.setConstraintEvaluation("wf", false);
		validator.setConstraintEvaluation("ver", false);
		validator.setConstraintEvaluation("sat", false);
		validator.setConstraintEvaluation("cov", true);
		validator.setConstraintEvaluation("comp", false);
		validator.setConstraintEvaluation("red", false);
		validator.evaluate();
		time1 = validator.getTimeMilliseconds();
		log.warn("COV evaluation round 1 took " + time1 + " ms");
		validator.evaluate();
		time2 = validator.getTimeMilliseconds();
		log.warn("COV evaluation round 2 took " + time2 + " ms");
		
		this.status.put(RbacUMLValidatorUtils.EVAL_COV, validator.evaluate());
		time3 = validator.getTimeMilliseconds();
		log.warn("COV evaluation round 1 took " + time3 + " ms");
		log.warn("Fastest COV evaluation took " 
				+ Math.min(time1, Math.min(time2, time3)));
		this.timing.put(RbacUMLValidatorUtils.EVAL_COV, 
				Math.min(time1, Math.min(time2, time3)));
	}
	
	private void evaluateComp(Collection<? extends Element> elements) {
		log.warn("Preparing 3 rounds of COMP evaluation");
		long time1, time2, time3;
		IRbacUMLValidator validator = new SimpleRbacUMLValidator();
		validator.setElements(elements);
		validator.setConstraintEvaluation("wf", false);
		validator.setConstraintEvaluation("ver", false);
		validator.setConstraintEvaluation("sat", false);
		validator.setConstraintEvaluation("cov", false);
		validator.setConstraintEvaluation("comp", true);
		validator.setConstraintEvaluation("red", false);
		this.status.put(RbacUMLValidatorUtils.EVAL_COMP, validator.evaluate());
		time1 = validator.getTimeMilliseconds();
		log.warn("COMP evaluation round 1 took " + time1 + " ms");
		validator.evaluate();
		time2 = validator.getTimeMilliseconds();
		log.warn("COMP evaluation round 2 took " + time2 + " ms");
		validator.evaluate();
		time3 = validator.getTimeMilliseconds();
		log.warn("COMP evaluation round 1 took " + time3 + " ms");
		log.warn("Fastest COMP evaluation took " 
				+ Math.min(time1, Math.min(time2, time3)));
		this.timing.put(RbacUMLValidatorUtils.EVAL_COMP, 
				Math.min(time1, Math.min(time2, time3)));
	}
	
	private void evaluateRed(Collection<? extends Element> elements) {
		log.warn("Preparing 3 rounds of RED evaluation");
		long time1, time2, time3;
		IRbacUMLValidator validator = new SimpleRbacUMLValidator();
		validator.setElements(elements);
		validator.setConstraintEvaluation("wf", false);
		validator.setConstraintEvaluation("ver", false);
		validator.setConstraintEvaluation("sat", false);
		validator.setConstraintEvaluation("cov", false);
		validator.setConstraintEvaluation("comp", false);
		validator.setConstraintEvaluation("red", true);
		this.status.put(RbacUMLValidatorUtils.EVAL_RED, validator.evaluate());
		time1 = validator.getTimeMilliseconds();
		log.warn("RED evaluation round 1 took " + time1 + " ms");
		validator.evaluate();
		time2 = validator.getTimeMilliseconds();
		log.warn("RED evaluation round 2 took " + time2 + " ms");
		validator.evaluate();
		time3 = validator.getTimeMilliseconds();
		log.warn("RED evaluation round 1 took " + time3 + " ms");
		log.warn("Fastest RED evaluation took " 
				+ Math.min(time1, Math.min(time2, time3)));
		this.timing.put(RbacUMLValidatorUtils.EVAL_RED, 
				Math.min(time1, Math.min(time2, time3)));
	}
	
	
	private void evaluateFullLazy(Collection<? extends Element> elements) {
		log.warn("Preparing 3 rounds of FULL_LAZY evaluation");
		long time1, time2, time3;
		IRbacUMLValidator validator = new SimpleRbacUMLValidator();
		validator.setElements(elements);
		validator.setLazyEvaluation(true);
		validator.setConstraintEvaluation("wf", true);
		validator.setConstraintEvaluation("ver", true);
		validator.setConstraintEvaluation("sat", true);
		validator.setConstraintEvaluation("cov", true);
		validator.setConstraintEvaluation("comp", true);
		validator.setConstraintEvaluation("red", true);
		this.status.put(RbacUMLValidatorUtils.EVAL_FULL_LAZY, validator.evaluate());
		time1 = validator.getTimeMilliseconds();
		log.warn("FULL_LAZY evaluation round 1 took " + time1 + " ms");
		validator.evaluate();
		time2 = validator.getTimeMilliseconds();
		log.warn("FULL_LAZY evaluation round 2 took " + time2 + " ms");
		validator.evaluate();
		time3 = validator.getTimeMilliseconds();
		log.warn("FULL_LAZY evaluation round 3 took " + time3 + " ms");
		log.warn("Fastest FULL_LAZY evaluation tool " 
				+ Math.min(time1, Math.min(time2, time3)) + " ms.");
		this.timing.put(RbacUMLValidatorUtils.EVAL_FULL_LAZY, 
				Math.min(time1, Math.min(time2, time3)));
	}
	
	private void evaluateFullMulti(Collection<? extends Element> elements) {
		log.warn("Preparing 3 rounds of FULL_MULTI evaluation");
		long time1, time2, time3;
		long start, end;
		IRbacUMLValidator validator = new SimpleRbacUMLValidator();
		IRbacUMLValidator validatorCOV = new SimpleRbacUMLValidator();
		IRbacUMLValidator validatorCOMP = new SimpleRbacUMLValidator();
		IRbacUMLValidator validatorRED = new SimpleRbacUMLValidator();
		validator.setElements(elements);
		validatorCOV.setElements(elements);
		validatorRED.setElements(elements);
		validatorCOMP.setElements(elements);
		validator.setLazyEvaluation(true); // not necessary for the multithreaded ones
		validator.setConstraintEvaluation("wf", true);
		validator.setConstraintEvaluation("ver", true);
		validator.setConstraintEvaluation("sat", true);
		validator.setConstraintEvaluation("cov", false);
		validator.setConstraintEvaluation("comp", false);
		validator.setConstraintEvaluation("red", false);
		validatorCOV.setConstraintEvaluation("wf", false);
		validatorCOV.setConstraintEvaluation("ver", false);
		validatorCOV.setConstraintEvaluation("sat", false);
		validatorCOV.setConstraintEvaluation("cov", true);
		validatorCOV.setConstraintEvaluation("comp", false);
		validatorCOV.setConstraintEvaluation("red", false);
		validatorCOMP.setConstraintEvaluation("wf", false);
		validatorCOMP.setConstraintEvaluation("ver", false);
		validatorCOMP.setConstraintEvaluation("sat", false);
		validatorCOMP.setConstraintEvaluation("cov", false);
		validatorCOMP.setConstraintEvaluation("comp", true);
		validatorCOMP.setConstraintEvaluation("red", false);
		validatorRED.setConstraintEvaluation("wf", false);
		validatorRED.setConstraintEvaluation("ver", false);
		validatorRED.setConstraintEvaluation("sat", false);
		validatorRED.setConstraintEvaluation("cov", false);
		validatorRED.setConstraintEvaluation("comp", false);
		validatorRED.setConstraintEvaluation("red", true);
		start = System.currentTimeMillis();
		this.status.put(RbacUMLValidatorUtils.EVAL_FULL_MULTI, validator.evaluate());
		log.warn("WF and VER took " + validator.getTimeMilliseconds());
		Thread cov = new Thread(validatorCOV, "coverage 1");
		Thread comp = new Thread(validatorCOMP, "completeness 1");
		Thread red = new Thread(validatorRED, "redundancy 1");
		cov.start();
		comp.start();
		red.start();
		try {
			cov.join();
			comp.join();
			red.join();
		} catch (InterruptedException e) {
			log.error("Ooops, something went wrong with an evaluation thread: " + e.getMessage());
			return;
		}
		end = System.currentTimeMillis();
		time1 = end - start;
		log.warn("FULL_MULTI evaluation round 1 took " + time1 + " ms");
		
		start = System.currentTimeMillis();
		this.status.put(RbacUMLValidatorUtils.EVAL_FULL_MULTI, validator.evaluate());
		log.warn("WF and VER took " + validator.getTimeMilliseconds());
		cov = new Thread(validatorCOV, "coverage 2");
		comp = new Thread(validatorCOMP, "completeness 2");
		red = new Thread(validatorRED, "redundancy 2");
		cov.start();
		comp.start();
		red.start();
		try {
			cov.join();
			comp.join();
			red.join();
		} catch (InterruptedException e) {
			log.error("Ooops, something went wrong with an evaluation thread: " + e.getMessage());
			return;
		}
		end = System.currentTimeMillis();
		time2 = end - start;
		log.warn("FULL_MULTI evaluation round 2 took " + time1 + " ms");
		
		start = System.currentTimeMillis();
		this.status.put(RbacUMLValidatorUtils.EVAL_FULL_MULTI, validator.evaluate());
		log.warn("WF and VER took " + validator.getTimeMilliseconds());
		cov = new Thread(validatorCOV, "coverage 3");
		comp = new Thread(validatorCOMP, "completeness 3");
		red = new Thread(validatorRED, "redundancy 3");
		cov.start();
		comp.start();
		red.start();
		try {
			cov.join();
			comp.join();
			red.join();
		} catch (InterruptedException e) {
			log.error("Ooops, something went wrong with an evaluation thread: " + e.getMessage());
			return;
		}
		end = System.currentTimeMillis();
		time3 = end - start;
		log.warn("FULL_MULTI evaluation round 3 took " + time1 + " ms");
		
		log.warn("Fastest FULL_MULTI evaluation tool " 
				+ Math.min(time1, Math.min(time2, time3)) + " ms.");
		this.timing.put(RbacUMLValidatorUtils.EVAL_FULL_MULTI, 
				Math.min(time1, Math.min(time2, time3)));
	}
	
	private void evaluateWF(Collection<? extends Element> elements) {
		log.warn("Preparing 3 rouds of WF evaluation");
		long time1, time2, time3;
		IRbacUMLValidator validator = new SimpleRbacUMLValidator();
		validator.setElements(elements);
		validator.setLazyEvaluation(false);
		validator.setConstraintEvaluation("wf", true);
		validator.setConstraintEvaluation("ver", false);
		validator.setConstraintEvaluation("sat", false);
		validator.setConstraintEvaluation("cov", false);
		validator.setConstraintEvaluation("comp", false);
		validator.setConstraintEvaluation("red", false);
		this.status.put(RbacUMLValidatorUtils.EVAL_WF, validator.evaluate());
		time1 = validator.getTimeMilliseconds();
		log.warn("WF evaluation round 1 took " + time1 + " ms");
		validator.evaluate();
		time2 = validator.getTimeMilliseconds();
		log.warn("WF evaluation round 2 took " + time2 + " ms");
		validator.evaluate();
		time3 = validator.getTimeMilliseconds();
		log.warn("WF evaluation round 3 took " + time3 + " ms");
		log.warn("Fastest WF evaluation tool " 
				+ Math.min(time1, Math.min(time2, time3)) + " ms.");
		this.timing.put(RbacUMLValidatorUtils.EVAL_WF, 
				Math.min(time1, Math.min(time2, time3)));
	}
	
	private void evaluateWfVer(Collection<? extends Element> elements) {
		log.warn("Preparing 3 rouds of VER evaluation");
		long time1, time2, time3;
		IRbacUMLValidator validator = new SimpleRbacUMLValidator();
		validator.setElements(elements);
		validator.setLazyEvaluation(false);
		validator.setConstraintEvaluation("wf", true);
		validator.setConstraintEvaluation("ver", true);
		validator.setConstraintEvaluation("sat", false);
		validator.setConstraintEvaluation("cov", false);
		validator.setConstraintEvaluation("comp", false);
		validator.setConstraintEvaluation("red", false);
		this.status.put(RbacUMLValidatorUtils.EVAL_WF_VER, validator.evaluate());
		time1 = validator.getTimeMilliseconds();
		log.warn("VER evaluation round 1 took " + time1 + " ms");
		validator.evaluate();
		time2 = validator.getTimeMilliseconds();
		log.warn("VER evaluation round 2 took " + time2 + " ms");
		validator.evaluate();
		time3 = validator.getTimeMilliseconds();
		log.warn("VER evaluation round 3 took " + time3 + " ms");
		log.warn("Fastest VER evaluation tool " 
				+ Math.min(time1, Math.min(time2, time3)) + " ms.");
		this.timing.put(RbacUMLValidatorUtils.EVAL_WF_VER, 
				Math.min(time1, Math.min(time2, time3)));
	}
	
	private void evaluateVERLazy(Collection<? extends Element> elements) {
		log.warn("Preparing 3 rouds of VER_LAZY evaluation");
		long time1, time2, time3;
		IRbacUMLValidator validator = new SimpleRbacUMLValidator();
		validator.setElements(elements);
		validator.setLazyEvaluation(true);
		validator.setConstraintEvaluation("wf", true);
		validator.setConstraintEvaluation("ver", true);
		validator.setConstraintEvaluation("sat", false);
		validator.setConstraintEvaluation("cov", false);
		validator.setConstraintEvaluation("comp", false);
		validator.setConstraintEvaluation("red", false);
		
		validator.evaluate();
		time1 = validator.getTimeMilliseconds();
		log.warn("VER_LAZY evaluation round 1 took " + time1 + " ms");
		validator.evaluate();
		time2 = validator.getTimeMilliseconds();
		log.warn("VER_LAZY evaluation round 2 took " + time2 + " ms");
		this.status.put(RbacUMLValidatorUtils.EVAL_WF_VER_LAZY, null);
		this.status.put(RbacUMLValidatorUtils.EVAL_WF_VER_LAZY, validator.evaluate());
		time3 = validator.getTimeMilliseconds();
		log.warn("VER_LAZY evaluation round 3 took " + time3 + " ms");
		log.warn("Fastest VER_LAZY evaluation tool " 
				+ Math.min(time1, Math.min(time2, time3)) + " ms.");
		this.timing.put(RbacUMLValidatorUtils.EVAL_WF_VER_LAZY, 
				Math.min(time1, Math.min(time2, time3)));
	}
	
	/**
	 * Given a model's root element, computes the list of elements to pass to 
	 * the validator
	 * @param root the model's root element
	 * @return a collection of elements sufficient to evaluate the rbacUML model
	 */
	private List<Element> computeElementsList(Element root) {
		log.warn("Computing list of elements to validate");
		Package p = root.getNearestPackage();
		log.warn("nearest package: " + p.toString());
		List<Element> elements = p.allOwnedElements();
		log.warn("I found " + elements.size() + " potential elements");
		List<Element> elts = new ArrayList<Element>();
		for (Element candidate:elements) {
				if (candidate.getAppliedStereotypes().size() > 0)
					elts.add(candidate);
		}
		log.warn(elts.size() + " elements will be used for validation");
		this.elements = elts.size();
		return elts;
	}
	
	/**
	 * Returns the IStatus corresponding to the evaluation type passed in the 
	 * parameter, or null if it hasn't been evaluated.
	 * @param name the type of evaluation considered. Use the EVAL_* constants in 
	 * RbacUMLValidatorUtil.
	 * @return the IStatus resulting from the evaluation, or null if the evaluation
	 * hasn't been performed.
	 */
	public IStatus getStatus(int name) {
		if (status.containsKey(name))
			return status.get(name);
		return null;
	}
	
	/**
	 * Counts the number of Status items of a particular type
	 * @param evalType the type of evaluation whose status to consider
	 * @param statusType the type of status to count. Can be IStatus.ERROR, 
	 * IStatus.WARNING, IStatus.INFO or IStatus.OK.
	 * @return the number of IStatus of the selected type. Can be 0.
	 */
	public int countStatus(int evalType, int statusType) {
		assert(this.status.containsKey(evalType));
		assert(this.status.get(evalType) instanceof MultiStatus);
		int matches = 0;
		IStatus[] status = ((MultiStatus)this.status.get(evalType)).getChildren();
		for (int i = 0; i < status.length; i++) {
			if (status[i].matches(statusType))
				matches++;
		}
		return matches;
	}
	
	public long getTiming(int evalType) {
		assert(this.timing.containsKey(evalType));
		return this.timing.get(evalType).longValue();
	}
	
	public long getNumElements() {
		return this.elements;
	}
	
	public long countErrors(int type) {
		long result = 0;
		if (this.status.containsKey(type)) {
			IStatus status = this.status.get(type);
			result = countFailures(true, status);
		}
		log.info("Found " + result + " errors");
		// For some reason, RSA counts errors and warnings three times.
		//Maybe because we run three evaluations in a row?
		return result;
	}
	
	public long countWarnings(int type) {
		long result = 0;
		if (this.status.containsKey(type)) {
			IStatus status = this.status.get(type);
			result = countFailures(false, status);
		}
		log.info("Found " + result + " warnings");
		// For some reason, RSA counts errors and warnings three times. 
		// Maybe because we run three evaluations in a row?
		return result;
	}
	
	private long countFailures(boolean error, IStatus status) {
		if (status.isOK()) {
			log.trace("countFailures: status is OK (" + status.getMessage() + ")");
			return 0;
		}
		if (!status.isMultiStatus()) {
			if ((status.getSeverity() == Status.ERROR) && (error == true)) {
				log.trace("Single status, error (" + status.getMessage() + ")");
				return 1;
			}
			else if ((status.getSeverity() == Status.WARNING) && (error == false)) {
				log.trace("Single status, warning(" + status.getMessage() + ")");
				return 1;
			}
			else {
				if (status.getSeverity() == Status.ERROR)
					log.trace("Single status, ERROR, but we're looking for " +
							"warnings (" + status.getMessage() + ")");
				else if (status.getSeverity() == Status.WARNING)
					log.trace("Single status, WARNING, but we're looking for " +
							"errors (" + status.getMessage() + ")");
				else
					log.debug("Single status: " + status.getSeverity() + " (" 
							+ status.getMessage() + ")");
				return 0;
			}
		}
		// this is a multistatus, we break it down into an array and recursively 
		//call countFailures(..)
		IStatus[] array = status.getChildren();
		long counter = 0;
		for(int i = 0; i < array.length; i++) {
			log.trace("Recursive call to countFailures with following status: " 
					+ status.getMessage());
			counter += countFailures(error, array[i]);
		}
		return counter;
	}
	
	private boolean exportToDb(String dbfile, String table) {
		try {
			this.sqlite = new SQLitePerformanceExport(dbfile, table);
		} catch (IOException e) {
			log.error("IOException when trying to open SQLite database: " 
					+ e.getMessage());
			e.printStackTrace();
			return false;
		} catch (SQLiteException e) {
			log.error("SQLiteException when trying to open SQLite database: " 
					+ e.getMessage());
			e.printStackTrace();
			return false;
		}
		this.sqlite.updateRecord(this.name, SQLitePerformanceExport.ELEMENTS, 
				this.elements);
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_FULL)) {
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.ERRORS_FULL, 
					countErrors(RbacUMLValidatorUtils.EVAL_FULL));
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.WARNINGS_FULL, 
					countWarnings(RbacUMLValidatorUtils.EVAL_FULL));
		}
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_FULL_LAZY)) {
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.ERRORS_FULL_LAZY, 
					countErrors(RbacUMLValidatorUtils.EVAL_FULL_LAZY));
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.WARNINGS_FULL_LAZY, 
					countWarnings(RbacUMLValidatorUtils.EVAL_FULL_LAZY));
		}
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_FULL_MULTI)) {
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.ERRORS_FULL_MULTI, 
					countErrors(RbacUMLValidatorUtils.EVAL_FULL_MULTI));
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.WARNINGS_FULL_MULTI, 
					countWarnings(RbacUMLValidatorUtils.EVAL_FULL_MULTI));
		}
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_WF)) {
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.ERRORS_WF, 
					countErrors(RbacUMLValidatorUtils.EVAL_WF));
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.WARNINGS_WF, 
					countWarnings(RbacUMLValidatorUtils.EVAL_WF));
		}
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_VER)) {
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.ERRORS_VER, 
					countErrors(RbacUMLValidatorUtils.EVAL_VER));
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.WARNINGS_VER, 
					countWarnings(RbacUMLValidatorUtils.EVAL_VER));
		}
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_SAT)) {
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.ERRORS_SAT, 
					countErrors(RbacUMLValidatorUtils.EVAL_SAT));
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.WARNINGS_SAT, 
					countWarnings(RbacUMLValidatorUtils.EVAL_SAT));
		}
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_COV)) {
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.ERRORS_COV, 
					countErrors(RbacUMLValidatorUtils.EVAL_COV));
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.WARNINGS_COV, 
					countWarnings(RbacUMLValidatorUtils.EVAL_COV));
		}
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_COMP)) {
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.ERRORS_COMP, 
					countErrors(RbacUMLValidatorUtils.EVAL_COMP));
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.WARNINGS_COMP, 
					countWarnings(RbacUMLValidatorUtils.EVAL_COMP));
		}
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_RED)) {
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.ERRORS_RED, 
					countErrors(RbacUMLValidatorUtils.EVAL_RED));
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.WARNINGS_RED, 
					countWarnings(RbacUMLValidatorUtils.EVAL_RED));
		}
		
		
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_FULL)) {
			this.sqlite.updateRecord(this.name, 
					SQLitePerformanceExport.TIME_FULL, 
					this.timing.get(RbacUMLValidatorUtils.EVAL_FULL));
		}
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_FULL_LAZY)) {
			this.sqlite.updateRecord(this.name, SQLitePerformanceExport.TIME_FULL_LAZY, 
					this.timing.get(RbacUMLValidatorUtils.EVAL_FULL_LAZY));
		}
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_FULL_MULTI)) {
			this.sqlite.updateRecord(this.name, SQLitePerformanceExport.TIME_FULL_MULTI, 
					this.timing.get(RbacUMLValidatorUtils.EVAL_FULL_MULTI));
		}
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_WF)) {
			this.sqlite.updateRecord(this.name, SQLitePerformanceExport.TIME_WF, 
					this.timing.get(RbacUMLValidatorUtils.EVAL_WF));
		}
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_VER)) {
			this.sqlite.updateRecord(this.name, SQLitePerformanceExport.TIME_VER, 
					this.timing.get(RbacUMLValidatorUtils.EVAL_VER));
		}
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_SAT)) {
			this.sqlite.updateRecord(this.name, SQLitePerformanceExport.TIME_SAT, 
					this.timing.get(RbacUMLValidatorUtils.EVAL_SAT));
		}
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_COV)) {
			this.sqlite.updateRecord(this.name, SQLitePerformanceExport.TIME_COV, 
					this.timing.get(RbacUMLValidatorUtils.EVAL_COV));
		}
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_COMP)) {
			this.sqlite.updateRecord(this.name, SQLitePerformanceExport.TIME_COMP, 
					this.timing.get(RbacUMLValidatorUtils.EVAL_COMP));
		}
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_RED)) {
			this.sqlite.updateRecord(this.name, SQLitePerformanceExport.TIME_RED, 
					this.timing.get(RbacUMLValidatorUtils.EVAL_RED));
		}
		
		
		return true;
	}
	
	private String stats() {
		String stats = "";
		if (this.name != null)
			stats += "Model name: " + this.name + "\n";
		stats += "Elements considered: " + this.elements + "\n";
		
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_FULL))
			stats += "Full evaluation resulted in " 
				+ countErrors(RbacUMLValidatorUtils.EVAL_FULL) + " errors and " 
				+ countWarnings(RbacUMLValidatorUtils.EVAL_FULL) + " warnings\n";
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_FULL))
			stats += "Full evaluation took " 
				+ this.timing.get(RbacUMLValidatorUtils.EVAL_FULL) 
				+ " milliseconds\n";

		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_FULL_LAZY))
			stats += "Lazy full evaluation resulted in " 
				+ countErrors(RbacUMLValidatorUtils.EVAL_FULL_LAZY) 
				+ " errors and " 
				+ countWarnings(RbacUMLValidatorUtils.EVAL_FULL_LAZY) 
				+ " warnings\n";
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_FULL_LAZY))
			stats += "Lazy full evaluation took " 
				+ this.timing.get(RbacUMLValidatorUtils.EVAL_FULL_LAZY) 
				+ " milliseconds\n";
		
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_WF))
			stats += "WF evaluation resulted in " 
				+ countErrors(RbacUMLValidatorUtils.EVAL_WF) + " errors and " 
				+ countWarnings(RbacUMLValidatorUtils.EVAL_WF) + " warnings\n";
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_WF))
			stats += "WF evaluation took " 
				+ this.timing.get(RbacUMLValidatorUtils.EVAL_WF) 
				+ " milliseconds\n";
		
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_WF_VER))
			stats += "Verification evaluation resulted in " 
				+ countErrors(RbacUMLValidatorUtils.EVAL_WF_VER) 
				+ " errors and " 
				+ countWarnings(RbacUMLValidatorUtils.EVAL_WF_VER) 
				+ " warnings\n";
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_WF_VER))
			stats += "Verification evaluation took " 
				+ this.timing.get(RbacUMLValidatorUtils.EVAL_WF_VER) 
				+ " milliseconds\n";
		
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_WF_VER_LAZY))
			stats += "Lazy verification evaluation resulted in " 
				+ countErrors(RbacUMLValidatorUtils.EVAL_WF_VER_LAZY) 
				+ " errors and " 
				+ countWarnings(RbacUMLValidatorUtils.EVAL_WF_VER_LAZY) 
				+ " warnings\n";
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_WF_VER_LAZY))
			stats += "Lazy verification evaluation took " 
				+ this.timing.get(RbacUMLValidatorUtils.EVAL_WF_VER_LAZY) 
				+ " milliseconds\n";
		
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_VER))
			stats += "VER verification evaluation resulted in " 
				+ countErrors(RbacUMLValidatorUtils.EVAL_VER) 
				+ " errors and " 
				+ countWarnings(RbacUMLValidatorUtils.EVAL_VER) 
				+ " warnings\n";
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_VER))
			stats += "VER verification evaluation took " 
				+ this.timing.get(RbacUMLValidatorUtils.EVAL_VER) 
				+ " milliseconds\n";
		
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_SAT))
			stats += "SAT verification evaluation resulted in " 
				+ countErrors(RbacUMLValidatorUtils.EVAL_SAT) 
				+ " errors and " 
				+ countWarnings(RbacUMLValidatorUtils.EVAL_SAT) 
				+ " warnings\n";
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_SAT))
			stats += "SAT verification evaluation took " 
				+ this.timing.get(RbacUMLValidatorUtils.EVAL_SAT) 
				+ " milliseconds\n";
		
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_COV))
			stats += "COV verification evaluation resulted in " 
				+ countErrors(RbacUMLValidatorUtils.EVAL_COV) 
				+ " errors and " 
				+ countWarnings(RbacUMLValidatorUtils.EVAL_COV) 
				+ " warnings\n";
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_COV))
			stats += "COV verification evaluation took " 
				+ this.timing.get(RbacUMLValidatorUtils.EVAL_COV) 
				+ " milliseconds\n";
		
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_COMP))
			stats += "COMP verification evaluation resulted in " 
				+ countErrors(RbacUMLValidatorUtils.EVAL_COMP) 
				+ " errors and " 
				+ countWarnings(RbacUMLValidatorUtils.EVAL_COMP) 
				+ " warnings\n";
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_COMP))
			stats += "COMP verification evaluation took " 
				+ this.timing.get(RbacUMLValidatorUtils.EVAL_COMP) 
				+ " milliseconds\n";
		
		if (this.status.containsKey(RbacUMLValidatorUtils.EVAL_RED))
			stats += "RED verification evaluation resulted in " 
				+ countErrors(RbacUMLValidatorUtils.EVAL_RED) 
				+ " errors and " 
				+ countWarnings(RbacUMLValidatorUtils.EVAL_RED) 
				+ " warnings\n";
		if (this.timing.containsKey(RbacUMLValidatorUtils.EVAL_RED))
			stats += "RED verification evaluation took " 
				+ this.timing.get(RbacUMLValidatorUtils.EVAL_RED) 
				+ " milliseconds\n";
		return stats;
	}
}
