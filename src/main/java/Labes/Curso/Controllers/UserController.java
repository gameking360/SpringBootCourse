package Labes.Curso.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Labes.Curso.Services.UserService;
import jakarta.validation.Valid;
import Labes.Curso.Models.User;
import Labes.Curso.Models.User.CreateUser;
import Labes.Curso.Models.User.UpdateUser;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    
    @Autowired
    private UserService userService;


    // Localhost:8080/user/1
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){

        User user = this.userService.findById(id);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    @Validated(CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody User obj){
        this.userService.creaUser(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/ {id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
        }
        
    @PutMapping("/{id}")
    @Validated(UpdateUser.class)
    public ResponseEntity<Void> updateUser(@Valid @RequestBody User obj, Long id){
        obj.setId(id);
        this.userService.updateUser(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){

        this.userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
