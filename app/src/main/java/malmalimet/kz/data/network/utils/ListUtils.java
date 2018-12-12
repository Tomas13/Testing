package malmalimet.kz.data.network.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Utility methods for working with lists of things.
 */
public class ListUtils {

    /**
     * Map a function to the given list and return a new mapped list.
     *
     * @param list   - the list to be mapped.
     * @param mapper - the function to map.
     * @param <T>    - input list type.
     * @param <S>    - output list type.
     * @return Mapped list.
     */
    public static <T, S> List<S> map(List<T> list, Mapper<T, S> mapper) {

        if (list == null) {
            return null;
        }

        List<S> result = new ArrayList<>();
        for (T item : list) {
            result.add(mapper.map(item));
        }

        return result;
    }

    /**
     * Iterate over the list and run a function for each item.
     *
     * @param list     - the list.
     * @param iterator - the iterator which defines the function to run for each item.
     * @param <T>      - type of list.
     */
    public static <T> void forEach(List<T> list, Iterator<T> iterator) {
        if (list != null && iterator != null) {
            for (int i = 0; i < list.size(); i++) {
                iterator.iterate(list.get(i), i);
            }
        }
    }

    /**
     * Iterate over the sparse array and run a function for each item.
     *
     * @param array    - the sparse array.
     * @param iterator - the iterator which defines the function to run for each item.
     * @param <T>      - type of sparse array.
     */
    public static <T> void forEach(SparseArray<T> array, Iterator<T> iterator) {
        if (array != null && iterator != null) {
            for (int i = 0; i < array.size(); ++i) {
                int key = array.keyAt(i);
                iterator.iterate(array.get(key), key);
            }
        }
    }

