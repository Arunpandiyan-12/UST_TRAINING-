package com.example.jobnotification.repository;

import com.example.jobnotification.model.JobNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobNotification, Long> {
}
