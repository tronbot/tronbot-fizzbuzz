package io.tronbot.fizzbuzz.v4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Storing Trees
 * 
 * Suppose you had a tree structure, where each node contains a geographic name
 * (e.g., continent, country, state, etc.), and each node can have many children
 * but exactly one parent. What database schema would you use to store such a
 * structure? Given a particular node, how would you enumerate all of that
 * node’s siblings?
 * 
 * @Author Juanyong Zhang
 * @Date Oct 24, 2016
 */
public class StoringTrees<VAL> {
	public class Entry<V> {
		public Entry(Long id, Long parentId, V value) {
			super();
			this.id = id;
			this.parentId = parentId;
			this.value = value;
		}

		private final Long id;
		private final Long parentId;
		private final V value;

		public Long getId() {
			return id;
		}

		public Long getParentId() {
			return parentId;
		}

		public V getValue() {
			return value;
		}
	}

	private List<Entry<VAL>> entries = new ArrayList<>();

	public void put(Long id, Long parentId, VAL value) {
		entries.add(new Entry<VAL>(id, parentId, value));
	}

	public void clear() {
		entries.clear();
	}

	public Entry<VAL> getEntryById(Long id) {
		Optional<Entry<VAL>> res = entries.stream().filter(entry -> entry.id.equals(id)).findFirst();
		return res.isPresent() ? res.get() : null;
	}

	public Long getId(VAL node) {
		Entry<VAL> res = getEntry(node);
		return res == null ? ((long) entries.size() + 1) : res.getId();
	}

	public Entry<VAL> getEntry(VAL node) {
		Optional<Entry<VAL>> res = entries.stream().filter(entry -> entry.value.equals(node)).findFirst();
		return res.isPresent() ? res.get() : null;
	}

	public List<VAL> getSiblings(VAL node) {
		Entry<VAL> given = getEntry(node);
		if (null != given && null != given.parentId) {
			return entries.stream().filter(entry -> !entry.equals(given) && given.parentId.equals(entry.parentId))
					.map(entry -> entry.value).collect(Collectors.toList());
		}
		return null;
	}
}
