package com.exam.controller;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.aspectj.lang.annotation.DeclareWarning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @Autowired
    private  QuizService quizService;

    //add ques
    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question){
        return ResponseEntity.ok(this.service.addQuestion(question));
    }

    //updte ques
    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question){
        return ResponseEntity.ok(this.service.updateQuestion(question));
    }

    //get all questions of any quiz
    @GetMapping("/quiz/{qid}")
    public  ResponseEntity<?> getQuestionsOfQuiz(@PathVariable ("qid") Long qid){
//        Quiz quiz= new Quiz();
//        quiz.setQid(qid);
//        Set<Question> questionsOfQuiz =this.service.getQuestionsOfQuiz(quiz);
//        return  ResponseEntity.ok(questionsOfQuiz);
        Quiz quiz=this.quizService.getQuiz(qid);
        Set<Question> questions=quiz.getQuestions();

        List<Question> list=new ArrayList(questions);
        if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions()))
        {
            //means u have mre questions than u can send
            list=list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()+1));
        }
        list.forEach((q)->{
            q.setAnswer("");
        });
        Collections.shuffle(list); //wl shw questions randmly
        return  ResponseEntity.ok(list);

    }
//gttng all questions
    @GetMapping("/quiz/all/{qid}")
    public  ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable ("qid") Long qid){

        Quiz quiz= new Quiz();
        quiz.setQid(qid);
        Set<Question> questionsOfQuiz =this.service.getQuestionsOfQuiz(quiz);
        return  ResponseEntity.ok(questionsOfQuiz);

    }


    //get singlq ques
    @GetMapping("/{quesId}")
    public  Question get(@PathVariable ("quesId") Long quesId){
        return  this.service.getQuestion(quesId);
    }

    //delete
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId){
        this.service.deleteQuestion(quesId);
    }

    //eval quiz
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
        System.out.println(questions);

        Double marksGot= (double) 0;
        int correctAnswers = 0;
        int attempted = 0;

        for (Question q: questions){

            //singlw qstn
            Question question = this.service.get(q.getQuesId());
            if(question.getAnswer().equals(q.getGivenAnswer())){
                //correct answer
                correctAnswers++;
                double marksSingle=Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
                 marksGot += marksSingle;
            }
            if(q.getGivenAnswer()!=null ){

                attempted++;
            }

        }
        Map<String,Object> map=Map.of("marksGot", marksGot,"correctAnswers",correctAnswers, "attempted", attempted);

        return ResponseEntity.ok(map);

    }
}
