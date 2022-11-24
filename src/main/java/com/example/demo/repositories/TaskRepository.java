package com.example.demo.repositories;

import com.example.demo.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Tasks, Long> {

    void deleteAllByFolder_Id(Long id);
    List<Tasks> findTasksByFolder_Id(Long id);

}
