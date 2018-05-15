package io.vavr.gson.collection;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.IndexedSeq;
import io.vavr.collection.Vector;

import java.lang.reflect.Type;
import java.util.Arrays;

public class IndexedSeqTest extends CollectionTest<IndexedSeq<?>> {

    @Override
    IndexedSeq<?> of(Object... arr) {
        return Vector.ofAll(Arrays.asList(arr));
    }

    @Override
    Class<?> clz() {
        return IndexedSeq.class;
    }

    @Override
    Type type() {
        return new TypeToken<IndexedSeq<Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<IndexedSeq<IndexedSeq<Integer>>>(){}.getType();
    }
}
