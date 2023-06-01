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
            <button v-on:click="sendInvite(user.id)">Send Invite</button>
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
      selectedUserId: "",
    }
  },
  created() {
    userService.getAllUsers().then(response => {
      this.users = response.data;
    }).catch(error => {
      console.error("Whoops", error);
    });
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
      const user = this.$store.getters.getUser;
      this.message.senderId = user.id;
      this.message.receiverId = id;
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
    }
  }
}
</script>