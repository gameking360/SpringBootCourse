package Labes.Curso.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import Labes.Curso.Models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    

    List<Task> findByUser_Id(Long id);

    // @Query(value = "SELECT t FROM TASK t WHERE t.user.id = :id")
    // List<Task> findByUser_Id(@Param("id") Long id);

    // @Query(value = "SELECT * FROM task t where t.user_id = :id",nativeQuery = true)
    // List<Task> findByUser_Id(@Param("id") Long id);
}

