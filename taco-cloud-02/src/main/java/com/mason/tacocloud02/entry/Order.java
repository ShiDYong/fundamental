package com.mason.tacocloud02.entry;//tag::all[]
//tag::allButValidation[]

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.sql.Date;

//在被校验的类上声明校验规则：具体到场景中就是Taco类和Order类
@Data
public class Order {

    private Long id;
    private Date placeAt;

    //end::allButValidation[]
    @NotBlank(message="Name is required")
    //tag::allButValidation[]
    private String name;
    //end::allButValidation[]

    @NotBlank(message="Street is required")
    //tag::allButValidation[]
    private String street;
    //end::allButValidation[]

    @NotBlank(message="City is required")
    //tag::allButValidation[]
    private String city;
    //end::allButValidation[]

    @NotBlank(message="State is required")
    //tag::allButValidation[]
    private String state;
    //end::allButValidation[]

    @NotBlank(message="Zip code is required")
    //tag::allButValidation[]
    private String zip;
    //end::allButValidation[]

    @CreditCardNumber(message="Not a valid credit card number")  //该注解声明属性值必须是合法的信用卡号
    //tag::allButValidation[]
    private String ccNumber;
    //end::allButValidation[]

    //通过正则表达式进行校验
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    //tag::allButValidation[]
    private String ccExpiration;
    //end::allButValidation[]

    //确保它的值包含3位数字
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    //tag::allButValidation[]
    private String ccCVV;

}
//end::allButValidation[]
//end::all[]
