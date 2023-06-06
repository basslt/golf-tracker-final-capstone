<template>
  <div>
    <h2>Score Log</h2>
    <form @submit.prevent="submitScore">
      <div v-for="player in players" :key="player.id">
        <label>{{ player.name }}</label>
        <input type="number" v-model="scores[player.id]" required>
      </div>
      <button type="submit">Submit Score</button>
    </form>
  </div>
</template>

<script>
import scoreService from '../services/ScoreService';
import matchPlayerService from '../services/MatchPlayerService';

export default {
  data() {
    return {
      teeTimeId: null,
      playerId: null,
      players: [],
      scores: {},
    };
  },
  mounted() {
    const teeTimeId = this.$route.params.teeTimeId;
    const playerId = this.$route.query.playerId;
    this.teeTimeId = teeTimeId;
    this.playerId = playerId;
    this.fetchPlayers();
  },
 methods: {
  fetchPlayers() {
    matchPlayerService.getPlayersByMatchId(this.teeTime.matchId) // Assuming there is a method in the matchPlayerService to fetch players by match ID
      .then(response => {
        this.players = response.data;
      })
      .catch(error => {
        console.error('Failed to fetch players:', error);
      });
  },
    submitScore() {
      const score = {
        teeTimeId: this.teeTimeId,
        playerId: this.playerId,
        score: this.scores[this.playerId],
      };
      // Make an API call to submit the score
      scoreService.createScore(score)
        .then(() => {
          console.log('Score submitted successfully');
          // Redirect to the tee time page or perform any other necessary action
        })
        .catch((error) => {
          console.error('Failed to submit score:', error);
        });
    },
  },
};
</script>






<style scoped>
div {
display : block;
}
</style>

