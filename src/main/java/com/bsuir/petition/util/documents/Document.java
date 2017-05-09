package com.bsuir.petition.util.documents;

import com.itextpdf.text.DocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.util.List;

public abstract class Document {

    public abstract void setObjectList(Object object);

    public abstract void buildPdf(com.itextpdf.text.Document doc) throws DocumentException;

    public abstract void buildXls(Workbook workbook) throws Exception;

    public abstract void buildCsv(ICsvBeanWriter writer) throws IOException;
}
