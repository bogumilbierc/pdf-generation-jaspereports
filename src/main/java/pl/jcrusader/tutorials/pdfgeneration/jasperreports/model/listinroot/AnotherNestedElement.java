package pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.listinroot;

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
public class AnotherNestedElement {
    private String caption;
    private String currency;
    private BigDecimal amount;
}
