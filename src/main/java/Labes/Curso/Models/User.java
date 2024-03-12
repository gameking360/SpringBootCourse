package Labes.Curso.Models;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = User.TABLE_NAME)
public class User {
    public interface CreateUser{}

    public interface UpdateUser{}

    public static final String TABLE_NAME = "users"; 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true)
    private Long id;

    
    @Column(name = "username",length = 100, unique = true, nullable = false)
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(min = 2,max = 100,groups = CreateUser.class)
    private String username;

    
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "password", nullable = false,length = 60)
    @NotNull(groups = {CreateUser.class,UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class,UpdateUser.class})
    @Size(min = 8,max = 60, groups = {CreateUser.class,UpdateUser.class})
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<Task>();

    public User(){}

    public User(long id, String username, String password){
        this.id = id;
        this.password = password;
        this.username = username;
    }


    public long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

@Override
public boolean equals(Object obj) {
    
    if(obj == this) return true;

    if( obj == null) return false;

    if ( !(obj instanceof User)) return false;


    User user = (User) obj;

    return Objects.equals(id,user.id) && Objects.equals(username, user.getUsername()) && Objects.equals(password, user.password     );
}

@Override
public int hashCode() {
    
    final int prime = 31;
    int result = 1;

    result = prime * result + (this.id == null ? 0 : this.id.hashCode() ) ; 

    return result;
}
}
