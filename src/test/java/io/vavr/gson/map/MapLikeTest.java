package io.vavr.gson.map;

import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import io.vavr.collection.Map;
import io.vavr.gson.AbstractTest;
import org.junit.Test;

import java.lang.reflect.Type;

public abstract class MapLikeTest<T extends Map<?,?>> extends AbstractTest {

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
        assert gson.toJson(of(1, 2)).equals("{\"1\":2}");
    }

    @Test
    public void deserializeSimpleType() {
        Object obj = gson.fromJson("{\"1\":2}", clz());
        assert clz().isAssignableFrom(obj.getClass());
        Map<?, ?> map = (Map<?, ?>) obj;
        assert map.head()._2 instanceof JsonPrimitive;
        assert ((JsonPrimitive) map.head()._2).getAsInt() == 2;
    }

    @Test
    public void deserialize() {
        Map<String, Integer> map = gson.fromJson("{\"1\":2}", type());
        assert clz().isAssignableFrom(map.getClass());
        assert map.get("1").get() == 2;
    }

    @Test
    public void deserializeWithCast() {
        Map<String, Integer> map = gson.fromJson("{\"1\":\"2\"}", type());
        assert clz().isAssignableFrom(map.getClass());
        assert map.get("1").get() == 2;
    }

    @Test
    public void deserializeNested() {
        Map<String, Map<String, Integer>> map = gson.fromJson("{\"1\":{\"2\":3}}", typeWithNestedType());
        assert clz().isAssignableFrom(map.get("1").get().getClass());
        assert map.get("1").get().get("2").get() == 3;
    }
}
