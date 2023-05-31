
<template>
  <div class = "create-league">
    <button @click="toggleForm">Create League</button>
    <league-form v-if="showForm" @create-league="handleCreateLeague"></league-form>
  </div>
</template>

<script>
import LeagueForm from './LeagueForm.vue';
import leagueService from '../services/LeagueService';
import { mapState } from 'vuex';

export default {
  components: {
    LeagueForm
  },
  data() {
    return {
      showForm: false
    };
  },
  computed: {
    ...mapState(['activeUSer'])
  },
  methods: {
    toggleForm() {
      this.showForm = !this.showForm;
    },
    createLeague(league) {
      const user = this.activeUSer;
      league.user = user;
      leagueService.addLeague(league)
        .then(createdLeague => {
          console.log('League created:', createdLeague);
          
          this.showForm = false; // Reset form visibility
        })
        .catch(error => {
          console.error('Error creating league:', error);
         
        });
    }
  }
}
</script>

<style scoped>



</style>
