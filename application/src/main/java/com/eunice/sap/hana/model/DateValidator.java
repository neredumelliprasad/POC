package com.eunice.sap.hana.model;
/**
 * Created by roychoud on 27 Dec 2019.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * History:
 * <ul>
 * <li> 27 Dec 2019 : roychoud - Created
 * </ul>
 *
 * @authors roychoud : Arunava Roy Choudhury
 * © 2019 HERE
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DateValidator
{
    String dateFormat() default "YYYY/MM/DD";
}
