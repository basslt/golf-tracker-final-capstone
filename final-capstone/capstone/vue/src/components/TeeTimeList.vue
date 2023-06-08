<template>
  <div class="teetimes-container">
    <!-- Upcoming Tee Times -->
    <h2>Upcoming Tee Times:</h2>
    <div v-for="teeTime in upcomingTeeTimes" :key="teeTime.teeTimeId">
      <div class="tee-time-card" @click="formatTeeTime(teeTime)">
        <div class="tee-time-info">
          <p>{{ teeTime.matchName }}</p>
          <p>{{ formatDateTime(teeTime.time) }}</p>
          <p>{{ teeTimeCourseNames[teeTime.courseId] }}</p> <!-- Updated line -->
        </div>
        <div class="tee-time-buttons">
          <button v-if="!isEditable" @click="openPlayerList(teeTime)">View Scores</button>
          <button v-else-if="selectedTeeTime === teeTime" @click="closePlayerList">Cancel</button>
          <button v-else @click="openPlayerList(teeTime)">Enter Scores</button>
        </div>
      </div>
      <TeeTimePopout
        v-if="selectedTeeTime === teeTime && isEditable"
        :selectedTeeTime="teeTime"
        :players="selectedTeeTimePlayers"
        @close="closePlayerList"
        @submit-scores="submitScores"
        @scores-submitted="moveToRecentTeeTimes"
      />
      <TeeTimePopout
        v-else-if="selectedTeeTime === teeTime && !isEditable"
        :selectedTeeTime="teeTime"
        :players="teeTime.players"
        :isEditable="false"
        @close="closePlayerList"
      />
    </div>

    <!-- Recent Tee Times -->
    <h2>Recent Tee Times:</h2>
    <div v-for="teeTime in recentTeeTimes" :key="teeTime.teeTimeId">
      <div class="tee-time-card" @click="formatTeeTime(teeTime)">
        <div class="tee-time-info">
          <p>{{ teeTime.matchName }}</p>
          <p>{{ formatDateTime(teeTime.time) }}</p>
          <p>{{ teeTimeCourseNames[teeTime.courseId] }}</p> <!-- Updated line -->
        </div>
        <button @click="openPlayerList(teeTime)">View Scores</button>
        <div v-if="selectedTeeTime === teeTime && !isEditable">
          <div v-for="player in teeTime.players" :key="player.id">
            <p>{{ player.username }}: {{ getPlayerScore(teeTime.teeTimeId, player.id) }}</p>
          </div>
        </div>
      </div>
      <TeeTimePopout
        v-if="selectedTeeTime === teeTime && !isEditable"
        :selectedTeeTime="teeTime"
        :players="teeTime.players"
        :isEditable="false"
        @close="closePlayerList"
      />
    </div>
  </div>
</template>




<script>
import TeeTimeService from '../services/TeeTimeService';
import MatchPlayerService from '../services/MatchPlayerService';
import UserService from '../services/UserService';
import ScoreService from '../services/ScoreService';
import TeeTimePopout from './TeeTimePopout.vue';
import CourseService from '../services/CourseService';

