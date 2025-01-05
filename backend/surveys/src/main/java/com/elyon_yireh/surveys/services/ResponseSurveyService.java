package com.elyon_yireh.surveys.services;


import com.elyon_yireh.surveys.model.entities.*;
import com.elyon_yireh.surveys.model.schemas.create.CreateResponse;
import com.elyon_yireh.surveys.model.utils.DocumentEnum;
import com.elyon_yireh.surveys.repository.mysql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseSurveyService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private RespondentRepository respondentRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public List<ResponseSurveyEntity> getAll() {
        return responseRepository.findAll();
    }

    public List<ResponseSurveyEntity> createSurveyResponse(CreateResponse request) {
        try {
            // Guardar RespondentEntity
            RespondentEntity respondent = respondentRepository.save(
                    RespondentEntity.builder()
                            .phone(request.respondent().phone())
                            .email(request.respondent().email())
                            .documentType(DocumentEnum.valueOf(request.respondent().documentType()))
                            .documentNumber(request.respondent().documentNumber())
                            .name(request.respondent().firstname())
                            .apellido(request.respondent().lastname())
                            .student(request.respondent().student())
                            .build()
            );

            // Obtener SurveyEntity
            SurveyEntity survey = surveyRepository.findById(request.surveyId())
                    .orElseThrow(() -> new Exception("Survey not found"));

            // Procesar las respuestas
            List<ResponseSurveyEntity> responseEntities = request.responses().stream().map(responseSchema -> {
                // Recuperar la pregunta
                QuestionEntity question = questionRepository.findById(responseSchema.questionId())
                        .orElseThrow(() -> new RuntimeException("Question not found"));

                // Recuperar la respuesta seleccionada
                AnswerEntity answer = answerRepository.findById(responseSchema.answerId())
                        .orElseThrow(() -> new RuntimeException("Answer not found"));

                // Crear y retornar la entidad ResponseSurveyEntity
                return new ResponseSurveyEntity(null, respondent, survey, question, answer);
            }).collect(Collectors.toList());

            // Guardar todas las respuestas
            return  responseRepository.saveAll(responseEntities);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
