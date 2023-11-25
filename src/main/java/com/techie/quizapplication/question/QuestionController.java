package com.techie.quizapplication.question;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("quiz")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("listQuestions")
    public ResponseEntity<List<Question>> getQuestions(){
        List<Question> questions = questionService.getAllQuestions();
        if(!questions.isEmpty())
            return new ResponseEntity<>(questions, HttpStatus.OK);
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    @PostMapping("addNewQuestion")
    public ResponseEntity<String> addNewQuestion(@RequestBody Question question){
        String result = questionService.addNewQuestion(question);
        if(result.equals("success"))
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        List<Question> questions = questionService.getQuestionsByCategory(category);
        if(questions.isEmpty())
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("random")
    public ResponseEntity<Question> getRandomQuestion(){
        Optional<Question> question = questionService.getRandomQuestion();
        boolean present = question.isPresent();
        if(present)
            return new ResponseEntity<>(question.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
