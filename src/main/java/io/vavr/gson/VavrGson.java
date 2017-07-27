/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import com.google.gson.GsonBuilder;
import io.vavr.collection.*;

public class VavrGson {

    public static GsonBuilder registerAll(GsonBuilder builder)  {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        }
        registerAllSeq(builder);
        return builder;
    }

    public static GsonBuilder registerAllSeq(GsonBuilder builder)  {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        }
        registerArray(builder);
        registerList(builder);
        registerQueue(builder);
        registerStream(builder);
        registerVector(builder);
        return builder;
    }

    public static GsonBuilder registerArray(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        }
        builder.registerTypeAdapter(Array.class, new SeqConverter<>(Array::ofAll));
        return builder;
    }

    public static GsonBuilder registerList(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        }
        builder.registerTypeHierarchyAdapter(List.class, new SeqConverter<>(List::ofAll));
        return builder;
    }

    public static GsonBuilder registerQueue(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        }
        builder.registerTypeAdapter(Queue.class, new SeqConverter<>(Queue::ofAll));
        return builder;
    }

    public static GsonBuilder registerStream(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        }
        builder.registerTypeHierarchyAdapter(Stream.class, new SeqConverter<>(Stream::ofAll));
        return builder;
    }

    public static GsonBuilder registerVector(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        }
        builder.registerTypeAdapter(Vector.class, new SeqConverter<>(Vector::ofAll));
        return builder;
    }
}
