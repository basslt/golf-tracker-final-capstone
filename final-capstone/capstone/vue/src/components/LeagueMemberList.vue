<template>
  <div>
      <h2>League Members</h2>
      <div>
          <ul v-for="member in leagueMemberNames" v-bind:key="member.id">
              <li>{{member.username}}</li>
          </ul>
      </div>
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

<style>

</style>