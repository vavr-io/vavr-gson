package io.vavr.gson.multimap;

import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import io.vavr.collection.List;
import io.vavr.collection.Multimap;
import io.vavr.gson.AbstractTest;
import org.junit.Test;

import java.lang.reflect.Type;

public abstract class MultimapLikeTest<T extends Multimap<?,?>> extends AbstractTest {

    abstract T of(Object key, Object value);
    abstract Class<?> clz();
    abstract Type type();
    abstract Type typeWithNestedType();

    @Test(expected = JsonParseException.class)
    public void badJson() {
        gson.fromJson("1", type());
    }

    @Test
    public void serialize() {
        assert gson.toJson(of(1, 2)).equals("{\"1\":[2]}");
    }

    @Test
    public void deserializeSimpleType() {
        Object obj = gson.fromJson("{\"1\":[2]}", clz());
        assert clz().isAssignableFrom(obj.getClass());
        Multimap<?, ?> map = (Multimap<?, ?>) obj;
        assert map.head()._2 instanceof JsonPrimitive;
        assert ((JsonPrimitive) map.head()._2).getAsInt() == 2;
    }

    @Test
    public void deserialize() {
        Multimap<String, Integer> map = gson.fromJson("{\"1\":[2]}", type());
        assert clz().isAssignableFrom(map.getClass());
        assert map.get("1").get().equals(List.of(2));
    }

    @Test
    public void deserializeWithCast() {
        Multimap<String, Integer> map = gson.fromJson("{\"1\":[\"2\"]}", type());
        assert clz().isAssignableFrom(map.getClass());
        assert map.get("1").get().equals(List.of(2));
    }

    @Test
    public void deserializeNested() {
        Multimap<String, Multimap<String, Integer>> map = gson.fromJson("{\"1\":[{\"2\":[3]}]}", typeWithNestedType());
        assert clz().isAssignableFrom(map.get("1").get().head().getClass());
        assert map.get("1").get().head().get("2").get().equals(List.of(3));
    }
}
