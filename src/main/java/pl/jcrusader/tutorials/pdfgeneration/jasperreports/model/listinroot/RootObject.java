package pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.listinroot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by bogumil on 6/28/17.
 */
@AllArgsConstructor
@Builder
@Getter
public class RootObject {
    private String name;
    private String surname;
    private BigDecimal amount;
    private Integer numberOfDays;

    private List<NestedElement> nestedElements;
    private List<AnotherNestedElement> anotherNestedElements;

}
