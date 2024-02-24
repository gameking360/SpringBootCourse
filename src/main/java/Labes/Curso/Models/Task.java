package Labes.Curso.Models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {
     
    private static final String TABLE_NAME = "tasks";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true)
    private Long id;

    @ManyToOne //Muitas tarefas para 1 usuário (n para 1)
    @JoinColumn(name = "user_id",nullable = false,updatable = false)  // Associação de tabelas
    private User user;

    @Column(name = "description",nullable = false,length = 255)
    @NotBlank
    @Size(min = 10, max = 255)
    private String description;


    public Task(){}

    public Task(Long id, User user, String description){
        this.id = id;
        this.user = user;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object obj) {
        
        if( obj == this) return true;
        if(!(obj instanceof Task)) return false;

        Task task = (Task) obj;

        return Objects.equals(this.id, task.id) && Objects.equals(this.user, task.user) && Objects.equals(this.description, task.description);
    }

    @Override
    public int hashCode() {
    
    final int prime = 31;
    int result = 1;

    result = prime * result + (this.id == null ? 0 : this.id.hashCode() ) ; 

    return result;
    }

}
