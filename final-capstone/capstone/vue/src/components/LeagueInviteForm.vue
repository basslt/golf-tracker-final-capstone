<template>
  <div>
    <form v-on:submit.prevent>
      <label for="username">Search For User to Invite: </label>
      <input type="text" name="username" v-model="filterText" />
    </form>
    <table>
      <thead>
        <th>User</th>
        <th></th>
      </thead>
      <tbody>
        <tr v-for="user in filteredUsers" v-bind:key="user.id">
          <td>
            {{ user.username }}
          </td>
          <td>
            <button v-on:click="getCurrentUser(); sendInvite(user.id)">Send Invite</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import userService from '../services/UserService.js';
import messageService from '../services/MessageService';

export default {

  data() {
    return {
      message: {
        senderId: '',
        receiverId: '',
        content: 'hey',
        timestamp: Date.now()
      },
      users: [],
      filterText: "",
      selectedUserId: ""
    }

  },
  created() {
    userService.getAllUsers().then(response => {
      this.users = response.data;
    }).catch(error => {
      console.error("Whoops", error);
    });
    // userService.getUserByUsername(this.$store.state.loggedUser.username).then((response) => {
    //   this.message.senderId = response.data.id;
    // });
  },

  computed: {
    filteredUsers() {
      return this.users.filter( (user) => {
        return user.username.includes(this.filterText);
      })
    }
  },
  methods: {
    sendInvite(id) {
      // this.message = { 
      //   senderId: this.getCurrentUser(),
      //   receiverId: id,
      //   content: 'Hey',
      //   timestamp: Date.now()
      // }
      this.message.receiverId = id;
      // this.message.senderId = this.getCurrentUser();
      // this.message.content = 'Hey';
      // this.message.timestamp = Date.now();

      messageService.createMessage(this.message).then( (response) => {
        if (response.status === 201) {
          console.log("Success");
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
     // const message = 'hey';

      // Invitation logic here
      // Perform actions like generating invitation link or opening a modal
    },
    getCurrentUser() {
      userService.getUserByUsername(this.$store.state.loggedUser.username).then(response => {
        this.message.senderId = response.data.id;
      });
    }
  }
}
</script>