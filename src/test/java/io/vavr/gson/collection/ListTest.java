package io.vavr.gson.collection;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.List;

import java.lang.reflect.Type;
import java.util.Arrays;

public class ListTest extends CollectionTest<List<?>> {

    @Override
    List<?> of(Object... arr) {
        return List.ofAll(Arrays.asList(arr));
    }

    @Override
    Class<?> clz() {
        return List.class;
    }

    @Override
    Type type() {
        return new TypeToken<List<Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<List<List<Integer>>>(){}.getType();
    }
}
