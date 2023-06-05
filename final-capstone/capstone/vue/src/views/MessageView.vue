<template>
  <div class="message-view">
    <h2>{{ message.subject }}</h2>
    <p>From: {{ message.sender }}</p>
    <p>To: {{ message.recipient }}</p>
    <p>{{ message.content }}</p>
    <button @click="deleteMessage">Delete</button>
  </div>
</template>

<script>
import messageService from '../services/MessageService';

export default {
  props: {
    messageId: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      message: null,
    };
  },
  mounted() {
    this.fetchMessage();
  },
  methods: {
    fetchMessage() {
      messageService.getMessageById(this.messageId)
        .then((message) => {
          this.message = message;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    deleteMessage() {
      messageService.deleteMessage(this.messageId)
        .then(() => {
          
          this.$emit('message-deleted');
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
<style scoped>
.message-view {
  margin-bottom: 20px;
}

.message-view h2 {
  margin-bottom: 10px;
}

.message-view p {
  margin-bottom: 5px;
}

</style>