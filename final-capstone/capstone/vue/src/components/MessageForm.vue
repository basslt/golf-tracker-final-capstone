<template>
  <div>
    <h2>{{ formTitle }}</h2>
    <form @submit.prevent="sendMessage">
      <label for="recipient">Recipient:</label>
      <input type="text" id="recipient" v-model="recipient" required>
      <label for="subject">Subject:</label>
      <input type="text" id="subject" v-model="subject" required>
      <label for="content">Content:</label>
      <textarea id="content" v-model="content" required></textarea>
      <button type="submit">Send</button>
    </form>
  </div>
</template>

<script>
import MessageService from '../services/MessageService';

export default {
  data() {
    return {
      recipient: '',
      subject: '',
      content: '',
    };
  },
  computed: {
    formTitle() {
      return this.isReply ? 'Reply Message' : 'Compose Message';
    },
    isReply() {
      
      return !!this.$props.replyTo;
    },
  },
  methods: {
    sendMessage() {
      const message = {
        recipient: this.recipient,
        subject: this.subject,
        content: this.content,
      };
      if (this.isReply) {
        message.replyTo = this.$props.replyTo;
      }
      MessageService.createMessage(message)
        .then(() => {
          
          this.$router.push('/sent-messages');
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>

<style scoped>

</style>
