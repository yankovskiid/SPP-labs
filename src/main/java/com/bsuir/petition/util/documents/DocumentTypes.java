package com.bsuir.petition.util.documents;

import java.util.*;

public class DocumentTypes {

    private static TreeMap<String, String> types = new TreeMap<>();

    public static String getType(String type) {
        initTypes();
        for (Map.Entry<String, String> entry : types.entrySet()) {
            if (entry.getKey().equals(type)) {
                return entry.getValue();
            }
        }
        return types.firstEntry().getValue();
    }

    private static void initTypes() {
        types.put("pdf", "pdfBuilder");
    }
}
