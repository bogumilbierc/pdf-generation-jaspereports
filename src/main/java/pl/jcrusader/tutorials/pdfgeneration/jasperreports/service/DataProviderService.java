package pl.jcrusader.tutorials.pdfgeneration.jasperreports.service;

import pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.simple.FlatStructuredClass;

import java.math.BigDecimal;

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

}
