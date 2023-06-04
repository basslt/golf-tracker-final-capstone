<template>
  <div>
    <h2>Leagues</h2>
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
      filterText: "",
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
     leagueService.getLeagues().then(response => {
       this.leagues = response.data;
     })
    },
  }
};
</script>

<style scoped>

  .league-list{
    display: inline-block;
    flex-direction: column;
    justify-content: space-evenly;
    background-color: rgb(180, 180, 180);
    flex-basis: 100%;
    flex-grow: 1;
    align-items: flex-start;
    padding-left: 5%;
    padding-top: 5%;
    padding-right: 10%;
    padding-bottom: 5%;
    margin-top: 3%;
  }

  th {
    margin-top: 10%;
    text-align: left;
  }

  td {
    padding-bottom: 3%;
  }

  .search {
    margin-bottom: 5%;
  }

</style>

