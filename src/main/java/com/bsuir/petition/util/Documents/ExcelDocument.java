package com.bsuir.petition.util.Documents;

import com.bsuir.petition.bean.entity.Comment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelDocument extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HSSFSheet excelSheet = workbook.createSheet("Simple excel example");
        response.setHeader("Content-Disposition", "attachment; filename=\"excelDocument.xls\"");

        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);

        CellStyle styleHeader = workbook.createCellStyle();
        styleHeader.setFillForegroundColor(HSSFColor.BLUE.index);
        styleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styleHeader.setFont(font);

        setExcelHeader(excelSheet, styleHeader);

        List<Comment> comments = (List<Comment>) model.get("modelObject");
        int rowCount = 1;
        for (Comment comment: comments) {
            HSSFRow row = excelSheet.createRow(rowCount++);
            row.createCell(0).setCellValue(comment.getText());
            row.createCell(1).setCellValue(comment.getUser().getNick());
        }
    }

    public void setExcelHeader(HSSFSheet excelSheet, CellStyle styleHeader) {
        HSSFRow header = excelSheet.createRow(0);
        header.createCell(0).setCellValue("Text");
        header.getCell(0).setCellStyle(styleHeader);
        header.createCell(1).setCellValue("User");
        header.getCell(1).setCellStyle(styleHeader);
    }
}
