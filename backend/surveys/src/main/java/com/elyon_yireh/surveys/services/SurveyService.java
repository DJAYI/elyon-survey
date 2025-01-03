package com.elyon_yireh.surveys.services;

import com.elyon_yireh.surveys.model.entities.AnswerEntity;
import com.elyon_yireh.surveys.model.entities.DepartmentEntity;
import com.elyon_yireh.surveys.model.entities.QuestionEntity;
import com.elyon_yireh.surveys.model.entities.SurveyEntity;
import com.elyon_yireh.surveys.model.schemas.create.CreateAnswer;
import com.elyon_yireh.surveys.model.schemas.create.CreateQuestion;
import com.elyon_yireh.surveys.model.schemas.create.CreateSurvey;
import com.elyon_yireh.surveys.repository.mysql.AnswerRepository;
import com.elyon_yireh.surveys.repository.mysql.DepartmentRepository;
import com.elyon_yireh.surveys.repository.mysql.QuestionRepository;
import com.elyon_yireh.surveys.repository.mysql.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class SurveyService {
    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    public List<SurveyEntity> getAll() {
        return surveyRepository.findAll();
    }

    public Optional<SurveyEntity> getById(UUID id) {
        return surveyRepository.findById(id);
    }

    public SurveyEntity createSurvey(CreateSurvey survey) throws Exception {
        DepartmentEntity departmentEntity = departmentRepository.findById(survey.departmentId()).orElseThrow(() -> new Exception("Department not found"));
        return surveyRepository.save(SurveyEntity.builder().departmentEntity(departmentEntity).build());
    }

    public void deleteSurvey(UUID id) throws Exception {
        SurveyEntity survey = surveyRepository.findById(id).orElseThrow(() -> new Exception());
        survey.setDepartmentEntity(null);
        surveyRepository.delete(survey);
    }

    public List<QuestionEntity> findAllQuestions(UUID id) throws Exception{
        SurveyEntity survey = surveyRepository.findById(id).orElseThrow(()-> new Exception("Survey not found"));
        return List.copyOf(survey.getQuestionEntities());
    }

    public QuestionEntity findQuestion(UUID id, Long questionId) throws Exception{
        SurveyEntity survey = surveyRepository.findById(id).orElseThrow(()-> new Exception("Survey not found"));
        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(()-> new Exception("Question not found"));

        if (survey.getQuestionEntities().contains(question)){
            return question;
        } throw new Exception("The question with id: "+ question.getId() + " NOT exits in survey with id: " + survey.getId());
    }

    public SurveyEntity addQuestion(CreateQuestion question, UUID id) throws Exception {
        SurveyEntity survey = surveyRepository.findById(id).orElseThrow(() -> new Exception("Survey not found"));
        QuestionEntity questionEntity = questionRepository.save(QuestionEntity.builder().statement(question.statement()).build());
        survey.getQuestionEntities().add(questionEntity);
        return surveyRepository.save(survey);
    }

    public void deleteQuestion(Long questionId, UUID id) throws Exception {
        SurveyEntity survey = surveyRepository.findById(id).orElseThrow(() -> new Exception("Survey not found"));
        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(() -> new Exception("Question not found"));
        if (survey.getQuestionEntities().contains(question)) {
            survey.getQuestionEntities().remove(question);
            surveyRepository.save(survey); // Guardar cambios
            questionRepository.delete(question); // Eliminar de la BD
        } else {
            throw new Exception("The question with id: " + question.getId() + " does not exist in survey with id: " + survey.getId());
        }
    }

    public QuestionEntity updateQuestion(Long questionId, UUID surveyId, String statement) throws Exception {
        SurveyEntity survey = surveyRepository.findById(surveyId).orElseThrow(() -> new Exception("Survey not found"));;
        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(() -> new Exception("Question not found"));

        if (survey.getQuestionEntities().contains(question)){
            question.setStatement(statement);
            return  questionRepository.save(question);
        } throw new Exception("The question with id: " + question.getId() + " does not exist in survey with id: " + survey.getId());
    }

    public List<AnswerEntity> findAllAnswersFromQuestion (UUID surveyId, Long questionId) throws Exception {
        SurveyEntity survey = surveyRepository.findById(surveyId).orElseThrow(()-> new Exception("Survey not found"));
        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(()-> new Exception("Question not found"));

        if (survey.getQuestionEntities().contains(question)){
            return List.copyOf(question.getAnswerEntities());
        } throw new Exception("The question with id: "+ question.getId() + " NOT exits in survey with id: " + survey.getId());
    }

    public AnswerEntity findAnswerFromQuestion (UUID surveyId, Long questionId, Long answerId) throws Exception {
        SurveyEntity survey = surveyRepository.findById(surveyId).orElseThrow(()-> new Exception("Survey not found"));
        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(()-> new Exception("Question not found"));
        AnswerEntity answer = answerRepository.findById(answerId).orElseThrow(()-> new Exception("Answer not found"));

        if (survey.getQuestionEntities().contains(question) && question.getAnswerEntities().contains(answer)){
            return answer;
        } throw new Exception("The question with id: "+ question.getId() + " NOT exits in survey with id: " + survey.getId());
    }

    public QuestionEntity addAnswerToQuestion (UUID surveyId, Long questionId, CreateAnswer answer) throws Exception {
        SurveyEntity survey = surveyRepository.findById(surveyId).orElseThrow(()-> new Exception("Survey not found"));
        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(()-> new Exception("Question not found"));
        AnswerEntity answerEntity = AnswerEntity.builder().statement(answer.statement()).value(answer.value()).build();

        if (survey.getQuestionEntities().contains(question)){
            question.getAnswerEntities().add(answerEntity);
            return questionRepository.save(question);
        } throw new Exception("The question with id: "+ question.getId() + " NOT exits in survey with id: " + survey.getId());
    }

    public void deleteAnswerFromQuestion (UUID surveyId, Long questionId, Long answerId) throws Exception {
        SurveyEntity survey = surveyRepository.findById(surveyId).orElseThrow(()-> new Exception("Survey not found"));
        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(()-> new Exception("Question not found"));
        AnswerEntity answer = answerRepository.findById(answerId).orElseThrow(()-> new Exception("Answer not found"));

        if (survey.getQuestionEntities().contains(question) && question.getAnswerEntities().contains(answer)){
            question.getAnswerEntities().remove(answer);
            questionRepository.save(question); // Guardar cambios
            answerRepository.delete(answer); // Eliminar de la BD
        } else {
            throw new Exception("The question with id: "+ question.getId() + " NOT exits in survey with id: " + survey.getId());
        }
    }

    public AnswerEntity updateAnswerFromQuestion (UUID surveyId, Long questionId, Long answerId, CreateAnswer answer) throws Exception {
        SurveyEntity survey = surveyRepository.findById(surveyId).orElseThrow(()-> new Exception("Survey not found"));
        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(()-> new Exception("Question not found"));
        AnswerEntity answerEntity = answerRepository.findById(answerId).orElseThrow(()-> new Exception("Answer not found"));

        if (survey.getQuestionEntities().contains(question) && question.getAnswerEntities().contains(answerEntity)){
            answerEntity.setStatement(answer.statement());
            answerEntity.setValue(answer.value());
            return answerRepository.save(answerEntity);
        } throw new Exception("The question with id: "+ question.getId() + " NOT exits in survey with id: " + survey.getId());
    }
}
