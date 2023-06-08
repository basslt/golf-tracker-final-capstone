<template>
  <div class="popup">
    <h3>Players:</h3>
    <ul>
      <li v-for="player in players" :key="player.id">
        {{ player.username }}:
        <input type="number" v-model="player.score" />
      </li>
    </ul>
    <button @click="submit">Submit</button>
    <button @click="close">Close</button>
  </div>
</template>

<script>
export default {
  props: {
    players: {
      type: Array,
      required: true
    }
  },
  methods: {
    close() {
      this.$emit('close');
    },
    submit() {
      const scoresData = this.players.map(player => ({
        playerId: player.id,
        score: player.score
      }));
      this.$emit('submit-scores', scoresData);
    }
  }
};
</script>

<style scoped>
.popup {
  background-color: #fff;
  border: 1px solid #ccc;
  padding: 10px;
  /* Additional styles as needed */
}

ul {
  list-style: none;
  padding: 0;
}

li {
  margin-bottom: 5px;
}

input[type="number"] {
  width: 50px;
}
</style>
