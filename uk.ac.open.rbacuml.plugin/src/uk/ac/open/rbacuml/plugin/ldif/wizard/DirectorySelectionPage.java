/**
 * 
 */
package uk.ac.open.rbacuml.plugin.ldif.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Lionel Montrieux <L.M.C.Montrieux@open.ac.uk>
 *
 */
public class DirectorySelectionPage extends WizardPage {
	private Text text1;
	private Composite container;
	private Button button1;
	private Button redundantButton;
	private FileDialog fd;

	protected DirectorySelectionPage() {
		super("Select LDIF file");
		setTitle("Select LDIF file");
		setDescription("LDIF import wizard - user directory selection");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		Label label1 = new Label(container, SWT.NULL);
		label1.setText("User directory file:");

		text1 = new Text(container, SWT.BORDER | SWT.SINGLE);
		text1.setText("");
		
		button1 = new Button(container, SWT.PUSH);
		button1.setText("Browse");
		button1.addListener(SWT.Selection, new Listener() {


			public void handleEvent(Event event) {
				Shell shell = new Shell();
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				text1.setText(fd.open());
				if (!text1.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
			
		});
		text1.addKeyListener(new KeyListener() {


			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}


			public void keyReleased(KeyEvent e) {
				if (!text1.getText().isEmpty()) {
					setPageComplete(true);
				}
			}

		});
		
		// Does the user want to have the redundant users detected and 
		// merged?
		Label redundantLabel = new Label(container, SWT.NULL);
		redundantLabel.setText("Detect and merge redundant users");
		redundantButton = new Button(container, SWT.CHECK);
		redundantButton.setSelection(false);
		
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		text1.setLayoutData(gd);
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(false);

	}
	
	public String getDirectoryFile() {
		return text1.getText();
	}
	
	public boolean hasRedundancyDetection() {
		return redundantButton.getSelection();
	}

}
