# 🤖 Spring Boot 3.4.2 + Ollama + DeepSeek-R1 Integration (Java 21)

This project demonstrates how to run a local **Large Language Model (LLM)** using **[Ollama](https://ollama.com)** and integrate it with a **Spring Boot 3.4.2** application using **DeepSeek-R1**. No need for cloud APIs — run everything locally for maximum performance and data privacy.

### 🎯 Challenge Focus: DeepSeek-R1 Integration

The primary objective was to demonstrate a seamless integration of the DeepSeek-R1 model using Ollama, highlighting:
- Local AI inference
- Minimal resource consumption
- Easy-to-implement AI chat functionality
- Cross-platform compatibility

## 🧩 Features

- 🔗 Connect Spring Boot with Ollama's local LLM API
- 💬 AI-powered chat endpoint using DeepSeek-R1 model
- 🛡️ Keeps your prompts and data local
- ⚡ Low latency inference via REST
- ☕ Powered by Java 21 and Spring Boot 3.4.2
- 🅰️ Integrated Angular Frontend

## ✨ Preview

Here's what the chat UI looks like when the Angular frontend is running:

![alt text](https://github.com/HenryXiloj/demo-ollama-deepseek-r1/blob/main/img1.png?raw=true)

## 📦 Prerequisites

- Java 21
- Maven 3.8+
- Ollama installed locally (WSL/Ubuntu/macOS/Linux)
- DeepSeek-R1 model pulled via Ollama

## 🚀 Getting Started

### 1. Install Ollama

```bash
curl -fsSL https://ollama.com/install.sh | sh
ollama serve
```

### 2. Pull DeepSeek-R1 Model

```bash
ollama pull deepseek-r1:1.5b
```

### 3. Build and Run the Application

```bash
# Clone the repository
git clone https://github.com/HenryXiloj/demo-ollama-deepseek-r1
cd demo-ollama-deepseek-r1

# Clean, build, and run the application
mvn clean install 
spring-boot:run
```

> **Note:** The Maven build will:
> - Install Node.js and npm
> - Install frontend dependencies
> - Build the Angular frontend
> - Compile and run the Spring Boot backend

## 📁 Project Structure

```
📁 demo-ollama-deepseek-r1/
├── angular-ui/
│   └── src/app/
│       ├── app.component.ts
│       ├── app.component.html
│       ├── app.config.ts
│       └── chat/
│           ├── chat.component.ts
│           ├── chat.component.html
│           └── chat.component.scss
│
├── src/main/java/com/henry/ollama/
│   ├── Application.java
│   ├── config/OllamaProperties.java
│   ├── controller/ChatController.java
│   ├── record/OllamaRequest.java
│   ├── record/OllamaResponse.java
│   └── service/OllamaService.java

```

## 🧪 Testing the API

After starting the application, you can test the chat endpoint:

```bash
curl -X POST -H "Content-Type: text/plain" \
  -d "Explain AI in simple terms" \
  http://localhost:8080/api/chat
```

## 🪟 Bonus: WSL on Windows Setup

### WSL Side

```bash
sudo ufw enable
sudo ufw allow 11434
sudo systemctl stop ollama
sudo lsof -i :11434
export OLLAMA_HOST=0.0.0.0
ollama serve
```

### Windows (PowerShell)

```powershell
$wsl_ip = (wsl hostname -I).Split()[0]
netsh interface portproxy add v4tov4 listenport=11434 listenaddress=0.0.0.0 connectport=11434 connectaddress=$wsl_ip
New-NetFirewallRule -DisplayName "Ollama-WSL" -Direction Inbound -Protocol TCP -LocalPort 11434 -Action Allow
```

## 📚 Resources

- [Ollama Documentation](https://ollama.com)
- [DeepSeek on Hugging Face](https://huggingface.co/deepseek-ai)
- [Spring Boot 3.4.2 Docs](https://docs.spring.io/spring-boot/docs/3.4.2/reference/htmlsingle/)
- [Java 21 Features](https://docs.oracle.com/en/java/javase/21/)
- [LinkedIn Article: Integrating Ollama DeepSeek-R1 with Spring Boot](https://www.linkedin.com/pulse/integrating-ollama-deepseek-r1-spring-boot-henry-xiloj-herrera-jvaye/)

## 📜 License

This project is open-source under the MIT License.

## 🙌 Author

Created with ❤️ by Henry Xiloj 🔗 [Blog: jarmx.blogspot.com](https://jarmx.blogspot.com)
