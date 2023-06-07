<template>
  <div>
    <header>
      <hamburger-menu />
    </header>
    <div class="main">
      
        <div class="new-message">
            <button class="new-message-button" @click="showNewMessageForm=true"> <i class="fa-regular fa-note-sticky" style="color: #059262;"></i> New Message</button>
            <new-message-form v-show="showNewMessageForm" @close="showNewMessageForm = false" />
        </div>
        <div class="heading">
          <!-- <h2>Messages</h2> -->
        </div>
        <div class="content">
            <message-list @message-clicked="showDetails" />
            <message-details :message="selectedMessage" v-if="selectedMessage" v-on:close="closeMessage"/>
            <create-league v-if="this.$store.state.showCreateForm"/>
        </div>
        
    </div>
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
  background-color: #059262;
  align-items: center;
  
}

.new-message-button {
  display: inline-block;
  padding: 10px 40px;
  background-color: #f8d340;
  color:white ;
  border: none;
  border-radius: 15px;
  cursor: pointer;
  margin-top: 50px;
  margin-bottom: 20px;
  font-size: 25px;
  align-items: flex-start;
  
}

.content {
  display: flex;
  align-items: flex-start;
  margin: 10px;
  
  
  border-radius: 10px;
  
}

.heading{
  margin-top: 30px;
  color: white;
}

</style>