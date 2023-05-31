<template>
  <div class="add-league">
    <h2>Add a New League</h2>
    <form @submit.prevent="createLeague">
      <div class="form-group">
        <label for="leagueName">League Name:</label>
        <input type="text" id="leagueName" v-model="leagueName" required>
      </div>
      <div class="form-group">
        <label for="organizerId">Organizer ID:</label>
        <input type="text" id="organizerId" v-model="organizerId" required>
      </div>
      <button type="submit">Create League</button>
    </form>
  </div>
</template>

<script>
import leagueService from '../services/LeagueService';

export default {
  data() {
    return {
      leagueName: '',
      organizerId: ''
    };
  },
  methods: {
    createLeague() {
      const league = {
        leagueName: this.leagueName,
        organizerId: this.organizerId
      };

      leagueService.addLeague(league)
        .then(() => {
          
          console.log('League created!');
         
        })
        .catch(error => {
          console.error('Failed to create league:', error);
          
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
