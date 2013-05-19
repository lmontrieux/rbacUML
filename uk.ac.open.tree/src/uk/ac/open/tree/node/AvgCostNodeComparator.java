/**
 * 
 */
package uk.ac.open.tree.node;

import java.util.Comparator;

/**
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public class AvgCostNodeComparator implements Comparator<INode> {

	@Override
	public int compare(INode node0, INode node1) {
		double cost0 = node0.getCombinedCost() / node0.getLevel();
		double cost1 = node1.getCombinedCost() / node1.getLevel();
		return new Double(cost0 - cost1).intValue();
	}

}
