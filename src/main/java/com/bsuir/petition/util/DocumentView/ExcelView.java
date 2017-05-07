package com.bsuir.petition.util.DocumentView;

import com.bsuir.petition.bean.entity.Comment;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"my-xls-file.xls\"");

        @SuppressWarnings("unchecked")
        List<Comment> comments = (List<Comment>) model.get("comments");

        Sheet sheet = workbook.createSheet("Comment detail");
        sheet.setDefaultColumnWidth(30);

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Text");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("UserNick");
        header.getCell(1).setCellStyle(style);

        int rowCount = 1;

        for (Comment comment : comments) {
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(comment.getText());
            userRow.createCell(1).setCellValue(comment.getUser().getNick());
        }
    }
}
