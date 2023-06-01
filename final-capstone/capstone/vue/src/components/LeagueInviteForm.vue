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
            <button v-on:click="sendInvite()">Send Invite</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import userService from '../services/UserService.js';
//import messageService from '../services/MessageService';

export default {

  data() {
    return {
      invite: {
        toUsername: ""
      },
      users: [],
      filterText: ""
    }

  },
  created() {
    userService.getAllUsers().then(response => {
      this.users = response.data;
    }).catch(error => {
      console.error("Whoops", error);
    })
  },
  computed: {
    filteredUsers() {
      return this.users.filter( (user) => {
        return user.username.includes(this.filterText);
      })
    }
  },
  methods: {
    sendInvite() {
     // const message = 'hey';

      // Invitation logic here
      console.log(`Inviting to league with ID: ${this.leagueId}`);
      // Perform actions like generating invitation link or opening a modal
    }
  }
}
</script>