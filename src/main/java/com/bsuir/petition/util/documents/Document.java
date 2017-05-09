package com.bsuir.petition.util.documents;

import com.itextpdf.text.DocumentException;

import java.util.List;

public abstract class Document {

    public abstract void setObjectList(List<?> objectList);

    public abstract void buildPdf(com.itextpdf.text.Document doc) throws DocumentException;

    public abstract void buildXls();

    public abstract void buildCsv();
}
