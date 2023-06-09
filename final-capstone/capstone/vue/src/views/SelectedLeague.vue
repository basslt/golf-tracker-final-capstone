<template>
  <div class="background">
    <header>
      <hamburger-menu />
    </header>
    <div class="main">
     
      <main class="content">
        <div class="grid-container">
          <div class="column">
            <div class="left-content">
              <create-league v-if="this.$store.state.showCreateForm" />
              <!-- <tee-time-list :teeTime="teeTime" :teeTimePlayers="teeTimePlayers" :matchName="matchName" />
               <TeeTimeCard :teeTime="teeTime" :teeTimePlayers="teeTimePlayers" :matchName="matchName" />
              <tee-time-card /> -->

              <past-tee-time-list v-bind:league-id="leagueId" @past-tee-time-click="showPastDetails"/>
              <past-tee-time-details v-if="selectedPastTeeTime" :teeTime="selectedPastTeeTime" @close="closePastDetails" />
              <upcoming-tee-time-list v-bind:league-id="leagueId" @upcoming-tee-time-click="showUpcomingDetails"/>
              <upcoming-tee-time-details v-if="selectedUpcomingTeeTime" :teeTime="selectedUpcomingTeeTime" @close="closeUpcomingDetails"/>

              <div class="buttons">
                <button class="new-tee-time-button" @click="showTeeTimeCreateForm=true">Schedule Tee Time <i class="fa-solid fa-calendar-plus" style="color: #059262;"></i></button>
                <tee-time-form v-if="showTeeTimeCreateForm" v-bind:league-id="leagueId" @close="showTeeTimeCreateForm=false" />
              </div>

            </div>
          </div>
          <div class="column">
            <div class="leaderboard">
              <league-leaderboard v-bind:league-id="leagueId"  />
            </div>
          </div>
          <div class="column">
            <div class="right-content">
              <league-member-list v-bind:league-id="leagueId" />
              <button class="league-invite-form" @click="showLeagueInviteForm=true">Add New Members <i class="fa-solid fa-user-plus" style="color: #059262;"></i></button>
                <league-invite-form v-if="showLeagueInviteForm" v-bind:league-id="leagueId" @close="showLeagueInviteForm=false" />
            </div>
          </div>
        </div>
      </main>
    
    </div>
  </div>
<!-- 


  <div>
        <header>
            <hamburger-menu />
        </header>
        <div class="main">
        <main class="content">
            <div class="leaderboard">
            <league-leaderboard v-bind:league-id="leagueId"/>
            <create-league v-if="this.$store.state.showCreateForm"/>
            <div class="parent-container">
            <div class="left-column">
            
            </div>
            </div>
            <tee-time v-bind:league-id="leagueId"/>
            <TeeTimeCard :teeTime="teeTime" :teeTimePlayers="teeTimePlayers" :matchName="matchName" />

            <button class="new-tee-time-button" @click="showTeeTimeCreateForm=true">Schedule Tee Time</button>
            <tee-time-form v-if="showTeeTimeCreateForm" v-bind:league-id="leagueId" @close="showTeeTimeCreateForm=false"/>
            <button class="league-invite-form" @click="showLeagueInviteForm=true">Invite Members</button>
            <league-invite-form v-if="showLeagueInviteForm" v-bind:league-id="leagueId" @close="showLeagueInviteForm=false" />
            <league-member-list v-bind:league-id="leagueId"/>
            </div>
        </main>
      
      </div>
  </div> -->
</template>

<script>
import LeagueLeaderboard from "../components/LeagueLeaderboard.vue"
import HamburgerMenu from '../components/HamburgerMenu.vue'
import LeagueInviteForm from '../components/LeagueInviteForm.vue'
import CreateLeague from '../components/CreateLeague.vue'
import TeeTimeForm from '../components/TeeTimeForm.vue'
// import TeeTimeList from '../components/TeeTimeList.vue'
// import TeeTimeCard from '../components/TeeTimeCard.vue'
import LeagueMemberList from '../components/LeagueMemberList.vue'
import UpcomingTeeTimeList from '../components/UpcomingTeeTimeList.vue';
import UpcomingTeeTimeDetails from '../components/UpcomingTeeTimeDetails.vue';
import PastTeeTimeList from '../components/PastTeeTimeList.vue';
import PastTeeTimeDetails from '../components/PastTeeTimeDetails.vue';
// import userService from '../services/UserService';
// import leaderboardService from '../services/Leaderboard';
// import scoreService from '../services/ScoreService';


