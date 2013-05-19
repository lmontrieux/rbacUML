/**
 * 
 */
package uk.ac.open.rbacuml.dsml.plugin.research.fixing.change;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;

import com.ibm.xtools.modeler.ui.UMLModeler;

import uk.ac.open.rbacuml.dsml.plugin.research.RDSterHelper;

/**
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public class RDEltAddChange implements Change {
	private static int nbr = 1;
	private int type;
	private String name;
	private RecordingCommand command = null;
	private Class element;
	private Stereotype stereotype;
	private Logger log = Logger.getLogger(this.getClass());
	private List<Change> observers;
	
	public RDEltAddChange(int type, String name, EObject container) {
		this.type = type;
		this.name = name + RDEltAddChange.nbr;
		this.observers = new ArrayList<Change>();
		RDEltAddChange.nbr++;
		Stereotype stereotype = null;
		if (type == Change.NEW_PERMISSION)
			stereotype = ((Element)UMLModeler.getUMLUIHelper().getSelectedElements().get(0)).getApplicableStereotype(RDSterHelper.PERMISSION);
		else if (type == Change.NEW_FORBIDDEN)
			stereotype = ((Element)UMLModeler.getUMLUIHelper().getSelectedElements().get(0)).getApplicableStereotype(RDSterHelper.FORBIDDEN);
		else if (type == Change.NEW_GRANTED)
			stereotype = ((Element)UMLModeler.getUMLUIHelper().getSelectedElements().get(0)).getApplicableStereotype(RDSterHelper.GRANTED);
		else if (type == Change.NEW_RESOURCE)
			stereotype = ((Element)UMLModeler.getUMLUIHelper().getSelectedElements().get(0)).getApplicableStereotype(RDSterHelper.RESOURCE);
		else if (type == Change.NEW_ROLE)
			stereotype = ((Element)UMLModeler.getUMLUIHelper().getSelectedElements().get(0)).getApplicableStereotype(RDSterHelper.ROLE);
		else if (type == Change.NEW_USER)
			stereotype = ((Element)UMLModeler.getUMLUIHelper().getSelectedElements().get(0)).getApplicableStereotype(RDSterHelper.USER);
		this.stereotype = stereotype;
		this.command = new RDEltAddChangeCommand("Adding Class " + this.name, stereotype, container, 
				this);
	}
	
	public void observe(Change change) {
		this.observers.add(change);
	}
	
	public void changeName(String name) {
		for (Change change:this.observers) {
			change.notify(name, this);
		}
	}

	public boolean conflicts(Change change) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Command getEMFCommand() {
		if (command == null)
			log.debug("EMFCommand is null for addition of Class " + name);
		return command;
	}

	public int getType() {
		return type;
	}

	public int getCost() {
		return 2;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Class getElement() {
		return element;
	}
	
	public void setElement(Class element) {
		this.element = element;
	}
	
	public String toString() {
		String res = "Addition of a Class named " + name;
		if (this.stereotype != null)
			res += " and stereotyped with " + stereotype.getName();
		return res;
		
	}

	public void notify(String name, RDEltAddChange rdEltAddChange) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isUndone() {
		NamedElement element = (NamedElement)UMLModeler.getUMLUIHelper().getSelectedElements().get(0);
		for (Element candidate:element.getOwner().allOwnedElements()) {
			if (candidate instanceof Class) {
				if (((Class)candidate).getName().equals(this.getName()))
					return false;
			}
		}
		return true;
	}
	
	public boolean apply(CommandStack stack) {
		log.debug("Applying...");
		stack.execute(this.getEMFCommand());
		if (isUndone())
			throw new RuntimeException("Could not apply change: " + this.toString());
		log.debug("Applied!");
		return true;
	}
	
	public Command getUndoCommand() {
		return new EltDelChangeCommand("Undoing element creation", this.element);
	}
	
	public boolean undo(CommandStack stack) {
		log.debug("Undoing...");
		stack.execute(getUndoCommand());
		if (!isUndone()) {
			log.debug("Couldn't undo");
			throw new RuntimeException("Change couldn't be undone: " + this.toString());
		}
		log.debug("Undone!");
		return true;
	}

}
