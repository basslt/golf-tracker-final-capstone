<template>
  <div class="tee-time-card" @click="showPlayers = !showPlayers">
    <div class="tee-time-info">
      <h3>{{ teeTime.matchName }}</h3>
      <p>{{ teeTime.time }}</p>
    </div>
    <div v-if="showPlayers">
      <h4>Players:</h4>
      <ul>
        <li v-for="player in teeTimePlayers" :key="player.id">
          {{ player.username }}
          <!-- Add score input fields here -->
        </li>
      </ul>
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
      default: () => []
    }
  },
  data() {
    return {
      showPlayers: false
    };
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
