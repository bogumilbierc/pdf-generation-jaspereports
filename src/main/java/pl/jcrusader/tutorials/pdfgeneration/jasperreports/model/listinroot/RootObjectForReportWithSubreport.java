package pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.listinroot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by bogumil on 7/2/17.
 */
@AllArgsConstructor
@Builder
@Getter
public class RootObjectForReportWithSubreport {
    private String name;
    private String surname;
    private BigDecimal amount;
    private Integer numberOfDays;

    private List<NestedElement> smallDebts;
    private List<NestedElement> bigDebts;
}