export default {
  components: {
    TeeTimePopout,
  },
  data() {
    return {
      upcomingTeeTimes: [],
      recentTeeTimes: [],
      selectedTeeTimePlayers: [],
      selectedTeeTime: null,
      isEditable: true,
      teeTimeCourseNames: {},
    };
  },
  methods: {
        formatDateTime(timestamp) {
      const date = new Date(timestamp);
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
      const time = date.toLocaleString('default', { hour: 'numeric', minute: 'numeric' });
    

      return `${month}/${day} ${time}`;
    },
    formatTeeTime(teeTime) {
      teeTime.time = this.formatDateTime(teeTime.time);
    },
  getAllTeeTimes() {
  TeeTimeService.getAllTeeTimes()
    .then((response) => {
      const allTeeTimes = response.data;
      const currentDate = new Date().toISOString();

      this.upcomingTeeTimes = allTeeTimes.filter(
        (teeTime) => teeTime.time > currentDate
      );

      this.recentTeeTimes = allTeeTimes.filter(
        (teeTime) => teeTime.time <= currentDate
      );

      this.fetchCourseNames();
      this.fetchScoresForRecentTeeTimes(); // Fetch scores for each recent tee time
    })
    .catch((error) => {
      console.log(error);
    });
},
fetchCourseNames() {
  const courseIds = this.upcomingTeeTimes.map((teeTime) => teeTime.courseId);
  const coursePromises = courseIds.map((courseId) => this.getCourseName(courseId));
  Promise.all(coursePromises)
    .then((courseNames) => {
      this.upcomingTeeTimes.forEach((teeTime, index) => {
        this.$set(this.teeTimeCourseNames, teeTime.teeTimeId, courseNames[index]);
      });
    })
    .catch((error) => {
      console.log(error);
    });
},

    getCourseName(courseId) {
  return CourseService.getCourseName(courseId)
    .then(response => {
      const course = response.data;
      const courseName = course && course.name ? course.name : '';
      this.$set(this.teeTimeCourseNames, courseId, courseName);
    })
    .catch(error => {
      console.log(error);
    });
},

  fetchScoresForRecentTeeTimes() {
  this.recentTeeTimes.forEach((teeTime) => {
    ScoreService.getScoresByMatch(teeTime.teeTimeId)
      .then((response) => {
        const scores = response.data;
        if (teeTime.players) {
          teeTime.players.forEach((player) => {
            const score = scores.find((s) => s.playerId === player.id);
            player.score = score ? score.score : null;
          });
        }
      })
      .catch((error) => {
        console.log('Error fetching scores:', error);
      });
  });
},



fetchScoresForSelectedTeeTime() {
  ScoreService.getScoresByMatch(this.selectedTeeTime.teeTimeId)
    .then(response => {
      const scores = response.data;
      this.selectedTeeTime.players.forEach(player => {
        const score = scores.find(s => s.playerId === player.id);
        player.score = score ? score.score : null;
      });
    })
    .catch(error => {
      console.log('Error fetching scores:', error);
    });
},

      hasScores(teeTime) {
    return teeTime.players.some(player => player.score !== null);
  },
    openPlayerList(teeTime) {
  this.selectedTeeTime = teeTime;
  this.isEditable = this.upcomingTeeTimes.includes(teeTime);
  if (!this.isEditable) {
    this.fetchScoresForRecentTeeTimes();
  }
  this.getPlayers(teeTime);
  this.fetchScoresForSelectedTeeTime(); // Fetch scores for the selected tee time
},

    closePlayerList() {
  this.selectedTeeTime = null;
  this.selectedTeeTimePlayers = [];
  this.isEditable = true;
  this.resetScoresForSelectedTeeTime(); // Reset scores for the selected tee time
},

resetScoresForSelectedTeeTime() {
  if (this.selectedTeeTime) {
    this.selectedTeeTime.players.forEach(player => {
      player.score = null;
    });
  }
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
      // Update the scores in the recent tee times
      const recentTeeTime = this.recentTeeTimes.find(tt => tt.teeTimeId === this.selectedTeeTime.teeTimeId);
      if (recentTeeTime) {
        scoresData.forEach(scoreData => {
          const { playerId, score } = scoreData;
          const player = recentTeeTime.players.find(player => player.id === playerId);
          if (player) {
            player.score = score;
          }
        });
      }
      this.moveToRecentTeeTimes(); // Move tee time to recent tee times
      this.closePlayerList(); // Close the popout
    })
    .catch(error => {
      console.log('Error submitting scores:', error);
    });
},



  updateScores(scoresData) {
  scoresData.forEach(scoreData => {
    const { playerId, score } = scoreData;
    const teeTime = this.recentTeeTimes.find(tt => tt.teeTimeId === this.selectedTeeTime.teeTimeId);
    if (teeTime) {
      const player = teeTime.players.find(player => player.id === playerId);
      if (player) {
        player.score = score;
      }
    }
  });
},
getPlayerScore(teeTimeId, playerId) {
  const teeTime = this.recentTeeTimes.find((teeTime) => teeTime.teeTimeId === teeTimeId);
  const player = teeTime.players.find((player) => player.id === playerId);
  return player ? player.score : '';
},


    removeTeeTime() {
      this.selectedTeeTime = null;
      this.selectedTeeTimePlayers = [];
    },
    moveToRecentTeeTimes() {
      const teeTimeIndex = this.upcomingTeeTimes.findIndex(
        (teeTime) => teeTime.teeTimeId === this.selectedTeeTime.teeTimeId
      );
      if (teeTimeIndex !== -1) {
        const teeTime = this.upcomingTeeTimes.splice(teeTimeIndex, 1)[0];
        this.recentTeeTimes.unshift(teeTime);
      }
      this.closePlayerList();
    }
  },
 
  created() {
    this.getAllTeeTimes();
  },
};
</script>

<style scoped>

.teetimes-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.tee-time-card {
  cursor: pointer;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  background-color: green;
  color: white;
  font-weight: bold;
}

.tee-time-info {
  margin-bottom: 10px;
}

.tee-time-info p {
  margin: 0;
}

.tee-time-buttons button {
  background-color: yellow;
}
</style>

