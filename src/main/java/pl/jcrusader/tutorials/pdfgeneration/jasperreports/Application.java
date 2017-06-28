package pl.jcrusader.tutorials.pdfgeneration.jasperreports;

import pl.jcrusader.tutorials.pdfgeneration.jasperreports.service.TutorialGenerationService;

/**
 * Created by bogumil on 6/28/17.
 */
public class Application {

    public static void main(String args[]) {
        TutorialGenerationService tutorialGenerationService = new TutorialGenerationService();
        tutorialGenerationService.generatePdfWithFlatStructuredData();
        tutorialGenerationService.generatePdfWithListsInsideRootObject();
        tutorialGenerationService.generatePdfWithCustomFont();

    }


}
