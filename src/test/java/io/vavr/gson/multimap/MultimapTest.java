package io.vavr.gson.multimap;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.Multimap;

import java.lang.reflect.Type;

public class MultimapTest extends MultimapLikeTest<Multimap<?,?>> {
    @Override
    Multimap<?, ?> of(Object key, Object value) {
        return HashMultimap.withSeq().of(key, value);
    }

    @Override
    Class<?> clz() {
        return Multimap.class;
    }

    @Override
    Type type() {
        return new TypeToken<Multimap<String, Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<Multimap<String, Multimap<String, Integer>>>(){}.getType();
    }
}
