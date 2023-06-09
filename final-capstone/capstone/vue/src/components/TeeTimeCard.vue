<template>
  <div class="tee-time-card" @click="togglePlayers">
    <div class="tee-time-info">
      <h3>{{ matchName }}</h3>
      <p>{{ teeTime.time }}</p>
    </div>
    <div v-if="showPlayers">
      <h4>Players:</h4>
      <ul>
        <li v-for="player in teeTimePlayers" :key="player.id">
          {{ player.username }}
          <input type="number" v-model="player.score" placeholder="Enter score" />
        </li>
      </ul>
      <button @click="submitScores">Submit</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    teeTime: {
      type: Object,
      required: true
    },
    teeTimePlayers: {
      type: Array,
      required: true
    },
    matchName: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      showPlayers: false
    };
  },
  methods: {
    togglePlayers() {
      this.showPlayers = !this.showPlayers;
    },
    submitScores() {
      // Handle the submission of scores
      // You can access the scores from the `teeTimePlayers` array
      // and perform the necessary logic to update the leaderboard
      // Once the scores are submitted, you can hide the tee-time card
      this.showPlayers = false;
    }
  }
};
</script>

<style scoped>
.tee-time-card {
  background-color: lightgray;
  padding: 1rem;
  margin-bottom: 1rem;
  cursor: pointer;
}

.tee-time-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

ul {
  padding-left: 1rem;
}
</style>
