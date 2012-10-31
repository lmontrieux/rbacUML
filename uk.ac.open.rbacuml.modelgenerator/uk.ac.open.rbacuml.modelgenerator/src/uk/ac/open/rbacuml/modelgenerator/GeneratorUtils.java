package uk.ac.open.rbacuml.modelgenerator;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import uk.ac.open.rbacuml.modelgenerator.rbac.elements.Element;

public class GeneratorUtils {
	public static Map<String,String> options = new Hashtable<String, String>();
	
	/**
	 * Select a number of elements randonly from a list. Ensures that the same
	 * element is not selected more than once. The elements are ordered randomly
	 * @param list the list from which the elements are selected
	 * @param number the number of elements to select. Must be <= list.size()
	 */
	public static List selectRandomElement(List list, int number) {
		assert(list.size() >= number);
		List result = new ArrayList();
		// If the number of elements to select is equal to the size of the
		// original list, we return it
		if (list.size() == number) {
			return list;
		}
		Random rdm = new Random();
		while (result.size() < number) {
			Element element = (Element)list.get(rdm.nextInt(list.size()));
			if (!result.contains(element)) {
				result.add(element);
			}
		}
		return result;
	}

}
