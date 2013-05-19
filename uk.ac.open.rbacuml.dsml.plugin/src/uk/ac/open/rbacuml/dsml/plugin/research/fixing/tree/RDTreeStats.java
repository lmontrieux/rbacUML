/**
 * 
 */
package uk.ac.open.rbacuml.dsml.plugin.research.fixing.tree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public class RDTreeStats {
	private String name;
	private long start;
	private long end;
	private List<Long> solutionTimes;
	private List<Integer> solutionCosts;
	private int children;
	private int modelID = 1;
	private int evalID = 1;
	private List<RbacNode> nodes;
	private Logger log = Logger.getLogger(this.getClass());
	
	public RDTreeStats(String name) {
		this.name = name;
		this.solutionCosts = new ArrayList<Integer>();
		this.solutionTimes = new ArrayList<Long>();
		this.nodes = new ArrayList<RbacNode>();
		this.children = 1; // we need to count the root
	}
	
	public void addSolution(int cost, long time) {
		solutionCosts.add(new Integer(cost));
		solutionTimes.add(new Long(time));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}
	
	public long getTotalTimeMilli() {
		return end - start;
	}
	
	public long getSolTimeMilli(int i) {
		return this.solutionTimes.get(i) - this.start;
	}
	
	public void addChild() {
		this.children++;
	}
	
	public int getNumNodes() {
		return this.children;
	}
	
	public String toString() {
		String stats = "Model name: " + name + "\n";
		stats += "Total generation time: " + getTotalTimeMilli() + " ms\n";
		stats += "Generated " + this.solutionTimes.size() + " solutions using " + children + " nodes \n";
		for (int i = 0; i < this.solutionTimes.size(); i++) {
			stats += "Solution " + new Integer(i + 1).intValue() + " cost " + this.solutionCosts.get(i) + " and generated after " + getSolTimeMilli(i) + " ms\n";
		}
		stats += "\n";
		for (RbacNode node:this.nodes) {
			stats += "Node: " + node.toString() + "\n";
		}
		return stats;
	}
	
	/**
	 * Exports the statistics to a file.
	 * @param filename the name of the file (incl. path)
	 * @return true if the stats could be written to the file, false otherwise
	 */
	public boolean toFile(String filename) {
		File file = new File(filename);
		BufferedWriter out = null;
		try {
			FileWriter fstream = new FileWriter(file);
			out = new BufferedWriter(fstream);
			out.write(toString());
		} catch (IOException e) {
			log.debug("Problem while exporting stats to file: " + e.getMessage());
			return false;
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				log.debug("Can't close buffer on file " + filename);
				return false;
			}
		}
		return true;
	}
	
	public void addNode(RbacNode node) {
		this.nodes.add(node);
	}
}
