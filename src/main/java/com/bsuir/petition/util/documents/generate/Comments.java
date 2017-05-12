package com.bsuir.petition.util.documents.generate;

import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.util.documents.Document;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Comments extends Document {

    private List<Comment> comments;

    @Override
    public void setObjectList(Object objectList) {
        this.comments = (List<Comment>) objectList;
    }

    @Override
    public void buildPdf(com.itextpdf.text.Document doc) throws DocumentException {
        try {
            BaseFont baseFont = BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            com.itextpdf.text.Font font = new com.itextpdf.text.Font(baseFont);
            com.itextpdf.text.Font bold = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 14, com.itextpdf.text.Font.BOLD);

            doc.add(new Paragraph("Comments statistics", bold));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Total comments: " + comments.size(), bold));
            doc.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(2);
            table.addCell(new PdfPCell(new Paragraph("Comment text", font)));
            table.addCell(new PdfPCell(new Paragraph("User nickname", font)));
            for (Comment comment : comments) {
                table.addCell(new PdfPCell(new Paragraph(comment.getText(), font)));
                table.addCell(new PdfPCell(new Paragraph(comment.getUser().getNick(), font)));
            }
            doc.add(table);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buildXls(Workbook workbook) throws Exception {
        Sheet sheet = workbook.createSheet("Comments");
        sheet.setDefaultColumnWidth(60);

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.MEDIUM);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        style.setWrapText(true);

        CellStyle headerStyle = workbook.createCellStyle();
        Font fontS = workbook.createFont();
        font.setFontName("Arial");
        font.setBold(true);
        headerStyle.setBorderBottom(BorderStyle.MEDIUM);
        headerStyle.setFont(fontS);
        headerStyle.setWrapText(true);
        Row headerTitle = sheet.createRow(0);
        headerTitle.createCell(0).setCellValue("Comments statistics");;
        headerTitle.getCell(0).setCellStyle(headerStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));

        Row header = sheet.createRow(1);
        header.createCell(0).setCellValue("Comment text");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("User nickname");
        header.getCell(1).setCellStyle(style);

        int rowCount = 2;

        CellStyle otherCellStyle = workbook.createCellStyle();
        otherCellStyle.setBorderBottom(BorderStyle.MEDIUM);
        otherCellStyle.setWrapText(true);

        for (Comment comment : comments) {
            Row commentRow = sheet.createRow(rowCount++);
            commentRow.createCell(0).setCellValue(comment.getText());
            commentRow.getCell(0).setCellStyle(otherCellStyle);
            commentRow.createCell(1).setCellValue(comment.getUser().getNick());
            commentRow.getCell(1).setCellStyle(otherCellStyle);
        }
    }

    @Override
    public void buildCsv(ICsvBeanWriter writer) throws IOException {
        String[] header = {"text","userName"};
        writer.writeHeader(header);
        for (Comment comment : comments) {
            try {
                writer.write(new CommentBean(comment.getText(), comment.getUser().getNick()), header);
            } catch (SuperCsvException exception) {
                System.out.println(exception.toString());
            }
        }
    }

    public class CommentBean {
        private String text;
        private String userName;

        public CommentBean(String text, String userName) {
            this.text = text;
            this.userName = userName;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
