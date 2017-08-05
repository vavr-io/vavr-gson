package io.vavr.gson.collection;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.Stream;

import java.lang.reflect.Type;
import java.util.Arrays;

public class StreamTest extends CollectionTest<Stream<?>> {

    @Override
    Stream<?> of(Object... arr) {
        return Stream.ofAll(Arrays.asList(arr));
    }

    @Override
    Class<?> clz() {
        return Stream.class;
    }

    @Override
    Type type() {
        return new TypeToken<Stream<Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<Stream<Stream<Integer>>>(){}.getType();
    }
}
