package pl.jcrusader.tutorials.pdfgeneration.jasperreports.service;

import pl.jcrusader.tutorials.pdfgeneration.jasperreports.generator.JasperGenerator;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.listinroot.RootObject;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.listinroot.RootObjectForReportWithSubreport;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.model.simple.FlatStructuredClass;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

/**
 * Created by bogumil on 6/28/17.
 */
public class TutorialGenerationService {

    private JasperGenerator jasperGenerator;
    private DataProviderService dataProviderService;

    public TutorialGenerationService() {
        this.jasperGenerator = new JasperGenerator();
        this.dataProviderService = new DataProviderService();
    }

    public void generatePdfWithFlatStructuredData() {
        FlatStructuredClass flatStructuredClass = dataProviderService.getFlatStructedClass();
        byte[] pdfBytes = jasperGenerator.generatePdf("/templates/FlatStructuredTemplate.jrxml", flatStructuredClass);
        saveToFile(pdfBytes, "FlatStructured.pdf");
    }

    public void generatePdfWithListsInsideRootObject() {
        RootObject rootObject = dataProviderService.getRootObjectWithLists();
        byte[] pdfBytes = jasperGenerator.generatePdf("/templates/ListsInsideRootObjectsTemplate.jrxml", rootObject);
        saveToFile(pdfBytes, "ListsInsideRootObject.pdf");
    }

    public void generatePdfWithCustomFont() {
        FlatStructuredClass flatStructuredClass = dataProviderService.getFlatStructedClass();
        byte[] pdfBytes = jasperGenerator.generatePdf("/templates/CustomFontTemplate.jrxml", flatStructuredClass);
        saveToFile(pdfBytes, "CustomFont.pdf");
    }

    public void generatePdfWithReportWithSubreport() {
        RootObjectForReportWithSubreport rootObjectForReportWithSubreport = dataProviderService.getRootObjectForReportWithSubreport();
        byte[] pdfBytes = jasperGenerator.generatePdf("/templates/ReportWithSubreportTemplate.jrxml", rootObjectForReportWithSubreport, Arrays.asList(
                JasperGenerator.Subreport.builder()
                        .resourceName("/templates/SubreportForSectionTemplate.jrxml")
                        .parameterName("SectionSubReportTemplate")
                        .build()
        ));
        saveToFile(pdfBytes, "ReportWithSubreport.pdf");
    }

    private void saveToFile(byte[] bytes, String fileName) {
        try {
            File file = new File(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
