/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import com.google.gson.GsonBuilder;
import io.vavr.Tuple;
import io.vavr.collection.*;

public class VavrGson {

    public static GsonBuilder registerAll(GsonBuilder builder)  {
        checkBuilder(builder);
        registerTuples(builder);
        registerAllCollections(builder);
        registerAllMaps(builder);
        registerAllMultimaps(builder);
        return builder;
    }

    public static GsonBuilder registerAllMultimaps(GsonBuilder builder) {
        checkBuilder(builder);
        registerHashMultimap(builder);
        registerLinkedHashMultimap(builder);
        registerTreeMultimap(builder);
        return builder;
    }

    public static GsonBuilder registerHashMultimap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(HashMultimap.class, new MultimapConverter<>(t -> HashMultimap.withSeq().ofEntries(t)));
    }

    public static GsonBuilder registerLinkedHashMultimap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(LinkedHashMultimap.class, new MultimapConverter<>(t -> LinkedHashMultimap.withSeq().ofEntries(t)));
    }

    public static GsonBuilder registerTreeMultimap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(TreeMultimap.class, new MultimapConverter<>(t -> TreeMultimap.withSeq().ofEntries(t)));
    }

    public static GsonBuilder registerAllMaps(GsonBuilder builder) {
        checkBuilder(builder);
        registerHashMap(builder);
        registerLinkedHashMap(builder);
        registerTreeMap(builder);
        return builder;
    }

    public static GsonBuilder registerHashMap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(HashMap.class, new MapConverter<>(HashMap::ofEntries));
    }

    public static GsonBuilder registerLinkedHashMap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(LinkedHashMap.class, new MapConverter<>(LinkedHashMap::ofEntries));
    }

    public static GsonBuilder registerTreeMap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(TreeMap.class, new MapConverter<>(TreeMap::ofEntries));
    }

    public static GsonBuilder registerTuples(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeHierarchyAdapter(Tuple.class, new TupleConverter());
    }

    public static GsonBuilder registerAllCollections(GsonBuilder builder)  {
        checkBuilder(builder);
        registerArray(builder);
        registerList(builder);
        registerQueue(builder);
        registerStream(builder);
        registerVector(builder);
        registerHashSet(builder);
        registerLinkedHashSet(builder);
        return builder;
    }

    public static GsonBuilder registerHashSet(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(HashSet.class, new TraversableConverter<>(HashSet::ofAll));
    }

    public static GsonBuilder registerLinkedHashSet(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(LinkedHashSet.class, new TraversableConverter<>(LinkedHashSet::ofAll));
    }

    public static GsonBuilder registerArray(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(Array.class, new TraversableConverter<>(Array::ofAll));
    }

    public static GsonBuilder registerList(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeHierarchyAdapter(List.class, new TraversableConverter<>(List::ofAll));
    }

    public static GsonBuilder registerQueue(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(Queue.class, new TraversableConverter<>(Queue::ofAll));
    }

    public static GsonBuilder registerStream(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeHierarchyAdapter(Stream.class, new TraversableConverter<>(Stream::ofAll));
    }

    public static GsonBuilder registerVector(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(Vector.class, new TraversableConverter<>(Vector::ofAll));
    }

    private static GsonBuilder checkBuilder(GsonBuilder builder) {
        if (builder == null) {
            throw new NullPointerException("builder cannot be null");
        }
        return builder;
    }
}
