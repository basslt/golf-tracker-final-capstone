<template>
  <div>
      <h2>My Leagues</h2>
      <div class="search">
        <label for="leagueName">Search For a League: </label>
        <input type="text" name="leagueName" v-model="filterText">
    </div>
    <table>
      <thead>
        <th>League Name</th>
      </thead>
      <tbody class="league-list">
        <tr  class="row" v-for="league in filteredLeagues" :key="league.leagueId" >
          <router-link v-bind:to="{ name: 'SelectedLeague', params: { id: league.leagueId }}">
           <td>{{ league.leagueName }}</td>
          </router-link>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import leagueService from '../services/LeagueService'

export default {
    data() {
        return {
            leagues: [],
            filterText: ""
        };
    },
    created() {
        this.fetchLeagues();
    },
    computed: {
        filteredLeagues() {
            return this.leagues.filter( (league) => {
                return league.leagueName.includes(this.filterText);
            })
        }
    },
    methods: {
        fetchLeagues() {
            const user = this.$store.getters.getUser;
            leagueService.getLeaguesByUserId(user.id).then( (response) => {
                this.leagues = response.data;
            })
        },
     }
};

</script>

<style>

</style>