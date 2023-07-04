package com.example.demo.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class BaseEnumFactory implements ConverterFactory<String, IBaseEnum> {
    /** 缓存IBaseEnum实现类 */
    private static final Map<Class, Converter> cacheMap = new HashMap();

    @Override
    public <T extends IBaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Converter<String, T> converter = cacheMap.get(targetType);
        if (converter == null) {
            converter = new BaseEnumConverter<>(targetType);
            cacheMap.put(targetType,converter);
        }
        return converter;
    }
    public class BaseEnumConverter<T extends IBaseEnum> implements Converter<String, T> {
        private final Map<String, T> enumMap = new HashMap();
        public BaseEnumConverter(Class<T> enumType) {
            Arrays.stream(enumType.getEnumConstants())
                    .forEach(x -> {
                        enumMap.put(x.getValue(), x);
                    });
        }

        @Override
        public T convert(String source) {
            return Optional.of(source).map(enumMap::get).orElseThrow(()->new NoSuchElementException("不存在的性别"));
        }
    }
}
