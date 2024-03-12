package Labes.Curso.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Labes.Curso.Models.Task;
import Labes.Curso.Models.User;
import Labes.Curso.Repositories.TaskRepository;
import jakarta.transaction.Transactional;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;    

    
    public Task findById(Long id){

        Optional<Task> task = this.taskRepository.findById(id);

        return task.orElseThrow( () -> new RuntimeException(
            "Task não encontrada. \nId: " + id + "\nTipo: " + task.getClass().getName()
            ));
    }

    public List<Task> findAllByUserId(Long id){
        List<Task> tasks = this.taskRepository.findByUser_Id(id);
        return tasks;
    }

    @Transactional
    public Task createTask(Task obj){
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);

        obj = this.taskRepository.save(obj);
        return obj;

    }

    @Transactional
    public Task updateTask(Task obj){
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        this.taskRepository.save(newObj);

        return newObj;
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        try{
            this.taskRepository.deleteById(id);;
        }catch(Exception ex){
            throw new RuntimeException("Não é possível excluir, pois há entidades relacionadas.");  
        }
    }
}
