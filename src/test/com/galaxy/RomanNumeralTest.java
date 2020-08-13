package com.galaxy;

import org.junit.Test;

import com.galaxy.RomanNumeral;

/**
 * Created by two8g on 17-4-12.
 */
public class RomanNumeralTest {

    private String[] ILLEGAL_ROMANS = {"", "a", "b", "c", "A", "B", "ABC", "abC",
            "IL", "IC", "ID", "IM",
            "VX", "VL", "VC", "VD", "VM",
            "XD", "XM",
            "LC", "LD", "LM",
            "DM",
            "IIII", "VVVV", "XXXX", "LLLL", "CCCC", "DDDD", "MMMM",
            "IXL"};
    private String[] ROMANS = {"I", "V", "X", "L", "C", "D", "M",
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    @Test
    public void intConstructor() {
        for (int i = 1; i <= 3999; i++) {
            assert new RomanNumeral(i).getValue() == i;
        }
    }

    @Test
    public void constructor() {
        for (int i = 1; i <= 3999; i++) {
            RomanNumeral romanNumeral = new RomanNumeral(i);
            assert romanNumeral.getValue() == new RomanNumeral(romanNumeral.toString()).getValue();
            assert new RomanNumeral(i).getValue() == new RomanNumeral(romanNumeral.toString()).getValue();
            assert romanNumeral.toString().equals(new RomanNumeral(romanNumeral.toString()).toString());
            assert new RomanNumeral(i).toString().equals(new RomanNumeral(romanNumeral.toString()).toString());
        }
    }

    @Test(expected = NumberFormatException.class)
    public void intConstructorIllegal0() {
        new RomanNumeral(0);
    }

    @Test(expected = NumberFormatException.class)
    public void intConstructorIllegal4000() {
        new RomanNumeral(4000);
    }

    @Test
    public void romanConstructor() {
        for (String roman : ROMANS) {
            new RomanNumeral(roman);
        }
    }

    @Test
    public void romanConstructorIllegal() {
        for (String roman : ILLEGAL_ROMANS) {
            try {
                new RomanNumeral(roman);
                assert false;
            } catch (NumberFormatException e) {

            }
        }
    }

}