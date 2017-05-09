package com.bsuir.petition.util.documents.generate;

import com.bsuir.petition.bean.dto.petition.ShortPetitionDTO;
import com.bsuir.petition.bean.dto.user.ShortUserInformationDTO;
import com.bsuir.petition.bean.dto.user.UserListDTO;
import com.bsuir.petition.util.documents.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Users extends Document {

    UserListDTO userListDTO;

    @Override
    public void setObjectList(Object object) {
        this.userListDTO = (UserListDTO) object;
    }

    @Override
    public void buildPdf(com.itextpdf.text.Document doc) throws DocumentException {
        ArrayList<ShortUserInformationDTO> users = this.userListDTO.getUsers();
        doc.add(new Paragraph("Users statistic"));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Total users: " + users.size()));
        doc.add(new Paragraph(" "));
        PdfPTable table = new PdfPTable(4);
        table.addCell(new PdfPCell(new Paragraph("User id")));
        table.addCell(new PdfPCell(new Paragraph("Email")));
        table.addCell(new PdfPCell(new Paragraph("Nick")));
        table.addCell(new PdfPCell(new Paragraph("Roles")));
        for (ShortUserInformationDTO user : users) {
            table.addCell(new PdfPCell(new Paragraph(String.valueOf(user.getId()))));
            table.addCell(new PdfPCell(new Paragraph(user.getEmail())));
            table.addCell(new PdfPCell(new Paragraph(user.getNick())));
            StringJoiner stringJoiner = new StringJoiner(",");
            for (String role : user.getRoles()) {
                stringJoiner.add(role);
            }
            table.addCell(new PdfPCell(new Paragraph(stringJoiner.toString())));
        }
        doc.add(table);
    }

    @Override
    public void buildXls() {

    }

    @Override
    public void buildCsv() {

    }
}
