<template>
  <div class="create-league">
    <h2>Create a League</h2>
    <form @submit.prevent="createLeague">
      <label for="leagueName">League Name:</label>
      <input type="text" id="leagueName" v-model="leagueName" required>
      <button type="submit" v-on:click="createLeague()">Create League</button>
    </form>
  </div>
</template>

<script>
import leagueServices from '../services/LeagueService';

export default {
  data() {
    return {
      league: {
      leagueName: '',
    
    }
      
    };
  },
  methods: {
    createLeague() {
    
      leagueServices.addLeague(this.league)
        .then(createdLeague => {
          console.log('League created:', createdLeague);
          // Additional actions after successful league creation
        })
        .catch(error => {
          console.error('Failed to create league:', error);
          // Handle the error condition
        });

      this.leagueName = ''; // Reset the form input value
    }
  }
}
</script>

<style>
/* Your styles here */
</style>
