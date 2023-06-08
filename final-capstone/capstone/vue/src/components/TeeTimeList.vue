<template>
  <div class="teetimes-container">
    <!-- Upcoming Tee Times -->
  <h2>Upcoming Tee Times:</h2>
<div v-for="teeTime in upcomingTeeTimes" :key="teeTime.teeTimeId">
  <div class="tee-time-card" @click="formatTeeTime(teeTime)">
    <div class="tee-time-info">
      <p>{{ teeTime.matchName }}</p>
      <p>{{ formatDateTime(teeTime.time) }}</p>
      <p>{{ teeTimeCourseNames[teeTime.teeTimeId] }}</p>
    </div>
    <div class="tee-time-buttons">
      <button v-if="!isEditable" @click="openPlayerList(teeTime)">View Scores</button>
      <button v-else-if="selectedTeeTime === teeTime" class="cancel-button" @click="closePlayerList">Cancel</button>
      <button v-else @click="openPlayerList(teeTime)">Enter Scores</button>
    </div>
  </div>
  <TeeTimePopout
    v-if="selectedTeeTime === teeTime && isEditable"
    :selectedTeeTime="teeTime"
    :players="selectedTeeTimePlayers"
    :courseName="teeTimeCourseNames[teeTime.teeTimeId]"
    @close="closePlayerList"
    @submit-scores="submitScores"
    @scores-submitted="moveToRecentTeeTimes"
  >
    <button class="cancel-button" @click="closePlayerList">Cancel</button>
  </TeeTimePopout>
  <TeeTimePopout
    v-else-if="selectedTeeTime === teeTime && !isEditable"
    :selectedTeeTime="teeTime"
    :players="teeTime.players"
    :courseName="teeTimeCourseNames[teeTime.teeTimeId]"
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
      <p>{{ teeTimeCourseNames[teeTime.teeTimeId] }}</p>
    </div>
    <div class="tee-time-buttons">
      <button class="yellow-button" @click="openPlayerList(teeTime)">View Scores</button>
    </div>
  </div>
  <TeeTimePopout
    v-if="selectedTeeTime === teeTime && !isEditable"
   :selectedTeeTime="teeTime"
    :players="selectedTeeTimePlayers"
    :courseName="teeTimeCourseNames[teeTime.teeTimeId]"
    :scores="teeTime.score"
    :isEditable="false"
    @close="closePlayerList"
  >
    <button class="cancel-button" @click="closePlayerList">Cancel</button>
  </TeeTimePopout>
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
      teeTimeCourseNames: {}
      
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
      console.log('Upcoming tee times:', this.upcomingTeeTimes);
        
      this.fetchScoresForRecentTeeTimes(); // Fetch scores for each recent tee time
    })
    .catch((error) => {
      console.log(error);
    });
},
 fetchCourseNames() {
  return CourseService.getAllCourses()
    .then((response) => {
      console.log('Fetched course names:', response.data);
      const courses = response.data;
      const courseNames = courses.map((course) => course.name);
      this.teeTimeCourseNames = courseNames;
    })
    .catch((error) => {
      console.log('Error fetching course names:', error);
      this.teeTimeCourseNames = [];
    });
},

  getCourseName(courseId) {
  return CourseService.getCourseName(courseId)
    .then((response) => {
      console.log('Course response:', response);
      const course = response.data;
      return course && course.name ? course.name : '';
    })
    .catch((error) => {
      console.log('Error fetching course name:', error);
      return '';
    });
},

 fetchScoresForRecentTeeTimes() {
  const scorePromises = this.recentTeeTimes.map((teeTime) => {
    return ScoreService.getScoresByMatch(teeTime.teeTimeId)
      .then((response) => {
        const scores = response.data;
        if (Array.isArray(teeTime.players)) {
          teeTime.players.forEach((player) => {
            const score = scores.find((s) => s.playerId === player.id);
            player.score = score ? score.score : null;
          });
          console.log('Fetched scores for tee time:', teeTime.teeTimeId);
          console.log('Updated players:', teeTime.players);
        } else {
          console.log('Tee time players array is undefined or not an array');
        }
      })
      .catch((error) => {
        console.log('Error fetching scores:', error);
      });
  });

  Promise.all(scorePromises)
    .then(() => {
      console.log('All scores fetched successfully');
    })
    .catch((error) => {
      console.log('Error fetching scores:', error);
    });
},
// submitTeeTime() {
//   if (Array.isArray(this.selectedTeeTimePlayers)) {
//     console.log('Selected tee time players:', this.selectedTeeTimePlayers);
//     // Perform further actions with the selected tee time players
//   } else {
//     console.log('Selected tee time players array is undefined or not an array');
//   }
// },

