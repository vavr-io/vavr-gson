package io.vavr.gson.collection;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.HashSet;

import java.lang.reflect.Type;
import java.util.Arrays;

public class HashSetTest extends CollectionTest<HashSet<?>> {

    @Override
    HashSet<?> of(Object... arr) {
        return HashSet.ofAll(Arrays.asList(arr));
    }

    @Override
    Class<?> clz() {
        return HashSet.class;
    }

    @Override
    Type type() {
        return new TypeToken<HashSet<Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<HashSet<HashSet<Integer>>>(){}.getType();
    }
}
