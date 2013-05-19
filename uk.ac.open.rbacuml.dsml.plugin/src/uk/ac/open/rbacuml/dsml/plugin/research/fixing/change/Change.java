package uk.ac.open.rbacuml.dsml.plugin.research.fixing.change;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;

public interface Change {
	public static final int ADDITION = 0;
	public static final int DELETION = 1;
	public static final int NEW_USER = 2;
	public static final int NEW_ROLE  = 3;
	public static final int NEW_PERMISSION  = 4;
	public static final int NEW_RESOURCE  = 5;
	public static final int NEW_GRANTED  = 6;
	public static final int NEW_FORBIDDEN  = 7;
	
	public boolean conflicts(Change change);
	
	public Command getEMFCommand();
	
	public Command getUndoCommand();
	
	public int getType();
	
	public int getCost();

	public void notify(String name, RDEltAddChange rdEltAddChange);
	
	public boolean apply(CommandStack stack);
	
	public boolean undo(CommandStack stack);
	
	public boolean isUndone();
}
