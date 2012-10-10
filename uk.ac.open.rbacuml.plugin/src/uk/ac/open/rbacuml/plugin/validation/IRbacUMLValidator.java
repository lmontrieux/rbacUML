package uk.ac.open.rbacuml.plugin.validation;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.uml2.uml.Element;

public interface IRbacUMLValidator extends Runnable {
	
	public void setConstraintEvaluation(String type, boolean evaluate);
	public boolean getConstraintEvaluation(String type);
	public IStatus evaluate();
	public IStatus evaluate(boolean lazy);
	public IStatus evaluate(Collection<? extends Element> elements);
	public IStatus evaluate(Collection<? extends Element> elements, boolean lazy);
	public void setLazyEvaluation(boolean lazy);
	public boolean getLazyEvaluation();
	public void setElements(Collection<? extends Element> elements);
	public IStatus getResult();
	public long getTimeMilliseconds();
}
