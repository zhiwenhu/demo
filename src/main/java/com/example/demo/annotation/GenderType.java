package com.example.demo.annotation;

import com.example.demo.enums.Gender;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderType.Validator.class)
public @interface GenderType {
    String message() default "must be any of enum {enumClass}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    /**
     * 允许枚举
     *
     * @return
     */
    Class<? extends Enum<?>> enumClass();

    class Validator implements ConstraintValidator<GenderType, Gender> {
        private Class<? extends Enum<?>> enumClass;

        @Override
        public void initialize(GenderType constraintAnnotation) {
            enumClass = constraintAnnotation.enumClass();
        }

        @Override
        public boolean isValid(Gender value, ConstraintValidatorContext context) {
            if (value == null) {
                return false;
            }
            return Stream.of(enumClass.getEnumConstants()).collect(Collectors.toList()).contains(value);
        }
    }
}
