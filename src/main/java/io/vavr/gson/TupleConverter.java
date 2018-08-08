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
import io.vavr.*;
import io.vavr.collection.List;
import io.vavr.collection.Traversable;

import java.lang.reflect.Type;

abstract class TupleConverter<T> extends JsonArrayConverter<T> {

    static class N0 extends TupleConverter<Tuple0> {

        @Override
        Tuple0 create(Object[] arr) {
            return Tuple.empty();
        }

        @Override
        Traversable<?> toTraversable(Tuple0 src) {
            return List.empty();
        }
    }

    static class N1 extends TupleConverter<Tuple1<?>> {

        @Override
        Tuple1<?> create(Object[] arr) {
            return Tuple.of(arr[0]);
        }

        @Override
        Traversable<?> toTraversable(Tuple1<?> src) {
            return List.of(src._1);
        }
    }

    static class N2 extends TupleConverter<Tuple2<?, ?>> {

        @Override
        Tuple2<?, ?> create(Object[] arr) {
            return Tuple.of(arr[0], arr[1]);
        }

        @Override
        Traversable<?> toTraversable(Tuple2<?, ?> src) {
            return List.of(src._1, src._2);
        }
    }

    static class N3 extends TupleConverter<Tuple3<?, ?, ?>> {

        @Override
        Tuple3<?, ?, ?> create(Object[] arr) {
            return Tuple.of(arr[0], arr[1], arr[2]);
        }

        @Override
        Traversable<?> toTraversable(Tuple3<?, ?, ?> src) {
            return List.of(src._1, src._2, src._3);
        }
    }

    static class N4 extends TupleConverter<Tuple4<?, ?, ?, ?>> {

        @Override
        Tuple4<?, ?, ?, ?> create(Object[] arr) {
            return Tuple.of(arr[0], arr[1], arr[2], arr[3]);
        }

        @Override
        Traversable<?> toTraversable(Tuple4<?, ?, ?, ?> src) {
            return List.of(src._1, src._2, src._3, src._4);
        }
    }

    static class N5 extends TupleConverter<Tuple5<?, ?, ?, ?, ?>> {

        @Override
        Tuple5<?, ?, ?, ?, ?> create(Object[] arr) {
            return Tuple.of(arr[0], arr[1], arr[2], arr[3], arr[4]);
        }

        @Override
        Traversable<?> toTraversable(Tuple5<?, ?, ?, ?, ?> src) {
            return List.of(src._1, src._2, src._3, src._4, src._5);
        }
    }

    static class N6 extends TupleConverter<Tuple6<?, ?, ?, ?, ?, ?>> {

        @Override
        Tuple6<?, ?, ?, ?, ?, ?> create(Object[] arr) {
            return Tuple.of(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
        }

        @Override
        Traversable<?> toTraversable(Tuple6<?, ?, ?, ?, ?, ?> src) {
            return List.of(src._1, src._2, src._3, src._4, src._5, src._6);
        }
    }

    static class N7 extends TupleConverter<Tuple7<?, ?, ?, ?, ?, ?, ?>> {

        @Override
        Tuple7<?, ?, ?, ?, ?, ?, ?> create(Object[] arr) {
            return Tuple.of(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
        }

        @Override
        Traversable<?> toTraversable(Tuple7<?, ?, ?, ?, ?, ?, ?> src) {
            return List.of(src._1, src._2, src._3, src._4, src._5, src._6, src._7);
        }
    }

    static class N8 extends TupleConverter<Tuple8<?, ?, ?, ?, ?, ?, ?, ?>> {

        @Override
        Tuple8<?, ?, ?, ?, ?, ?, ?, ?> create(Object[] arr) {
            return Tuple.of(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7]);
        }

        @Override
        Traversable<?> toTraversable(Tuple8<?, ?, ?, ?, ?, ?, ?, ?> src) {
            return List.of(src._1, src._2, src._3, src._4, src._5, src._6, src._7, src._8);
        }
    }

    abstract T create(Object[] arr);

    @Override
    T fromJsonArray(JsonArray json, Type type, Type[] subTypes, JsonDeserializationContext ctx) {
        if (subTypes.length > 0 && json.size() != subTypes.length) {
            throw new JsonParseException("elements expected: " + subTypes.length);
        }
        if (json.size() > 8) {
            throw new JsonParseException("bad tuple arity");
        }
        Object[] arr = new Object[json.size()];
        for (int i = 0; i < json.size(); i++) {
            JsonElement el = json.get(i);
            arr[i] = subTypes.length > 0 ? ctx.deserialize(el, subTypes[i]) : el;
        }
        return create(arr);
    }
}
