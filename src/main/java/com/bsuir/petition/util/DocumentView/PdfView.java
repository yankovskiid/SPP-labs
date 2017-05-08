package com.bsuir.petition.util.DocumentView;

import com.bsuir.petition.bean.dto.comment.CommentDTO;
import com.bsuir.petition.bean.dto.comment.CommentListDTO;
import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.util.DocumentView.Abstract.AbstractPdfView;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class PdfView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"my-pdf-file.pdf\"");

        List<Comment> comments = (List<Comment>) model.get("comments");
        document.add(new Paragraph("Generated Comments " + LocalDate.now()));

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);

        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(BaseColor.WHITE);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Text", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("User nick", font));
        table.addCell(cell);

        for(Comment comment : comments){
            table.addCell(comment.getText());
            table.addCell(comment.getUser().getNick());
        }

        document.add(table);
    }
}
