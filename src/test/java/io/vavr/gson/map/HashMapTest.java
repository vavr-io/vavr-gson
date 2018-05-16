package io.vavr.gson.map;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.HashMap;

import java.lang.reflect.Type;

public class HashMapTest extends MapLikeTest<HashMap<?,?>> {
    @Override
    HashMap<?, ?> of(Object key, Object value) {
        return HashMap.of(key, value);
    }

    @Override
    Class<?> clz() {
        return HashMap.class;
    }

    @Override
    Type type() {
        return new TypeToken<HashMap<String, Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<HashMap<String, HashMap<String, Integer>>>(){}.getType();
    }
}
