package io.vavr.gson.collection;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

import java.lang.reflect.Type;
import java.util.Arrays;

public class SetTest extends CollectionTest<Set<?>> {

    @Override
    Set<?> of(Object... arr) {
        return HashSet.ofAll(Arrays.asList(arr));
    }

    @Override
    Class<?> clz() {
        return Set.class;
    }

    @Override
    Type type() {
        return new TypeToken<Set<Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<Set<Set<Integer>>>(){}.getType();
    }
}
