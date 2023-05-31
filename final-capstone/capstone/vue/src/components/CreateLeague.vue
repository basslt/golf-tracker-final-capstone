<template>
  <div class="main">
    <h2>Create a League</h2>
<<<<<<< HEAD
    <form @submit.prevent="createLeague">
      <label for="leagueName">League Name:</label>
      <input type="text" id="leagueName" v-model="league.Name" required>
=======
    <form @submit.prevent>
      <label for="name">League Name:</label>
      <input type="text" name="name" v-model="league.name" required>
>>>>>>> 9cf696ea543948ff5f3d1009466458dde5289e47
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

<<<<<<< HEAD
    
    
=======
      this.league = ''; // Reset the form input value
>>>>>>> 9cf696ea543948ff5f3d1009466458dde5289e47
    }
  }
}
</script>

<style>


</style>


