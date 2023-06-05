<template>
  <div>
    <form v-on:submit.prevent>
      <div class="form-group">
        <label for="username">Search Users: </label>
        <input type="text" name="username" v-model="filterText" />
      </div>
      <!-- <div class="form-group">
        <label for="userlist"></label>
        <textarea name="userlist" id="userlist" class="form-control" cols="30" rows="10"></textarea>
      </div> -->
    </form>
    <table class="table">
      <thead>
        <th>User</th>
        <th></th>
      </thead>
      <tbody>
        <tr v-for="user in filteredUsers" v-bind:key="user.id">
          <td>
            {{ user.username }}
          </td>
          <td class="button">
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
      memberships: [],
      usersNotInLeague: [],
      users: [],

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
      return this.usersNotInLeague.filter( (user) => {
          return user.username.includes(this.filterText)
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
    },
  },
}
</script>


<style scoped>

.form-group {
  display: block;
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