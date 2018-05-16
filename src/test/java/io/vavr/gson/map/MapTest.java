package io.vavr.gson.map;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;

import java.lang.reflect.Type;

public class MapTest extends MapLikeTest<Map<?,?>> {
    @Override
    Map<?, ?> of(Object key, Object value) {
        return HashMap.of(key, value);
    }

    @Override
    Class<?> clz() {
        return Map.class;
    }

    @Override
    Type type() {
        return new TypeToken<Map<String, Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<Map<String, Map<String, Integer>>>(){}.getType();
    }
}
