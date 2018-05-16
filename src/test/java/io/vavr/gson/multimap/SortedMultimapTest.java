package io.vavr.gson.multimap;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.SortedMultimap;
import io.vavr.collection.TreeMultimap;

import java.lang.reflect.Type;

public class SortedMultimapTest extends MultimapLikeTest<SortedMultimap<?,?>> {
    @Override
    @SuppressWarnings("unchecked")
    SortedMultimap<?, ?> of(Object key, Object value) {
        return TreeMultimap.withSeq().of((Comparable) key, value);
    }

    @Override
    Class<?> clz() {
        return SortedMultimap.class;
    }

    @Override
    Type type() {
        return new TypeToken<SortedMultimap<String, Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<SortedMultimap<String, SortedMultimap<String, Integer>>>(){}.getType();
    }
}
