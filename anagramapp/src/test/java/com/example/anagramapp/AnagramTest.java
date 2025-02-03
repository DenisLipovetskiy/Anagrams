package com.example.anagramapp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AnagramTest {

    @Test
    public void testBasicAnagram() {
        String input = "Foxminded cool 24/7";
        String expected = "dednimxoF looc 24/7";
        String actual = Anagram.build(input, "");  // Empty filter
        assertEquals("Check reversing normal text with digits/punctuation", expected, actual);
    }

    @Test
    public void testAnagramWithFilter() {
        // Custom filter "xl"
        assertEquals("dexdnimoF oocl 7/42",
                Anagram.build("Foxminded cool 24/7", "xl"));
        assertEquals("dcb1a hgfle",
                Anagram.build("a1bcd efglh", "xl"));
    }

    @Test
    public void testDigitsAndPunctuationRemainInPlace() {
        String input = "a1bcd efg!h";
        String expected = "d1cba hgf!e";
        String actual = Anagram.build(input, "");
        assertEquals("Check digits and punctuation remain in place", expected, actual);
    }

    @Test
    public void testEmptyInput() {
        assertEquals("Empty string should return empty string",
                "", Anagram.build("", ""));
    }
}
