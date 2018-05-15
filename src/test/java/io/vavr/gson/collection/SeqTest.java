package io.vavr.gson.collection;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.List;
import io.vavr.collection.Seq;

import java.lang.reflect.Type;
import java.util.Arrays;

public class SeqTest extends CollectionTest<Seq<?>> {

    @Override
    Seq<?> of(Object... arr) {
        return List.ofAll(Arrays.asList(arr));
    }

    @Override
    Class<?> clz() {
        return Seq.class;
    }

    @Override
    Type type() {
        return new TypeToken<Seq<Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<Seq<Seq<Integer>>>(){}.getType();
    }
}
