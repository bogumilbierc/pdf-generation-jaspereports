package pl.jcrusader.tutorials.pdfgeneration.jasperreports.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JsonDataSource;
import org.apache.commons.io.IOUtils;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.exception.CannotCompileReportException;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.exception.CannotExportJasperPrintToPdf;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.exception.CannotFillReportWithDataException;
import pl.jcrusader.tutorials.pdfgeneration.jasperreports.exception.CannotPrepareJsonDataSourceException;

import java.io.InputStream;
import java.util.*;

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
        return generatePdf(resourceName, data, Collections.emptyList());
    }

    /**
     * Generates PDF file using Jasper Reports.
     * Provided template and data are combined together to make PDF file.
     * Templates must be raw, not compiled version of Jasper template. Compilation will be done in this method.
     * Data must be object with all properties required to populate the template. This object will be serialized to JSON and then passed to the Report Filler.
     *
     * @param resourceName  name of the resource, that contains ".jrxml" file with template
     * @param data          data that will be used to populate template
     * @param subReportList list of subreports that are required to compile and fill template provided in resourceName
     * @return byte array with PDF file
     * @throws CannotExportJasperPrintToPdf if report cannot be exported to PDF
     */
    public byte[] generatePdf(String resourceName, Object data, List<Subreport> subReportList) {
        JasperReport compiledReport = compileReport(resourceName);
        Map<String, Object> compiledSubreportsMap = new HashMap<>();
        for (Subreport subreport : subReportList) {
            JasperReport compiledSubReport = compileReport(subreport.getResourceName());
            compiledSubreportsMap.put(subreport.getParameterName(), compiledSubReport);
        }
        JasperPrint filledJasperPrint = fillReport(compiledReport, getJsonDataSource(data), compiledSubreportsMap);
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
     * @param parametersMap  map of parameters that will be passed along with datasource to fill the report
     * @return JasperPrint which is a result of filling given precompiled report with given data source
     * @throws CannotFillReportWithDataException if given precompiled report cannot be filled with given data
     */
    private JasperPrint fillReport(JasperReport compiledReport, JsonDataSource jsonDataSource, Map<String, Object> parametersMap) {
        try {
            return JasperFillManager.fillReport(compiledReport, new LinkedHashMap<>(), jsonDataSource);
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

    /**
     * Inner class used to pass Subreport parameters
     */
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Subreport {
        /**
         * Name of the parameter that is used in main report under "subreportExpression"
         */
        private String parameterName;

        /**
         * Name of the resource that contains subreport JRXML file
         */
        private String resourceName;
    }

}
