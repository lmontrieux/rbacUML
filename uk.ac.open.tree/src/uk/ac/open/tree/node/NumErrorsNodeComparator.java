/**
 * 
 */
package uk.ac.open.tree.node;

import java.util.Comparator;

/**
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public class NumErrorsNodeComparator implements Comparator<INode> {

	@Override
	public int compare(INode node0, INode node1) {
		return node0.getNumErrors() - node1.getNumErrors();
	}

}
