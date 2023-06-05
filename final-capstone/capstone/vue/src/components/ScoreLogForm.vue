<template>
  <div>
    <h4>Score Log Form</h4>
    <form @submit.prevent="logScore">
      <div>
        <label for="score">Score:</label>
        <input type="number" v-model="score" required>
      </div>
      <button type="submit">Log Score</button>
    </form>
  </div>
</template>

<script>
import ScoreService from '../services/ScoreService';

export default {
      props: {
    teeTimeId: {
      type: Number,
      required: true
    },
    selectedUserId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      score: null
    };
  },
  methods: {
    logScore() {
      const teeTimeId = this.teeTimeId;
      const userId = this.userId; // Get the selected user ID from the parent component
      const score = this.score;
      // Call the appropriate service method to log the score
  ScoreService.logScore(teeTimeId, userId, score)
    .then(() => {
      // Score logged successfully, perform any necessary actions or show a success message
      console.log('Score logged successfully!');
      
      // Clear the form field after logging the score
      this.score = null;
    })
  }
  }
};
</script>




<style scoped>

</style>

