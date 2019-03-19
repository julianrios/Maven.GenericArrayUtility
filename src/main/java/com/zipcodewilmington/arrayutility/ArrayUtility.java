package com.zipcodewilmington.arrayutility;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<T> {
    protected T[] array;

    public ArrayUtility(T[] array) {
        this.array = array;
    }

    public T[] removeValue(T valueToRemove) {
        Stream<T> stream = Arrays.stream(array);
        return stream.filter(element -> (!(element.equals(valueToRemove))))
                .toArray(size -> Arrays.copyOf(array, size));
    }

    public Integer countDuplicatesInMerge(T[] arrayToMerge, T valueToEvaluate) {
        ArrayUtility util =  new ArrayUtility(mergeArray(arrayToMerge));
        return util.getNumberOfOccurrences(valueToEvaluate);
    }

    public T getMostCommonFromMerge(T[] arrayToMerge) {
        T common = arrayToMerge[0];
        int commonCount = getNumberOfOccurrences(common);

        for (T currentObject : (T[])mergeArray(arrayToMerge)) {
            int currentCount = getNumberOfOccurrences(currentObject);
            if (currentCount > commonCount) {
                common = currentObject;
                commonCount = currentCount;
            }
        }
        return common;
    }

    private Object[] mergeArray(T[] arrayToMerge) {
//        return Stream.of(array, arrayToMerge)
//                .flatMap(Stream::of)
//                .toArray();
        return Stream.concat(Arrays.stream(array),
                Arrays.stream(arrayToMerge))
                .toArray();
    }

    public Integer getNumberOfOccurrences(T valueToEvaluate) {
        Stream<T> stream =  Arrays.stream(array);
        return (int) stream.filter(element -> element.equals(valueToEvaluate)).count();
    }
}
