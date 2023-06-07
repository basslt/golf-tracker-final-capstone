<template>
    <div class="container">
        <div class="main">
            <div>
                <button type="button" v-on:click="showInbox">Inbox</button>
                <button type="button" v-on:click="showSent">Sent</button>
            </div>
            <div class="message-list">
                <div class="received-messages" v-if="$store.state.showInbox === 'Inbox'">
                    <h2>Received Messages</h2>
                    <inbox-message-card v-for="message in receivedMessages" :key="message.id" :message="message" v-on:click="showMessage(message)" />
                </div>
                <div class="sent-messages" v-if="$store.state.showInbox === 'Sent'">
                    <h2>Sent Messages</h2>
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
            currentUserId: ''
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

.container {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
}

.message-list {
    width: 100%;
    margin: 5px;

    /* display: flex;
    flex-direction: column;
    width: 50%; */
}

.main {
    position: absolute;
   
}



</style>
