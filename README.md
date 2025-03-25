# 🤖 Spring Boot 3.4.2 + Ollama + DeepSeek-R1 Integration (Java 21)

This project demonstrates how to run a local **Large Language Model (LLM)** using **[Ollama](https://ollama.com)** and integrate it with a **Spring Boot 3.4.2** application using **DeepSeek-R1**. No need for cloud APIs — run everything locally for maximum performance and data privacy.

## 🧩 Features

- 🔗 Connect Spring Boot with Ollama's local LLM API
- 💬 AI-powered chat endpoint using DeepSeek-R1 model
- 🛡️ Keeps your prompts and data local
- ⚡ Low latency inference via REST
- ☕ Powered by Java 21 and Spring Boot 3.4.2

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

### 3. Test Locally via cURL

```bash
curl -X POST http://localhost:11434/api/generate \
  -d '{"model": "deepseek-r1:1.5b", "prompt": "Hello", "stream": false}'
```

## 🛠️ Spring Boot Configuration

Create an `application.yml` with the following configuration:

```yaml
spring:
  application:
    name: demo-deepseek-r1.ollama
server:
  port: 8080
  error:
    include-message: always
ollama:
  endpoint: http://localhost:11434/api/generate
  model: deepseek-r1:1.5b
  timeout:
    connect: 30000
    read: 60000
```

## 🧪 Testing the API

Start the Spring Boot application, then test the chat endpoint:

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
export OLLAMA_HOST=0.0.0.0
ollama serve
```

### Windows (PowerShell)

```powershell
$wsl_ip = (wsl hostname -I).Split()[0]
netsh interface portproxy add v4tov4 listenport=11434 listenaddress=0.0.0.0 connectport=11434 connectaddress=$wsl_ip
New-NetFirewallRule -DisplayName "Ollama-WSL" -Direction Inbound -Protocol TCP -LocalPort 11434 -Action Allow
```

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/ollama/
│   │       ├── ChatController.java
│   │       ├── OllamaService.java
│   │       └── dto/
│   │           ├── OllamaRequest.java
│   │           └── OllamaResponse.java
│   └── resources/
│       └── application.yml
```

## 📚 Resources

- [Ollama Documentation](https://ollama.com)
- [DeepSeek on Hugging Face](https://huggingface.co/deepseek-ai)
- [Spring Boot 3.4.2 Docs](https://docs.spring.io/spring-boot/docs/3.4.2/reference/htmlsingle/)
- [Java 21 Features](https://docs.oracle.com/en/java/javase/21/)

## 📜 License

This project is open-source under the MIT License.
