package com.galaxy;

import java.io.Serializable;

/**
 * roman numeral
 * https://en.wikipedia.org/wiki/Roman_numerals
 * value	        1, 2,  3,   4,  5, 6,  7,   8,    9,  10.
 * roman numeral	I, II, III, IV, V, VI, VII, VIII, IX, X.
 * part code is copied from http://math.hws.edu/eck/cs124/javanotes7/c8/ex3-ans.html
 * Created by chandra on 17-08-2020.
 */
public final class RomanNumeral implements Serializable, Comparable<RomanNumeral> {
    private static final String ROMAN_NUMERAL_REGEX = "^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 3999;

    //copied from http://math.hws.edu/eck/cs124/javanotes7/c8/ex3-ans.html
    private static final int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    //numbers value
    private final int value;
    //roman as roman numeral
    private final String roman;

    /**
     * @param roman must be UpperCase
     */
    public RomanNumeral(String roman) {
        verify(roman);
        this.roman = roman;
        this.value = roman2int(this.roman);
    }

    /**
     * @param n int [1,3999]
     */
    public RomanNumeral(int n) {
        verify(n);
        //todo add cache
        this.value = n;
        this.roman = int2roman(n);
    }

    //roman verify, true: legal; false: illegal
    private void verify(String roman) {
        if (roman == null || roman.length() <= 0 || !roman.matches(ROMAN_NUMERAL_REGEX)) {
            throw new NumberFormatException("roman string is illegal");
        }
    }

    //int value verify, true: legal; false: illegal
    private void verify(int n) {
        if (n < MIN_VALUE || MAX_VALUE < n) {
            throw new NumberFormatException("param n must in [1, 3999]");
        }
    }

    //copied from http://math.hws.edu/eck/cs124/javanotes7/c8/ex3-ans.html
    private String int2roman(int n) {
        StringBuilder roman = new StringBuilder();  // The roman numeral.
        int N = n;        // N represents the part of num that still has
        //   to be converted to Roman numeral representation.
        for (int i = 0; i < numbers.length; i++) {
            while (N >= numbers[i]) {
                roman.append(romans[i]);
                N -= numbers[i];
            }
        }
        return roman.toString();
    }


    /**
     * copied from http://math.hws.edu/eck/cs124/javanotes7/c8/ex3-ans.html
     *
     * @param roman must be UpperCase
     * @return
     */
    private int roman2int(String roman) {
        //roman = roman.toUpperCase();  // Convert to upper case letters.

        int i = 0;       // A position in the string, roman;
        int arabic = 0;  // Arabic numeral equivalent of the part of the string that has
        //    been converted so far.

        while (i < roman.length()) {

            char letter = roman.charAt(i);        // Letter at current position in string.
            int number = letterToNumber(letter);  // Numerical equivalent of letter.

            i++;  // Move on to next position in the string

            if (i == roman.length()) {
                // There is no letter in the string following the one we have just processed.
                // So just add the number corresponding to the single letter to arabic.
                arabic += number;
            } else {
                // Look at the next letter in the string.  If it has a larger Roman numeral
                // equivalent than number, then the two letters are counted together as
                // a Roman numeral with value (nextNumber - number).
                int nextNumber = letterToNumber(roman.charAt(i));
                if (nextNumber > number) {
                    // Combine the two letters to get one value, and move on to next position in string.
                    arabic += (nextNumber - number);
                    i++;
                } else {
                    // Don't combine the letters.  Just add the value of the one letter onto the number.
                    arabic += number;
                }
            }

        }  // end while

        if (arabic < 1 || arabic > 3999) {
            throw new NumberFormatException("param n must in [1, 3999]");
        }

        return arabic;
    }

    /**
     * Find the integer value of letter considered as a Roman numeral.  Throws
     * NumberFormatException if letter is not a legal Roman numeral.  The letter
     * must be upper case.
     */
    private int letterToNumber(char letter) {
        switch (letter) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new NumberFormatException(
                        "Illegal character \"" + letter + "\" in Roman numeral");
        }
    }

    @Override
    public int compareTo(RomanNumeral o) {
        return Integer.compare(value, o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RomanNumeral that = (RomanNumeral) o;
        return roman.equals(that.roman) || value == that.value;
    }

    @Override
    public int hashCode() {
        return getValue();
    }

    @Override
    public String toString() {
        return roman;
    }

    public int getValue() {
        return value;
    }

    public String getRoman() {
        return roman;
    }
}
