package mk.ukim.finki.taskplanning.web;

import mk.ukim.finki.taskplanning.model.Status;
import mk.ukim.finki.taskplanning.model.Task;
import mk.ukim.finki.taskplanning.model.User;
import mk.ukim.finki.taskplanning.service.TaskService;
import mk.ukim.finki.taskplanning.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks";
    }

    @GetMapping("/add-task")
    public String addTask(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("statusList", Arrays.asList(Status.values()));

        return "form";
    }

    @GetMapping("/edit-task/{id}")
    public String editTask(@PathVariable Long id,Model model){
        model.addAttribute("task",this.taskService.findById(id));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("statusList", Arrays.asList(Status.values()));
        return "form";
    }

    @PostMapping("/add-task")
    public String createTask(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam String status,
                             @RequestParam(required = false) List<Task> dependsOn,
                             @RequestParam Long userId,
                             @RequestParam String startTime,
                             @RequestParam String endTime) {

        taskService.create(title, description, status, dependsOn, userId, LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
        return "redirect:/tasks";
    }

    @PostMapping("/update-task/{id}")
    public String updateTask(@PathVariable Long id,
                             @RequestParam String title,
                             @RequestParam String description,
                             @RequestParam String status,
                             @RequestParam List<Task> dependsOn,
                             @RequestParam Long userId,
                             @RequestParam String startTime,
                             @RequestParam String endTime) {

        taskService.update(id, title, description, status, dependsOn, userId, LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }
}