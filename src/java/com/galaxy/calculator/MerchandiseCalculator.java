package com.galaxy.calculator;

import com.galaxy.exception.UnknownMerchandiseException;

/**
 * Created by chandra on 17-08-2020.
 */
public interface MerchandiseCalculator {
    void computeMerchandiseExchanges(String s);

    int[] getMerchandiseUnitCredit(String name) throws UnknownMerchandiseException;
}
