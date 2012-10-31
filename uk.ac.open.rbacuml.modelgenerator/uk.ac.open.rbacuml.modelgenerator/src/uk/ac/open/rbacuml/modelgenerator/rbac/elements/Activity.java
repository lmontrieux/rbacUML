package uk.ac.open.rbacuml.modelgenerator.rbac.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jdom.Namespace;

public class Activity implements Element {
	private String name;
	private Element parent;
	private List<ActivityPartition> partitions;
	private List<Action> actions;
	private UUID baseUUID;
	private UUID sterUUID;
	
	public Activity(String name, Element parent) {
		this.name = name;
		this.parent = parent;
		this.partitions = new ArrayList<ActivityPartition>();
		this.actions = new ArrayList<Action>();
		this.baseUUID = UUID.randomUUID();
		this.sterUUID = UUID.randomUUID();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Element getParent() {
		return this.parent;
	}
	
	public ActivityPartition getActivityPartition(String name) {
		for (ActivityPartition partition: this.partitions) {
			if (partition.getName().equals(name))
				return partition;
		}
		return null;
	}
	
	public List<ActivityPartition> getActivityPartitions() {
		return this.partitions;
	}
	
	public boolean addActivityPartition(ActivityPartition partition) {
		if (this.partitions.contains(partition))
			return false;
		return this.partitions.add(partition);
	}
	
	public String toString() {
		return "Activity " + this.name + "\t partitions: " + partitions.size();
	}
	
	public UUID getBaseUUID() {
		return this.baseUUID;
	}
	
	public UUID getSterUUID() {
		return this.sterUUID;
	}
	
	public org.jdom.Element toXmi(Namespace xmiNS) {
		org.jdom.Element element = new org.jdom.Element("packagedElement");
		element.setAttribute("type", "uml:Activity", xmiNS);
		element.setAttribute("id", this.baseUUID.toString(), xmiNS);
		element.setAttribute("name", this.name);
		// adding the list of partitions, both as attribute and as <group> child
		// (why it needs to be in both places is beyond me)
		String partitions = "";
		for (ActivityPartition ap: this.partitions) {
			partitions += " " + ap.getBaseUUID().toString();
			element.addContent(ap.toXmi(xmiNS));
		}
		if (!partitions.equals(""))
			partitions = partitions.substring(1);
		element.setAttribute("partition", partitions);
		
		// Adding the list of actions as a <node> child
		for (Action action:this.actions){
			element.addContent(action.toXmi(xmiNS));
		}
		
		return element;
	}

	public boolean addAction(Action action) {
		if (!this.actions.contains(action)) {
			this.actions.add(action);
			return true;
		} else
			return false;
	}
}
