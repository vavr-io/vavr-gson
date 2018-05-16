package io.vavr.gson.multimap;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.LinkedHashMultimap;

import java.lang.reflect.Type;

public class LinkedHashMultimapTest extends MultimapLikeTest<LinkedHashMultimap<?,?>> {
    @Override
    LinkedHashMultimap<?, ?> of(Object key, Object value) {
        return LinkedHashMultimap.withSeq().of(key, value);
    }

    @Override
    Class<?> clz() {
        return LinkedHashMultimap.class;
    }

    @Override
    Type type() {
        return new TypeToken<LinkedHashMultimap<String, Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<LinkedHashMultimap<String, LinkedHashMultimap<String, Integer>>>(){}.getType();
    }
}
