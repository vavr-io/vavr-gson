package io.vavr.gson.collection;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.TreeSet;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Arrays;

public class TreeSetTest extends CollectionTest<TreeSet<?>> {

    @Override
    @SuppressWarnings("unchecked")
    TreeSet<?> of(Object... arr) {
        return TreeSet.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), Arrays.asList(arr));
    }

    @Override
    Class<?> clz() {
        return TreeSet.class;
    }

    @Override
    Type type() {
        return new TypeToken<TreeSet<Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<TreeSet<TreeSet<Integer>>>(){}.getType();
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
