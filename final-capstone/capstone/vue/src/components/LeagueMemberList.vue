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
import leagueMembership from '../services/LeagueMembership'
import userService from '../services/UserService'

export default {
    data() {
        return {
            leagueMembers: [],
            leagueMemberNames: []
        }
    },
    methods: {
        getLeagueMembers() {
            leagueMembership.getAllLeagueMemberships().then( (response) => {
                this.leagueMembers = response.data;
                this.leagueMembers.forEach( (member) => {
                    userService.getUserById(member.userId).then( (response) => {
                        this.leagueMemberNames.push(response.data);
                    })
                });
            });
        }
    },
    created() {
        this.getLeagueMembers();
    },
    computed: {

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