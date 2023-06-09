<template>
  <div>
    <header>
      <hamburger-menu />
    </header>
    <div class="main">
      
        <div class="new-message">
            <button class="new-message-button" @click="showNewMessageForm=true"> <i class="fa-regular fa-note-sticky" style="color: #059262;"></i> New Message</button>
            <new-message-form v-show="showNewMessageForm" @close="closeNewMessageForm" />
        </div>
        <div class="heading">
          <!-- <h2>Messages</h2> -->
        </div>
        <div class="content">
            <!-- <message-list @message-clicked="showDetails" :receivedMessages="receivedMessages" :sentMessages="sentMessages" /> -->
            <message-list @sent-message-clicked="showSentDetails" @received-message-clicked="showReceivedDetails" />
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
import messageService from '../services/MessageService'


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
        updatedMessage: {
          messageId: null,
          senderId: null,
          receiverId: null,
          leagueId: null,
          content: '',
          type: '',
          messageRead: true,
          timestamp: null
        }
      }
    },
    methods: {
      showSentDetails(message) {
        this.selectedMessage = message;
        this.$store.commit('SET_SENT_MESSAGE_READ', { messageId: message.messageId, readStatus: true })
        this.updatedMessage.messageId = message.messageId;
        this.updatedMessage.senderId = message.senderId;
        this.updatedMessage.receiverId = message.receiverId;
        this.updatedMessage.leagueId = message.leagueId;
        this.updatedMessage.content = message.content;
        this.updatedMessage.type = message.type;
        this.updatedMessage.timestamp = message.timestamp;
        messageService.updateMessage(message.messageId, this.updatedMessage).then( (response) => {
          if (response.status === 200) {
            console.log('Successfully updated message');
          }
        })
      },
      showReceivedDetails(message) {
        this.selectedMessage = message;
        this.$store.commit('SET_RECEIVED_MESSAGE_READ', { messageId: message.messageId, readStatus: true })
        this.updatedMessage.messageId = message.messageId;
        this.updatedMessage.senderId = message.senderId;
        this.updatedMessage.receiverId = message.receiverId;
        this.updatedMessage.leagueId = message.leagueId;
        this.updatedMessage.content = message.content;
        this.updatedMessage.type = message.type;
        this.updatedMessage.timestamp = message.timestamp;
        messageService.updateMessage(message.messageId, this.updatedMessage).then( (response) => {
          if (response.status === 200) {
            console.log('Successfully updated message');
          }
        })
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
      closeNewMessageForm() {
          this.showNewMessageForm = false;
          this.getSentMessages();
      },
      getReceivedMessage() {
          const user = this.$store.getters.getUser;
          const userId = user.id;
          messageService.getReceivedMessagesByUser(userId).then( (response) => {
            this.$store.commit('SET_RECEIVED_MESSAGES', response.data);
          });
      },
      getSentMessages() {
        const user = this.$store.getters.getUser;
        const userId = user.id;
        messageService.getSentMessagesByUser(userId).then( (response) => {
          console.log(response.data);
          this.$store.commit('SET_SENT_MESSAGES', response.data);
        });
      }
    },
    created() {
        this.getReceivedMessage();
        this.getSentMessages();
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