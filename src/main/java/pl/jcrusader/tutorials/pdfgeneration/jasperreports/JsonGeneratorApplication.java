package pl.jcrusader.tutorials.pdfgeneration.jasperreports;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.service.DataProviderService;

import java.io.File;
import java.io.IOException;

/**
 * Created by bogumil on 6/28/17.
 */
public class JsonGeneratorApplication {

    public static void main(String args[]) throws IOException {
        DataProviderService dataProviderService = new DataProviderService();

        writeToJsonFile(dataProviderService.getFlatStructedClass(), "FlatStructured.json");
    }

    private static void writeToJsonFile(Object data, String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String objectAsJson = objectMapper.writeValueAsString(data);
        File file = new File(fileName);
        FileUtils.writeStringToFile(file, objectAsJson, "UTF-8");
    }

}
