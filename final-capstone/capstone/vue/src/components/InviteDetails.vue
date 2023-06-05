<template>
 <div class="background" v-if="invite">
    <div class="container" >
      <h4>From: {{senderUser.username}}</h4>
      <h4>To: {{toUser.username}}</h4>
      <h4>{{invite.timestamp}}</h4>
      <p>Message: {{invite.content}}</p>
      <button class="accept" v-on:click="acceptInvite">Accept Invite</button>
      <button class="exit-button" v-on:click="closeInvite">x</button>
    </div>
      
  </div>
</template>

<script>
import userService from '../services/UserService'
import leagueMembership from '../services/LeagueMembership'


export default {
    name: "invite-detail",
    props: {
        invite: {
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
        closeInvite() {
            this.$emit('close');
        },
        acceptInvite() {
            this.membership.leagueId = this.invite.leagueId;
            this.membership.userId = this.invite.receiverId;
            leagueMembership.addLeagueMembership(this.membership).then( (response) => {
                if (response.status === 201) {
                    this.$router.push({ name: 'SelectedLeague', params: {id: this.membership.leagueId}});
                    console.log('LeagueMembership created!');
                }
            }).catch((error) => {
                if (error.response) {
                this.errorMsg = "Error submitting new membership";
                } else if (error.request) {
                this.errorMsg = "Error submitting new membership. Server could not be reached";
                } else {
                this.errorMsg = "Error submitting new membership";
                }
        })
        }
    },
    created() {
      userService.getUserById(this.invite.senderId).then( (response) => {
          this.senderUser = response.data;
      })
      userService.getUserById(this.invite.receiverId).then( (response) => {
            this.toUser = response.data;
      })
  }

}
</script>

<style scoped>
#new-message {
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

.exit-button {
    position: absolute;
    right: 35%;
    top: 10%;
}
</style>