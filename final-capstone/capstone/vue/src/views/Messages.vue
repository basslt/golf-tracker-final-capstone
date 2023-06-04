<template>
  <div class="main">
      <div class="content">
        <message-list />
        <message-card />
      </div>
  </div>
</template>

<script>
import MessageCard from '../components/MessageCard.vue'
import MessageList from '../components/MessageList.vue'

export default {
    components: {
        MessageCard,
        MessageList
    }

}
</script>

<style scoped>

.main {
    display: flex;
    flex-direction: column;
    height: 100vh;
    width: 100vw;
    justify-content: center;
    align-items: center;
}

.content {
    
}

</style>