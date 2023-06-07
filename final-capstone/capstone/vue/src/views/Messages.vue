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
            <message-details :message="selectedMessage" v-if="selectedMessage" v-on:close="closeMessage"/>
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

export default {
    components: {
        MessageList,
        NewMessageForm,
        HamburgerMenu,
        CreateLeague,
        MessageDetails,
    },
    data() {
      return {
        showNewMessageForm: false,
        selectedMessage: null,
        selectedInvite: null,
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
      },
      // handleReceivedMessageDeleted() {
      //   const user = this.$store.getters.getUser;
      //   const userId = user.id;
      //   messageService.getReceivedMessagesByUser(userId).then( (response) => {
      //     this.receivedMessages = response.data;
      //       // this.$store.commit('SET_RECEIVED_MESSAGES', response.data);
      //   });
      // },
      // handleSentMessageDeleted() {
      //   const user = this.$store.getters.getUser;
      //   const userId = user.id;
      //   messageService.getSentMessagesByUser(userId).then( (response) => {
      //     this.sentMessages = response.data;
      //       // this.$store.commit('SET_SENT_MESSAGES', response.data);
      //   });
      // }
    },
    // created() {
    //     const user = this.$store.getters.getUser;
    //     const userId = user.id;
    //     messageService.getReceivedMessagesByUser(userId).then( (response) => {
    //       this.receivedMessages = response.data;
    //     });
    //     messageService.getSentMessagesByUser(userId).then( (response) => {
    //       this.sentMessages = response.data;
    //     });
    // }
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