package com.galaxy;

/**
 * Created by chandra on 17-08-2020.
 */
public class GalaxyNoteConstants {
    public static final String ROMAN_NUMERAL_REGEX = "(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})";
    public static final String INTERGALACTIC_UNIT_REGEX = "[a-z]+( [a-z]+)*";
    public static final String MERCHANDISE_REGEX = "[A-Z][a-z]*";
    public static final String MUCH_QUERY_PRE = "how much is ";
    public static final String MANY_CREDITS_QUERY_PRE = "how many Credits is ";
    public static final String QUERY_SUF = " ?";
    public static final String INVALID_QUERIES_OUTPUT = "I have no idea what you are talking about";
    public static final String CREDITS = "Credits";

    public static final String UNIT_ROMAN_MAP_REGEX = "^" + INTERGALACTIC_UNIT_REGEX + " is " + ROMAN_NUMERAL_REGEX + "$";

    public static final String MERCHANDISE_EXCHANGE_REGEX = "^" + INTERGALACTIC_UNIT_REGEX + " " + MERCHANDISE_REGEX + " is [1-9][0-9]+ " + CREDITS + "$";

    public static final String HOW_MUCH_QUERY_REGEX = "^" + MUCH_QUERY_PRE + INTERGALACTIC_UNIT_REGEX + " \\?$";

    public static final String HOW_MANY_CREDITS_QUERY_REGEX = "^" + MANY_CREDITS_QUERY_PRE + INTERGALACTIC_UNIT_REGEX + " " + MERCHANDISE_REGEX + " \\?$";
}
