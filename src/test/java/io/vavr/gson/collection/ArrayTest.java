package io.vavr.gson.collection;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.Array;

import java.lang.reflect.Type;
import java.util.Arrays;

public class ArrayTest extends CollectionTest<Array<?>> {

    @Override
    Array<?> of(Object... arr) {
        return Array.ofAll(Arrays.asList(arr));
    }

    @Override
    Class<?> clz() {
        return Array.class;
    }

    @Override
    Type type() {
        return new TypeToken<Array<Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<Array<Array<Integer>>>(){}.getType();
    }
}
