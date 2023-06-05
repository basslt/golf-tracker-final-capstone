<template>
  <div class="messages-list">
    <h2>Sent Messages</h2>
    <ul>
      <li v-for="message in sentMessages" :key="message.id">
        <router-link :to="`/messages/${message.id}`">{{ message.subject }}</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import messageService from '../services/MessageService';

export default {
  data() {
    return {
      sentMessages: [],
    };
  },
  mounted() {
    this.fetchSentMessages();
  },
  methods: {
    fetchSentMessages() {
      messageService.getSentMessagesByUser(this.userId)
        .then((messages) => {
          this.sentMessages = messages;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
<style scoped>
.messages-list {
  margin-bottom: 20px;
}

.messages-list li {
  margin-bottom: 10px;
}

.messages-list li a {
  text-decoration: none;
  color: #333;
}

.messages-list li a:hover {
  text-decoration: underline;
}
</style>