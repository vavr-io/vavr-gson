/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import com.google.gson.*;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Map;

import java.lang.reflect.Type;
import java.util.function.Function;
import java.util.stream.Collectors;

class MapConverter<K, V, T extends Map<K, V>> extends JsonObjectConverter<T> {

    private final Function<Iterable<Tuple2<String, ?>>, Map<?, ?>> factory;

    MapConverter(Function<Iterable<Tuple2<String, ?>>, Map<?, ?>> factory) {
        this.factory = factory;
    }

    @Override
    @SuppressWarnings("unchecked")
    T fromJsonObject(JsonObject obj, Type type, Type[] subTypes, JsonDeserializationContext ctx) throws JsonParseException {
        Function<java.util.Map.Entry<String, JsonElement>, Tuple2<String, ?>> mapper;
        if (subTypes.length == 2) {
            mapper = e -> Tuple.of(ctx.deserialize(new JsonPrimitive(e.getKey()), subTypes[0]), ctx.deserialize(e.getValue(), subTypes[1]));
        } else {
            mapper = e -> Tuple.of(e.getKey(), e.getValue());
        }
        return (T) factory.apply(obj.entrySet().stream().map(mapper).collect(Collectors.toList()));
    }

    @Override
    Map<K, V> toMap(T src) {
        return src;
    }
}
