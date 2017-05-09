package com.bsuir.petition.util.documents.xls;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class XlsBuilder extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"document.xls\"");

        HashMap<String, Object> modelDoc = (HashMap<String, Object>) model.get("model");
        com.bsuir.petition.util.documents.Document document = (com.bsuir.petition.util.documents.Document) modelDoc.get("object");
        document.setObjectList(modelDoc.get("model"));
        document.buildXls(workbook);
    }
}
