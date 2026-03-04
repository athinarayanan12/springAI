package com.ai.advance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.advance.service.AIService;

@RestController
@RequestMapping("/db")
public class AIAgentController {
	
	@Autowired
	private AIService aiService;
	
	@GetMapping("/chat")
	public String getMessage(@RequestParam String prompt,@RequestParam String system) {
		return aiService.getAIResponse(prompt, system);
	}
}
