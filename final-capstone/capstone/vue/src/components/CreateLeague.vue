<template>
  <div class="add-league">
    <h2>Add a New League</h2>
    <form @submit.prevent="createLeague">
      <div class="form-group">
        <label for="leagueName">League Name:</label>
        <input type="text" id="leagueName" v-model="league.leagueName" required>
      </div>
      <button type="submit">Create League</button>
    </form>
  </div>
</template>

<script>
import leagueService from '../services/LeagueService';
import userService from '../services/UserService';

export default {
  data() {
    return {
      league: {
        leagueName: '',
        organizerId: ''
      }
    };
  },
  methods: {
    createLeague() {
      leagueService.addLeague(this.league)
        .then(() => {
          console.log('League created!');
        })
        .catch(error => {
          console.error('Failed to create league:', error);
          
        });
    }
  },
  created() {
    userService.getUserByUsername(this.$store.state.loggedUser.username).then((response) => {
      this.league.organizerId = response.data.id;
    });
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
