package Labes.Curso.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Labes.Curso.Models.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    

    


}
