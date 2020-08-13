package com.galaxy.calculator;

import com.galaxy.GalaxyNoteConstants;
import com.galaxy.RomanNumeral;
import com.galaxy.exception.GalaxyException;
import com.galaxy.exception.UnknownUnitException;

import java.util.HashMap;

/**
 * Created by chandra on 17-08-2020.
 */
public class IntergalacticUnitCalculatorImpl implements IntergalacticUnitCalculator {

    private HashMap<String, String> map;

    public IntergalacticUnitCalculatorImpl() {
        map = new HashMap<>();
    }

    public IntergalacticUnitCalculatorImpl(HashMap<String, String> map) {
        this.map = map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public int valueOfUnit(String unit) {
        String roman = romanNumeralOfUnit(unit);
        return new RomanNumeral(roman).getValue();
    }

    @Override
    public void addUnitRomanMapEntry(String s) {
        if (s.matches(GalaxyNoteConstants.UNIT_ROMAN_MAP_REGEX)) {
            String[] strings = s.split(" is ");
            String unit = strings[0];
            String roman = strings[1];
            //todo é‡�å¤�æ•°æ�®å¼‚å¸¸å¤„ç�†
            map.put(unit, roman);
        } else {
            throw new GalaxyException("illegal map: " + s);
        }
    }

    private String romanNumeralOfUnit(String unit) {
        StringBuilder roman = new StringBuilder();
        for (String u : unit.split(" ")) {
            String roman0 = romanOfUnit(u);
            if (roman0 == null) {
                throw new UnknownUnitException("unit=" + u);
            }
            roman.append(roman0);
        }
        if (roman.length() <= 0) {
            throw new UnknownUnitException("unit=" + unit);
        }
        return roman.toString();
    }

    private String romanOfUnit(String u) {
        return map == null ? null : map.get(u);
    }
}
