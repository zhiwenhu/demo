package com.example.demo.enums;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender attr) {
        return attr != null ? attr.getValue() : null;
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        Gender[] enums = Gender.class.getEnumConstants();

        for (Gender e : enums) {
            if (e.getValue().equals(dbData)) {
                return e;
            }
        }

        throw new UnsupportedOperationException("枚举转化异常。枚举【" + Gender.class.getSimpleName() + "】,数据库库中的值为：【" + dbData + "】");
    }

}
