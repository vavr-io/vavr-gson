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
import io.vavr.collection.List;
import io.vavr.collection.Traversable;
import io.vavr.control.Option;

import java.lang.reflect.Type;

class OptionConverter extends JsonArrayConverter<Option<?>> {

    @Override
    Option<?> fromJsonArray(JsonArray arr, Type type, Type[] subTypes, JsonDeserializationContext ctx) throws JsonParseException {
        if (arr.size() < 1 || arr.size() > 2) {
            throw new JsonParseException("bad json format");
        }
        String status = arr.get(0).getAsString();
        if ("defined".equals(status)) {
            if (arr.size() == 2) {
                Object el = subTypes.length > 0 ? ctx.deserialize(arr.get(1), subTypes[0]) : arr.get(1);
                return Option.some(el);
            } else {
                throw new JsonParseException("expected array size: 2");
            }
        } else {
            return Option.none();
        }
    }

    @Override
    Traversable<?> toTraversable(Option<?> src) {
        return src.isDefined() ? List.of("defined", src.get()) : List.of("undefined");
    }
}
