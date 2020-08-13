package com.galaxy.calculator;

/**
 * Created by chandra on 17-08-2020.
 */
public interface CalculatorFactory {
    IntergalacticUnitCalculator getIntergalacticUnitCalculator();

    MerchandiseCalculator getMerchandiseCalculator();
}
