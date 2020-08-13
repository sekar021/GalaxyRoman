package com.galaxy.calculator;

import com.galaxy.GalaxyNoteConstants;
import com.galaxy.exception.GalaxyException;
import com.galaxy.exception.UnknownMerchandiseException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chandra on 17-08-2020.
 */
public class MerchandiseCalculatorImpl implements MerchandiseCalculator {
    private IntergalacticUnitCalculator intergalacticUnitCalculator;
    private Map<String, int[]> merchandiseCredits;

    public MerchandiseCalculatorImpl() {
        merchandiseCredits = new HashMap<String, int[]>();
    }

    public MerchandiseCalculatorImpl(IntergalacticUnitCalculator intergalacticUnitCalculator, Map<String, int[]> merchandiseCredits) {
        this.intergalacticUnitCalculator = intergalacticUnitCalculator;
        this.merchandiseCredits = merchandiseCredits;
    }

    public MerchandiseCalculatorImpl(Map<String, int[]> merchandiseCredits) {
        this.merchandiseCredits = merchandiseCredits;
    }

    public void setMerchandiseCredits(Map<String, int[]> merchandiseCredits) {
        this.merchandiseCredits = merchandiseCredits;
    }

    public void setIntergalacticUnitCalculator(IntergalacticUnitCalculator intergalacticUnitCalculator) {
        this.intergalacticUnitCalculator = intergalacticUnitCalculator;
    }

    @Override
    public void computeMerchandiseExchanges(String s) {
        if (intergalacticUnitCalculator == null) {
            throw new GalaxyException("this merchandiseCalculator must working with a intergalacticUnitCalculator");
        }
        if (s.matches(GalaxyNoteConstants.MERCHANDISE_EXCHANGE_REGEX)) {
            String merchandise = merchandiseOfExchange(s);
            String units = unitsOfExchange(s);
            int credits = creditsNumberOfExchange(s);
            merchandiseCredits.put(merchandise, creditsNumberOfMerchandise(units, credits));
        } else {
            throw new IllegalArgumentException();
        }
    }

    private String unitsOfExchange(String s) {
        return s.replaceFirst("(" + GalaxyNoteConstants.INTERGALACTIC_UNIT_REGEX + ").*", "$1");
    }

    private String merchandiseOfExchange(String s) {
        return s.replaceFirst(".*?(" + GalaxyNoteConstants.MERCHANDISE_REGEX + ").*", "$1");
    }

    private int creditsNumberOfExchange(String s) {
        return Integer.parseInt(s.replaceFirst(".*?is ([1-9][0-9]+) " + GalaxyNoteConstants.CREDITS, "$1"));
    }

    private int valueOfUnit(String unit) {
        return intergalacticUnitCalculator.valueOfUnit(unit);
    }

    private int[] creditsNumberOfMerchandise(String unit, int credits) {
        return new int[]{valueOfUnit(unit), credits};
    }

    /**
     * get the credits number which merchandise equivalently
     *
     * @param name
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public int[] getMerchandiseUnitCredit(String name) throws UnknownMerchandiseException {
        if (merchandiseCredits.get(name) == null) {
            throw new UnknownMerchandiseException("unknown merchandise: " + name);
        } else {
            return merchandiseCredits.get(name);
        }
    }

}
