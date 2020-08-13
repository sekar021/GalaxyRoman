package com.galaxy.calculator;

/**
 * Created by chandra on 17-08-2020.
 */
public class CalculatorFactoryImpl implements CalculatorFactory {
    private static CalculatorFactoryImpl INSTANCE;

    static {
        INSTANCE = new CalculatorFactoryImpl();
    }

    private CalculatorFactoryImpl() {
    }

    public static CalculatorFactoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public IntergalacticUnitCalculator getIntergalacticUnitCalculator() {
        return new IntergalacticUnitCalculatorImpl();
    }

    @Override
    public MerchandiseCalculator getMerchandiseCalculator() {
        return new MerchandiseCalculatorImpl();
    }
}
