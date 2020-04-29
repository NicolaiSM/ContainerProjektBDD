package website.model.annotations;


import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;



@Documented
@Constraint(validatedBy = UserLoginValidator.class)
@Target( ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLogin {

    String message() default "Sorry, invalid login";
 
    String password();
 
    String username();
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
}
	
	