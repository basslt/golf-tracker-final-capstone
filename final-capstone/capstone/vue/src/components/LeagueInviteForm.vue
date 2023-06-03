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
import leagueMembership from '../services/LeagueMembership'
//import LeagueMemberList from '../components/LeagueMemberList.vue'

export default {
  // components: {
  //   LeagueMemberList
  // },

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
      leagueUserMemberships: [],
      activeLeagueId: 0,
      leagueUsers: []
    }
  },
  created() {
    userService.getAllUsers().then(response => {
      this.users = response.data;
    }).catch(error => {
      console.error("Whoops", error);
    });

    this.activeLeagueId = this.$store.state.activeLeagueId;
    leagueMembership.getLeagueMembershipByLeagueId(this.activeLeagueId).then( (response) => {
      this.leagueUserMemberships = response.data;
    });
    this.leagueUserMemberships.forEach( (membership) => {
      userService.getUserById(membership.userId).then( (response) => {
        this.leagueUsers.push(response.data);
      })
    });
  },

  computed: {
    filteredUsers() {
      return this.leagueUsers.filter( (user) => {
        if (user.userId != this.activeLeagueId) {
          return user.username.includes(this.filterText)
        }
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