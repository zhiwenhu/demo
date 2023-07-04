package com.example.demo.utils;

import com.example.demo.enums.IBaseEnum;

public class BaseEnumUtil {
    public static <E extends Enum<?> & IBaseEnum> E codeOf(Class<E> enumClass, String code){
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getValue() == code)
                return e;
        }
        return null;
    }
}
