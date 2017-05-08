package com.bsuir.petition.util.DocumentView;

import com.bsuir.petition.bean.entity.Comment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class XlsView extends com.bsuir.petition.util.DocumentView.AbstractXlsView {

    private List<Comment> comments;

    public XlsView(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model, XSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Sheet sheet = workbook.createSheet("Comments Xls");
        sheet.setDefaultColumnWidth(30);

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Text");
        header.createCell(1).setCellValue("User nick");

        int rowCount = 1;
        for (Comment comment : comments) {
            Row commentRow = sheet.createRow(rowCount++);
            commentRow.createCell(0).setCellValue(comment.getText());
            commentRow.createCell(1).setCellValue(comment.getUser().getNick());
        }
    }
}
