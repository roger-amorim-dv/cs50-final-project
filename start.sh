#!/usr/bin/env bash

export OPENAI_API_KEY="YOUR_OWN_OPENIA_KEY"
./gradlew clean build -x test
java -jar build/libs/chatgpt-0.0.1.jar