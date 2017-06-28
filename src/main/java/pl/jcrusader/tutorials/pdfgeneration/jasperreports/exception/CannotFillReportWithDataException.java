package pl.jcrusader.tutorials.pdfgeneration.jasperreports.exception;

/**
 * Created by bogumil on 6/28/17.
 */
public class CannotFillReportWithDataException extends JasperException {
    public CannotFillReportWithDataException(String s) {
        super(s);
    }

    public CannotFillReportWithDataException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
