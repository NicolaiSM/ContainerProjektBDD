package website.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

import website.repository.UsersRepository;


public class UserLoginValidator implements ConstraintValidator<ValidLogin, CredentialForm> {
	
	private UsersRepository userRepository;
	
	private String username = "";
	private String password = "";
	
	public UserLoginValidator(UsersRepository userRepository) {
		this.userRepository = userRepository;
		
	}
	
	@Override
	public void initialize(ValidLogin validLogin) {
		username = validLogin.username();
		password = validLogin.password();
	}
	

	@Override
	public boolean isValid(CredentialForm value, ConstraintValidatorContext context) { 
		return value != null &&	userRepository.findById(username).filter(user -> user.getPassword().equals(password)).isPresent();
	}
	
	
}
