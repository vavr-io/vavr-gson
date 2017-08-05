package io.vavr.gson.collection;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.Queue;

import java.lang.reflect.Type;
import java.util.Arrays;

public class QueueTest extends CollectionTest<Queue<?>> {

    @Override
    Queue<?> of(Object... arr) {
        return Queue.ofAll(Arrays.asList(arr));
    }

    @Override
    Class<?> clz() {
        return Queue.class;
    }

    @Override
    Type type() {
        return new TypeToken<Queue<Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<Queue<Queue<Integer>>>(){}.getType();
    }
}
