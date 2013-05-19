package uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree;

import java.util.EventObject;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.command.CommandStackListener;

public class RDCommandStackListener implements CommandStackListener {
	private Logger log = Logger.getLogger(this.getClass());
	
	public RDCommandStackListener() {
		
	}
	public void commandStackChanged(EventObject event) {
		log.debug("CommandStack has changed: " + event.getClass().getCanonicalName() + ": " + event.toString());
	}

}
