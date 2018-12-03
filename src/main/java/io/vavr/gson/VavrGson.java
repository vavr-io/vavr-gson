/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.gson;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;

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
        registerAdapters(builder);
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
        return registerAdapters(builder, Lazy.class);
    }

    /**
     * Registers the {@link Option} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerOption(GsonBuilder builder) {
        return registerAdapters(builder).registerTypeHierarchyAdapter(Option.class, new OptionConverter());
    }

    /**
     * Registers all the {@link Multimap} converters.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerAllMultimaps(GsonBuilder builder) {
        registerAdapters(builder);
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
        return registerAdapters(builder, HashMultimap.class);
    }

    /**
     * Registers the {@link Multimap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerMultimap(GsonBuilder builder) {
        return registerAdapters(builder, Multimap.class);
    }

    /**
     * Registers the {@link SortedMultimap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerSortedMultimap(GsonBuilder builder) {
        return registerAdapters(builder, SortedMultimap.class);
    }

    /**
     * Registers the {@link LinkedHashMultimap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerLinkedHashMultimap(GsonBuilder builder) {
        return registerAdapters(builder, LinkedHashMultimap.class);
    }

    /**
     * Registers the {@link TreeMultimap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerTreeMultimap(GsonBuilder builder) {
        return registerAdapters(builder, TreeMultimap.class);
    }

    /**
     * Registers all the {@link Map} converters.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerAllMaps(GsonBuilder builder) {
        registerAdapters(builder);
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
        return registerAdapters(builder, HashMap.class);
    }

    /**
     * Registers the {@link Map} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerMap(GsonBuilder builder) {
        return registerAdapters(builder, Map.class);
    }

    /**
     * Registers the {@link SortedMap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerSortedMap(GsonBuilder builder) {
        return registerAdapters(builder, SortedMap.class);
    }

    /**
     * Registers the {@link LinkedHashMap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerLinkedHashMap(GsonBuilder builder) {
        return registerAdapters(builder, LinkedHashMap.class);
    }

    /**
     * Registers the {@link TreeMap} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerTreeMap(GsonBuilder builder) {
        return registerAdapters(builder, TreeMap.class);
    }

    /**
     * Registers all the {@link Tuple}s converters.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerTuples(GsonBuilder builder) {
        return registerAdapters(builder,
                Tuple0.class,
                Tuple1.class,
                Tuple2.class,
                Tuple3.class,
                Tuple4.class,
                Tuple5.class,
                Tuple6.class,
                Tuple7.class,
                Tuple8.class);
    }

    /**
     * Registers all the collection converters.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerAllCollections(GsonBuilder builder)  {
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
        return registerAdapters(builder, PriorityQueue.class);
    }

    /**
     * Registers the {@link HashSet} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerHashSet(GsonBuilder builder) {
        return registerAdapters(builder, HashSet.class);
    }

    /**
     * Registers the {@link Set} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerSet(GsonBuilder builder) {
        return registerAdapters(builder, Set.class);
    }

    /**
     * Registers the {@link SortedSet} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    @SuppressWarnings("unchecked")
    public static GsonBuilder registerSortedSet(GsonBuilder builder) {
        return registerAdapters(builder, SortedSet.class);
    }

    /**
     * Registers the {@link LinkedHashSet} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerLinkedHashSet(GsonBuilder builder) {
        return registerAdapters(builder, LinkedHashSet.class);
    }

    /**
     * Registers the {@link TreeSet} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    @SuppressWarnings("unchecked")
    public static GsonBuilder registerTreeSet(GsonBuilder builder) {
        return registerAdapters(builder, TreeSet.class);
    }

    /**
     * Registers the {@link Array} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerArray(GsonBuilder builder) {
        return registerAdapters(builder, Array.class);
    }

    /**
     * Registers the {@link Seq} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerSeq(GsonBuilder builder) {
        return registerAdapters(builder, Seq.class);
    }

    /**
     * Registers the {@link IndexedSeq} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerIndexedSeq(GsonBuilder builder) {
        return registerAdapters(builder, IndexedSeq.class);
    }

    /**
     * Registers the {@link LinearSeq} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerLinearSeq(GsonBuilder builder) {
        return registerAdapters(builder, LinearSeq.class);
    }

    /**
     * Registers the {@link List} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerList(GsonBuilder builder) {
        return registerAdapters(builder, List.class);
    }

    /**
     * Registers the {@link Queue} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerQueue(GsonBuilder builder) {
        return registerAdapters(builder, Queue.class);
    }

    /**
     * Registers the {@link Stream} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerStream(GsonBuilder builder) {
        return registerAdapters(builder, Stream.class);
    }

    /**
     * Registers the {@link Vector} converter.
     * @param builder The GSON builder to register the converters with.
     * @return A reference to {@code builder}.
     */
    public static GsonBuilder registerVector(GsonBuilder builder) {
        return registerAdapters(builder, Vector.class);
    }

    public static Map<Type, Object> typeAdapters() {
        return collectionTypeAdapters()
                .merge(tupleTypeAdapters())
                .merge(mapTypeAdapters())
                .merge(multiMapTypeAdapters())
                .merge(lazyTypeAdapters());
    }

    public static Map<Class<?>, Object> typeHierarchyAdapters() {
        return API.<Class<?>, Object>Map()
                .put(List.class, new TraversableConverter<>(List::ofAll))
                .put(Stream.class, new TraversableConverter<>(Stream::ofAll))
                .put(Option.class, new OptionConverter());
    }

    public static Map<Type, Object> lazyTypeAdapters() {
        return API.<Type, Object>Map()
                .put(Lazy.class, new LazyConverter());
    }

    public static Map<Type, Object> multiMapTypeAdapters() {
        return API.<Type, Object>Map()
                .put(Multimap.class, new MultimapConverter<>(t -> HashMultimap.withSeq().ofEntries(t)))
                .put(SortedMultimap.class, new MultimapConverter<>(t -> TreeMultimap.withSeq().ofEntries(t)))
                .put(HashMultimap.class, new MultimapConverter<>(t -> HashMultimap.withSeq().ofEntries(t)))
                .put(LinkedHashMultimap.class, new MultimapConverter<>(t -> LinkedHashMultimap.withSeq().ofEntries(t)))
                .put(TreeMultimap.class, new MultimapConverter<>(t -> TreeMultimap.withSeq().ofEntries(t)));
    }

    public static Map<Type, Object> mapTypeAdapters() {
        return API.<Type, Object>Map()
                .put(Map.class, new MapConverter<>(HashMap::ofEntries))
                .put(SortedMap.class, new MapConverter<>(TreeMap::ofEntries))
                .put(HashMap.class, new MapConverter<>(HashMap::ofEntries))
                .put(LinkedHashMap.class, new MapConverter<>(LinkedHashMap::ofEntries))
                .put(TreeMap.class, new MapConverter<>(TreeMap::ofEntries));
    }

    public static Map<Type, Object> collectionTypeAdapters() {
        return API.<Type, Object>Map()
                .put(Array.class, new TraversableConverter<>(Array::ofAll))
                .put(Seq.class, new TraversableConverter<>(List::ofAll))
                .put(IndexedSeq.class, new TraversableConverter<>(Vector::ofAll))
                .put(LinearSeq.class, new TraversableConverter<>(List::ofAll))
                .put(Queue.class, new TraversableConverter<>(Queue::ofAll))
                .put(Vector.class, new TraversableConverter<>(Vector::ofAll))
                .put(Set.class, new TraversableConverter<>(HashSet::ofAll))
                .put(SortedSet.class, new TraversableConverter<>(it -> TreeSet.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), it)))
                .put(HashSet.class, new TraversableConverter<>(HashSet::ofAll))
                .put(LinkedHashSet.class, new TraversableConverter<>(LinkedHashSet::ofAll))
                .put(TreeSet.class, new TraversableConverter<>(it -> TreeSet.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), it)))
                .put(PriorityQueue.class, new TraversableConverter<>(it -> PriorityQueue.ofAll((o1, o2) -> ((Comparable) o1).compareTo(o2), it)));
    }

    public static Map<Type, Object> tupleTypeAdapters() {
        return API.Map(Tuple0.class, new TupleConverter.N0(),
                Tuple1.class, new TupleConverter.N1(),
                Tuple2.class, new TupleConverter.N2(),
                Tuple3.class, new TupleConverter.N3(),
                Tuple4.class, new TupleConverter.N4(),
                Tuple5.class, new TupleConverter.N5(),
                Tuple6.class, new TupleConverter.N6(),
                Tuple7.class, new TupleConverter.N7(),
                Tuple8.class, new TupleConverter.N8());
    }

    private static GsonBuilder registerAdapters(GsonBuilder builder, Class<?> ...types) {
        Objects.requireNonNull(builder, "builder cannot be null");
        List.ofAll(Arrays.asList(types))
                .forEach(type -> {
                    typeAdapters().get(type).map(adapter -> builder.registerTypeAdapter(type, adapter));
                    typeHierarchyAdapters().get(type).map(adapter -> builder.registerTypeHierarchyAdapter(type, adapter));
                });
        return builder;
    }
}
