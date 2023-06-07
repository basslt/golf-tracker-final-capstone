<template>
  <div class="background" v-if="message">
    <div class="container">
      <span class="close-button" @click="closeMessage"> <i class="fa-solid fa-xmark" style="color: #000000;"></i></span> 
      <h4>From: {{senderUser.username}}</h4>
      <h4>To: {{toUser.username}}</h4>
      <h4>{{message.timestamp}}</h4>
      <h3>Message </h3>
      <p>{{message.content}}</p>
      <div class="buttons">
      <button class="accept" v-on:click="acceptInvite" v-if="message.type==='Invite'"> <span>Accept Invite</span> </button>
      <!-- <button class="exit-button" v-on:click="closeMessage">x</button> -->
      <button class="delete" v-on:click="deleteMessage"> <span> Delete</span></button>
      </div>
    </div>
      
  </div>
</template>

<script>
import userService from '../services/UserService'
import leagueMembership from '../services/LeagueMembership'
import messageService from '../services/MessageService'

export default {
  name: "message-detail",
  isInvite: false,
  props: {
        message: {
            type: Object,
            required: true
        }
  },
  data() {
    return {
      senderUser: [],
      toUser: [],
      membership: {
          leagueId: '',
          userId: ''
      }
    }
  },
  methods: {
    closeMessage() {
      this.$emit('close');
    },
    acceptInvite() {
      this.membership.leagueId = this.message.leagueId;
      this.membership.userId = this.message.receiverId;
      leagueMembership.addLeagueMembership(this.membership).then( (response) => {
          if (response.status === 201) {
              this.$router.push({ name: 'SelectedLeague', params: {id: this.membership.leagueId}});
              console.log('LeagueMembership created!');
              messageService.deleteMessage(this.message.messageId).then( (response) => {
                if (response.status === 204) {
                  console.log("Successfully deleted message");
                }
              });
          }
      }).catch((error) => {
          if (error.response) {
          this.errorMsg = "Error submitting new membership";
          } else if (error.request) {
          this.errorMsg = "Error submitting new membership. Server could not be reached";
          } else {
          this.errorMsg = "Error submitting new membership";
          }
      });
    },
    deleteMessage() {
        if (this.message.receiverId === this.$store.state.user.id) {
          messageService.deleteMessage(this.message.messageId).then( (response) => {
            if (response.status === 204) {
              console.log("Successfully deleted message");
              this.closeMessage();
              window.location.reload();
            }
          });
        } else {
            messageService.deleteMessage(this.message.messageId).then( (response) => {
              if (response.status === 204) {
                console.log("Successfully deleted message");
                this.closeMessage();
                window.location.reload();
              }
            });
        }

    }
  },
  created() {
      userService.getUserById(this.message.senderId).then( (response) => {
          this.senderUser = response.data;
      })
      userService.getUserById(this.message.receiverId).then( (response) => {
            this.toUser = response.data;
      });
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

.container {
  display: flex;
  flex-direction: column;
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  max-width: 400px;
  width: 100%;
  margin: 20px;
}

.exit-button {
  position: absolute;
  right: 10px;
  top: 10px;
}

h4{
  margin: 10px;
}
h3{
  display: flex;
  justify-content: center;
  margin: 10px;
}
p{
  display: flex;
  margin: 10px;
 background-color: lightgray;
 border-radius: 10px;
 padding: 10px;
}
.close-button{
 display: flex;
  justify-content: flex-end;
  align-content: flex-end;
  height: 10px;
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
  
}

button  {
display: flex;
 justify-content: center;
 align-items: center;
 
  padding: 10px 40px;
  background-color: #ffde59;
  color: white;
  border: none;
  border-radius: 15px; 
  cursor: pointer;
  font-weight: bold;
  margin: 5px;
}

button:hover {
  background-color: #fce279;
}
.buttons{
display: flex;
justify-content: center;
align-self: center;
margin: 5px;
}

</style>