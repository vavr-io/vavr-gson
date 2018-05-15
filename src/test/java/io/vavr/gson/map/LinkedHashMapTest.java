package io.vavr.gson.map;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.LinkedHashMap;

import java.lang.reflect.Type;

public class LinkedHashMapTest extends MapLikeTest<LinkedHashMap<?,?>> {
    @Override
    LinkedHashMap<?, ?> of(Object key, Object value) {
        return LinkedHashMap.of(key, value);
    }

    @Override
    Class<?> clz() {
        return LinkedHashMap.class;
    }

    @Override
    Type type() {
        return new TypeToken<LinkedHashMap<String, Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<LinkedHashMap<String, LinkedHashMap<String, Integer>>>(){}.getType();
    }
}