    /**
     * Zip two lists into one using the given zipper function.
     *
     * @param one    - the first list.
     * @param two    - the second list.
     * @param zipper - the zipper function which takes an item from the first list, an item from the second list
     *               and returns a result based on these two values.
     * @param <T>    - type of first list.
     * @param <S>    - type of second list.
     * @param <U>    - type of the resulting list.
     * @return A new list with values returned by the zipper function.
     */
    public static <T, S, U> List<U> zip(List<T> one, List<S> two, Zipper<T, S, U> zipper) {
        if (one != null && two != null && zipper != null) {
            int size = Math.min(one.size(), two.size());
            List<U> result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                result.add(zipper.zip(one.get(i), two.get(i)));
            }
            return result;
        }
        return null;
    }

    /**
     * Find an element satisfying the given predicate from the given list.
     *
     * @param list      - the list to be searched.
     * @param predicate - the predicate to apply to each of the list's elements.
     * @return Found element, or null if no element satisfies the given predicate.
     */
    @Nullable
    public static <T> T find(List<T> list, Func1<T, Boolean> predicate) {
        if (list == null) {
            return null;
        }

        for (T item : list) {
            if (predicate.call(item)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Find the index of an element satisfying the given predicate.
     *
     * @param list      The list to be searched.
     * @param predicate The predicate to apply to each of the list's elements.
     * @return Index of the matching element, or -1 if no element satisfies the predicate.
     */
    public static <T> int indexOf(List<T> list, Func1<T, Boolean> predicate) {
        if (list == null) {
            return -1;
        }

        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (predicate.call(list.get(i))) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Filter a list of items by the given predicate.
     *
     * @param list      - the list to filter.
     * @param predicate - the predicate.
     * @param <T>       - list type.
     * @return A new filtered list.
     */
    public static <T> List<T> filter(List<T> list, Func1<T, Boolean> predicate) {
        List<T> result = new ArrayList<>();

        if (list == null) {
            return result;
        }

        for (T item : list) {
            if (predicate.call(item)) {
                result.add(item);
            }
        }

        return result;
    }

    /**
     * Reduce (fold) the given list using the provided reducer and an initial value.
     *
     * @param list         - the list to reduce.
     * @param reducer      - reducer function.
     * @param initialValue - initial accumulated value for the reducer.
     * @param <T>          - list type.
     * @param <S>          - result type.
     * @return Reduced value.
     */
    public static <T, S> S reduce(List<T> list, Reducer<S, T> reducer, S initialValue) {
        S result = initialValue;
        for (T item : list) {
            result = reducer.reduce(result, item);
        }

        return result;
    }

    /**
     * Convert the list to a human readable string.
     *
     * @param list - the input list.
     * @return The string representation of the list.
     */
    public static String toString(List<?> list) {
        return "[" + join(map(list, Object::toString), ", ") + "]";
    }

    /**
     * Join an array of strings by the provided delimiter.
     *
     * @param strings   - list of strings to join.
     * @param delimiter - the delimiter string.
     * @return Joined string.
     */
    public static String join(List<String> strings, String delimiter) {
        String result = "";
        for (int i = 0; i < strings.size() - 1; i++) {
            result += strings.get(i) + delimiter;
        }

        if (strings.size() > 0) {
            return result + strings.get(strings.size() - 1);
        }
        return result;
    }

    public static <T> boolean any(List<T> list, Func1<T, Boolean> predicate) {
        for (T item : list) {
            if (predicate.call(item)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean every(List<T> list, Func1<T, Boolean> predicate) {
        for (T item : list) {
            if (!predicate.call(item)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean equals(List<T> first, List<T> second, Func2<T, T, Boolean> comparator) {

        if (first == second) {
            return true;
        }

        if (first == null || second == null) {
            return false;
        }

        if (first.size() != second.size()) {
            return false;
        }

        for (int i = 0; i < first.size(); i++) {
            if (!comparator.call(first.get(i), second.get(i))) {
                return false;
            }
        }

        return true;
    }

    public static <T extends Comparable<T>> boolean equals(List<T> first, List<T> second) {
        if (first == second) {
            return true;
        }

        if (first == null || second == null) {
            return false;
        }

        if (first.size() != second.size()) {
            return false;
        }

        for (int i = 0; i < first.size(); i++) {
            if (first.get(i).compareTo(second.get(i)) != 0) {
                return false;
            }
        }

        return true;
    }

    public static <T extends Comparable<T>> T min(List<T> list) {
        return min(list, i -> i);
    }

    public static <T extends Comparable<T>> T max(List<T> list) {
        return max(list, i -> i);
    }

    public static <T, C extends Comparable<C>> T min(List<T> list, Func1<T, C> criterionGetter) {
        T min = null;

        for (T item : list) {
            if (min == null || criterionGetter.call(item).compareTo(criterionGetter.call(min)) < 0) {
                min = item;
            }
        }

        return min;
    }

    public static <T, C extends Comparable<C>> T max(List<T> list, Func1<T, C> criterionGetter) {
        T max = null;

        for (T item : list) {
            if (max == null || criterionGetter.call(item).compareTo(criterionGetter.call(max)) > 0) {
                max = item;
            }
        }

        return max;
    }

    public static <T> List<T> selectRandomElements(@NonNull List<T> list, int count) {
        if (count > list.size()) {
            throw new IllegalArgumentException("count cannot be greater than list size");
        }

        Random random = new Random();
        List<T> result = new ArrayList<>();

        List<Integer> indices = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            indices.add(i);
        }

        for (int i = 0; i < count; i++) {
            int indexOfIndex = random.nextInt(indices.size());
            int index = indices.get(indexOfIndex);
            result.add(list.get(index));
            indices.remove(indexOfIndex);
        }

        return result;
    }

    public interface Reducer<S, T> {
        S reduce(S acc, T item);
    }

    public interface Mapper<T, S> {
        S map(T item);
    }

    public interface Iterator<T> {
        void iterate(T item, int index);
    }

    public interface Zipper<T, S, U> {
        U zip(T t, S s);
    }
}
