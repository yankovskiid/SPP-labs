package com.bsuir.petition.util.documents.generate;

import com.bsuir.petition.bean.dto.petition.PetitionListDTO;
import com.bsuir.petition.bean.dto.petition.ShortPetitionDTO;
import com.bsuir.petition.bean.entity.*;
import com.bsuir.petition.util.documents.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.util.ArrayList;

public class CompletedPetitions extends Document{

    PetitionListDTO petitionListDTO;

    @Override
    public void setObjectList(Object object) {
        this.petitionListDTO = (PetitionListDTO) object;
    }

    @Override
    public void buildPdf(com.itextpdf.text.Document doc) throws DocumentException {
        ArrayList<ShortPetitionDTO> petitions = this.petitionListDTO.getPetitions();
        doc.add(new Paragraph("Completed petitions"));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Total petitions: " + petitions.size()));
        doc.add(new Paragraph(" "));
        PdfPTable table = new PdfPTable(5);
        table.addCell(new PdfPCell(new Paragraph("Petition id")));
        table.addCell(new PdfPCell(new Paragraph("Name")));
        table.addCell(new PdfPCell(new Paragraph("Votes")));
        table.addCell(new PdfPCell(new Paragraph("Needed votes")));
        table.addCell(new PdfPCell(new Paragraph("Expiry date")));
        for (ShortPetitionDTO petition : petitions) {
            table.addCell(new PdfPCell(new Paragraph(String.valueOf(petition.getId()))));
            table.addCell(new PdfPCell(new Paragraph(petition.getName())));
            table.addCell(new PdfPCell(new Paragraph(String.valueOf(petition.getNumberVotes()))));
            table.addCell(new PdfPCell(new Paragraph(String.valueOf(petition.getNumberNecessaryVotes()))));
            table.addCell(new PdfPCell(new Paragraph(petition.getExpiryDate().toString())));
        }
        doc.add(table);
    }

    @Override
    public void buildXls(Workbook workbook) throws Exception {
        Sheet sheet = workbook.createSheet("Comments");
        sheet.setDefaultColumnWidth(30);

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        style.setWrapText(true);

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Petition id");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Name");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Votes");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Needed votes");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Expiry date");
        header.getCell(4).setCellStyle(style);

        int rowCount = 1;

        CellStyle otherCellStyle = workbook.createCellStyle();
        otherCellStyle.setWrapText(true);

        for (ShortPetitionDTO petition : petitionListDTO.getPetitions()) {
            Row commentRow = sheet.createRow(rowCount++);
            commentRow.createCell(0).setCellValue(String.valueOf(petition.getId()));
            commentRow.getCell(0).setCellStyle(otherCellStyle);
            commentRow.createCell(1).setCellValue(petition.getName());
            commentRow.getCell(1).setCellStyle(otherCellStyle);
            commentRow.createCell(2).setCellValue(String.valueOf(petition.getNumberVotes()));
            commentRow.getCell(2).setCellStyle(otherCellStyle);
            commentRow.createCell(3).setCellValue(String.valueOf(petition.getNumberNecessaryVotes()));
            commentRow.getCell(3).setCellStyle(otherCellStyle);
            commentRow.createCell(4).setCellValue(petition.getExpiryDate().toString());
            commentRow.getCell(4).setCellStyle(otherCellStyle);
        }
    }

    @Override
    public void buildCsv(ICsvBeanWriter writer) throws IOException {
        String[] header = {"id", "name", "votes", "necessaryVotes", "expiryDate"};
        writer.writeHeader(header);
        ArrayList<ShortPetitionDTO> petitions = this.petitionListDTO.getPetitions();
        for (ShortPetitionDTO petition: petitions) {
            writer.write(
                    new CompletedPetitionBean(
                            petition.getId(),
                            petition.getName(),
                            petition.getNumberVotes(),
                            petition.getNumberNecessaryVotes(),
                            petition.getExpiryDate().toString()
                    ), header);
        }
    }

    public class CompletedPetitionBean {
        private long id;
        private String name;
        private int votes;
        private int necessaryVotes;
        private String expiryDate;

        public CompletedPetitionBean(long id, String name, int votes, int necessaryVotes, String expiryDate) {
            this.id = id;
            this.name = name;
            this.votes = votes;
            this.necessaryVotes = necessaryVotes;
            this.expiryDate = expiryDate;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getVotes() {
            return votes;
        }

        public int getNecessaryVotes() {
            return necessaryVotes;
        }

        public String getExpiryDate() {
            return expiryDate;
        }
    }
}
