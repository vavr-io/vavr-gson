package io.vavr.gson.seq;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import io.vavr.collection.Seq;
import io.vavr.gson.VavrGson;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Type;

public abstract class SeqTest<T extends Seq<?>> {

    abstract T of(Object... arr);
    abstract Type type();
    abstract Type typeWithNestedType();

    private static Gson gson;

    @BeforeClass
    public static void before() {
        GsonBuilder builder = new GsonBuilder();
        VavrGson.registerAll(builder);
        gson = builder.create();
    }

    @Test(expected = JsonParseException.class)
    public void badJson() {
        gson.fromJson("1", type());
    }

    @Test
    public void serialize() {
        assert gson.toJson(of(1, 2)).equals("[1,2]");
    }

    @Test
    public void deserialize() {
        Seq<Integer> seq = gson.fromJson("[1,2]", type());
        assert seq.equals(of(1, 2));
    }

    @Test
    public void deserializeNested() {
        Seq<Integer> seq = gson.fromJson("[[1],[2]]", typeWithNestedType());
        assert seq.equals(of(of(1), of(2)));
    }
}
