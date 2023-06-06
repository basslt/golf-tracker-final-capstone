<template>
  <div class="all-messages-view">
    <h2>All Messages</h2>
    <ul>
      <li v-for="message in messages" :key="message.id" class="message">
        <router-link :to="`/messages/${message.id}`">{{ message.subject }}</router-link>
      </li>
      <MessageList />
    </ul>
  </div>
</template>

<script>
import messageService from '../services/MessageService';
import userService from '../services/UserService';


export default {
  data() {
    return {
      messages: []
    };
  },

  created() {
    this.fetchAllMessages();
  },
  methods: {
    fetchAllMessages() {
      
      const userId = userService.getCurrentUserId();
      messageService.getReceivedMessagesByUser(userId)
        .then(messages => {
          this.messages = messages;
        })
        .catch(error => {
          console.error(error.message);
        });
    }
  }
};
</script>
<style scoped>
/* Container for the messages */
.message-container {
  display: flex;
  flex-direction: column;
}

/* Individual message */
.message {
  display: flex;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #ccc;
}

/* Sender's name */
.sender {
  flex-basis: 20%;
  font-weight: bold;
}

/* Message subject */
.subject {
  flex-basis: 60%;
}

/* Timestamp */
.timestamp {
  flex-basis: 20%;
  text-align: right;
  color: #999;
}

/* Hover effect on message */
.message:hover {
  background-color: #f5f5f5;
  cursor: pointer;
}
</style>


