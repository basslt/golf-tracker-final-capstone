<template>
  <div class = "create-league">
    <button @click="toggleForm">Create League</button>
    <league-form v-if="showForm" @create-league="handleCreateLeague"></league-form>
  </div>
</template>

<script>
import LeagueForm from './LeagueForm.vue';

export default {
  components: {
    LeagueForm
  },
  data() {
    return {
      showForm: false
    };
  },
  methods: {
    toggleForm() {
      this.showForm = !this.showForm;
    },
    handleCreateLeague(leagueName) {
      // Perform necessary actions with the leagueName
      console.log(`Creating league: ${leagueName}`);
      // Reset form visibility
      this.showForm = false;
    }
  }
}
</script>
<style scoped>


</style>
