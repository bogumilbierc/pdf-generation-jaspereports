package pl.jcrusader.tutorials.pdfgeneration.jasperreports.service;

import pl.jcrusader.tutorials.pdfgeneration.jasperreports.generator.JasperGenerator;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.simple.FlatStructuredClass;

import java.math.BigDecimal;

/**
 * Created by bogumil on 6/28/17.
 */
public class TutorialGenerationService {

    private JasperGenerator jasperGenerator;

    public TutorialGenerationService() {
        this.jasperGenerator = new JasperGenerator();
    }

    public void generatePdfWithFlatStructuredData() {
        FlatStructuredClass flatStructuredClass = FlatStructuredClass.builder()
                .name("John")
                .surname("Doe")
                .amount(BigDecimal.valueOf(16.89))
                .numberOfDays(15)
                .build();
    }
}
