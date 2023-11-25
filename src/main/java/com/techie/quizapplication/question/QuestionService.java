package com.techie.quizapplication.question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<Question> getAllQuestions();

    String addNewQuestion(Question question);

    List<Question> getQuestionsByCategory(String category);

    Optional<Question> getRandomQuestion();
}
