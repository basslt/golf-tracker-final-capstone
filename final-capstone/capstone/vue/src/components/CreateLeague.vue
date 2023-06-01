<template>
  <div class="add-league">
    <h2>Add a New League</h2>
    <form v-on:submit.prevent>
      <div class="form-group">
        <label for="leagueName">League Name:</label>
        <input type="text" id="leagueName" v-model="league.leagueName" required>
      </div>
      <button type="submit" v-on:click="createLeague()">Create League</button>
    </form>
    <LeagueLeaderboard :leagueId="newLeagueId" />
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
      },
      newLeagueId: '',
    };
  },
  methods: {
    createLeague() {
      leagueService.addLeague(this.league)
        .then( (response) => {
          this.newLeagueId = response.data.leagueId;
          this.$router.push({ name: 'League', params: { id: this.newLeagueId}})
          console.log('League created!');
        })
        .catch(error => {
          console.error('Failed to fetch league ID:', error);
        });
    },
  },
  created() {
    userService.getUserByUsername(this.$store.state.loggedUser.username).then((response) => {
      this.league.organizerId = response.data.id;
    });
  }
}
</script>



<style scoped>
.add-league {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-image: url("../assets/createleague.jpg");
  background-size: cover;
  background-position: center;
  text-align: center;
  color: white;
  height: 100vh;
  width: 299%;
}
h2 {
  font-size: 36px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #004d33;
}

form{
  margin-bottom: 1rem; 
 background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 5px;
}
label {
  margin-right: 0.5rem;
  color:#004d33;
}
input {
  width: 100%;
  height: 2rem;
  padding: 0.25rem;
  font-size: 1rem;
}
button {
  padding: 0.5rem 1rem;
  background-color: green;
  color: #fff;
  border: none;
  cursor: pointer;
}
</style>
