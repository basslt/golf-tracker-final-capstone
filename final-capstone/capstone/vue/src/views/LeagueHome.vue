<template>
  <div>
      <league-leaderboard />
      <league-page-nav />
  </div>
</template>

<script>
import LeagueLeaderboard from "../components/LeagueLeaderboard.vue"
import LeaguePageNav from "../components/LeaguePageNav.vue"

export default {
    components: {
        LeagueLeaderboard,
        LeaguePageNav
    }

}
</script>

<style>

</style>
