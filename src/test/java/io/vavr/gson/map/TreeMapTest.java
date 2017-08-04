package io.vavr.gson.map;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.TreeMap;

import java.lang.reflect.Type;

public class TreeMapTest extends MapTest<TreeMap<?,?>> {
    @Override
    @SuppressWarnings("unchecked")
    TreeMap<?, ?> of(Object key, Object value) {
        return TreeMap.of((Comparable) key, value);
    }

    @Override
    Class<?> clz() {
        return TreeMap.class;
    }

    @Override
    Type type() {
        return new TypeToken<TreeMap<String, Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<TreeMap<String, TreeMap<String, Integer>>>(){}.getType();
    }
}
