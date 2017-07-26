package io.vavr.gson;

import org.junit.Test;

public class VavrGsonTest {

    @Test(expected = NullPointerException.class)
    public void emptyBuilder() {
        VavrGson.registerAll(null);
    }
}
