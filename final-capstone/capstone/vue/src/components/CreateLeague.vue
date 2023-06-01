
<template>
  <div class = "create-league">
    <button @click="toggleForm">Create League</button>
    <league-form v-if="showForm" @create-league="handleCreateLeague"></league-form>
  </div>
</template>

<script>
import leagueService from '../services/LeagueService';


export default {
  components: {
    LeagueForm
  },
  data() {
    return {
      showForm: false
    };
  },
  methods: {
  createLeague() {
  leagueService.addLeague(this.league)
    .then(() => {
      leagueService.getLeagueByName(this.league.leagueName)
        .then(response => {
          const newLeagueId = response.data.leagueId;
          if (newLeagueId !== undefined) {
            this.$router.push({ name: 'League', params: { id: newLeagueId }});
          } else {
            console.error('Failed to retrieve league ID');
          }
          console.log('League created!');
        })
        .catch(error => {
          console.error('Failed to fetch league ID:', error);
        });
    })
    .catch(error => {
      console.error('Failed to create league:', error);
    });
},
  }
};





<style >



</style>
