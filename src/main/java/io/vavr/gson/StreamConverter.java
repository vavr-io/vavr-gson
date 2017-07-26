/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import com.google.gson.*;
import io.vavr.collection.Stream;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class StreamConverter implements JsonSerializer<Stream<?>>, JsonDeserializer<Stream<?>> {

    @Override
    public Stream<?> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        if (jsonElement.isJsonArray()) {
            Stream<JsonElement> stream = Stream.ofAll(jsonElement.getAsJsonArray());
            if (type instanceof ParameterizedType) {
                Type[] types = ((ParameterizedType) type).getActualTypeArguments();
                if (types.length == 1) {
                    return stream.map(e -> ctx.deserialize(e, types[0]));
                }
            }
            return stream;
        } else {
            throw new JsonParseException("array expected");
        }
    }

    @Override
    public JsonElement serialize(Stream<?> stream, Type type, JsonSerializationContext ctx) {
        return stream.foldLeft(new JsonArray(), (a, e) -> {
            a.add(ctx.serialize(e));
            return a;
        });
    }
}
