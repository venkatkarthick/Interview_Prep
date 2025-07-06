package LLD.ConceptAndCoding.E_ChainOfResponsibilityPattern.Code;

public class Client {
    public static void main(String[] args) {

        LogProcessor logger = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));

        logger.log(LogProcessor.ERROR, "exception happens");
        logger.log(LogProcessor.DEBUG, "Need to be dubugged");
        logger.log(LogProcessor.INFO, "API gives success response");

    }
}
