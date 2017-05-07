package com.bsuir.petition.util.DocumentView.Resolver;

import com.bsuir.petition.util.DocumentView.PdfView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

public class PdfViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        PdfView view = new PdfView();
        return view;
    }
}
