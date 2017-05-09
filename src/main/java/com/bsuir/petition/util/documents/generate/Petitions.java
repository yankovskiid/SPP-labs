package com.bsuir.petition.util.documents.generate;

import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.util.documents.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.util.List;

public class Petitions extends Document {

    private List<Petition> petitions;

    @Override
    public void setObjectList(Object objectList) {
        this.petitions = (List<Petition>) objectList;
    }

    @Override
    public void buildPdf(com.itextpdf.text.Document doc) throws DocumentException {
        doc.add(new Paragraph("Petitions statistics"));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Total petitions: " + this.petitions.size()));
        doc.add(new Paragraph(" "));
        PdfPTable table = new PdfPTable(4);
        table.addCell(new PdfPCell(new Paragraph("Petition id")));
        table.addCell(new PdfPCell(new Paragraph("Name")));
        table.addCell(new PdfPCell(new Paragraph("Needed votes")));
        table.addCell(new PdfPCell(new Paragraph("Expiry date")));
        for (Petition petition : petitions) {
            table.addCell(new PdfPCell(new Paragraph(String.valueOf(petition.getId()))));
            table.addCell(new PdfPCell(new Paragraph(petition.getName())));
            table.addCell(new PdfPCell(new Paragraph(String.valueOf(petition.getNumberNecessaryVotes()))));
            table.addCell(new PdfPCell(new Paragraph(petition.getExpiryDate().toString())));
        }
        doc.add(table);
    }

    @Override
    public void buildXls(Workbook workbook) throws Exception {
        Sheet sheet = workbook.createSheet("Petitions");
        sheet.setDefaultColumnWidth(20);

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setWrapText(true);
        style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        CellStyle style1 = workbook.createCellStyle();
        Font font1 = workbook.createFont();
        font1.setFontName("Arial");
        style1.setWrapText(true);
        font1.setBold(true);
        font1.setColor(HSSFColor.BLACK.index);
        style1.setFont(font1);

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Petition id");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Petition name");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Needed votes");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Expiry date");
        header.getCell(3).setCellStyle(style);

        int rowCount = 1;

        for (Petition petition : petitions) {
            Row commentRow = sheet.createRow(rowCount++);
            commentRow.createCell(0).setCellValue(String.valueOf(petition.getId()));
            commentRow.getCell(0).setCellStyle(style1);
            commentRow.createCell(1).setCellValue(petition.getName());
            commentRow.getCell(1).setCellStyle(style1);
            commentRow.createCell(2).setCellValue(String.valueOf(petition.getNumberNecessaryVotes()));
            commentRow.getCell(2).setCellStyle(style1);
            commentRow.createCell(3).setCellValue(petition.getExpiryDate().toString());
            commentRow.getCell(3).setCellStyle(style1);
        }
    }

    @Override
    public void buildCsv(ICsvBeanWriter writer) throws IOException {
        String[] header = {"id","name","numberNecessaryVotes","expiryDate"};

        writer.writeHeader(header);

        for (Petition petition : petitions) {
            writer.write(petition, header);
        }
    }
}
