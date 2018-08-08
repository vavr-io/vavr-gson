/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import com.google.gson.GsonBuilder;
import io.vavr.*;
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
        registerMultimap(builder);
        registerSortedMultimap(builder);
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
     * Registers the {@link Multimap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerMultimap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(Multimap.class, new MultimapConverter<>(t -> HashMultimap.withSeq().ofEntries(t)));
    }

    /**
     * Registers the {@link SortedMultimap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerSortedMultimap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(SortedMultimap.class, new MultimapConverter<>(t -> TreeMultimap.withSeq().ofEntries(t)));
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
        registerMap(builder);
        registerSortedMap(builder);
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
     * Registers the {@link Map} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerMap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(Map.class, new MapConverter<>(HashMap::ofEntries));
    }

    /**
     * Registers the {@link SortedMap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerSortedMap(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(SortedMap.class, new MapConverter<>(TreeMap::ofEntries));
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
        return checkBuilder(builder)
                .registerTypeAdapter(Tuple0.class, new TupleConverter.N0())
                .registerTypeAdapter(Tuple1.class, new TupleConverter.N1())
                .registerTypeAdapter(Tuple2.class, new TupleConverter.N2())
                .registerTypeAdapter(Tuple3.class, new TupleConverter.N3())
                .registerTypeAdapter(Tuple4.class, new TupleConverter.N4())
                .registerTypeAdapter(Tuple5.class, new TupleConverter.N5())
                .registerTypeAdapter(Tuple6.class, new TupleConverter.N6())
                .registerTypeAdapter(Tuple7.class, new TupleConverter.N7())
                .registerTypeAdapter(Tuple8.class, new TupleConverter.N8());
    }

    /**
     * Registers all the collection converters.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerAllCollections(GsonBuilder builder)  {
        checkBuilder(builder);
        registerArray(builder);
        registerSeq(builder);
        registerIndexedSeq(builder);
        registerLinearSeq(builder);
        registerList(builder);
        registerQueue(builder);
        registerStream(builder);
        registerVector(builder);
        registerSet(builder);
        registerSortedSet(builder);
        registerHashSet(builder);
        registerLinkedHashSet(builder);
        registerTreeSet(builder);
        registerPriorityQueue(builder);
        return builder;
    }

    /**
     * Registers the {@link PriorityQueue} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    @SuppressWarnings("unchecked")
    public static GsonBuilder registerPriorityQueue(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(PriorityQueue.class, new TraversableConverter<>(it -> PriorityQueue.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), it)));
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
     * Registers the {@link Set} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerSet(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(Set.class, new TraversableConverter<>(HashSet::ofAll));
    }

    /**
     * Registers the {@link SortedSet} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    @SuppressWarnings("unchecked")
    public static GsonBuilder registerSortedSet(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(SortedSet.class, new TraversableConverter<>(it -> TreeSet.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), it)));
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
     * Registers the {@link Seq} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerSeq(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(Seq.class, new TraversableConverter<>(List::ofAll));
    }

    /**
     * Registers the {@link IndexedSeq} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerIndexedSeq(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(IndexedSeq.class, new TraversableConverter<>(Vector::ofAll));
    }

    /**
     * Registers the {@link LinearSeq} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerLinearSeq(GsonBuilder builder) {
        return checkBuilder(builder).registerTypeAdapter(LinearSeq.class, new TraversableConverter<>(List::ofAll));
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
