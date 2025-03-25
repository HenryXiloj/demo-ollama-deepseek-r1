package com.henry.ollama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping
    public String forwardToIndex() {
        return "forward:/index.html";
    }
}

