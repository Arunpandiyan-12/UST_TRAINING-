package com.example.jobnotification.service;

import com.example.jobnotification.model.JobNotification;
import com.example.jobnotification.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobNotificationService {

    @Autowired
    private JobRepository jobRepository;

    public JobNotification addJobNotification(JobNotification jobNotification) {
        return jobRepository.save(jobNotification);
    }

    public List<JobNotification> getAllJobNotifications() {
        return jobRepository.findAll();
    }

    public Optional<JobNotification> getJobById(Long jobId) {
        return jobRepository.findById(jobId);
    }

    public void deleteJobById(Long jobId) {
        jobRepository.deleteById(jobId);
    }
}
