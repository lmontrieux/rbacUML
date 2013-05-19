/**
 * 
 */
package uk.ac.open.tree.node;

import java.util.Comparator;

/**
 * @author Lionel Montrieux <Lionel.Montrieux@open.ac.uk>
 *
 */
public class RedErrorsNodeComparator implements Comparator<INode> {

	@Override
	public int compare(INode node0, INode node1) {
		int err0 = node0.getParent().getNumErrors() - node0.getNumErrors();
		int err1 = node1.getParent().getNumErrors() - node0.getNumErrors();
		return err1 - err0;
	}

}
