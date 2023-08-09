package com.exam.model.exam;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cid;

    private String title;

    private String description;


    //1 caregory can have many quizzes. 1 to many rltndhp
    @OneToMany(mappedBy = "category", cascade = CascadeType.MERGE) //only 1 tble el frm i.e category_cid. no column wl bcme fr quizzes
    @JsonIgnore // whn catgory will be ftched, we wont get quizzes. so ignrng quizzess
    private Set<Quiz> quizzes= new LinkedHashSet<>(); //linkedhashset- to maintain order

    public Category() {
       super();
    }

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
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
}
