/**
 * 
 */
package uk.ac.open.rbacuml.plugin.ldif.wizard;

import java.io.File;
import java.io.FileNotFoundException;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import uk.ac.open.rbacuml.plugin.ldif.AbstractUserDirectory;
import uk.ac.open.rbacuml.plugin.ldif.LDAPDirectory;

/**
 * @author Lionel Montrieux <L.M.C.Montrieux@open.ac.uk>
 *
 */
public class LDIFImportWizard extends Wizard implements IWorkbenchWizard {

	protected DirectorySelectionPage dirPage;
	protected ModelSelectionPage modelPage;
	
	/**
	 * Creates a new wizard to import the contents of an LDIF file into a 
	 * UML model
	 */
	public LDIFImportWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	public void addPages() {
		dirPage = new DirectorySelectionPage();
		modelPage = new ModelSelectionPage();
		addPage(dirPage);
		addPage(modelPage);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		System.out.println("Selected user directory: " + dirPage.getDirectoryFile());
		System.out.println("Selected model: " + modelPage.getModelFile());
		
		AbstractUserDirectory dir = new LDAPDirectory();
		File ldif = new File(dirPage.getDirectoryFile());
		
		// extracting users and roles from the user directory
		try {
			System.out.println("populating...");
			dir.populate(ldif);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		// writing user directory into the model
		dir.openXMI(modelPage.getModelFile());
		dir.addToXMI();
		dir.writeXMI(modelPage.getModelFile());
		return true;
	}


	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}

}
