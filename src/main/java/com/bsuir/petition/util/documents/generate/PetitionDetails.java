package com.bsuir.petition.util.documents.generate;

import com.bsuir.petition.bean.dto.category.CategoryDTO;
import com.bsuir.petition.bean.dto.petition.PetitionDTO;
import com.bsuir.petition.bean.dto.petition.ShortPetitionDTO;
import com.bsuir.petition.bean.entity.Category;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.util.documents.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

public class PetitionDetails extends Document {

    PetitionDTO petition;

    @Override
    public void setObjectList(Object objectList) {
        petition = (PetitionDTO) objectList;
    }

    @Override
    public void buildPdf(com.itextpdf.text.Document doc) throws DocumentException {
        try {
            BaseFont baseFont = BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            com.itextpdf.text.Font font = new com.itextpdf.text.Font(baseFont);

            Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);

            doc.add(new Paragraph("Petition \"" + petition.getName() + "\" statistic", bold));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Categories", bold));

            StringJoiner categories = new StringJoiner(",");
            for (CategoryDTO category : petition.getCategories()) {
                categories.add(category.getName());
            }
            String categoriesList = categories.toString();
            doc.add(new Paragraph(categoriesList, font));

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Description", bold));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(petition.getDescription(), font));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Votes count", bold));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(String.valueOf(petition.getNumberVotes())));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Needed votes count", bold));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(String.valueOf(petition.getNumberNecessaryVotes())));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Comments count", bold));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(String.valueOf(petition.getCommentsCount())));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Expiry date", bold));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(petition.getExpiryDate().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buildXls(Workbook workbook) throws Exception {
        Sheet sheet = workbook.createSheet("Comments");

        CellStyle style = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        style.setWrapText(true);

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Title");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Votes count");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Needed votes");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Comments count");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Expiry date");
        header.getCell(4).setCellStyle(style);

        CellStyle otherCellStyle = workbook.createCellStyle();
        otherCellStyle.setWrapText(true);

        Row commentRow = sheet.createRow(1);
        commentRow.createCell(0).setCellValue(petition.getName());
        commentRow.getCell(0).setCellStyle(otherCellStyle);
        commentRow.createCell(1).setCellValue(String.valueOf(petition.getNumberVotes()));
        commentRow.getCell(1).setCellStyle(otherCellStyle);
        commentRow.createCell(2).setCellValue(String.valueOf(petition.getNumberNecessaryVotes()));
        commentRow.getCell(2).setCellStyle(otherCellStyle);
        commentRow.createCell(3).setCellValue(String.valueOf(petition.getCommentsCount()));
        commentRow.getCell(3).setCellStyle(otherCellStyle);
        commentRow.createCell(4).setCellValue(petition.getExpiryDate().toString());
        commentRow.getCell(4).setCellStyle(otherCellStyle);
    }

    @Override
    public void buildCsv(ICsvBeanWriter writer) throws IOException {
        String[] header = {"name","categories","description","votes","neededVotes","comments","expiryDate"};

        writer.writeHeader(header);

        StringJoiner categories = new StringJoiner(",");
        for (CategoryDTO category : petition.getCategories()) {
            categories.add(category.getName());
        }
        String categoriesList = categories.toString();

        writer.write(new PetitionDetailsBean(petition.getName(), categoriesList, petition.getDescription(),
                                             String.valueOf(petition.getNumberVotes()), String.valueOf(petition.getNumberNecessaryVotes()),
                                             String.valueOf(petition.getCommentsCount()), petition.getExpiryDate().toString()), header);
    }

    public class PetitionDetailsBean {
        private String name;
        private String categories;
        private String description;
        private String votes;
        private String neededVotes;
        private String comments;
        private String expiryDate;

        public PetitionDetailsBean(String name, String categories, String description, String votes, String neededVotes, String comments, String expiryDate) {
            this.name = name;
            this.categories = categories;
            this.description = description;
            this.votes = votes;
            this.neededVotes = neededVotes;
            this.comments = comments;
            this.expiryDate = expiryDate;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategories() {
            return categories;
        }

        public void setCategories(String categories) {
            this.categories = categories;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVotes() {
            return votes;
        }

        public void setVotes(String votes) {
            this.votes = votes;
        }

        public String getNeededVotes() {
            return neededVotes;
        }

        public void setNeededVotes(String neededVotes) {
            this.neededVotes = neededVotes;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
        }
    }
}
