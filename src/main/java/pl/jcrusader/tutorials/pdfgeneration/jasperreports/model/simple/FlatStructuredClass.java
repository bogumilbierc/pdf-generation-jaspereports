package pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.simple;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Created by bogumil on 6/28/17.
 */
@AllArgsConstructor
@Builder
@Getter
public class FlatStructuredClass {
    private String name;
    private String surname;
    private BigDecimal amount;
    private Integer numberOfDays;
}
