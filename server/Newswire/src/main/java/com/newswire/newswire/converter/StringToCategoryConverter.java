package com.newswire.newswire.converter;

import com.newswire.newswire.entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategoryConverter implements Converter<String, Category> {
    @Override
    public Category convert(String source) {
        if ("sports".equalsIgnoreCase(source)) {
            return Category.SPORTS;
        } else if ("finance".equalsIgnoreCase(source)) {
            return Category.FINANCE;
        } else if ("politics".equalsIgnoreCase(source)) {
            return Category.POLITICS;
        } else {
            throw new IllegalArgumentException("Invalid category name: " + source);
        }
    }
}
