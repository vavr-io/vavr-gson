/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import io.vavr.collection.Stream;
import io.vavr.collection.Traversable;

import java.lang.reflect.Type;
import java.util.function.Function;

class TraversableConverter<T extends Traversable<?>> extends JsonArrayConverter<T> {

    private final Function<Iterable<?>, Traversable<?>> factory;

    TraversableConverter(Function<Iterable<?>, Traversable<?>> factory) {
        this.factory = factory;
    }

    @Override
    @SuppressWarnings("unchecked")
    T fromJsonArray(JsonArray arr, Type type, Type[] subTypes, JsonDeserializationContext ctx) throws JsonParseException {
        Stream<Object> stream = Stream.ofAll(arr).map(e -> subTypes.length == 1 ? ctx.deserialize(e, subTypes[0]) : e);
        return (T) factory.apply(stream);
    }

    @Override
    Traversable<?> toTraversable(T src) {
        return src;
    }
}
