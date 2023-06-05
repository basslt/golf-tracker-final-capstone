<template>
  <div>
    <h3>{{ message.subject }}</h3>
    <p><strong>From:</strong> {{ message.sender }}</p>
    <p><strong>To:</strong> {{ message.recipient }}</p>
    <p>{{ message.timestamp }}</p>
    <p>{{ message.content }}</p>
  </div>
</template>

<script>
export default {
  props: {
    message: {
      type: Object,
      required: true,
    },
  },
};
</script>
