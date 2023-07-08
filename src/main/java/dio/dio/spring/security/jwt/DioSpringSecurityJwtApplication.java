package dio.dio.spring.security.jwt;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dio.dio.spring.security.jwt.model.User;
import dio.dio.spring.security.jwt.repository.UserRepository;
import dio.dio.spring.security.jwt.service.UserService;

@SpringBootApplication
public class DioSpringSecurityJwtApplication {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(DioSpringSecurityJwtApplication.class, args);
	}
	
	@PostConstruct
	public void initUsers() {		
		User user = new User(1, "John Doe", "johndoe", "password123", Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
		
		userService.createUser(user);		
	}
}
