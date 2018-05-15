package io.vavr.gson.map;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.SortedMap;
import io.vavr.collection.TreeMap;

import java.lang.reflect.Type;

public class SortedMapTest extends MapLikeTest<SortedMap<?,?>> {
    @Override
    @SuppressWarnings("unchecked")
    SortedMap<?, ?> of(Object key, Object value) {
        return TreeMap.of((Comparable) key, value);
    }

    @Override
    Class<?> clz() {
        return SortedMap.class;
    }

    @Override
    Type type() {
        return new TypeToken<SortedMap<String, Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<SortedMap<String, SortedMap<String, Integer>>>(){}.getType();
    }
}
