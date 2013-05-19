/**
 * 
 */
package uk.ac.open.rbacuml.dsml.plugin.research.fixing.change;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;

import com.ibm.xtools.modeler.ui.UMLModeler;
import com.ibm.xtools.uml.type.EditRequestParameters;
import com.ibm.xtools.uml.type.UMLElementFactory;
import com.ibm.xtools.uml.type.UMLElementTypes;

/**
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public class RDEltAddChangeCommand extends RecordingCommand {
	private String name;
	private Stereotype stereotype;
	private EObject container;
	RDEltAddChange change;
	
	
	private Logger log = Logger.getLogger(this.getClass());
	
	public RDEltAddChangeCommand(String name, Stereotype stereotype, 
			EObject container, RDEltAddChange change) {
		super(UMLModeler.getEditingDomain(), name);
		this.name = name;
		this.stereotype = stereotype;
		this.container = container;
		this.change = change;
		log.debug("Create Add Class command for " + name);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
	 */
	@Override
	protected void doExecute() {
		log.debug("Executing addition of a new Class " + name);
		EObject element;
		if (stereotype != null) {
			Map requestParameters = new HashMap();
			requestParameters.put(EditRequestParameters.APPLIED_STEREOTYPE_PARAM_NAME, stereotype.getQualifiedName());
			element = UMLElementFactory.createElement(container, UMLElementTypes.CLASS, requestParameters, new NullProgressMonitor());
		} else {
			element = UMLElementFactory.createElement(container, 
					UMLElementTypes.CLASS, new NullProgressMonitor());
		}
		this.name = ((Class)element).getName();
		this.change.setName(this.name);
		NamedElement elt = (NamedElement)element;
		this.change.setElement((Class)element);
	}
}
