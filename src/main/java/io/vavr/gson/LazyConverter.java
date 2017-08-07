/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import com.google.gson.*;
import io.vavr.Lazy;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

class LazyConverter implements JsonSerializer<Lazy<?>>, JsonDeserializer<Lazy<?>> {

    @Override
    public Lazy<?> deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type subType = parameterizedType.getActualTypeArguments()[0];
            return Lazy.of(() -> ctx.deserialize(json, subType));
        } else {
            return Lazy.of(() -> json);
        }
    }

    @Override
    public JsonElement serialize(Lazy<?> src, Type type, JsonSerializationContext ctx) {
        return ctx.serialize(src.get());
    }
}
