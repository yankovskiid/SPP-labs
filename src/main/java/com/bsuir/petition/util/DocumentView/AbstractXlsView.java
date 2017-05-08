package com.bsuir.petition.util.DocumentView;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public abstract class AbstractXlsView extends AbstractView {

    private static final String CONTENT_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        buildExcelDocument(model, workbook, request, response);
        ByteArrayOutputStream baos = createTemporaryOutputStream();
        response.setHeader("Content-Disposition", "attachment;filename=filename.xlsx");
        response.setContentType(CONTENT_TYPE_XLSX);
        workbook.write(baos);

        writeToResponse(response, baos);
    }

    protected abstract void buildExcelDocument(Map<String, Object> model, XSSFWorkbook workbook,
                                               HttpServletRequest request, HttpServletResponse response) throws Exception;
}
