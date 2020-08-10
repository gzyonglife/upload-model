package com.jinlong.uploadmodel.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
/**
 * @description: ValidatorConfiguration
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 15:54
 */
@Configuration
public class ValidatorConfiguration {
    /**
     * 校验validator
     * @return
     */
    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}