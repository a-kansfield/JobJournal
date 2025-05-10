package edu.wgu.jobjournalcapstone.Data.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(
//        generator= ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
@Table(name = "applications")
public class Application{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Date Created and Date Updated are entirely automated and only serve to keep a record of when a user creates and updates an application
    @Column(name="date_created", columnDefinition="TIMESTAMP", nullable = false)
    private Instant dateCreated;

    @Column(name="date_updated", columnDefinition="TIMESTAMP", nullable = false)
    private Instant dateUpdated;

    @Column(name="job_title", nullable = false)
    private String jobTitle;

    @Column(name="employer", nullable = false)
    private String employer;

    //Date the Employer posted the job listing
    @Column(name="date_posted")
    @Temporal(TemporalType.DATE)
    private LocalDate datePosted;

    // Date the User applied for the position if applicable
    @Column(name="date_applied")
    @Temporal(TemporalType.DATE)
    private LocalDate dateApplied;

    // Date the application is due if applicable
    @Column(name="date_due")
    @Temporal(TemporalType.DATE)
    private LocalDate dateDue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    //private Status status;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateDue() {
        return dateDue;
    }

    public void setDateDue(LocalDate dateDue) {
        this.dateDue = dateDue;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Instant dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }

    public LocalDate getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(LocalDate dateApplied) {
        this.dateApplied = dateApplied;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
