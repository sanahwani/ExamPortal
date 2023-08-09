package com.exam.repo;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    public List <Quiz> findBycategory(Category category);

    //fn to return only active quizzes
    public List<Quiz> findByActive(Boolean b);

    //fn to return active quizzes of categry
    public List<Quiz> findByCategoryAndActive(Category c, Boolean b);
}
