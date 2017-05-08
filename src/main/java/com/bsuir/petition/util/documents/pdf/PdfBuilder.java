package com.bsuir.petition.util.documents.pdf;

import com.bsuir.petition.bean.entity.Comment;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class PdfBuilder extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document doc, PdfWriter writer,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        List<Comment> comments = (List<Comment>) model.get("comments");

        for (Comment comment : comments) {
            doc.add(new Paragraph(comment.getText()));
            doc.add(new Paragraph(comment.getUser().getNick()));
        }

    }
}
