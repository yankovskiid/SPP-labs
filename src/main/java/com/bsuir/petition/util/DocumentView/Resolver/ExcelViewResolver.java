package com.bsuir.petition.util.DocumentView.Resolver;

import com.bsuir.petition.util.DocumentView.ExcelView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

public class ExcelViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        ExcelView excelView = new ExcelView();
        return excelView;
    }
}
