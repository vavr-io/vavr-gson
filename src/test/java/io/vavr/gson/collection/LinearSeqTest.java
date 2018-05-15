package io.vavr.gson.collection;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.LinearSeq;
import io.vavr.collection.List;

import java.lang.reflect.Type;
import java.util.Arrays;

public class LinearSeqTest extends CollectionTest<LinearSeq<?>> {

    @Override
    LinearSeq<?> of(Object... arr) {
        return List.ofAll(Arrays.asList(arr));
    }

    @Override
    Class<?> clz() {
        return LinearSeq.class;
    }

    @Override
    Type type() {
        return new TypeToken<LinearSeq<Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<LinearSeq<LinearSeq<Integer>>>(){}.getType();
    }
}
