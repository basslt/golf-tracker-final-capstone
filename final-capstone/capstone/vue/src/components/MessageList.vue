<template>
    <div class="container">
        <div class="message-list">
            <message-card v-for="message in $store.state.messages" :key="message.id" :message="message" v-on:click="showMessage(message)"/>
        </div>
    </div>
</template>

<script>
import messageService from '../services/MessageService'
import MessageCard from '../components/MessageCard.vue'

export default {
    name: "message-list",
    components: {
        MessageCard,
    },
    data() {
        return {
        }
    },
    methods: {
        getMessages() {
            messageService.getAllMessages().then( (response) => {
                this.$store.commit("SET_MESSAGES", response.data);
            });
        },

        showMessage(message) {
            this.$emit('message-clicked', message);
        },

    },
    created() {
        this.getMessages();
    }

}

</script>

<style scoped>

.container {
    display: flex;
    flex-direction: column;
}

.message-list {
    width: 50%;
}

</style>
