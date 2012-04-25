package uk.ac.open.rbacuml.plugin.ldif;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class LDAPDirectory extends AbstractUserDirectory {
	
	public LDAPDirectory() {
		super();
	}

	@Override
	public boolean populate(File source) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(source));
		String line = null;
		List<String> record = new ArrayList<String>();
		try {
			while ((line = br.readLine()) != null) {
				if (line.startsWith("dn: ")) {
					// a record always starts with 'dn: '
					// when we find a new record, we handle the previous one 
					// then start populating a new one
					if (record.size() > 0) {
						handleRecord(record);
						record.clear();
					}
					record.add(line);
				}
				else if (line.startsWith("#") || line.isEmpty()) {
					// comments and empty lines should be ignored.
				}
				else {
					record.add(line);
				}
			}
			handleRecord(record);
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Converts an array of String representing an LDAP record in LDIF format 
	 * into the internal directory representation. Ignores all the unnecessary 
	 * information.
	 * @param record the record to be handled, as a List of Strings. The first 
	 * line starts with 'dn: '
	 */
	private void handleRecord(List<String> record) {
		// the record must start with 'dn: '
		assert(record.get(0).startsWith("dn: "));
		
		String name = record.get(0).substring(4);
		if (name.contains("User")) {
			// it's probably a user, let's check for an objectClass
			IUser user = new SimpleUser(name, UUID.randomUUID().toString());
			boolean isUser = false;
			for(String line:record) {
				if (line.equals("objectClass: user")) {
					// it's definitely a user!
					isUser = true;
				} else if (line.startsWith("memberOf: ") && line.contains("Group")) {
					//it's a group! We create a new Role!
					line = line.substring(10);
					IRole role = new SimpleRole(line, UUID.randomUUID().toString());
					user.addRole(role);
					addRole(role);
				}
			}
			if (isUser)
				addUser(user);
			
		} else if (name.contains("Group")) {
			// it's probably a group, let's check for an objectClass
			IRole role = new SimpleRole(name, UUID.randomUUID().toString());
			for(String line:record) {
				if (line.equals("objectClass: group")) {
					// it's definitely a group!
					addRole(role);
				} else if (line.startsWith("member: ") && line.contains("User")) {
					// That's a User that belongs to that group!
					line = line.substring(8);
					if (hasUser(line)) {
						getUser(line).addRole(role);
					} else {
						IUser newUser = new SimpleUser(line, UUID.randomUUID().toString());
						newUser.addRole(role);
						addUser(newUser);
					}
				} else if (line.startsWith("Member: ") && line.contains("Group")) {
					// We have a role hierarchy in here!
					line = line.substring(8);
					IRole parent = new SimpleRole(line, UUID.randomUUID().toString());
					if (!hasRole(line)) {
						addRole(parent);
					}
					getRole(line).addParent(parent);
				}
			}
		}
	}

}
