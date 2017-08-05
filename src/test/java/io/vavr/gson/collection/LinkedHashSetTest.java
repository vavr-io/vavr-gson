package io.vavr.gson.collection;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.LinkedHashSet;

import java.lang.reflect.Type;
import java.util.Arrays;

public class LinkedHashSetTest extends CollectionTest<LinkedHashSet<?>> {

    @Override
    LinkedHashSet<?> of(Object... arr) {
        return LinkedHashSet.ofAll(Arrays.asList(arr));
    }

    @Override
    Class<?> clz() {
        return LinkedHashSet.class;
    }

    @Override
    Type type() {
        return new TypeToken<LinkedHashSet<Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<LinkedHashSet<LinkedHashSet<Integer>>>(){}.getType();
    }
}
