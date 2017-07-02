package pl.jcrusader.tutorials.pdfgeneration.jasperreports.service;

import pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.listinroot.AnotherNestedElement;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.listinroot.NestedElement;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.listinroot.RootObject;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.listinroot.RootObjectForReportWithSubreport;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.simple.FlatStructuredClass;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by bogumil on 6/28/17.
 */
public class DataProviderService {

    public FlatStructuredClass getFlatStructedClass() {
        return FlatStructuredClass.builder()
                .name("John")
                .surname("Doe")
                .amount(BigDecimal.valueOf(16.89))
                .numberOfDays(15)
                .build();
    }

    public RootObject getRootObjectWithLists() {
        return RootObject.builder()
                .name("John")
                .surname("Doe")
                .amount(BigDecimal.valueOf(16.89))
                .numberOfDays(15)
                .nestedElements(
                        Arrays.asList(
                                NestedElement.builder()
                                        .name("Nested Element 1")
                                        .price(BigDecimal.valueOf(10.4))
                                        .quantity(10)
                                        .build(),
                                NestedElement.builder()
                                        .name("Nested Element 2")
                                        .price(BigDecimal.valueOf(7.4))
                                        .quantity(99)
                                        .build()
                        )
                )
                .anotherNestedElements(
                        Arrays.asList(
                                AnotherNestedElement.builder()
                                        .caption("First Caption")
                                        .currency("GBP")
                                        .amount(BigDecimal.valueOf(100))
                                        .build(),
                                AnotherNestedElement.builder()
                                        .caption("Second Caption")
                                        .currency("USD")
                                        .amount(BigDecimal.valueOf(7))
                                        .build()
                        )
                )
                .build();
    }

    public RootObjectForReportWithSubreport getRootObjectForReportWithSubreport() {
        return RootObjectForReportWithSubreport.builder()
                .name("John")
                .surname("Doe")
                .amount(BigDecimal.valueOf(16.89))
                .numberOfDays(15)
                .bigDebts(
                        Arrays.asList(
                                NestedElement.builder()
                                        .name("BigDebt 1")
                                        .price(BigDecimal.valueOf(500))
                                        .quantity(1)
                                        .build(),
                                NestedElement.builder()
                                        .name("BigDebt 2")
                                        .price(BigDecimal.valueOf(800))
                                        .quantity(1)
                                        .build()
                        )
                )
                .smallDebts(
                        Arrays.asList(
                                NestedElement.builder()
                                        .name("SmallDebt 1")
                                        .price(BigDecimal.valueOf(10))
                                        .quantity(1)
                                        .build(),
                                NestedElement.builder()
                                        .name("SmallDebt 2")
                                        .price(BigDecimal.valueOf(8))
                                        .quantity(1)
                                        .build()
                        )
                )
                .build();
    }

}
