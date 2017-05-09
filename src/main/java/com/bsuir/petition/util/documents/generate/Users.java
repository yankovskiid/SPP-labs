package com.bsuir.petition.util.documents.generate;

import com.bsuir.petition.bean.dto.petition.ShortPetitionDTO;
import com.bsuir.petition.bean.dto.user.ShortUserInformationDTO;
import com.bsuir.petition.bean.dto.user.UserListDTO;
import com.bsuir.petition.util.documents.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
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
    public void buildCsv(ICsvBeanWriter writer) throws IOException   {
        String[] header = {"id","email","nick","roles"};

        writer.writeHeader(header);

        ArrayList<ShortUserInformationDTO> users = this.userListDTO.getUsers();
        for (ShortUserInformationDTO user : users) {
            StringJoiner stringJoiner = new StringJoiner(",");
            for (String role : user.getRoles()) {
                stringJoiner.add(role);
            }
            writer.write(new UserBean(String.valueOf(user.getId()), user.getEmail(), user.getNick(), stringJoiner.toString()), header);
        }
    }

    public class UserBean {
        private String id;
        private String email;
        private String nick;
        private String roles;

        public UserBean(String id, String email, String nick, String roles) {
            this.id = id;
            this.email = email;
            this.nick = nick;
            this.roles = roles;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }
    }
}
