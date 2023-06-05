<template>
  <div>
    <h2>Log Score</h2>
    <form @submit="logScore">
    
      <span>{{ matchId }}</span>
      <br>
      <label for="score">Score:</label>
      <input type="number" v-model="score" required>
      <br>
      <button type="submit">Log Score</button>
    </form>
  </div>
</template>

<script>
import MatchService from '../services/MatchService';

export default {
  data() {
    return {
      matchId: '',
      score: null
    };
  },
  methods: {
    logScore() {
      const scoreData = {
        matchId: this.matchId,
        score: this.score
      };
      
      MatchService.logScore(scoreData.matchId, scoreData)
        .then(() => {
        
          console.log('Score logged');
        })
        .catch((error) => {
        
          console.error(error.message);
        });
    }
  }
};
</script>

<style scoped>

</style>

