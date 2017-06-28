package pl.jcrusader.tutorials.pdfgeneration.jasperreports.exception;

/**
 * Created by bogumil on 6/28/17.
 */
public abstract class JasperException extends RuntimeException {
    public JasperException(String s) {
        super(s);
    }

    public JasperException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
