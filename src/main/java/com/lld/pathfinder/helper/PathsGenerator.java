package main.java.com.lld.pathfinder.helper;

import java.util.*;

public class PathsGenerator {

    private static <T> void generateAllPermutations(List<T> list, int start, List<List<T>> result) {
        if (start == list.size() - 1) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < list.size(); i++) {
            Collections.swap(list, i, start);
            generateAllPermutations(list, start + 1, result);
            Collections.swap(list, i, start); // backtrack
        }
    }

    // Helper function to check if a permutation is valid
    private static <T> boolean isValidPermutation(List<T> permutation, List<T> list1, List<T> list2) {
        for (int i = 0; i < list1.size(); i++) {
            if (permutation.indexOf(list2.get(i)) < permutation.indexOf(list1.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Main function to generate all valid permutations
    public static <T> List<List<T>> generateValidPermutations(List<T> list1, List<T> list2) {
        if (list1.size() != list2.size()) {
            throw new IllegalArgumentException("Lists must be of equal size");
        }

        List<T> combined = new ArrayList<>(list1);
        combined.addAll(list2);
        List<List<T>> allPermutations = new ArrayList<>();
        generateAllPermutations(combined, 0, allPermutations);
        Set<List<T>> validPermutations = new HashSet<>();
        for (List<T> permutation : allPermutations) {
            if (isValidPermutation(permutation, list1, list2)) {
                validPermutations.add(permutation);
            }
        }

        return new ArrayList<>(validPermutations);
    }
//    public static void main(String[] args) {
//        List<Integer> list1 = List.of(1, 2);
//        List<Integer> list2 = List.of(3, 4);
//        List<List<Integer>> result = generateValidPermutations(list1, list2);
//        for (List<Integer> permutation : result) {
//            System.out.println(permutation);
//        }
//    }
}
