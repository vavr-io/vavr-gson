package io.vavr.gson.multimap;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.HashMultimap;

import java.lang.reflect.Type;

public class HashMultimapTest extends MultimapLikeTest<HashMultimap<?,?>> {
    @Override
    HashMultimap<?, ?> of(Object key, Object value) {
        return HashMultimap.withSeq().of(key, value);
    }

    @Override
    Class<?> clz() {
        return HashMultimap.class;
    }

    @Override
    Type type() {
        return new TypeToken<HashMultimap<String, Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<HashMultimap<String, HashMultimap<String, Integer>>>(){}.getType();
    }
}
