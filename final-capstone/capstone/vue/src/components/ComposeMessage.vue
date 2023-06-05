<template>
  <div class="compose">
    <div class="compose-message">
    <textarea v-model="messageContent" placeholder="Write your message..."></textarea>
    <button @click="sendMessage">Send</button>
  </div>
    <h2>Compose Message</h2>
    <form @submit.prevent="sendMessage">
      <div>
        <label for="subject">Subject:</label>
        <input type="text" id="subject" v-model="subject" required>
      </div>
      <div>
        <label for="recipient">Recipient:</label>
        <input type="text" id="recipient" v-model="recipient" required>
      </div>
      <div>
        <label for="content">Content:</label>
        <textarea id="content" v-model="content" required></textarea>
      </div>
      <button type="submit">Send</button>
    </form>
  </div>
</template>

<script>
import messageService from '../services/MessageService';

export default {
  data() {
    return {
      subject: '',
      recipient: '',
      content: '',
    };
  },
  methods: {
    sendMessage() {
      const message = {
        subject: this.subject,
        recipient: this.recipient,
        content: this.content,
      };

      messageService.createMessage(message)
        .then(() => {
          // Optionally, you can emit an event to notify the parent component
          this.$emit('message-sent');
          this.resetForm();
        })
        .catch((error) => {
          console.error(error);
        });
    },
    resetForm() {
      this.subject = '';
      this.recipient = '';
      this.content = '';
    },
  },
};
</script>
<style scoped>
.compose-message {
  margin-bottom: 20px;
}

.compose-message textarea {
  width: 100%;
  height: 150px;
  resize: vertical;
}
</style>