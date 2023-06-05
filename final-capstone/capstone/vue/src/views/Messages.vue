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
            <message-list @message-clicked="showDetails" />
            <invite-list @invite-clicked="showInviteDetails" />
            <message-details :message="selectedMessage" v-if="selectedMessage" v-on:close="closeMessage"/>
            <invite-details :invite="selectedInvite" v-if="selectedInvite" v-on:close="closeInvite" />
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
import MessageDetails from'../components/MessageDetails.vue'
import InviteDetails from '../components/InviteDetails.vue'
import InviteList from '../components/InviteList.vue'

export default {
    components: {
        MessageList,
        NewMessageForm,
        HamburgerMenu,
        CreateLeague,
        MessageDetails,
        InviteDetails,
        InviteList
    },
    data() {
      return {
        showNewMessageForm: false,
        selectedMessage: null,
        selectedInvite: null

      }
    },
    methods: {
      showDetails(message) {
        this.selectedMessage = message;
      },
      showInviteDetails(invite) {
        this.selectedInvite = invite;
      },
      closeMessage() {
        this.selectedMessage = null;
      },
      closeInvite() {
        this.selectedInvite = null;
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