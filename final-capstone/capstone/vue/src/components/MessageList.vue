<template>
   
    <div class="container"> 
        <div class="buttons">
                <button class="inbox button" type="button" v-on:click="showInbox">Inbox</button>
                <button class="sent button" type="button" v-on:click="showSent">Sent</button>
            </div>
        <div class="main">
           
            <div class="message-list">
                <div class="received-messages" v-if="$store.state.showInbox === 'Inbox'">
                    <h2><span>Received Messages</span> </h2>
                    <inbox-message-card v-for="message in receivedMessages" :class="messageRead" :key="message.id" :message="message" v-on:click="showMessage(message)" />
                </div>
                <div class="sent-messages" v-if="$store.state.showInbox === 'Sent'">
                    <h2><span>Sent Messages</span></h2>
                    <sent-message-card v-for="message in sentMessages" :key="message.id" :message="message" v-on:click="showMessage(message)" />
                </div>
        </div>
        </div>
    </div>
</template>

<script>
import messageService from '../services/MessageService'
import SentMessageCard from './SentMessageCard.vue'
import InboxMessageCard from '../components/InboxMessageCard.vue'

export default {
    name: "message-list",
    components: {
        SentMessageCard,
        InboxMessageCard
    },
    data() {
        return {
            receivedMessages: [],
            sentMessages: [],
            currentUserId: '',
            messageRead: false
        }
    },
    methods: {
        getMessages() {
            messageService.getAllMessages().then( (response) => {
                this.messages = response.data;
            });
        },
        getCurrentUser() {
            this.currentUser = this.$store.getters.getUser;
        },
        showMessage(message) {
            this.$emit('message-clicked', message);
        },
        getReceivedMessages() {
            const user = this.$store.getters.getUser;
            const userId = user.id;
            messageService.getReceivedMessagesByUser(userId).then( (response) => {
                this.receivedMessages = response.data;
            });
        },
        getSentMessages() {
           const user = this.$store.getters.getUser;
           const userId = user.id;
           messageService.getSentMessagesByUser(userId).then( (response) => {
               this.sentMessages = response.data;
           });
        },
        showInbox() {
            this.$store.commit('SET_SHOW_INBOX_STATUS', "Inbox");
        },
        showSent() {
            this.$store.commit('SET_SHOW_INBOX_STATUS', "Sent");
        },
    },
    created() {
        this.getReceivedMessages();
        this.getSentMessages();
        this.getCurrentUser();
    },
}

</script>

<style scoped>
.container{
   display: flex;
  align-items: center; 
  flex-direction: column;

}



.message-list{
    width: 100%;
    margin: 10px;
    display: flex;
    justify-content: flex-start;
    border-radius: 10px;
    background: white;
}
.buttons{
    display: flex;
    align-self: flex-start;
    justify-content: flex-start;
    margin-left: 10px;
    size: 20px;
   
}
.inbox.button, .sent.button{
    color: white;
    background: #6AD6B2;
    border: none;
    border-radius: 3px;
    font-size: 20px;
    margin: 5px;
    margin-bottom: -12px;
    width: 100px;

}
.inbox.button:hover, .sent.button:hover{
    cursor: pointer;
    box-shadow: 0 0 10px black; 
  transition: box-shadow 0.3s ease;
    
}


h2 {
    align-items: center;
    display: flex;
    justify-content: center;
    border-bottom: #6AD6B2 solid;
    margin: 5px;
    padding: 5px;
    color: rgb(66, 66, 66);
    font-weight: lighter;
    
}



</style>
