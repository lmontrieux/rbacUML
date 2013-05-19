package uk.ac.open.rbacuml.dsml.plugin.research.fixing.change;

import org.apache.log4j.Logger;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;

import com.ibm.xtools.modeler.ui.UMLModeler;

public class RBACDeleteChangeCommand extends RecordingCommand {
	private Association element;
	private Logger log = Logger.getLogger(this.getClass());

	public RBACDeleteChangeCommand(String label, Association element) {
		super(UMLModeler.getEditingDomain(), label);
		assert(element != null);
		this.element = element;
		log.debug("Create Delete command");
		log.debug("Association to delete is " + element.getMemberEnds().get(0).getType().getName()
				+ " to " + element.getMemberEnds().get(1).getType().getName());
	}

	@Override
	protected void doExecute() {
		Property end1 = element.getMemberEnds().get(0);
		Property end2 = element.getMemberEnds().get(1);
		element.destroy();
		end1.destroy();
		end2.destroy();
	}

}
