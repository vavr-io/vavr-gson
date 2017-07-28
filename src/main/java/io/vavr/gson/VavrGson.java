/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import com.google.gson.GsonBuilder;
import io.vavr.Tuple;
import io.vavr.collection.*;

public class VavrGson {

    public static GsonBuilder registerAll(GsonBuilder builder)  {
        checkBuilder(builder);
        registerTuples(builder);
        registerAllTraversables(builder);
        return builder;
    }

    public static GsonBuilder registerTuples(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeHierarchyAdapter(Tuple.class, new TupleConverter());
    }

    public static GsonBuilder registerAllTraversables(GsonBuilder builder)  {
        checkBuilder(builder);
        registerArray(builder);
        registerList(builder);
        registerQueue(builder);
        registerStream(builder);
        registerVector(builder);
        return builder;
    }

    public static GsonBuilder registerArray(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(Array.class, new TraversableConverter<>(Array::ofAll));
    }

    public static GsonBuilder registerList(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeHierarchyAdapter(List.class, new TraversableConverter<>(List::ofAll));
    }

    public static GsonBuilder registerQueue(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(Queue.class, new TraversableConverter<>(Queue::ofAll));
    }

    public static GsonBuilder registerStream(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeHierarchyAdapter(Stream.class, new TraversableConverter<>(Stream::ofAll));
    }

    public static GsonBuilder registerVector(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(Vector.class, new TraversableConverter<>(Vector::ofAll));
    }

    private static GsonBuilder checkBuilder(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        }
        return builder;
    }
}
