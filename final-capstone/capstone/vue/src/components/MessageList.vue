<template>
  <div>
    <h2>All Messages</h2>
    <ul>
      <li v-for="message in messages" :key="message.id">
        <MessageItem :message="message" />
      </li>
    </ul>
  </div>
</template>

<script>
import MessageItem from './MessageItem.vue';
import MessageService from '../services/MessageService';

export default {
  data() {
    return {
      messages: [],
    };
  },
  mounted() {
    this.fetchMessages();
  },
  methods: {
    fetchMessages() {
      MessageService.getAllMessages()
        .then((messages) => {
          this.messages = messages;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
  components: {
    MessageItem,
  },
};
</script>