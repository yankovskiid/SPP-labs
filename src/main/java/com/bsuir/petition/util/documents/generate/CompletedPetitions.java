package com.bsuir.petition.util.documents.generate;

import com.bsuir.petition.bean.dto.petition.PetitionListDTO;
import com.bsuir.petition.bean.dto.petition.ShortPetitionDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.util.documents.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.supercsv.io.ICsvBeanWriter;

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
    public void buildXls() {

    }

    @Override
    public void buildCsv(ICsvBeanWriter writer) {

    }
}
