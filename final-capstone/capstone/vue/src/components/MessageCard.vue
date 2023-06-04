<template>
    <div>
        <div class="message-card">
            <h2>{{this.senderUser.username}}</h2>
        </div>
    </div>
</template>

<script>
import userService from '../services/UserService'

export default {
    name: 'message-card',
    props: {
        message: {
            type: Object
        }
    },
    data() {
        return {
            senderUser: [],
        }
    },
    created() {
        userService.getUserById(this.message.senderId).then( (response) => {
            this.senderUser = response.data;
        })
    }

    // data() {
    //     return {
    //         messages: []
    //     }
    // },
    // created() {
    //     this.messages = this.$store.state.messages;
    // }

}
</script>

<style scoped>

.message-card{
    border: solid;
    margin: 3%;
    width: 800%;
    height: 75px;
    border-radius: 12px;
    background-color: rgb(179, 218, 255);
    border-left-color: rgb(68, 68, 196);
    border-top-width: 1px;
    border-right-width: 1px;
    border-bottom-width: 1px;
    border-left-width: 7px;
}

</style>