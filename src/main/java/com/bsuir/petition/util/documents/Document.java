package com.bsuir.petition.util.documents;

import com.itextpdf.text.DocumentException;
import org.supercsv.io.ICsvBeanWriter;

import java.util.List;

public abstract class Document {

    public abstract void setObjectList(Object object);

    public abstract void buildPdf(com.itextpdf.text.Document doc) throws DocumentException;

    public abstract void buildXls();

    public abstract void buildCsv(ICsvBeanWriter writer);
}
