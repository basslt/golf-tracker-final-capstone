<template>
  <div class="add-league">
    <h2>Add a New League</h2>
    <form @submit.prevent="createLeague">
      <div class="form-group">
        <label for="leagueName">League Name:</label>
        <input type="text" id="leagueName" v-model="leagueName" required>
      </div>
      <!-- <div class="form-group">
        <label for="organizerId">Organizer ID:</label>
        <input type="text" id="organizerId" v-model="organizerId" required>
      </div> -->
      <button type="submit">Create League</button>
    </form>
  </div>
</template>

<script>
import leagueServices from '../services/LeagueService';
// import userService from '../services/UserService'
// import { mapGetters } from 'vuex';


export default {
  data() {
    return {
      leagueName: ''
    };
  },
  computed:{
    user(){
      return this.$store.getters.getUser;
    }
  },
  methods:{
   createLeague() {
    const leagueName = this.leagueName;
    const organizerId = this.$store.state.user.id;
    this.$store
      .dispatch('getUserById', organizerId)
      .then((user) => {
        const league = {
          name: leagueName,
          organizerId: user.userid
        };

        leagueServices
          .addLeague(league)
          .then((createdLeague) => {
            console.log('League created:', createdLeague);
          })
          .catch((error) => {
            console.error('Failed to create league:', error);
          });
      })
      .catch((error) => {
        console.error('Failed to retrieve user:', error);
      });
  }
}
}
</script>

<style scoped>
.form-group {
  margin-bottom: 1rem;
}

label {
  margin-right: 0.5rem;
}

input {
  width: 100%;
  height: 2rem;
  padding: 0.25rem;
  font-size: 1rem;
}

button {
  padding: 0.5rem 1rem;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}
</style>
