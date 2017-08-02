package io.vavr.gson.multimap;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.TreeMultimap;

import java.lang.reflect.Type;

public class TreeMultimapTest extends MultimapTest<TreeMultimap<?,?>> {
    @Override
    @SuppressWarnings("unchecked")
    TreeMultimap<?, ?> of(Object key, Object value) {
        return TreeMultimap.withSeq().of((Comparable) key, value);
    }

    @Override
    Class<?> clz() {
        return TreeMultimap.class;
    }

    @Override
    Type type() {
        return new TypeToken<TreeMultimap<String, Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<TreeMultimap<String, TreeMultimap<String, Integer>>>(){}.getType();
    }
}
