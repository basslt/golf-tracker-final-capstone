<template>
    <div class="container">
        <div class="invite-list">
            <invite-card v-for="invite in $store.state.invites" :key="invite.id" :invite="invite" v-on:click="showInvite(invite)" />
        </div>
    </div>
</template>

<script>
import InviteCard from '../components/InviteCard.vue'
import inviteService from '../services/InviteService'


export default {
    name: "message-list",
    components: {
        InviteCard
    },
    data() {
        return {

        }
    },
    methods: {
        getInvites() {
            inviteService.getAllInvites().then( (response) => {
                this.$store.commit("SET_INVITES", response.data);
            });
        },
        showInvite(invite) {
            this.$emit('invite-clicked', invite);
        }
    },
    created() {
        this.getInvites();
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