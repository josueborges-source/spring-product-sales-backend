package dio.dio.spring.security.jwt.controller;

import dio.dio.spring.security.jwt.model.User;
import dio.dio.spring.security.jwt.repository.UserRepository;
import dio.dio.spring.security.jwt.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
    @Autowired
    private UserService service;
    
    @Autowired
    private UserRepository funcionarioRepository;
    
    @GetMapping
    public ResponseEntity<List<User>> recuperarUsers(){
    	System.out.println("Get user: ");
    	List<User> usuarios = funcionarioRepository.findAll();
    	System.out.println("usuarios " + usuarios);
    	return ResponseEntity.ok(usuarios);
    }
    
    @PostMapping
    public void postUser(@RequestBody User user){
    	System.out.println("Printing user: " + user);
        service.createUser(user);
    }
    
    
    
}
