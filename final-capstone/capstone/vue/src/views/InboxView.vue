<template>
  <div>
    <h1>Inbox</h1>
    <MessageList :messages="receivedMessages" @message-selected="selectMessage" />
    <div v-if="selectedMessage">
      <h2>{{ selectedMessage.subject }}</h2>
      <p>From: {{ selectedMessage.sender }}</p>
      <p>{{ selectedMessage.timestamp }}</p>
      <p>{{ selectedMessage.content }}</p>
      <button @click="replyToMessage">Reply</button>
    </div>
  </div>
</template>

<script>
import MessageList from '../components/MessageList.vue';
import MessageService from '../services/MessageService';
import UserService from "../services/UserServices";
export default {
  components: {
    MessageList,
  },
  data() {
    return {
      receivedMessages: [],
      selectedMessage: null,
    };
  },
  methods: {
    fetchReceivedMessages() {
      const userId = UserService.getCurrentUserId();
      MessageService.getReceivedMessagesByUser(userId)
        .then((messages) => {
          this.receivedMessages = messages;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    selectMessage(message) {
      this.selectedMessage = message;
    },
    replyToMessage() {
     
      this.$router.push('/compose');
    },
  },
  created() {
    this.fetchReceivedMessages();
  },
};
</script>

<style scoped>

</style>
