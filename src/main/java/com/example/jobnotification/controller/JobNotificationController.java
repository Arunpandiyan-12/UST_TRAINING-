package com.example.jobnotification.controller;

import com.example.jobnotification.model.JobNotification;
import com.example.jobnotification.service.JobNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobNotificationController {

    @Autowired
    private JobNotificationService jobNotificationService;

    @PostMapping
    public ResponseEntity<JobNotification> createJob(@RequestBody JobNotification jobNotification) {
        JobNotification createdJob = jobNotificationService.addJobNotification(jobNotification);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @GetMapping
    public List<JobNotification> getAllJobs() {
        return jobNotificationService.getAllJobNotifications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobNotification> getJobById(@PathVariable Long id) {
        Optional<JobNotification> jobNotification = jobNotificationService.getJobById(id);
        return jobNotification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobById(@PathVariable Long id) {
        jobNotificationService.deleteJobById(id);
        return ResponseEntity.noContent().build();
    }
}
