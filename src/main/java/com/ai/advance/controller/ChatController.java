package com.ai.advance.controller;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.advance.config.TokenPrintAdvisor;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping
public class ChatController {
	
	@Value("classpath:/prompts/system.st")
	private Resource systemMessage;
	@Value("classpath:/prompts/user.st")
	private Resource userMessage;
	
	private ChatClient chatClient;
	
	public ChatController(ChatClient.Builder builder,ChatMemory chatMemory) {
		//store inmemory chat
		MessageChatMemoryAdvisor chatMemoryadvisor=MessageChatMemoryAdvisor.builder(chatMemory).build();								
		this.chatClient=builder.defaultAdvisors(chatMemoryadvisor,
				//new SimpleLoggerAdvisor()
				 new TokenPrintAdvisor()
				,new SafeGuardAdvisor(List.of("kill", "drugs")))//sensitive content avoid
				.defaultOptions(OpenAiChatOptions.builder().frequencyPenalty(0.4).maxTokens(600).presencePenalty(0.4).topP(0.5).build())
				.build();
	}
	//synchronous blocking wait for returning response
	@GetMapping("/chat")
	public ResponseEntity<String> getMessage(@RequestParam String query){
		String res=chatClient.prompt().system(systemMessage).user(user->user.text(userMessage).param("concept", query)).call().content();
		return ResponseEntity.ok(res);
	}
	//asynchronous non-blocking
	@GetMapping("/streamchat")
	public ResponseEntity<Flux<String>> streamchat(@RequestParam String query){
		Flux<String> chatFlux = chatClient.prompt().system(systemMessage).user(user->user.text(userMessage).param("concept", query)).stream().content();
		return ResponseEntity.ok(chatFlux);
	}
	@GetMapping("/countryCity")
	public List<String> cityOfCountry(@RequestParam(value = "country",defaultValue = "india") String message){
		String prompt ="Give me a List of city in ${message}. If you dont know please return 'no response' ${format}";
		ListOutputConverter list = new ListOutputConverter(new DefaultConversionService());
		PromptTemplate promptTemplate=new PromptTemplate(prompt);
		Prompt p =  promptTemplate.create(Map.of("message",message,"format",list.getFormat()));
		ChatResponse chatResponse= chatClient.prompt(p).call().chatResponse();
		return list.convert(chatResponse.getResult().getOutput().getText());
	}
	@GetMapping("/author/{author}")
	public Map<String,Object> getAuthors(@PathVariable("author") String author){
		String message="Generate List of  ${author} author books. Include author name as key and list of books as value. If you dont know say 'I dont know'. ${format} ";
		MapOutputConverter outputConverter =new MapOutputConverter();
		PromptTemplate pt =new PromptTemplate(message);
		Prompt prompt =pt.create(Map.of("author",author,"format",outputConverter.getFormat()));
		ChatResponse response = chatClient.prompt(prompt).call().chatResponse();
		return outputConverter.convert(response.getResult().getOutput().getText());
	}
}

