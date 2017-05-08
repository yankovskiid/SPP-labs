package com.bsuir.petition.util;

import com.bsuir.petition.util.DocumentView.Resolver.ExcelViewResolver;
import com.bsuir.petition.util.DocumentView.Resolver.PdfViewResolver;
import com.bsuir.petition.util.DocumentView.XlsView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .defaultContentType(MediaType.APPLICATION_JSON)
                .parameterName("type")
                .favorParameter(true)
                .ignoreUnknownPathExtensions(false)
                .ignoreAcceptHeader(false)
                .useJaf(true);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

//    @Bean
//    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
//        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
//        resolver.setContentNegotiationManager(manager);
//
//        List<ViewResolver> resolvers = new ArrayList<>();
//
//        resolvers.add(excelViewResolver());
//        resolvers.add(pdfViewResolver());
////        resolvers.add(csvViewResolver());
//
//        resolver.setViewResolvers(resolvers);
//        return resolver;
//    }

    @Bean
    public ViewResolver excelViewResolver() {
        return new ExcelViewResolver();
    }

    @Bean
    public ViewResolver pdfViewResolver() {
        return new PdfViewResolver();
    }

//    @Bean
//    public ViewResolver csvViewResolver() {
//        return new CsvViewResolver();
//    }
}
