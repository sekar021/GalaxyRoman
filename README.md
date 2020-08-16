# Problem Three: Merchant's Guide to the Galaxy

## How to run

run main() in [GalaxyMain.java](./src/main/java/two8g/galaxy/GalaxyMain.java)

## Thinking

File [Thinking](./Thinking.md) consists of analysis of problem, design of this application and consequent of my solution.

## core

the core process interface of this application is [MerchantGuideProcessor.java](./src/main/java/two8g/galaxy/process/MerchantGuideProcessor.java).
 
```java
public interface MerchantGuideProcessor {
    void setInputReader(InputReader inputReader);

    void setOutputWriter(OutputWriter outputWriter);

    void process() throws ProcessException;
}
```

and one implication is [MerchantGuideProcessorImpl.java](./src/main/java/two8g/galaxy/process/MerchantGuideProcessorImpl.java).

```java
public class MerchantGuideProcessorImpl implements MerchantGuideProcessor {

    private InputReader inputReader;
    private OutputWriter outputWriter;
    private IntergalacticUnitCalculator intergalacticUnitCalculator;
    private MerchandiseCalculator merchandiseCalculator;
    // ......
}
```

## uml

![diagram.png](./diagram.png)

---
Detail of this problem in file [Merchant_Guide_to_the_Galaxy](./Merchant_Guide_to_the_Galaxy.md)

