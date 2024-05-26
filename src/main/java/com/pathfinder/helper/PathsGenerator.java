package com.pathfinder.helper;

import java.util.*;

public class PathsGenerator {

    /**
     * method to generate all permutations of {@code locationsList} from index {@code start} to
     * {@code locationsList.size()} and store every permutation in {@code result}
     */
    private static <T> void generateAllPaths(List<T> locationsList, int start, List<List<T>> result) {
        if (start == locationsList.size() - 1) {
            result.add(new ArrayList<>(locationsList));
            return;
        }
        for (int i = start; i < locationsList.size(); i++) {
            Collections.swap(locationsList, i, start);
            generateAllPaths(locationsList, start + 1, result);
            Collections.swap(locationsList, i, start); // backtrack
        }
    }

    /**
     * @param path contains elements of both restaurantList & consumerList.
     * @param restaurantList list of restaurant locations
     * @param consumerList list of consumer locations, where element of index i
     *                    is consumer location for restaurantList element of index i
     * @return Returns true if indexOf consumerList[i],
     * is placed only after indexOf restaurantList [i], in the path. Else returns false.
     */
    private static <T> boolean isValidPath(List<T> path, List<T> restaurantList, List<T> consumerList) {
        for (int i = 0; i < restaurantList.size(); i++) {
            if (path.indexOf(consumerList.get(i)) < path.indexOf(restaurantList.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param restaurantList list of restaurant locations
     * @param consumerList list of consumer locations, where element of index i
     *                    is consumer location for restaurantList element of index i
     * @return Returns a list of all valid possible paths.
     * @throws IllegalArgumentException for an illegal param list
     *         ({@code restaurantList.size() != consumerList.size()})
     */
    public static <T> List<List<T>> generateValidPaths(List<T> restaurantList, List<T> consumerList) {
        if (restaurantList.size() != consumerList.size()) {
            throw new IllegalArgumentException("restaurant and consumer in batch must be of equal size");
        }

        List<T> combined = new ArrayList<>(restaurantList);
        combined.addAll(consumerList);
        List<List<T>> allPermutations = new ArrayList<>();
        generateAllPaths(combined, 0, allPermutations);
        Set<List<T>> validPaths = new HashSet<>();
        for (List<T> permutation : allPermutations) {
            if (isValidPath(permutation, restaurantList, consumerList)) {
                validPaths.add(permutation);
            }
        }

        return new ArrayList<>(validPaths);
    }
}
