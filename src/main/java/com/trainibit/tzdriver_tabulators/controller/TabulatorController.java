package com.trainibit.tzdriver_tabulators.controller;

import com.trainibit.tzdriver_tabulators.service.TabulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tabulator")
@Validated
public class TabulatorController {
    @Autowired
    private TabulatorService tabulatorService;


}
