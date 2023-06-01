<template>
  <div>
    <h2> Top Leagues</h2>
    <ul>
      <li v-for="league in leagues" :key="league.leagueId">
        {{ league.leagueName }}
      </li>
    </ul>
  </div>
</template>

<script>

import leagueService from '../services/LeagueService'

export default {
  data() {
    return {
      leagues: []
    };
  },
  created() {
    this.fetchLeagues();
  },
  methods: {
    fetchLeagues() {
     leagueService.getLeagues().then(response => {
       this.leagues = response.data;
     })
    }
  }
};
</script>

