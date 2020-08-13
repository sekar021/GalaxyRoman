package com.galaxy.process;

import com.galaxy.GalaxyNoteConstants;
import com.galaxy.calculator.CalculatorFactory;
import com.galaxy.calculator.CalculatorFactoryImpl;
import com.galaxy.calculator.IntergalacticUnitCalculator;
import com.galaxy.calculator.MerchandiseCalculator;
import com.galaxy.calculator.MerchandiseCalculatorImpl;
import com.galaxy.exception.GalaxyException;
import com.galaxy.exception.InputReadException;
import com.galaxy.exception.ProcessException;
import com.galaxy.exception.UnknownMerchandiseException;

import java.io.IOException;

/**
 * Created by chandra on 17-08-2020.
 */
public class MerchantGuideProcessorImpl implements MerchantGuideProcessor {

    private InputReader inputReader;
    private OutputWriter outputWriter;
    private IntergalacticUnitCalculator intergalacticUnitCalculator;
    private MerchandiseCalculator merchandiseCalculator;

    public MerchantGuideProcessorImpl() {
        initCalculators();
        this.inputReader = new ConsoleInputReader();
        this.outputWriter = new ConsoleOutputWriter();
    }

    public MerchantGuideProcessorImpl(IntergalacticUnitCalculator intergalacticUnitCalculator, MerchandiseCalculator merchandiseCalculator) {
        this.intergalacticUnitCalculator = intergalacticUnitCalculator;
        this.merchandiseCalculator = merchandiseCalculator;
        this.inputReader = new ConsoleInputReader();
        this.outputWriter = new ConsoleOutputWriter();
    }

    public MerchantGuideProcessorImpl(InputReader inputReader, OutputWriter outputWriter) {
        initCalculators();
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
    }

    public MerchantGuideProcessorImpl(InputReader inputReader, OutputWriter outputWriter, IntergalacticUnitCalculator intergalacticUnitCalculator, MerchandiseCalculator merchandiseCalculator) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.intergalacticUnitCalculator = intergalacticUnitCalculator;
        this.merchandiseCalculator = merchandiseCalculator;
    }

    private void initCalculators() {
        CalculatorFactory calculatorFactory = CalculatorFactoryImpl.getInstance();
        intergalacticUnitCalculator = calculatorFactory.getIntergalacticUnitCalculator();
        merchandiseCalculator = calculatorFactory.getMerchandiseCalculator();
        if (merchandiseCalculator instanceof MerchandiseCalculatorImpl) {
            MerchandiseCalculatorImpl merchandiseCalculator1 = (MerchandiseCalculatorImpl) merchandiseCalculator;
            merchandiseCalculator1.setIntergalacticUnitCalculator(intergalacticUnitCalculator);
        }
    }

    @Override
    public void setInputReader(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    @Override
    public void setOutputWriter(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    @Override
    public void process() throws ProcessException {
        if (inputReader == null) {
            throw new ProcessException("inputReader must not be null");
        }
        if (outputWriter == null) {
            throw new ProcessException("outputWriter must not be null");
        }
        String line = null;
        try {
            while ((line = inputReadLine()) != null) {
                processLine(line);
            }
        } catch (InputReadException e) {
            throw new ProcessException(e);
        } finally {
            closeReader();
            writerFinish();
        }
    }

    private void writerFinish() {
        outputWriter.done();
    }

    private void closeReader() {
        try {
            inputReader.close();
        } catch (IOException e) {
            throw new ProcessException(e);
        }
    }

    private void processLine(String line) {
        if (line.matches(GalaxyNoteConstants.UNIT_ROMAN_MAP_REGEX)) {
            processUnitRomanMap(line);
        } else if (line.matches(GalaxyNoteConstants.MERCHANDISE_EXCHANGE_REGEX)) {
            processMerchandiseExchange(line);
        } else if (line.endsWith(GalaxyNoteConstants.QUERY_SUF)) {
            queryHandle(line);
        } else {
            invalidQueryHandle();
        }
    }

    private void invalidQueryHandle() {
        outputWriter.writeOutput(GalaxyNoteConstants.INVALID_QUERIES_OUTPUT);
    }

    private String inputReadLine() throws InputReadException {
        return inputReader.readLine();
    }

    private void queryHandle(String query) {
        String answer = null;
        try {
            if (query.matches(GalaxyNoteConstants.HOW_MUCH_QUERY_REGEX)) {
                answer = howMuchQueryHandle(query);
            } else if (query.matches(GalaxyNoteConstants.HOW_MANY_CREDITS_QUERY_REGEX)) {
                answer = howManyCreditsQueryHandle(query);
            } else {
                answer = GalaxyNoteConstants.INVALID_QUERIES_OUTPUT;
            }
        } catch (GalaxyException e) {
            answer = GalaxyNoteConstants.INVALID_QUERIES_OUTPUT;
        }
        outputWriter.writeOutput(answer);
    }

    /**
     * @param query like "how much is pish tegj glob glob ?"
     * @return
     */
    private String howMuchQueryHandle(String query) {
        String unit = query.replaceFirst(GalaxyNoteConstants.MUCH_QUERY_PRE + "(" + GalaxyNoteConstants.INTERGALACTIC_UNIT_REGEX + ") \\?", "$1");
        int n = intergalacticUnitCalculator.valueOfUnit(unit);
        return String.format("%s is %d", unit, n);//"pish tegj glob glob is 42"
    }

    /**
     * @param query like "how many Credits is glob prok Silver ?"
     * @return
     */
    private String howManyCreditsQueryHandle(String query) {
        String unit = query.replaceFirst(GalaxyNoteConstants.MANY_CREDITS_QUERY_PRE + "(?<unit>" + GalaxyNoteConstants.INTERGALACTIC_UNIT_REGEX + ") " + GalaxyNoteConstants.MERCHANDISE_REGEX + " \\?", "${unit}");
        String merchandise = query.replaceFirst(GalaxyNoteConstants.MANY_CREDITS_QUERY_PRE + GalaxyNoteConstants.INTERGALACTIC_UNIT_REGEX + " (?<merchandise>" + GalaxyNoteConstants.MERCHANDISE_REGEX + ") \\?", "${merchandise}");
        try {
            //int n = intergalacticUnitCalculator.valueOfUnit(unit) * merchandiseCalculator.getMerchandiseUnitCredit(merchandise);
            int[] unitCredit = merchandiseCalculator.getMerchandiseUnitCredit(merchandise);
            if (intergalacticUnitCalculator.valueOfUnit(unit) * unitCredit[1] % unitCredit[0] != 0) {
                //todo 无法整除，无法计算的到整数的Credits
            }
            int n = intergalacticUnitCalculator.valueOfUnit(unit) * unitCredit[1] / unitCredit[0];
            return String.format("%s %s is %d Credits", unit, merchandise, n);//glob prok Silver is 68 Credits
        } catch (UnknownMerchandiseException e) {
            //return e.getMessage();
            return GalaxyNoteConstants.INVALID_QUERIES_OUTPUT;
        }
    }

    private void processUnitRomanMap(String s) {
        try {
            intergalacticUnitCalculator.addUnitRomanMapEntry(s);
        } catch (GalaxyException e) {
            invalidQueryHandle();
        }
    }

    private void processMerchandiseExchange(String s) {
        try {
            merchandiseCalculator.computeMerchandiseExchanges(s);
        } catch (GalaxyException e) {
            invalidQueryHandle();
        }
    }
}
