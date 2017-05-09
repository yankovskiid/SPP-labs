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
    public void buildXls() {

    }

    @Override
    public void buildCsv() {

    }
}
