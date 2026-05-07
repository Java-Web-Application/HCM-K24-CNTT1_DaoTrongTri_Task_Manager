package com.session10.taskmanager.controller;

import com.session10.taskmanager.model.TaskItem;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class TaskController {

    private final List<TaskItem> taskList = new ArrayList<>();

    public TaskController() {
        taskList.add(new TaskItem(
                "TASK-001",
                "Hoàn thiện báo cáo tháng",
                LocalDate.now().plusDays(7),
                "HIGH"
        ));
        taskList.add(new TaskItem(
                "TASK-002",
                "Chuẩn bị tài liệu họp nhóm",
                LocalDate.now().plusDays(3),
                "MEDIUM"
        ));
        taskList.add(new TaskItem(
                "TASK-003",
                "Xem xét code của thành viên",
                LocalDate.now().plusDays(14),
                "LOW"
        ));
        taskList.add(new TaskItem(
                "TASK-004",
                "Triển khai tính năng đăng nhập",
                LocalDate.now().plusDays(10),
                "HIGH"
        ));
        taskList.add(new TaskItem(
                "TASK-005",
                "Viết unit test cho module thanh toán",
                LocalDate.now().plusDays(5),
                "MEDIUM"
        ));
    }

    @GetMapping("/tasks")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskList);
        return "task-list";
    }

    @GetMapping("/tasks/new")
    public String showAddForm(Model model) {
        model.addAttribute("taskItem", new TaskItem());
        return "task-form";
    }

    @PostMapping("/tasks")
    public String saveTask(@Valid @ModelAttribute("taskItem") TaskItem taskItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "task-form";
        }
        taskItem.setId("TASK-" + String.format("%03d", taskList.size() + 1));
        taskList.add(taskItem);
        return "redirect:/tasks";
    }
}
