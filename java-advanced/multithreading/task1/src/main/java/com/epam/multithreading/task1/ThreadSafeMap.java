package com.epam.multithreading.task1;

import java.util.*;

public class ThreadSafeMap<K,V> implements Map {
    List<K> keys = new ArrayList<K>();
    List<V> values = new ArrayList<V>();
    private int index;

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean isEmpty() {
        return keys.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values.contains(value);
    }

    @Override
    public Object get(Object key) {
        int index = keys.indexOf(key);
        return index < 0 ? null : values.get(index);
    }

    @Override
    public Object put(Object key, Object value) {
            int index = keys.indexOf(key);
            if (index < 0) {
                index = keys.size();
                keys.add((K) key);
            }
            if (values.size() > index) {
                return values.set(index, (V) value);
            } else {
                values.add(index, (V) value);
            }
        return null;
    }

    @Override
    public Object remove(Object key) {
        int index = keys.indexOf(key);
        if (index >= 0) {
            keys.remove(index);
            return values.remove(index);
        }
        return null;
    }

    @Override
    public void putAll(Map m) {
        for (Object entryObject : m.entrySet()) {
            Entry<K,V> entry = ((Entry<K,V>) entryObject);
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
    }

    @Override
    public Set keySet() {
        return new HashSet(keys);
    }

    @Override
    public Collection values() {
        //return new ArrayList(values);  // without SynchronizedMap
        return values; // with SynchronizedMap
    }

    @Override
    public Set<Entry> entrySet() {
        Set<Entry> entries = new HashSet();
        for (int i = 0; i < keys.size(); i++) {
            entries.add(new AbstractMap.SimpleImmutableEntry(keys.get(i), values.get(i)));
        }
        return entries;
    }
}
