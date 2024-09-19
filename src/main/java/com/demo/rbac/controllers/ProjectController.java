package com.demo.rbac.controllers;

import com.demo.rbac.entities.Project;
import com.demo.rbac.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController extends GenericExceptionHandler {
    private final ProjectService projectService;

    @GetMapping("/list")
    public ResponseEntity<List<Project>> list() {
        return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
    }
}
