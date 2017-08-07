/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import com.google.gson.GsonBuilder;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.collection.*;
import io.vavr.control.Option;

/**
 * Contains static methods for registering Vavr converters.
 */
public class VavrGson {

    private VavrGson() {}

    /**
     * Registers all the Vavr converters.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerAll(GsonBuilder builder)  {
        checkBuilder(builder);
        registerTuples(builder);
        registerAllCollections(builder);
        registerAllMaps(builder);
        registerAllMultimaps(builder);
        registerLazy(builder);
        registerOption(builder);
        return builder;
    }

    /**
     * Registers the {@link Lazy} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerLazy(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(Lazy.class, new LazyConverter());
    }

    /**
     * Registers the {@link Option} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerOption(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeHierarchyAdapter(Option.class, new OptionConverter());
    }

    /**
     * Registers all the {@link Multimap} converters.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerAllMultimaps(GsonBuilder builder) {
        checkBuilder(builder);
        registerHashMultimap(builder);
        registerLinkedHashMultimap(builder);
        registerTreeMultimap(builder);
        return builder;
    }

    /**
     * Registers the {@link HashMultimap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerHashMultimap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(HashMultimap.class, new MultimapConverter<>(t -> HashMultimap.withSeq().ofEntries(t)));
    }

    /**
     * Registers the {@link LinkedHashMultimap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerLinkedHashMultimap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(LinkedHashMultimap.class, new MultimapConverter<>(t -> LinkedHashMultimap.withSeq().ofEntries(t)));
    }

    /**
     * Registers the {@link TreeMultimap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerTreeMultimap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(TreeMultimap.class, new MultimapConverter<>(t -> TreeMultimap.withSeq().ofEntries(t)));
    }

    /**
     * Registers all the {@link Map} converters.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerAllMaps(GsonBuilder builder) {
        checkBuilder(builder);
        registerHashMap(builder);
        registerLinkedHashMap(builder);
        registerTreeMap(builder);
        return builder;
    }

    /**
     * Registers the {@link HashMap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerHashMap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(HashMap.class, new MapConverter<>(HashMap::ofEntries));
    }

    /**
     * Registers the {@link LinkedHashMap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerLinkedHashMap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(LinkedHashMap.class, new MapConverter<>(LinkedHashMap::ofEntries));
    }

    /**
     * Registers the {@link TreeMap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerTreeMap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(TreeMap.class, new MapConverter<>(TreeMap::ofEntries));
    }

    /**
     * Registers all the {@link Tuple}s converters.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerTuples(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeHierarchyAdapter(Tuple.class, new TupleConverter());
    }

    /**
     * Registers all the collection converters.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerAllCollections(GsonBuilder builder)  {
        checkBuilder(builder);
        registerArray(builder);
        registerList(builder);
        registerQueue(builder);
        registerStream(builder);
        registerVector(builder);
        registerHashSet(builder);
        registerLinkedHashSet(builder);
        registerTreeSet(builder);
        return builder;
    }

    /**
     * Registers the {@link HashSet} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerHashSet(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(HashSet.class, new TraversableConverter<>(HashSet::ofAll));
    }

    /**
     * Registers the {@link LinkedHashSet} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerLinkedHashSet(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(LinkedHashSet.class, new TraversableConverter<>(LinkedHashSet::ofAll));
    }

    /**
     * Registers the {@link TreeSet} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    @SuppressWarnings("unchecked")
    public static GsonBuilder registerTreeSet(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(TreeSet.class, new TraversableConverter<>(it -> TreeSet.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), it)));
    }

    /**
     * Registers the {@link Array} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerArray(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(Array.class, new TraversableConverter<>(Array::ofAll));
    }

    /**
     * Registers the {@link List} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerList(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeHierarchyAdapter(List.class, new TraversableConverter<>(List::ofAll));
    }

    /**
     * Registers the {@link Queue} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerQueue(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(Queue.class, new TraversableConverter<>(Queue::ofAll));
    }

    /**
     * Registers the {@link Stream} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerStream(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeHierarchyAdapter(Stream.class, new TraversableConverter<>(Stream::ofAll));
    }

    /**
     * Registers the {@link Vector} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
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
