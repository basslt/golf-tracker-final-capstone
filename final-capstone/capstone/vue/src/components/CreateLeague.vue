<template>
  <div class="add-league">    
    <form v-on:submit.prevent>
      <div class="heading">
        <h2>Create a New League</h2>
      </div>
      <div class="form-group">
        <label for="leagueName">League Name:</label>
        <input type="text" id="leagueName" v-model="league.leagueName" required>
      </div>
      <button type="submit" v-on:click="createLeague()">Create League</button>
    </form>
  </div>
</template>

<script>
import leagueService from '../services/LeagueService';

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
      const user = this.$store.getters.getUser;
      this.league.organizerId = user.id;
      leagueService.addLeague(this.league)
        .then( (response) => {
          this.newLeagueId = response.data.leagueId;
          this.$router.push({ name: 'SelectedLeague', params: { id: this.newLeagueId}})
          console.log('League created!');
        })
        .catch(error => {
          console.error('Failed to fetch league ID:', error);
        });
    },
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

.heading{
  grid-row: 2;
  justify-self: center;
  align-items: center;
  font-size: 24px;
  font-weight: bold;
  color: #004d33;
  align-items: center;
  position: inherit;
}

</style>
