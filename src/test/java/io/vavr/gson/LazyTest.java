package io.vavr.gson;

import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import io.vavr.Lazy;
import org.junit.Test;

public class LazyTest extends AbstractTest {

    @Test
    public void serializeSome() {
        assert gson.toJson(Lazy.of(() -> 42)).equals("42");
    }

    @Test
    public void deserializeSimpleType() {
        Lazy<?> lazy = gson.fromJson("2", Lazy.class);
        assert lazy.get() instanceof JsonPrimitive;
        assert ((JsonPrimitive) lazy.get()).getAsInt() == 2;
    }

    @Test
    public void deserialize() {
        Lazy<Integer> lazy = gson.fromJson("1", new TypeToken<Lazy<Integer>>(){}.getType());
        assert lazy.get() == 1;
    }

    @Test
    public void deserializeNested() {
        Lazy<Lazy<Integer>> lazy = gson.fromJson("1", new TypeToken<Lazy<Lazy<Integer>>>(){}.getType());
        assert lazy.get().get() == 1;
    }

}
