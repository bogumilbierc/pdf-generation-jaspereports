package pl.jcrusader.tutorials.pdfgeneration.jasperreports.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JsonDataSource;
import org.apache.commons.io.IOUtils;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.exception.CannotCompileReportException;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.exception.CannotExportJasperPrintToPdf;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.exception.CannotFillReportWithDataException;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.exception.CannotPrepareJsonDataSourceException;

import java.io.InputStream;
import java.util.LinkedHashMap;

/**
 * This class acts as common point for all things happening around JasperReports.
 * It is meant to be used by other classes that will provide concrete data to this generic solution.
 *
 * @author bogumilbierc
 */
public class JasperGenerator {

    /**
     * Generates PDF file using Jasper Reports.
     * Provided template and data are combined together to make PDF file.
     * Templates must be raw, not compiled version of Jasper template. Compilation will be done in this method.
     * Data must be object with all properties required to populate the template. This object will be serialized to JSON and then passed to the Report Filler.
     *
     * @param resourceName name of the resource, that contains ".jrxml" file with template
     * @param data         data that will be used to populate template
     * @return byte array with PDF file
     * @throws CannotExportJasperPrintToPdf if report cannot be exported to PDF
     */
    public byte[] generatePdf(String resourceName, Object data) {
        JasperReport compiledReport = compileReport(resourceName);
        JasperPrint filledJasperPrint = fillReport(compiledReport, getJsonDataSource(data));
        try {
            return JasperExportManager.exportReportToPdf(filledJasperPrint);
        } catch (JRException e) {
            throw new CannotExportJasperPrintToPdf("Cannot export compiled and filled report to PDF", e);
        }

    }

    /**
     * Compiles JasperReports template (jrxml) to Jasper Report (equivalent of .jasper file)
     *
     * @param resourceName resource that contains jrxml file with Jasper Report template
     * @return compiled JasperReport (equivalent of .jasper file)
     * @throws CannotCompileReportException if given template cannot be compiled
     */
    private JasperReport compileReport(String resourceName) {
        try {
            return JasperCompileManager.compileReport(getClass().getResourceAsStream(resourceName));
        } catch (JRException e) {
            throw new CannotCompileReportException("Cannot compile provided JRXML to Jasper Report", e);
        }
    }


    /**
     * Fills given compiled JasperReport with given data source
     *
     * @param compiledReport precompiled JasperReport to fill with data
     * @param jsonDataSource data that would be used to fill given JasperReport
     * @return JasperPrint which is a result of filling given precompiled report with given data source
     * @throws CannotFillReportWithDataException if given precompiled report cannot be filled with given data
     */
    private JasperPrint fillReport(JasperReport compiledReport, JsonDataSource jsonDataSource) {
        try {
            return JasperFillManager.fillReport(compiledReport, new LinkedHashMap<String, Object>(), jsonDataSource);
        } catch (JRException e) {
            throw new CannotFillReportWithDataException("Cannot fill report with given data", e);
        }
    }

    /**
     * Gets JSON data source of given data.
     * Steps:
     * 1. Convert given data to JSON string using ObjectMapper
     * 2. Convert JSON string to InputStream using IOUtils (Apache Commons)
     * 3. Create JsonDataSource from InputStream generated in step above
     *
     * @param data data that will be put in JSON Data Source
     * @return JsonDataSource created from given data
     * @throws CannotPrepareJsonDataSourceException when JsonDataSource cannot be prepared with given data
     */
    private JsonDataSource getJsonDataSource(Object data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String objectAsJson = objectMapper.writeValueAsString(data);
            InputStream in = IOUtils.toInputStream(objectAsJson, "UTF-8");
            return new JsonDataSource(in);
        } catch (Exception e) {
            throw new CannotPrepareJsonDataSourceException("Cannot prepare JSON Data Source with given data", e);
        }

    }

}
