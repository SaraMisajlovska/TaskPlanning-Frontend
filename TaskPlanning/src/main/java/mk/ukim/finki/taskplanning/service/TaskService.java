package mk.ukim.finki.taskplanning.service;

import mk.ukim.finki.taskplanning.model.Task;
import mk.ukim.finki.taskplanning.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    Optional<Task> findById(Long id);

    List<Task> findAll();

    Optional<Task> findByTitle(String title);

    Task create( String title, String description, String status, List<Task> dependsOn, Long userId, LocalDateTime startTime, LocalDateTime endTime);

    void delete(Long id);

    Optional<Task> update(Long id, String title, String description, String status, List<Task> dependsOn, Long userId, LocalDateTime startTime, LocalDateTime endTime);

    List<Task> getOtherTasks(Long id);
}
