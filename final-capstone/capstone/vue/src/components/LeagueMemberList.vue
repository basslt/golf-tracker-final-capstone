<template>
<div class="container">
  <div class="background">
      <img class="background" src="../assets/Empty_Leaderboard.jpg" alt="">
  </div>
  <div class="table-container">
      <table class="leaderboard">
          <thead>
              <tr>
                <th>Username</th>
                <th>Scores</th>
                <th>Last Round</th>
              </tr>
          </thead>
          <tbody class="table-data">
              <tr v-for="member in leagueMemberNames" v-bind:key="member.id">
                  <td>{{ member.username }}</td>
                  <td class="average">78</td>
                  <td class="last-round">80</td>
              </tr>
          </tbody>
      </table>
  </div>
</div>
</template>

<script>
import userService from '../services/UserService'

export default {
    props: {
        leagueId: {
            type: Number,
            required: true
        }
    },
    data() {
        return {
            //leagueMembers: [],
            leagueMemberNames: [],
        }
    },
    methods: {
        getLeagueMembers() {
            userService.findUsersInLeague(this.leagueId).then( (response) => {
                this.leagueMemberNames = response.data;
            })
            .catch(error => {
                console.log(error);
            })
        }

    },
    watch: {
        leagueId: {
            immediate: true,
            handler() {
                this.getLeagueMembers();
            }
        }
    }

}
</script>

<style scoped>

.container {
    position: relative;
    width: 500px;
    height: 500px;
}

.background {
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-size: cover;
    background-position: center;
}

.table-container {
    position: absolute;
    top: 22%;
    left: 11%;
    width: 62%;
}


table {
    width: 125%;
    border-collapse: collapse;
    text-align: center;
}

th {
    padding-bottom: 2%;
    border-bottom: 1px solid black;
    text-align: center;
}


td {
    padding-bottom: 1%;
    padding-top: 1%;
    border-bottom: 1px solid black;
    vertical-align: center;
}

.average {
    border-left: 1px solid black;
    width: 25%;
}

.last-round {
    border-left: 1px solid black;
    width: 25%;
}

</style>