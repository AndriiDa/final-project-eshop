package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.Property;
import com.fs7.finalproject.eshop.model.dto.PropertyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class PropertyMapper extends AbstractMapper<Property, PropertyDto> {

    @Autowired
    public PropertyMapper() {
        super(Property.class, PropertyDto.class);
    }
}
