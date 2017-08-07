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
import io.vavr.Tuple;
import io.vavr.Tuple0;
import io.vavr.collection.Traversable;

import java.lang.reflect.Type;

class TupleConverter extends JsonArrayConverter<Tuple> {

    @Override
    Tuple fromJsonArray(JsonArray json, Type type, Type[] subTypes, JsonDeserializationContext ctx) {
        if (subTypes.length > 0 && json.size() != subTypes.length) {
            throw new JsonParseException("elements expected: " + subTypes.length);
        }
        Object[] arr = new Object[json.size()];
        for (int i = 0; i < json.size(); i++) {
            JsonElement el = json.get(i);
            arr[i] = subTypes.length > 0 ? ctx.deserialize(el, subTypes[i]) : el;
        }
        switch (json.size()) {
            case 0:
                return Tuple0.instance();
            case 1:
                return Tuple.of(arr[0]);
            case 2:
                return Tuple.of(arr[0], arr[1]);
            case 3:
                return Tuple.of(arr[0], arr[1], arr[2]);
            case 4:
                return Tuple.of(arr[0], arr[1], arr[2], arr[3]);
            case 5:
                return Tuple.of(arr[0], arr[1], arr[2], arr[3], arr[4]);
            case 6:
                return Tuple.of(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
            case 7:
                return Tuple.of(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
            case 8:
                return Tuple.of(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7]);
            default:
                throw new JsonParseException("bad tuple arity");
        }
    }

    @Override
    Traversable<?> toTraversable(Tuple src) {
        return src.toSeq();
    }
}
