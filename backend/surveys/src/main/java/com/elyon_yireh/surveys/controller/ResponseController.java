package com.elyon_yireh.surveys.controller;

import com.elyon_yireh.surveys.model.entities.ResponseEntity;
import com.elyon_yireh.surveys.model.schemas.create.CreateRespondent;
import com.elyon_yireh.surveys.model.schemas.create.CreateResponse;
import com.elyon_yireh.surveys.services.ResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/responses")
@PreAuthorize("denyAll()")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<ResponseEntity> getAll() {
        return responseService.getAll();
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity createResponse(@Valid @RequestBody CreateResponse response) throws Exception {
        return responseService.createResponse(response);
    }
}
