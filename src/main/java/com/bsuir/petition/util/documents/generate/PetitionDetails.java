package com.bsuir.petition.util.documents.generate;

import com.bsuir.petition.bean.dto.category.CategoryDTO;
import com.bsuir.petition.bean.dto.petition.PetitionDTO;
import com.bsuir.petition.bean.entity.Category;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.util.documents.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.poi.ss.usermodel.Workbook;
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
        Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);

        doc.add(new Paragraph("Petition \"" + petition.getName() + "\" statistic", bold));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Categories", bold));

        StringJoiner categories = new StringJoiner(",");
        for (CategoryDTO category : petition.getCategories()) {
            categories.add(category.getName());
        }
        String categoriesList = categories.toString();
        doc.add(new Paragraph(categoriesList));

        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Description", bold));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(petition.getDescription()));
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
    }

    @Override
    public void buildXls(Workbook workbook) throws Exception {

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
