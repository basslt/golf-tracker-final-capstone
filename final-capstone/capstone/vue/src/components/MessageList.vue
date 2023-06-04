<template>
    <div class="container">
        <div class="message-list">
            <message-card v-for="message in $store.state.messages" v-bind:key="message.id" v-bind:message="message" />
        </div>
    </div>
</template>

<script>
import messageService from '../services/MessageService'
import MessageCard from '../components/MessageCard.vue'

export default {
    name: "message-list",
    props: {
        message: Object
    },
    components: {
        MessageCard
    },
    methods: {
        getMessages() {
            messageService.getAllMessages().then( (response) => {
                this.$store.commit("SET_MESSAGES", response.data);
            });
        }
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

}

</style>