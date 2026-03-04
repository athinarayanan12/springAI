package com.ai.advance.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.ai.advance.config.AITool;

@Service
public class AIService {
	
	private ChatClient chatClient;
	private AITool aiTool;
	public AIService(ChatClient.Builder builder,AITool aiTool) {
		this.chatClient=builder.build();
		this.aiTool=aiTool;
	}
	public String getAIResponse(String prompt,String system) {
		return chatClient.prompt().user(prompt).tools(aiTool).system(system).call().content();
	}
}
