🚀 Spring AI Powered Application

An intelligent backend application built using Spring Boot + Spring AI, integrating AI capabilities with database-driven contextual responses.

This project demonstrates how to build a structured, production-style AI integration instead of simple prompt-based API calls.

🧠 Project Overview

This application integrates:
	•	Dynamic Prompt Engineering
	•	Configurable Model Parameters (Temperature tuning)
	•	Advisor Pattern for AI request/response lifecycle control
	•	MongoDB for contextual data retrieval
	•	AI + Database communication
	•	REST API endpoints for AI-driven responses

The system fetches real-time data from the database, injects structured context into prompts, and returns intelligent, business-driven responses.

🛠 Tech Stack
	•	Java
	•	Spring Boot
	•	Spring AI
	•	REST APIs
	•	OpenAI API
  
🔥 Key Features

✅ Prompt Engineering
	•	Structured system + user prompts
	•	Dynamic template injection
	•	Context-aware responses

✅ Temperature Configuration

Control creativity & determinism using:
  spring.ai.openai.chat.options.temperature=0.7
✅ Advisor Implementation
	•	Modify outgoing AI requests
	•	Inject system-level instructions
	•	Log and validate AI responses

✅ Database Integration
	•	Fetch live data from MySQLDB
	•	Inject into prompt dynamically
	•	Generate contextual AI output
