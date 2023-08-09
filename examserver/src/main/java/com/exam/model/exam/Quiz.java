package com.exam.model.exam;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long qid;

    private String title;

    @Column(length=5000)
    private String description;
    private  String maxMarks;
    private String numberOfQuestions;

    private boolean active=false;

   //reverse of 1 caregory can have many quizzes. many to 1
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

//    @JsonCreator
    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.MERGE) //1 quiz can have many questions
    @JsonIgnore
    private Set<Question> questions= new HashSet<>(); //wl nt maintain order


    public Quiz() {
        super();
    }

//    @JsonCreator
//    public Quiz (@JsonProperty("qid") Long qid ) {
//        this.qid = qid;
//    }
    public Quiz(String title, String description, String maxMarks, String numberOfQuestions, boolean active, Category category, Set<Question> questions) {

        this.title = title;
        this.description = description;
        this.maxMarks = maxMarks;
        this.numberOfQuestions = numberOfQuestions;
        this.active = active;
        this.category = category;
        this.questions = questions;
    }



    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(String numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
