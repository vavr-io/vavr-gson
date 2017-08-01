/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import com.google.gson.*;
import io.vavr.collection.Map;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract class JsonObjectConverter<T> implements JsonSerializer<T>, JsonDeserializer<T>  {

    private static final Type[] EMPTY_TYPES = new Type[0];

    abstract T fromJsonObject(JsonObject arr, Type type, Type[] subTypes, JsonDeserializationContext ctx) throws JsonParseException;
    abstract Map<?, ?> toMap(T src);

    @Override
    public T deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        if (json.isJsonObject()) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] types = parameterizedType.getActualTypeArguments();
                return fromJsonObject(json.getAsJsonObject(), type, types, ctx);
            } else {
                return fromJsonObject(json.getAsJsonObject(), type, EMPTY_TYPES, ctx);
            }
        } else {
            throw new JsonParseException("object expected");
        }
    }

    @Override
    public JsonElement serialize(T src, Type type, JsonSerializationContext ctx) {
        return toMap(src).foldLeft(new JsonObject(), (a, e) -> {
            a.add(ctx.serialize(e._1).getAsString(), ctx.serialize(e._2));
            return a;
        });
    }
}
