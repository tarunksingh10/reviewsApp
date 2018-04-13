package com.reviews.app.util;

public class HashMapSorting {
	/*public static void main(String args[]) throws ParseException {

		HashMap<String, Integer> codenames = new HashMap<String, Integer>();
		codenames.put("JDK 1.1.4", 1);
		codenames.put("J2SE 1.2", 2);
		codenames.put("J2SE 1.3", 3);
	
		System.out.println("HashMap before sorting, random order ");
		Set<Entry<String, Integer>> entries = codenames.entrySet();
		for (Entry<String, Integer> entry : entries) {
			System.out.println(entry.getKey() + " ==> " + entry.getValue());
		}

		TreeMap<String, Integer> sorted = new TreeMap<>(codenames);
		Set<Entry<String, Integer>> mappings = sorted.entrySet();
		System.out.println("HashMap after sorting by keys in ascending order ");
		for (Entry<String, Integer> mapping : mappings) {
			System.out.println(mapping.getKey() + " ==> " + mapping.getValue());
		}

		Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> e1,
					Entry<String, Integer> e2) {
				Integer v1 = e1.getValue();
				Integer v2 = e2.getValue();
				return v1.compareTo(v2);
			}
		};

		List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(
				entries);
		Collections.sort(listOfEntries, valueComparator);
		LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(
				listOfEntries.size());
		for (Entry<String, Integer> entry : listOfEntries) {
			sortedByValue.put(entry.getKey(), entry.getValue());
		}
		System.out.println("HashMap after sorting entries by values ");
		Set<Entry<String, Integer>> entrySetSortedByValue = sortedByValue
				.entrySet();
		for (Entry<String, Integer> mapping : entrySetSortedByValue) {
			System.out.println(mapping.getKey() + " ==> " + mapping.getValue());
		}

		// Read more:
		// http://www.java67.com/2015/01/how-to-sort-hashmap-in-java-based-on.html#ixzz5C5BasdAh
	}
*/}