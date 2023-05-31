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

export default {
  components: {
    LeagueInvitationButton
  },
  data() {
    return {
      leagueId: '', // Initialize with an empty value
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
      // Fetch the league data from your data source or API
      // Example: using a mock API response
      const response = {
        leagueId: '12345',
        leagueName: 'My League'
      };

      // Assign the retrieved data to the data properties
      this.leagueId = response.leagueId;
      this.leagueName = response.leagueName;
    }
  }
}
</script>