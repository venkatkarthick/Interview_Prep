package LLD.ConceptAndCoding.E_ChainOfResponsibilityPattern.Code;

public class ErrorLogProcessor extends LogProcessor {
    ErrorLogProcessor(LogProcessor nextLogProcessor) {
        super(nextLogProcessor);
    }

    @Override
    public void log(int logLevel, String message) {
        if(logLevel==ERROR) {
            System.out.println("LOG : ERROR : "+message);
        } else {
            super.log(logLevel, message);
        }
    }

    @Override
    public String toString() {
        return "In Error";
    }
}
