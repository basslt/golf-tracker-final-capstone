<template>
  <div class="popout-container">
      <div class="popout-card">  
          <h3>{{teeTime.matchName}}</h3>
          <p>{{ formatDateTime(teeTime.time)}} </p>
          <h2>{{courseName}}</h2>
        <div v-for="player in playerScores" v-bind:key="player.id">
            <p>{{player.username}}: {{player.score}}</p>
        </div>
        <div class="score-input-container" v-if="isEditable">
            <h4>Enter Scores</h4>
            <div v-for="player in roundPlayers" :key="player.id">
                <label for="score">{{player.username}}: </label>
                <input type="number" id="score" v-model="player.score" :placeholder="player.username" />
            </div>
            <!-- <li v-for="score in scores" :key="score.id">{{ score }}
                <p>{{ score.playerName }}: {{ score.score }}</p>
            </li> -->
            <button @click="submitScores">Submit</button>
      </div>
      <span class="close-button" @click="closeDetails"> <i class="fa-solid fa-xmark" style="color: #000000;"></i></span> 
        <!-- <button @click="closeDetails">Close</button> -->
      </div>
  </div>
</template>

<script>
import userService from '../services/UserService';
import courseService from '../services/CourseService';
import scoreService from '../services/ScoreService';

export default {
    props: {
        teeTime: {
            type: Object,
            required: true
        },
        course: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            roundPlayers: [],
            courseName: null,
            scores: [],
            playerScores: [],
            isEditable: false

        }
    },
    methods: {
        getAllRoundPlayers() {
            userService.findUsersInMatch(this.teeTime.teeTimeId).then( (response) => {
                console.log('roundPlayers: ', response.data);
                this.roundPlayers = response.data;
            });
        },
        getCourseName() {
            courseService.getCourseName(this.teeTime.courseId).then( (response) => {
                this.courseName = response;
            });
        },
        getMatchScores() {
            scoreService.getScoresByMatch(this.teeTime.teeTimeId).then( (response => {
                this.scores = response.data;
                console.log('scores', response.data);
                if (this.scores.length > 0) {
                    this.combineUserScore();
                } else {
                    this.isEditable = true;
                }
            }));
        },
        combineUserScore() {
            this.playerScores = this.scores.map( score => {
                const player = this.roundPlayers.find(player => player.id === score.playerId);
                return {
                    username: player.username,
                    score: score.score
                };
            });
        },
        submitScores() {
            const scoresData = this.players.map(player => ({
                playerId: player.id,
                score: player.score
            }));
            this.$emit('submit-scores', scoresData);
        },
        closeDetails() {
            this.$emit('close');
        },
        formatDateTime(timestamp) {
            const date = new Date(timestamp);
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            const time = date.toLocaleString('default', { hour: 'numeric', minute: 'numeric' });
            return `${month}/${day} ${time}`;
        },

    },
    created() {
        this.getAllRoundPlayers();
        this.getCourseName();
        this.getMatchScores();
    }
}

</script>

<style scoped>

.popout-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.popout-card {
  background-color: #fff;
  padding: 20px;
  border-radius: 4px;
}

</style>