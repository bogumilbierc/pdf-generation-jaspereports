package pl.jcrusader.tutorials.pdfgeneration.jasperreports.service;

import pl.jcrusader.tutorials.pdfgeneration.jasperreports.generator.JasperGenerator;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.simple.FlatStructuredClass;

/**
 * Created by bogumil on 6/28/17.
 */
public class TutorialGenerationService {

    private JasperGenerator jasperGenerator;
    private DataProviderService dataProviderService;

    public TutorialGenerationService() {
        this.jasperGenerator = new JasperGenerator();
    }

    public void generatePdfWithFlatStructuredData() {
        FlatStructuredClass flatStructuredClass = dataProviderService.getFlatStructedClass();
    }
}
