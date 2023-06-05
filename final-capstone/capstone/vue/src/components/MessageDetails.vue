<template>
  <div class="background" v-if="message">
    <div class="container" >
      <h4>From: {{senderUser.username}}</h4>
      <h4>To: {{toUser.username}}</h4>
      <h4>{{message.timestamp}}</h4>
      <p>Message: {{message.content}}</p>
      <button class="exit-button" v-on:click="closeMessage">x</button>
    </div>
      
  </div>
</template>

<script>
import userService from '../services/UserService'

export default {
  name: "message-detail",
  props: {
        message: {
            type: Object,
            required: true
        }
  },
  data() {
    return {
      senderUser: [],
      toUser: []
    }
  },
  methods: {
    closeMessage() {
      this.$emit('close');
    }
  },
  created() {
      userService.getUserById(this.message.senderId).then( (response) => {
          this.senderUser = response.data;
      })
      userService.getUserById(this.message.receiverId).then( (response) => {
            this.toUser = response.data;
      })
  }

}
</script>

<style scoped>

.background {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5); /* Adjust the alpha value (0.5) to control the transparency */
  display: flex;
  justify-content: center;
  align-items: center;
}

/* .container {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
   max-width: 400px; /* Adjust the width as needed 
  width: 100%; 
  display: flex;
  justify-content: center;
  align-items: center;
} */

.exit-button {
  position: absolute;
  right: 35%;
  top: 25%;
}

.container {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  max-width: 400px; /* Adjust the width as needed */
  width: 100%; 
  display: block;
  /* flex-direction: column;
  justify-content: center;
  align-items: flex-end; */
}

</style>