<template>
  <div>
    <h2>Log Score</h2>
    <form @submit.prevent="logScore">
     
      <label for="username">Username:</label>
      <input type="text" v-model="username" required>
      <label for="score">Score:</label>
      <input type="number" v-model="score" required>

      <button type="submit">Log Score</button>
    </form>
  </div>
</template>

<script>
import ScoreService from '../services/ScoreService';
import MatchPlayerService from '../services/MatchPlayerService';
import UserService from '../services/UserService';

export default {
  props: {
    matchId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      username: '',
      score: null
    };
  },
  methods: {
    logScore() {
    
      UserService.getUserIdByUsername(this.username)
        .then(user => {
          const playerName = user.name;

          
          MatchPlayerService.updateMatchPlayer(this.matchId, playerName)
            .then(matchPlayer => {
              const playerId = matchPlayer.playerId;

              const score = {
                matchId: this.matchId,
                playerId: playerId,
                // Other score properties
              };

              ScoreService.createScore(score)
                .then(() => {
                  // Score logged successfully
                })
                .catch(error => {
                  console.error('Failed to log score:', error);
                });
            })
            .catch(error => {
              console.error('Failed to get TeeTimePlayer:', error);
            });
        })
        .catch(error => {
          console.error('Failed to retrieve playerId:', error);
        });
    }
  }
};
</script>




<style scoped>

</style>

