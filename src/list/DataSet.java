package list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataSet {
	private List<Data>						 elements				 = new ArrayList<>();
	private Map<String, Data>			 elementById		 = new HashMap<>();
	private Map<String, Data>			 elementByAltId	 = new HashMap<>();
	private Map<String, Set<Data>> elementsByCogId = new HashMap<>();

	public void addElement(Data element) {
		if (element.getId() == null) {
			throw new InvalidArgumentException("Unknown id of element");
		}
		if (elementById.get(element.getId()) != null) {
			throw new InvalidArgumentException("Element with given id exists: " + element.getId());
		}
		elements.add(element);
		elementById.put(element.getId(), element);
		if (element.getAltGaId() != null) {
			elementByAltId.put(element.getAltGaId(), element);
		}
	}

	public void addAltMapping(String id, String altGaId) {
		Data element = elementById.get(id);
		if (element == null) {
			throw new InvalidArgumentException("Element with id " + id + " doesn't exist");
		}
		if (elementByAltId.get(altGaId) != null) {
			throw new InvalidArgumentException("At least two elements with gaID: " + altGaId);

		}
		elementByAltId.put(altGaId, element);
		if (element.getAltGaId() != null) {
			System.out.println("more than one element for gaID: " + altGaId);
		}
		element.setAltGaId(altGaId);
	}

	/**
	 * @return the elements
	 * @see #elements
	 */
	public List<Data> getElements() {
		return elements;
	}

	public void addCogMapping(String altId, String cogId) {
		Data element = elementByAltId.get(altId);
		if (element == null) {
			throw new InvalidArgumentException("Element with altId " + altId + " doesn't exist");
		}
		if (elementsByCogId.get(cogId) == null) {
			elementsByCogId.put(cogId, new HashSet<>());
		}

		elementsByCogId.get(cogId).add(element);
		element.getCog().add(new CogData(cogId));
	}

	public void addCogData(String cogId, String cat) {
		Set<Data> cogElements = elementsByCogId.get(cogId);
		if (cogElements == null) {
			System.out.println("Cannot find elements for cog: " + cogId);
		} else {
			for (Data data : cogElements) {
				boolean found = false;
				for (CogData cogData : data.getCog()) {
					if (cogData.getCogId().equals(cogId)) {
						found = true;
						cogData.setCogLetter(cat);
					}
				}
				if (!found) {
					throw new InvalidArgumentException("Problem with element: " + data.getId() + ". Expected cog id couldn't be found: " + cogId);
				}
			}
		}
	}

}
