package com.bsuir.petition.util.documents.csv;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CsvBuilder extends AbstractCsvView {
    @Override
    protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"document.csv\"");

        HashMap<String, Object> modelDoc = (HashMap<String, Object>) model.get("model");
        com.bsuir.petition.util.documents.Document document = (com.bsuir.petition.util.documents.Document) modelDoc.get("object");
        document.setObjectList(modelDoc.get("model"));

        // fix encoding
        Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
        writer.write('\uFEFF');

        ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
        document.buildCsv(csvWriter);
        csvWriter.close();
    }
}
