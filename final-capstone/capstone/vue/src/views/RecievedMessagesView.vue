<template>
  <div class="messages-list">
    <h2>Received Messages</h2>
    <ul>
      <li v-for="message in receivedMessages" :key="message.id">
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
      receivedMessages: [],
    };
  },
  mounted() {
    this.fetchReceivedMessages();
  },
  methods: {
    fetchReceivedMessages() {
      messageService.getReceivedMessagesByUser(this.userId)
        .then((messages) => {
          this.receivedMessages = messages;
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