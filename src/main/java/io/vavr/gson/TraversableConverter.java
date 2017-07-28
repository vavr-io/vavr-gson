/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.vavr.collection.Traversable;

import java.lang.reflect.Type;
import java.util.function.Function;

public class TraversableConverter<T extends Traversable<?>> extends JsonArrayConverter<T> {

    private final Function<Iterable<JsonElement>, Traversable<JsonElement>> factory;

    TraversableConverter(Function<Iterable<JsonElement>, Traversable<JsonElement>> factory) {
        this.factory = factory;
    }

    @Override
    @SuppressWarnings("unchecked")
    T fromJsonArray(JsonArray arr, Type type, Type[] subTypes, JsonDeserializationContext ctx) throws JsonParseException {
        Traversable<JsonElement> traversable = factory.apply(arr);
        if (subTypes.length == 1) {
            return (T) traversable.map(e -> ctx.deserialize(e, subTypes[0]));
        } else {
            return (T) traversable;
        }
    }

    @Override
    Traversable<?> toTraversable(T src) {
        return src;
    }
}
