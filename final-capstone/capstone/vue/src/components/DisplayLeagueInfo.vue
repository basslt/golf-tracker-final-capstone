<template>
  <div>
    <!-- Display league information -->
    <h1>{{ leagueName }}</h1>
    <!-- Other league details -->

    <!-- Use the LeagueInvitationButton component -->
    <league-invitation-button :league-id="leagueId"></league-invitation-button>
  </div>
</template>

<script>
import LeagueInvitationButton from './LeagueInvitationButton.vue';
import leagueService from '../services/LeagueService'

export default {
  components: {
    LeagueInvitationButton
  },
  data() {
    return {
      leagueId: '', 
      leagueName: ''
    };
  },
  created() {
    // Fetch the league data from your data source or API
    // Assign the retrieved data to the leagueId and leagueName properties
    this.fetchLeagueData();
  },
  methods: {
    fetchLeagueData() {
      leagueService.getLeagues(this.$store.state.a)

      // Assign the retrieved data to the data properties
      this.leagueId = response.leagueId;
      this.leagueName = response.leagueName;
    }
  }
}
</script>