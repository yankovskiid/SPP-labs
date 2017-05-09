package com.bsuir.petition.util.documents.pdf;

import com.bsuir.petition.bean.entity.Comment;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PdfBuilder extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document doc, PdfWriter writer,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {


        HashMap<String, Object> modelDoc = (HashMap<String, Object>) model.get("model");
        com.bsuir.petition.util.documents.Document document = (com.bsuir.petition.util.documents.Document) modelDoc.get("object");

        document.setObjectList(modelDoc.get("model"));
        document.buildPdf(doc);

        // form style pdf of team
        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = Image.getInstance("http://images.vfl.ru/ii/1494350324/0cde5a42/17163504.jpg");
        image.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());
        image.setAbsolutePosition(0, 0);
        canvas.addImage(image);
    }
}
