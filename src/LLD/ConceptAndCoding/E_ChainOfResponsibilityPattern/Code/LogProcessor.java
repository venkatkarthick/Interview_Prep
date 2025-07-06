package LLD.ConceptAndCoding.E_ChainOfResponsibilityPattern.Code;

public abstract class LogProcessor {
    public static int INFO=1;
    public static int DEBUG=2;
    public static int ERROR=3;

    LogProcessor nextLogProcessor;
    LogProcessor(LogProcessor logProcessor) {
        this.nextLogProcessor=logProcessor;
        //System.out.println(nextLogProcessor);
    }

    public void log(int logLevel, String message) {
        //System.out.println("Inside Log: " +nextLogProcessor);
        if(nextLogProcessor!=null) {
            nextLogProcessor.log(logLevel, message);
        }
    }

//    null
//    In Error
//    In Debug
//    Inside Log: In Debug
//    Inside Log: In Error
//    LOG : ERROR : exception happens
//    Inside Log: In Debug
//    LOG : DEBUG : Need to be dubugged
//    LOG : INFO : API gives success response
}
