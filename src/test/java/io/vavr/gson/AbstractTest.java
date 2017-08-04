package io.vavr.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.BeforeClass;

public class AbstractTest {

    protected static Gson gson;

    @BeforeClass
    public static void before() {
        GsonBuilder builder = new GsonBuilder();
        VavrGson.registerAll(builder);
        gson = builder.create();
    }

}
