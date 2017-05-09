package com.bsuir.petition.util.documents.generate;

import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.util.documents.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
    public void buildXls() {

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
