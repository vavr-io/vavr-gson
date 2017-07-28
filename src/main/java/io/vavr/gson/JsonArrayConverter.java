/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import com.google.gson.*;
import io.vavr.collection.Traversable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract class JsonArrayConverter<T> implements JsonSerializer<T>, JsonDeserializer<T>  {

    private static final Type[] EMPTY_TYPES = new Type[0];

    abstract T fromJsonArray(JsonArray arr, Type type, Type[] subTypes, JsonDeserializationContext ctx) throws JsonParseException;
    abstract Traversable<?> toTraversable(T src);

    @Override
    public T deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        if (json.isJsonArray()) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] types = parameterizedType.getActualTypeArguments();
                return fromJsonArray(json.getAsJsonArray(), type, types, ctx);
            } else {
                return fromJsonArray(json.getAsJsonArray(), type, EMPTY_TYPES, ctx);
            }
        } else {
            throw new JsonParseException("array expected");
        }
    }

    @Override
    public JsonElement serialize(T src, Type type, JsonSerializationContext ctx) {
        return toTraversable(src).foldLeft(new JsonArray(), (a, e) -> {
            a.add(ctx.serialize(e));
            return a;
        });
    }
}
