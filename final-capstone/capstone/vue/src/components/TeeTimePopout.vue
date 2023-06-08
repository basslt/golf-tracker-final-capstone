<template>
  <div class="popout-container">
    <div class="popout-card">
      <h3>{{ selectedTeeTime.matchName }}</h3>
      <p>{{ selectedTeeTime.time }}</p>
        <h2>{{ courseName }}</h2>
        <h2>Scores</h2>
      <div v-for="player in players" :key="player.id">
        <p>{{ player.username }}: {{ player.score }}</p>
      </div>
      <div class="score-input-container" v-if="isEditable">
        <div v-for="player in players" :key="player.id">
          <input type="number" v-model="player.score" :placeholder="player.username" />
        </div>
        <li v-for="score in scores" :key="score.id">{{ score }}
      <p>{{ score.playerName }}: {{ score.score }}</p>
    </li>
        <button @click="submitScores">Submit</button>
      </div>
      <div v-else>
        <button @click="close">Close</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    selectedTeeTime: {
      type: Object,
      required: true
    },
    players: {
      type: Array,
      required: true
    },
    isEditable: {
      type: Boolean,
      default: true
    },  
       courseName: {
    type: String,
    required: true
  },
      
  },
  methods: {
    submitScores() {
      const scoresData = this.players.map(player => ({
        playerId: player.id,
        score: player.score
      }));
      this.$emit('submit-scores', scoresData);
    },
    close() {
      this.$emit('close');
    }
  }
};
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

.score-input-container {
  margin-top: 10px;
}
</style>