// fetchScoresForSelectedTeeTime() {
//   if (!this.selectedTeeTime || !this.selectedTeeTime.teeTimeId) {
//     console.log('Selected tee time is undefined or does not have a valid teeTimeId');
//     return;
//   }

//   if (!Array.isArray(this.selectedTeeTime.players)) {
//     console.log('Selected tee time players array is undefined or not an array');
//     return;
//   }

//   ScoreService.getScoresByMatch(this.selectedTeeTime.teeTimeId)
//     .then(response => {
//       const scores = response.data;
//       console.log('Response data:', response.data);

//       this.selectedTeeTime.players.forEach(player => {
//         console.log('Iterating through players...');

//         const score = scores.find(s => s.playerId === player.id);
//         player.score = score ? score.score : null;
//         console.log('Selected tee time players:', this.selectedTeeTime.players);
//       });
//     })
//     .catch(error => {
//       console.log('Error fetching scores:', error);
//     });

// console.log('selectedTeeTime:', this.selectedTeeTime);
// console.log('selectedTeeTimePlayers:', this.selectedTeeTimePlayers);
// console.log('teeTime:', this.teeTime);
// this.selectedTeeTime = 3;
// this.selectedTeeTimePlayers = 3;
// this.teeTime = 3;

// },

  //     hasScores(teeTime) {
  //   return teeTime.players.some(player => player.score !== null);
  // },
    openPlayerList(teeTime) {
  this.selectedTeeTime = teeTime;
  this.isEditable = this.upcomingTeeTimes.includes(teeTime);
  this.fetchScoresForRecentTeeTimes();
  this.getPlayers(teeTime);
  // this.fetchScoresForSelectedTeeTime(); 
    },

    closePlayerList() {
  this.selectedTeeTime = null;
  this.selectedTeeTimePlayers = [];
  this.isEditable = true;
  this.resetScoresForSelectedTeeTime(); // Reset scores for the selected tee time
  console.log('closePlayerList called');
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
  console.log('Retrieved players:', players);
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
// getPlayerScore(teeTimeId, playerId) {
//   const teeTime = this.recentTeeTimes.find((teeTime) => teeTime.teeTimeId === teeTimeId);
//   const player = teeTime.players.find((player) => player.id === playerId);
//   return player ? player.score : '';
// },


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
    this.fetchCourseNames();
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
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: green;
  color: white;
  padding: 10px;
  margin-bottom: 10px;
}

.tee-time-info {
  flex-grow: 1;
}

.tee-time-buttons {
  display: flex;
}

.tee-time-buttons button {
  background-color: yellow;
  margin-left: 10px;
  padding: 5px 10px;
  border: none;
  border-radius: 3px;
  color: black;
}

.cancel-button {
  background-color: transparent;
  color: white;
}

.cancel-button:hover {
  background-color: red;
}
.teetime-popout {
  position: relative;
  background-color: green;
  color: white;
  padding: 10px;
  border-radius: 5px;
}

.cancel-button {
  position: absolute;
  bottom: -30px;
  background-color: yellow;
  color: black;
  padding: 5px 10px;
  border: none;
  border-radius: 3px;
}

.yellow-button {
  background-color: yellow;
  color: black;
}

.teetime-popout {
  background-color: green;
  color: white;
  padding: 10px;
  border-radius: 5px;
}

.teetime-popout .cancel-button {
  background-color: yellow;
  color: black;
}
.teetime-popout {
  position: relative;
  background-color: green;
  color: white;
  padding: 10px;
  border-radius: 5px;
}

.cancel-button {
  background-color: yellow;
  color: black;
  padding: 5px 10px;
  border: none;
  border-radius: 3px;
  margin-top: 10px;
  width: 100%;
  font-size: 14px;
}

.yellow-button {
  background-color: yellow
}

</style>

