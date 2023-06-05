<template>
  <div >
      <!-- <h2>League Members</h2> -->
      <table>
          <thead>
              <th>League Members</th>
              <th>Scores</th>
              <th>Last Round Score</th>
          </thead>
          <tbody class="table-data">
              <tr v-for="member in leagueMemberNames" v-bind:key="member.id">
                  <td>{{ member.username }}</td>
              </tr>
          </tbody>
      </table>
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
            leagueMembers: [],
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

table {
    width: 125%;
    border-collapse: collapse
}

th {
    text-align: left;
    padding-bottom: 2%;
    border-bottom: 1px solid black;
}

td {
    padding-bottom: 1%;
    padding-top: 1%;
    border-bottom: 1px solid black;
    vertical-align: center;
}

</style>