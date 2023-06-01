<template>

  <div class="add-league">
    <!-- <h2>Add a New League</h2> -->
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
   background-color: whitesmoke;
  align-items: center;
  justify-content: center;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  margin-bottom: 20px;
}


form{
  
 background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 5px;
}
label {
  margin-right: 0.5rem;
  color:#004d33;
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
  display: inline-block;
  padding: 10px 20px;
  background-color: #fdd430;
  color: #ffffff;
  border-radius: 25px;
  border: none;
  font-size: 16px;
  text-align: center;
  text-decoration: none;
  transition: background-color 0.3s ease;
}

button:hover{
   background-color: #f7ecc2;
}
</style>
