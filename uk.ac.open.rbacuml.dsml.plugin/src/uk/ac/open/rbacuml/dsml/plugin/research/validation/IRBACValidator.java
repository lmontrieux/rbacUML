package uk.ac.open.rbacuml.dsml.plugin.research.validation;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.uml2.uml.Element;

public interface IRBACValidator {
//	public void evaluateProfileConstraints(String profileName, Collection<Element> elements);
	public IStatus evaluateElements(Collection<Element> elements);
}
