<template>
  <div>
    <header>
      <hamburger-menu />
    </header>
    <body class="main">
        <div class="new-message">
            <button class="new-message-button" @click="showNewMessageForm=true">Send a Message</button>
            <new-message-form v-show="showNewMessageForm" @close="showNewMessageForm = false" />
        </div>
        <div class="content">
            <message-list />
            <create-league v-if="this.$store.state.showCreateForm"/>
        </div>
    </body>
  </div>
</template>

<script>
import MessageList from '../components/MessageList.vue'
import HamburgerMenu from '../components/HamburgerMenu.vue'
import NewMessageForm from '../components/NewMessageForm.vue'
import CreateLeague from '../components/CreateLeague.vue'

export default {
    components: {
        MessageList,
        NewMessageForm,
        HamburgerMenu,
        CreateLeague
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