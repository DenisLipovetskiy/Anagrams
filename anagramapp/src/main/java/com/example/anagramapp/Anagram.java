package com.example.anagramapp;

public class Anagram {

    public static String build(String input, String filter) {
        if (input == null || filter == null) {
            return "";
        }

        boolean useDefaultFilter = filter.isEmpty();

        StringBuilder resultBuilder = new StringBuilder();
        String[] words = input.split("\\s+");

        for (String word : words) {
            resultBuilder.append(reverseWordWithFilter(word, filter, useDefaultFilter)).append(" ");
        }

        return resultBuilder.toString().trim();
    }

    private static String reverseWordWithFilter(String word, String filter, boolean useDefaultFilter) {
        char[] chars = word.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {

            if (matchesFilter(chars[left], filter, useDefaultFilter)) {
                left++;
                continue;
            }

            if (matchesFilter(chars[right], filter, useDefaultFilter)) {
                right--;
                continue;
            }

            // Swap characters
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            left++;
            right--;
        }

        return new String(chars);
    }

    private static boolean matchesFilter(char c, String filter, boolean useDefaultFilter) {
        if (useDefaultFilter) {
            return !Character.isLetter(c);  // Default filter: allow only alphabetic characters
        } else {
            return filter.indexOf(c) >= 0;  // Custom filter: match any character in the filter string
        }
    }
}
