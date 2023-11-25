package com.techie.quizapplication.question;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public String addNewQuestion(Question question) {
        questionRepository.save(question);
        return "success";
    }

    @Override
    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    @Override
    public Optional<Question> getRandomQuestion() {
        long count = questionRepository.count();

        Random random = new Random(count);
        long num = random.nextLong();
        System.out.println(num);

        return questionRepository.findById(num);

    }
}
