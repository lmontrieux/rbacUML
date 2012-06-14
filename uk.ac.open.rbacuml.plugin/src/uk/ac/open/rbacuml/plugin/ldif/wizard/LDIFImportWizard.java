/**
 * 
 */
package uk.ac.open.rbacuml.plugin.ldif.wizard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import uk.ac.open.rbacuml.plugin.ldif.AbstractUserDirectory;
import uk.ac.open.rbacuml.plugin.ldif.IUser;
import uk.ac.open.rbacuml.plugin.ldif.LDAPDirectory;

/**
 * @author Lionel Montrieux <L.M.C.Montrieux@open.ac.uk>
 *
 */
public class LDIFImportWizard extends Wizard implements IWorkbenchWizard {

	protected DirectorySelectionPage dirPage;
	protected ModelSelectionPage modelPage;
	
	static Logger log = Logger.getLogger(LDIFImportWizard.class);
	
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
		log.debug("Selected user directory: " + dirPage.getDirectoryFile());
		log.debug("Selected model: " + modelPage.getModelFile());
		if (dirPage.hasRedundancyDetection())
			log.debug("Redundant users will be merged");
		else
			log.debug("Redundant users will NOT be merged");
		
		AbstractUserDirectory dir = new LDAPDirectory();
		File ldif = new File(dirPage.getDirectoryFile());
		
		// extracting users and roles from the user directory
		try {
			log.trace("populating...");
			dir.populate(ldif);
		} catch (FileNotFoundException e) {
			log.trace("File not found: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		// if selected, removing redundant users
		// TODO: should provide GUI feedback on which users are removed
		if (dirPage.hasRedundancyDetection()) {
			List<IUser> duplicates = dir.removeAllDuplicates();
			log.debug("Removed " + duplicates.size() + " duplicate users");
		}
		// writing user directory into the model
		log.trace("Starting writing XMI file");
		dir.openXMI(modelPage.getModelFile());
		dir.addToXMI();
		dir.writeXMI(modelPage.getModelFile());
		log.trace("XMI file written");
		return true;
	}


	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}

}
