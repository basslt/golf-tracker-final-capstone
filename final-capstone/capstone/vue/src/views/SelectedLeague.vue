<template>
  <div class="background">
    <header>
      <hamburger-menu />
    </header>
    <div class="main">
      <!-- <body> -->

      <main class="content">
        <div class="grid-container">
          <div class="column">
            <div class="left-content">
              <create-league v-if="this.$store.state.showCreateForm" />
              <tee-time-list />
              <tee-time-card />
              <div class="buttons">
                <button class="new-tee-time-button" @click="showTeeTimeCreateForm=true">Schedule Tee Time</button>
                <tee-time-form v-if="showTeeTimeCreateForm" v-bind:league-id="leagueId" @close="showTeeTimeCreateForm=false" />
                <button class="league-invite-form" @click="showLeagueInviteForm=true">Add New Members</button>
                <league-invite-form v-if="showLeagueInviteForm" v-bind:league-id="leagueId" @close="showLeagueInviteForm=false" />
              </div>
            </div>
          </div>
          <div class="column">
            <div class="leaderboard">

              <league-leaderboard v-bind:league-id="leagueId" />
            </div>
          </div>
          <div class="column">
            <div class="right-content">
              <league-member-list v-bind:league-id="leagueId" />
            </div>
          </div>
        </div>
      </main>
      <!-- </body> -->
    </div>
  </div>
</template>

<script>
import LeagueLeaderboard from "../components/LeagueLeaderboard.vue"
import HamburgerMenu from '../components/HamburgerMenu.vue'
import LeagueInviteForm from '../components/LeagueInviteForm.vue'
import CreateLeague from '../components/CreateLeague.vue'
import TeeTimeForm from '../components/TeeTimeForm.vue'
import TeeTimeList from '../components/TeeTimeList.vue'
import TeeTimeCard from '../components/TeeTimeCard.vue'
import LeagueMemberList from '../components/LeagueMemberList.vue'


export default {
    components: {
        LeagueLeaderboard,
        HamburgerMenu,
        LeagueInviteForm,
        CreateLeague,
        TeeTimeForm,
        TeeTimeList,
        TeeTimeCard,
        LeagueMemberList
    },
    data() {
        return {
            leagueId: null,
            showTeeTimeCreateForm: false,
            showLeagueInviteForm: false,
        }
    },
    created() {
        this.leagueId = parseInt(this.$route.params.id);
    }

}

</script>

<style scoped>
.content {
  display: flex;
  flex-direction: column;
  width: 100%;
  
}

.grid-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
}

.column {
  padding: 20px;
  background-color: #ffffff;
  border-radius: 10px;
  display: flex;
  justify-content: center;
}

.leaderboard {
  display: flex;
  align-items: flex-start;
  justify-content: center;
  padding-top: 100px;
  height: 700px;

}

.new-tee-time-button,
.league-invite-form {
  background: #ffed59;
  padding: 10px 20px;
  border: none;
  border-radius: 20px;
  color: white;
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 20px;
}

</style>