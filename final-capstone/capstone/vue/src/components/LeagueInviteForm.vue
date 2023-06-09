<template>
<div class="background">
    <div class="container">
      <div class="invite-member">
        <form v-on:submit.prevent>
          <div class="form-group">
            <label class="input-text" for="username">Search Users: </label>
            <input type="text" name="username" v-model="filterText" />
          </div>
          <!-- <div class="form-group">
            <label for="userlist"></label>
            <textarea name="userlist" id="userlist" class="form-control" cols="30" rows="10"></textarea>
          </div> -->
        </form>
        <table class="table">
          <!-- <thead> -->
            <!-- <th>User</th>
            <th></th> -->
          <!-- </thead> -->
          <tbody v-if="filteredUsers.length > 0">
            <tr v-for="user in filteredUsers" v-bind:key="user.id">
              <td>
                {{ user.username }}
              </td>
              <td class="button">
                <button type="submit" class="submit-button" v-on:click="sendInvite(user.id)">Send Invite</button>
              </td>
            </tr>
          </tbody>
        </table>
        <p v-if="filteredUsers.length === 0">No matching users</p>
        <button type="button" v-on:click="closeInviteForm">Close</button>
      </div>
      <div v-if="showSuccessPopup" class="invite-sent-popup">
          <p>Your invitation was sent!</p>
      </div>
    </div>
</div>
</template>

<script>
import userService from '../services/UserService.js';
import messageService from '../services/MessageService';
// import inviteService from '../services/InviteService';

export default {
  props: {
    leagueId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      invite: {
        senderId: '',
        receiverId: '',
        leagueId: null,
        content: 'Hey, join my league!',
        type: 'Invite',
        messageRead: false,
        timestamp: Date.now()
      },
      memberships: [],
      usersNotInLeague: [],
      users: [],
      showSuccessPopup: false,
      filterText: "",
      activeLeagueId: "",
    }
  },
  created() {
     const activeLeagueId = this.$route.params.id;
     userService.findUsersNotInLeague(activeLeagueId).then( (response) => {
       this.usersNotInLeague = response.data;
     })

  },

  computed: {
    filteredUsers() {
      if (this.filterText.trim() === '') {
        return [];
      }
      return this.usersNotInLeague.filter( (user) => {
        const name = user.username.toLowerCase();
        const search = this.filterText.toLowerCase();

        for (let i = 0; i < search.length; i++) {
          if (search[i] !== name[i]) {
            return false;
          }
        }
        return true;
      });
    }
  },
  methods: {
    sendInvite(id) {
      const user = this.$store.getters.getUser;
      this.invite.senderId = user.id;
      this.invite.receiverId = id;
      this.invite.leagueId = this.leagueId;
      messageService.createMessage(this.invite).then( (response) => {
        if (response.status === 201) {
          console.log("Success");
          this.showSuccessPopup = true;
          setTimeout( () => {
            this.showSuccessPopup = false;
          }, 1000);
        }
      }).catch((error) => {
        if (error.response) {
          this.errorMsg = "Error submitting new message";
        } else if (error.request) {
          this.errorMsg = "Error submitting new message. Server could not be reached";
        } else {
          this.errorMsg = "Error submitting new message";
        }
      })
    },
    closeInviteForm() {
      this.$emit('close');
    }
  },
}
</script>


<style scoped>

.background {
  position: absolute;
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
  position: absolute;
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  max-width: 400px; /* Adjust the width as needed */
  width: 100%; 
  display: flex;
  justify-content: center;
  align-items: center;
}

.invite-sent-popup {
  position: absolute;
  top: 50%;
  left: 50%;

}

.invite-member {
  display: flex;
  flex-direction: column;
  background-color: whitesmoke;
  align-items: center;
  justify-content: center;
   /* box-shadow: 0 0 10px #004d33; */
  padding: 20px;
  margin-bottom: 20px; 
   border-radius: 10px;
}

.form-group{
  display: flex;
  white-space: nowrap;
  margin-top: 30px;
  padding: 10px;
}

.input-text{
  margin-top: 7px;
}

 input[type="text"] {
    padding: 10px;
    border-width: 2px;
    border-radius: 10px;
    border-color: black;
    background-color: #fffbfb;
    font-size: 16px;
    color: rgb(0, 0, 0);
    transition: box-shadow 0.3s ease;
 }

 label {
    margin-right: 0.5rem;
    color:#000000;
    font-weight: bold;
    padding-bottom: 10px;
}

 input {
    width: 100%;
    height: 2rem;
    padding: 0.25rem;
    font-size: 1rem;
}

button{
   background-color: #ffde59;
  color: white;
  border: none;
  font-size: 15px;
  border-radius: 5px;
  margin-top: 15px;
  font-weight: bold;
}

button:hover{
   background-color:#fce279;
}


table {
  margin-top: 5%;
  table-layout: auto;
  width: 50%;
}

td {
  vertical-align: bottom;
}

</style>