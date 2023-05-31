<template>
  <div class="main">
    <h2>Create a League</h2>
    <form @submit.prevent>
      <label for="name">League Name:</label>
      <input type="text" name="name" v-model="league.name" required>
      <button type="submit" v-on:click="createLeague()">Create League</button>
    </form>
  </div>
</template>

<script>
import leagueService from '../services/LeagueService';

export default {
  name: "create-league",
  data() {
    return {
      league: {
        name: '',
        organizer_id: ''
    }
      
    };
  },
  methods: {
    createLeague() {
      leagueService.addLeague(this.league)
        .then(createdLeague => {
          console.log('League created:', createdLeague);
          // Additional actions after successful league creation
        })
        .catch(error => {
          console.error('Failed to create league:', error);
          // Handle the error condition
        });

      this.league = ''; // Reset the form input value
    }
  }
}
</script>

<style>
/* Your styles here */
</style>
