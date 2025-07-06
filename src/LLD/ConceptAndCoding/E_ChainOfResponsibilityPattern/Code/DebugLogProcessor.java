package LLD.ConceptAndCoding.E_ChainOfResponsibilityPattern.Code;

public class DebugLogProcessor extends LogProcessor {
    public DebugLogProcessor(LogProcessor nextLogProcessor) {
        super(nextLogProcessor);
    }

    @Override
    public void log(int logLevel, String message) {
        if(logLevel==DEBUG) {
            System.out.println("LOG : DEBUG : "+message);
        } else {
            super.log(logLevel, message);
        }
    }

    @Override
    public String toString() {
        return "In Debug";
    }
}
