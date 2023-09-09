#!/usr/bin/env bash

# Export ChatGPT API Key
export OPENAI_API_KEY="CHANGE_WITH_YOUR_TOKEN"

# Build and test the application
./gradlew clean build test --tests "com.br.chatgpt.controller.ChatGPTControllerTest.postTextTest"

# Run the JAR file
java -jar build/libs/chatgpt-0.0.1.jar