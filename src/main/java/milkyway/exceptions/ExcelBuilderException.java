package milkyway.exceptions;

public class ExcelBuilderException extends Exception  {
    public ExcelBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelBuilderException(String message) {
        super(message);
    }
}
