<template>
  <div class = "league-form">
    <form @submit.prevent="submitForm">
      <input type="text" v-model="leagueName" placeholder="Enter league name" required>
      <button type="submit">Create</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      leagueName: ''
    };
  },
  methods: {
    submitForm() {
      this.$emit('create-league', this.leagueName);
      this.leagueName = ''; // Reset form field
    }
  }
}
</script>

<style scoped>
.league-form {
  display: flex;
  flex-direction: column;
  margin-top: 10px;
}

.league-form input[type="text"] {
  padding: 5px;
  margin-bottom: 10px;
}

.league-form button {
  padding: 5px;
  background-color: #0077ff;
  color: #fff;
  border: none;
  cursor: pointer;
}

.league-form button:hover {
  background-color: #0056b3;
}
</style>