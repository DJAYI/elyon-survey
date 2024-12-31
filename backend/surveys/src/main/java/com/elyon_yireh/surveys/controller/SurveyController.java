package com.elyon_yireh.surveys.controller;

import com.elyon_yireh.surveys.model.entities.AnswerEntity;
import com.elyon_yireh.surveys.model.entities.QuestionEntity;
import com.elyon_yireh.surveys.model.entities.SurveyEntity;
import com.elyon_yireh.surveys.model.schemas.create.CreateAnswer;
import com.elyon_yireh.surveys.model.schemas.create.CreateQuestion;
import com.elyon_yireh.surveys.model.schemas.create.CreateSurvey;
import com.elyon_yireh.surveys.repository.mysql.SurveyRepository;
import com.elyon_yireh.surveys.services.SurveyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/surveys")
@PreAuthorize("denyAll()")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping()
    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.OK)
    public List<SurveyEntity> getAllSurveys(){
        return surveyService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.OK)
    public Optional<SurveyEntity> getSurvey(@Valid @PathVariable UUID id){
        return surveyService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyEntity createSurvey(@Valid @RequestBody CreateSurvey survey) throws Exception{
        return surveyService.createSurvey(survey);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSurvey(@Valid @PathVariable UUID id) throws Exception{
        surveyService.deleteSurvey(id);
    }

    @GetMapping("/{id}/questions")
    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionEntity> findAllQuestionsFromSurvey(@Valid @PathVariable UUID id) throws Exception{
        return surveyService.findAllQuestions(id);
    }

    @GetMapping("/{id}/questions/{questionId}")
    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.OK)
    public QuestionEntity findQuestionFromSurvey(@Valid @PathVariable UUID id, @Valid @PathVariable Long questionId) throws Exception{
        return surveyService.findQuestion(id, questionId);
    }

    @PostMapping("/{id}/questions")
    @PreAuthorize("hasAuthority('CREATE')")
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyEntity addQuestionToSurvey(@Valid @RequestBody CreateQuestion question, @PathVariable UUID id) throws Exception {
        return surveyService.addQuestion(question, id);
    }

    @DeleteMapping("/{id}/questions/{questionId}")
    @PreAuthorize("hasAuthority('DELETE')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuestion(@Valid @PathVariable Long questionId, @Valid @PathVariable UUID id) throws Exception{
        surveyService.deleteQuestion(questionId, id);
    }

    @PutMapping("/{id}/questions/{questionId}")
    @PreAuthorize("hasAuthority('UPDATE')")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionEntity updateQuestion(@Valid @PathVariable Long questionId, @Valid @PathVariable UUID id, @Valid CreateQuestion question) throws Exception {
        return surveyService.updateQuestion(questionId, id, question.statement());
    }

    @GetMapping("/{id}/questions/{questionId}/answers")
    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.OK)
    public List<AnswerEntity> findAllAnswersFromQuestion(@Valid @PathVariable UUID id, @Valid @PathVariable Long questionId) throws Exception{
        return surveyService.findAllAnswersFromQuestion(id, questionId);
    }

    @GetMapping("/{id}/questions/{questionId}/answers/{answerId}")
    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.OK)
    public AnswerEntity findAnswerFromQuestion(@Valid @PathVariable UUID id, @Valid @PathVariable Long questionId, @Valid @PathVariable Long answerId) throws Exception{
        return surveyService.findAnswerFromQuestion(id, questionId, answerId);
    }

    @PostMapping("/{id}/questions/{questionId}/answers")
    @PreAuthorize("hasAuthority('CREATE')")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionEntity addAnswerToQuestion(@Valid @PathVariable UUID id, @Valid @PathVariable Long questionId, @Valid @RequestBody CreateAnswer answer) throws Exception {
        return surveyService.addAnswerToQuestion(id, questionId, answer);
    }

    @DeleteMapping("/{id}/questions/{questionId}/answers/{answerId}")
    @PreAuthorize("hasAuthority('DELETE')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAnswer(@Valid @PathVariable UUID id, @Valid @PathVariable Long questionId, @Valid @PathVariable Long answerId) throws Exception{
        surveyService.deleteAnswerFromQuestion(id, questionId, answerId);
    }

    @PutMapping("/{id}/questions/{questionId}/answers/{answerId}")
    @PreAuthorize("hasAuthority('UPDATE')")
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerEntity updateAnswer(@Valid @PathVariable UUID id, @Valid @PathVariable Long questionId, @Valid @PathVariable Long answerId, @Valid CreateAnswer answer) throws Exception {
        return surveyService.updateAnswerFromQuestion(id, questionId, answerId, answer);
    }

}
