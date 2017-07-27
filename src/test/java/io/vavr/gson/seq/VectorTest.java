package io.vavr.gson.seq;

import com.google.gson.reflect.TypeToken;
import io.vavr.collection.Vector;

import java.lang.reflect.Type;
import java.util.Arrays;

public class VectorTest extends SeqTest<Vector<?>> {

    @Override
    Vector<?> of(Object... arr) {
        return Vector.ofAll(Arrays.asList(arr));
    }

    @Override
    Class<?> clz() {
        return Vector.class;
    }

    @Override
    Type type() {
        return new TypeToken<Vector<Integer>>(){}.getType();
    }

    @Override
    Type typeWithNestedType() {
        return new TypeToken<Vector<Vector<Integer>>>(){}.getType();
    }
}
