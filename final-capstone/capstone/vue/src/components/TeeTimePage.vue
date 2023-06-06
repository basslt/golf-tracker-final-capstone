<template>
  <div>
    <h2>{{ teeTime.matchName }}</h2>
    <ul>
      <li v-for="player in players" :key="player.id">
        {{ player.name }}
        <button @click="addScore(player.id)">Add Score</button>
      </li>
    </ul>
  </div>
</template>

<script>
import teeTimeService from '../services/TeeTimeService';
import ScoreService from '../services/ScoreService';
import matchPlayerService from '../services/MatchPlayerService';

export default {
  data() {
    return {
      teeTime: null,
      players: [],
    };
  },
  mounted() {
    const teeTimeId = this.$route.params.teeTimeId;
    this.fetchTeeTime(teeTimeId);
    this.fetchPlayers(teeTimeId);
  },
  methods: {
    fetchTeeTime(teeTimeId) {
      teeTimeService.getTeeTime(teeTimeId)
        .then(response => {
          this.teeTime = response.data;
        })
        .catch(error => {
          console.error('Failed to fetch tee time:', error);
        });
    },
  fetchPlayers() {
    matchPlayerService.getPlayersByMatchId(this.teeTime.matchId) // Assuming there is a method in the matchPlayerService to fetch players by match ID
      .then(response => {
        this.players = response.data;
      })
      .catch(error => {
        console.error('Failed to fetch players:', error);
      });
  },
  addScore(playerId) {
    const score = {
      teeTimeId: this.teeTime.id,
      playerId: playerId,
      // Include any other relevant properties for the score
    };

    ScoreService.createScore(score)
      .then(() => {
        console.log('Score added successfully');
        // Perform any additional actions, such as updating the score list or displaying a success message
      })
      .catch(error => {
        console.error('Failed to add score:', error);
        // Perform error handling, such as displaying an error message
      });
  },


},





};
</script>
