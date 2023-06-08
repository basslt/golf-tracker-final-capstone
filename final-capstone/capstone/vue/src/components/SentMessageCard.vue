<template>
    <div class="main">
        <div class="message-card" :class="this.$store.state.messageRead ? 'read' : 'unread'" @click="$emit('click')">
            <h2>{{this.senderUser.username}}</h2>
            <p>{{message.type}}</p>
        </div>
    </div>
</template>

<script>
import userService from '../services/UserService'

export default {
    name: 'message-card',
    props: {
        message: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            senderUser: [],
            messageSubject: ''
        }
    },
    created() {
        userService.getUserById(this.message.senderId).then( (response) => {
            this.senderUser = response.data;
        });
    },


}
</script>

<style scoped>

/* .message-card{
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
} */


.message-card {
  border: none;
  margin: 10px;
  width: 800px;
  height: 75px;
  border-radius: 12px;
  /* background-color:  #6AD6B2; */
  /* border-color: #059262;  */
  border-style: solid;
  /* border-top-width: 1px;
  border-right-width: 1px;
  border-bottom-width: 1px;
  border-left-width: 7px; */
  display: flex;
  align-items: center;
  padding: 10px;
  transition: background-color 0.3s;
}

.unread {
  background-color:  #6AD6B2;
  border-color: #059262;
  border-top-width: 2px;
  border-right-width: 2px;
  border-bottom-width: 2px;
  border-left-width: 7px;
}

.read > p {
  color: black;
  margin: 0;
  font-size: 14px;
  
}

.read > h2 {
  color: black;
  margin: 0;
  font-size: 25px;
  font-weight: bold;
}

.read {
  background-color:  white;
  border-color: #059262;
  border-top-width: 2px;
  border-right-width: 2px;
  border-bottom-width: 2px;
  border-left-width: 7px;
}

.main :hover{
  background-color: #7ED9C2; 
  cursor: pointer;
}
h2 {
  color: white;
  margin: 0;
  font-size: 25px;
  font-weight: bold;
}

p {
  color: white;
  margin: 0;
  font-size: 14px;
}

</style>