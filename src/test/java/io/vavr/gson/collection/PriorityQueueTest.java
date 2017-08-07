package io.vavr.gson.collection;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.PriorityQueue;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Arrays;

public class PriorityQueueTest extends CollectionTest<PriorityQueue<?>> {

    @Override
    @SuppressWarnings("unchecked")
    PriorityQueue<?> of(Object... arr) {
        return PriorityQueue.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), Arrays.asList(arr));
    }

    @Override
    Class<?> clz() {
        return PriorityQueue.class;
    }

    @Override
    Type type() {
        return new TypeToken<PriorityQueue<Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<PriorityQueue<PriorityQueue<Integer>>>(){}.getType();
    }

    @Test
    @Override
    public void deserializeSimpleType() {
        // ignore
    }

    @Test
    @Override
    public void deserializeNested() {
        // ignore
    }
}
