<template>
  <div>
    <h2>Tee Times:</h2>
    <div v-for="teeTime in teeTimes" :key="teeTime.teeTimeId">
      <div class="tee-time-container">
        <div class="tee-time-card">
          <div class="tee-time-info">
            <p>{{ teeTime.matchName }}</p>
            <p>{{ teeTime.time }}</p>
            <p>{{ teeTime.courseId }}</p>
          </div>
          <button @click="openPlayerList(teeTime)">View Players</button>
        </div>
   <TeeTimePopout v-if="selectedTeeTime === teeTime" :players="selectedTeeTimePlayers" @close="closePlayerList" @submit-scores="submitScores" />

      </div>
    </div>
  </div>
</template>

<script>
import TeeTimeService from '../services/TeeTimeService';
import MatchPlayerService from '../services/MatchPlayerService';
import UserService from '../services/UserService';
import ScoreService from '../services/ScoreService';
import TeeTimePopout from './TeeTimePopout.vue';

export default {
  components: {
    TeeTimePopout,
  },
  data() {
    return {
      teeTimes: [],
      selectedTeeTimePlayers: [],
      selectedTeeTime: null,
    };
  },
  methods: {
    getAllTeeTimes() {
      TeeTimeService.getAllTeeTimes()
        .then(response => {
          this.teeTimes = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    openPlayerList(teeTime) {
      this.selectedTeeTime = teeTime;
      this.getPlayers(teeTime);
    },
    closePlayerList() {
      this.selectedTeeTime = null;
      this.selectedTeeTimePlayers = [];
    },
    getPlayers(teeTime) {
      MatchPlayerService.getMatchPlayersByMatch(teeTime.teeTimeId)
        .then(response => {
          const playerIds = response.data.map(player => player.playerId);
          const getPlayerPromises = playerIds.map(playerId => this.getPlayerUsername(playerId));
          Promise.all(getPlayerPromises)
            .then(players => {
              this.selectedTeeTimePlayers = players.map(player => ({
                ...player,
                score: null
              }));
            })
            .catch(error => {
              console.log(error);
              this.selectedTeeTimePlayers = [];
            });
        })
        .catch(error => {
          console.log(error);
          this.selectedTeeTimePlayers = [];
        });
    },
    getPlayerUsername(playerId) {
      return UserService.getUsernameById(playerId)
        .then(response => {
          const username = response.data;
          console.log('Retrieved username:', username);
          return {
            id: playerId,
            username: username
          };
        })
        .catch(error => {
          console.log(error);
          return {
            id: playerId,
            username: ''
          };
        });
    },
    submitScores(scoresData) {
      // Prepare the scores data for submission to the score table
      const scorePromises = scoresData.map(scoreData => {
        const { playerId, score } = scoreData;
        const scoreEntry = {
          scoreId: playerId, // Assuming scoreId is the same as playerId
          matchId: this.selectedTeeTime.teeTimeId, // Assuming matchId is the same as teeTimeId
          playerId: playerId,
          score: score
        };
        return ScoreService.createScore(scoreEntry);
      });

      Promise.all(scorePromises)
        .then(() => {
          console.log('Scores submitted successfully');
        })
        .catch(error => {
          console.log('Error submitting scores:', error);
        });
    },
  },
  created() {
    this.getAllTeeTimes();
  },
};
</script>

<style scoped>
.tee-time-card {
  cursor: pointer;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
}

.tee-time-container {
  display: flex;
  flex-direction: column;
}
</style>
