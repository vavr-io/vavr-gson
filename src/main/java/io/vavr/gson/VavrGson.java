/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import com.google.gson.GsonBuilder;
import io.vavr.collection.Stream;

public class VavrGson {

    public static GsonBuilder registerAll(GsonBuilder builder)  {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        }
        registerStream(builder);
        return builder;
    }

    public static GsonBuilder registerStream(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        }
        builder.registerTypeHierarchyAdapter(Stream.class, new StreamConverter());
        return builder;
    }
}