export default {
    components: {
        LeagueLeaderboard,
        HamburgerMenu,
        LeagueInviteForm,
        CreateLeague,
        TeeTimeForm,
        // TeeTimeList,
        // TeeTimeCard,
        LeagueMemberList,
        UpcomingTeeTimeList,
        UpcomingTeeTimeDetails,
        PastTeeTimeList,
        PastTeeTimeDetails
    },
    data() {
        return {
            leagueId: null,
            showTeeTimeCreateForm: false,
            showLeagueInviteForm: false,
            selectedUpcomingTeeTime: null,
            selectedPastTeeTime: null,
            // leagueMembers: [],
            // memberScores: [],
            // memberNameScores: [],
            // latestMemberScore: []
        }
    },
    methods: {
      showUpcomingDetails(teeTime) {
        this.selectedUpcomingTeeTime = teeTime;
      },
      closeUpcomingDetails() {
        this.selectedUpcomingTeeTime = null;
      },
      showPastDetails(teeTime) {
        this.selectedPastTeeTime = teeTime;
      },
      closePastDetails() {
        this.selectedPastTeeTime = null;
        this.getLeagueMembers();
        this.getOrderedLeaderboard(); 
      },

      // getLeagueMembers() {
      //     userService.findUsersInLeague(this.leagueId).then( (response) => {
      //       console.log(response.data)
      //         this.leagueMembers = response.data;
      //         this.getLatestScore(); 
      //     })
      //     .catch(error => {
      //         console.log(error);
      //     });
      // },
      // getOrderedLeaderboard() {
      //     leaderboardService.getOrderedLeaderboard(this.leagueId).then( (response) => {
      //       console.log(response.data);
      //       this.memberScores = response.data;
      //     }).catch(error => {
      //        console.log(error);
      //     });
      // },

      // combineUsersScores() {
      //   if (this.leagueMembers.length > 0 && this.memberScores.length > 0) {
      //   this.memberNameScores = this.memberScores.map( (score) => {
      //     const member = this.leagueMembers.find(member => member.id === score.userId);
      //     return {
      //       username: member.username,
      //       totalScore: score.totalScore,
      //       latestScore: this.latestMemberScore.find( 
      //         latestScore => latestScore.playerId === score.userId
      //       ).score
      //     };
      //   });
      //   console.log(this.memberNameScores);
      //   }
      // },
      // getLatestScore() {
      //   const requests = this.leagueMembers.map(member => {
      //     return scoreService.getLatestPlayerScore(this.leagueId, member.id)
      //     .then(response => response.data)
      //     .catch(error => {
      //       console.log(error);
      //       return null;
      //     });
      //   });

      //   Promise.all(requests).then(scores => {
      //     this.latestMemberScore = scores.filter(score => score !== null);
      //     this.combineUsersScores();
      //   })
      //   .catch(error => {
      //     console.log(error);
      //   })
      // }


    },
    created() {
        this.leagueId = parseInt(this.$route.params.id);
        // this.getLeagueMembers();
        // this.getOrderedLeaderboard();    
    }
}
</script>

<style scoped>

.content {
  display: flex;
  flex-direction: column;
  width: 100%;
  background: lightgray;
  
}

.grid-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
}

.column {
  padding: 20px;
  background-color: lightgray;
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
  background: #ffe837;
  padding: 10px 20px;
  border: none;
  border-radius: 20px;
  color: white;
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 20px;
}

.new-tee-time-button:hover,
.league-invite-form:hover{
 cursor: pointer;
background-color: rgb(255, 255, 20);
 
}

.league-invite-form{
  padding: 10px;
  margin-top: 25px;
}

.left-content{
  display: flex;
  flex-direction: column;

}

.right-content{
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 10px;

}

.buttons {
  display: flex;
  justify-content: center;
}



</style>