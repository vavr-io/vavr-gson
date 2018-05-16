package io.vavr.gson.collection;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.SortedSet;
import io.vavr.collection.TreeSet;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Arrays;

public class SortedSetTest extends CollectionTest<SortedSet<?>> {

    @Override
    @SuppressWarnings("unchecked")
    SortedSet<?> of(Object... arr) {
        return TreeSet.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), Arrays.asList(arr));
    }

    @Override
    Class<?> clz() {
        return SortedSet.class;
    }

    @Override
    Type type() {
        return new TypeToken<SortedSet<Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<SortedSet<SortedSet<Integer>>>(){}.getType();
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
