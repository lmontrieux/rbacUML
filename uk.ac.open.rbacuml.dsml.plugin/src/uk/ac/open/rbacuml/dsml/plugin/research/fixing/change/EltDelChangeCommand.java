package uk.ac.open.rbacuml.dsml.plugin.research.fixing.change;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.uml2.uml.NamedElement;

import com.ibm.xtools.modeler.ui.UMLModeler;
import com.ibm.xtools.uml.type.UMLElementFactory;

public class EltDelChangeCommand extends RecordingCommand {
	private NamedElement element;
	private Logger log = Logger.getLogger(this.getClass());
	
	public EltDelChangeCommand(String label, NamedElement element) {
		super(UMLModeler.getEditingDomain(), label);
		this.element = element;
	}

	@Override
	protected void doExecute() {
		UMLElementFactory.destroyElement(element, new NullProgressMonitor());
	}

}
