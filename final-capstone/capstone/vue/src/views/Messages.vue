<template>
  <div>
    <header>
      <main-navigation />
    </header>
    <body class="main">
        <div class="new-message">
            <button class="new-message-button" @click="showNewMessageForm=true">Send a Message</button>
            <new-message-form v-show="showNewMessageForm" @close="showNewMessageForm = false" />
        </div>
        <div class="content">
            <message-list />
        </div>
    </body>
  </div>
</template>

<script>
import MessageList from '../components/MessageList.vue'
import MainNavigation from '../components/MainNavigation.vue'
import NewMessageForm from '../components/NewMessageForm.vue'

export default {
    components: {
        MessageList,
        MainNavigation,
        NewMessageForm
    },
    data() {
      return {
        showNewMessageForm: false
      }
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