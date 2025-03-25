// src/app/chat/chat.component.ts
import {
  AfterViewChecked,
  Component,
  ElementRef,
  ViewChild
} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements AfterViewChecked {
  prompt: string = '';
  messages: { sender: 'user' | 'bot', text: string }[] = [];
  loading = false;

  @ViewChild('chatBox') chatBox!: ElementRef;

  constructor(private http: HttpClient) {}

  sendMessage() {
    console.log("hello send message");
    if (!this.prompt.trim()) return;

    const userMessage = this.prompt.trim();
    this.messages.push({ sender: 'user', text: userMessage });
    this.loading = true;

    this.http.post('/api/chat', { message: userMessage }, { responseType: 'text' }).subscribe({
      next: (response) => {
        const cleanedText = response
          .replace(/<\/?think>/g, '')   // Remove <think> tags
          .replace(/\n/g, '<br>')       // Replace newlines with HTML line breaks
          .trim();

        console.log("response "+response);
        console.log(response);
        this.messages.push({ sender: 'bot', text: cleanedText });
        this.loading = false;
        this.prompt = '';
      },
      error: (err) => {
        this.messages.push({ sender: 'bot', text: 'Error: ' + err.message });
        this.loading = false;
      }
    });
  }

  ngAfterViewChecked(): void {
    if (this.chatBox) {
      this.chatBox.nativeElement.scrollTop = this.chatBox.nativeElement.scrollHeight;
    }
  }
}
