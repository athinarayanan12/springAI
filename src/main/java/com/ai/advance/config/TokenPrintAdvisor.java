package com.ai.advance.config;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

//custom log advisor
@Slf4j
public class TokenPrintAdvisor implements CallAdvisor,StreamAdvisor {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
		// TODO Auto-generated method stub
		log.info("My Token Print advisor is called:");
		log.info("Request: "+chatClientRequest.prompt().getContents());
		ChatClientResponse chatClientResponse= callAdvisorChain.nextCall(chatClientRequest);
		log.info("Response recived from token advisor");
		log.info("Response: "+chatClientResponse.chatResponse().getResult().getOutput().getText());
		log.info("Response: "+chatClientResponse.chatResponse().getMetadata().getUsage().getPromptTokens());//input tokens
		log.info("Completion Token: "+chatClientResponse.chatResponse().getMetadata().getUsage().getCompletionTokens());//output tokens
		log.info("Total tokens: "+chatClientResponse.chatResponse().getMetadata().getUsage().getTotalTokens());//total tokens
		
		return chatClientResponse;
	}

	@Override
	public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest,
			StreamAdvisorChain streamAdvisorChain) {
		// TODO Auto-generated method stub
		Flux<ChatClientResponse> chatClientResponse = streamAdvisorChain.nextStream(chatClientRequest);
		return chatClientResponse;
	}

}
