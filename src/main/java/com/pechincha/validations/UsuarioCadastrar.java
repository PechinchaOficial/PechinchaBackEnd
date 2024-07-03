package com.pechincha.validations;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.pechincha.validators.UsuarioCadastrarValidator;

@Constraint(validatedBy = UsuarioCadastrarValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
 public @interface UsuarioCadastrar {

    String message() default "Erro de validação";

     Class<?>[] groups() default {};

     Class<? extends Payload>[] payload() default {};
}
