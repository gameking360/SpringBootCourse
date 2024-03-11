package Labes.Curso.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Labes.Curso.Models.User;
import Labes.Curso.Repositories.TaskRepository;
import Labes.Curso.Repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    //Usar isso para instaciar e fazer as conexões necessárias
    @Autowired
    private TaskRepository taskRepository;

    public User findById(Long id){
        //Optional indica que se não existir, ele será vazio
        Optional<User> user =  this.userRepository.findById(id);

        return  user.orElseThrow( ()  -> new RuntimeException(
           "Usuário não encontrado: Id - " + id + ", Tipo: " + user.getClass().getName()
        ));
    }

    //Usar isso SEMPRE que for fazer inserts, deletes, update no banco
    @Transactional
    public User creaUser(User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());

        return obj;
    }

    @Transactional
    public User updateUser(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        this.userRepository.save(newObj);
        
        return newObj;
    }

    @Transactional
    public void deleteUser(Long id){
       
        findById(id);
        try{
            this.userRepository.deleteById(id);
        }catch(Exception ex){
            throw new RuntimeException("Não é possível excluir, pois há entidades relacionadas");
        }
        
    
    }
}
