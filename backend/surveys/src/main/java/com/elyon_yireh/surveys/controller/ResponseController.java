package com.elyon_yireh.surveys.controller;

import com.elyon_yireh.surveys.model.entities.ResponseSurveyEntity;
import com.elyon_yireh.surveys.model.schemas.create.CreateResponse;
import com.elyon_yireh.surveys.services.ResponseSurveyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/responses")
@PreAuthorize("denyAll()")
public class ResponseController {

    @Autowired
    private ResponseSurveyService responseSurveyService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<ResponseSurveyEntity> getAll() {
        return responseSurveyService.getAll();
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public List<ResponseSurveyEntity> createResponse(@Valid @RequestBody CreateResponse response) throws Exception {
        System.out.println(response.toString());
        return responseSurveyService.createSurveyResponse(response);
    }
}
