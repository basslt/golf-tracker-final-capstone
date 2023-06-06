<template>
    <div class="container">
        <div class="main">
            <div>
                <button type="button" v-on:click="showChange">Inbox</button>
                <button type="button" v-on:click="showChange">Sent</button>
            </div>
            <div class="message-list">
                <div class="received-messages" v-if="show===true">
                    <h2>Received Messages</h2>
                    <inbox-message-card v-for="message in receivedMessages" :key="message.id" :message="message" v-on:click="showMessage(message)"/>
                </div>
                <div class="sent-messages" v-if="show===false">
                    <h2>Sent Messages</h2>
                    <sent-message-card v-for="message in sentMessages" :key="message.id" :message="message" v-on:click="showMessage(message)"/>
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
            messages: [],
            receivedMessages: [],
            sentMessages: [],
            show: true,
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
        showChange() {
            this.show === true ? this.show = false : this.show = true;
        },
    },
    created() {
        this.getMessages();
        this.getReceivedMessages();
        this.getSentMessages();
        this.getCurrentUser()
    }

}

</script>

<style scoped>

.container {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
}

.message-list {
    /* display: flex;
    flex-direction: column;
    width: 50%; */
}

.main {
    position: absolute;
   
}

</style>
