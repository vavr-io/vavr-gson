/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import com.google.gson.*;
import io.vavr.collection.Seq;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Function;

public class SeqConverter<T extends Seq<?>> implements JsonSerializer<T>, JsonDeserializer<T> {

    private final Function<Iterable<JsonElement>, Seq<JsonElement>> factory;

    SeqConverter(Function<Iterable<JsonElement>, Seq<JsonElement>> factory) {
        this.factory = factory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        if (jsonElement.isJsonArray()) {
            Seq<JsonElement> seq = factory.apply(jsonElement.getAsJsonArray());
            if (type instanceof ParameterizedType) {
                Type[] types = ((ParameterizedType) type).getActualTypeArguments();
                if (types.length == 1) {
                    return (T) seq.map(e -> ctx.deserialize(e, types[0]));
                }
            }
            return (T) seq;
        } else {
            throw new JsonParseException("array expected");
        }
    }

    @Override
    public JsonElement serialize(T seq, Type type, JsonSerializationContext ctx) {
        return seq.foldLeft(new JsonArray(), (a, e) -> {
            a.add(ctx.serialize(e));
            return a;
        });
    }
}
