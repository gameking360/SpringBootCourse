package Labes.Curso.Controllers;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Labes.Curso.Models.Task;
import Labes.Curso.Services.TaskService;
import Labes.Curso.Services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {
    

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        Task obj = this.taskService.findById(id);
    
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getAllTasksByUserId(@PathVariable Long userId){
        userService.findById(userId);
        List<Task> objs = this.taskService.findAllByUserId(userId);

        return ResponseEntity.ok().body(objs);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> createTask(@Valid @RequestBody Task obj){
        this.taskService.createTask(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri();
        
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> updateTask(@Valid @RequestBody Task obj,Long id){
        obj.setId(id);

        this.taskService.updateTask(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        this.taskService.delete(id);

        return ResponseEntity.noContent().build();
    }

    
}
