<template>
  <div class="container">
      <h2>My Leagues</h2>
      <div class="search">
        <label for="leagueName">Search: </label>
        <input type="text" name="leagueName" v-model="filterText">
    </div>
    <table>
      <tbody class="league-list">
        <tr  class="row" v-for="league in filteredLeagues" :key="league.leagueId" >
          <router-link class="name" v-bind:to="{ name: 'SelectedLeague', params: { id: league.leagueId }}">
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
     },
     
};

</script>


<style scoped>
.container {
  background-color: #059262; 
  padding: 30px;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center; 
  justify-content: center; 
}

h2 {
  margin-bottom: 20px;
  text-align: center; 
  color: white;
}

.search {
  margin-bottom: 20px;
  text-align: center; 
  
  border-top: 1px solid #ffffff; 
  padding-top: 10px;
  font-weight: bold;

}

label {
  margin-right: 10px;
  color: white;
}

table {
  width: 100%;
  
}
input[type="text"]
   {
 padding: 5px;
  border: none;
  
  background-color: #fff9f9;
  
  color: #333;
  transition: box-shadow 0.3s ease;

 }

.row {
  display: flex;
  padding: 10px;
  text-align: center; 
  justify-content: center;
}

.name {

  color: white;
  text-decoration: none;

}
</style>