<template>
  <div>
    <h1>Match View</h1>
    <h2>Match Information</h2>
    
    <p>{{ matchId }}</p>
     <ScoreLogForm :matchId="matchId" />

    
  </div>
</template>

<script>
import ScoreLogForm from '../components/ScoreLogForm.vue';

export default {
  components: {
    ScoreLogForm
  },
  data() {
    return {
      matchId: ''
    };
  }
};
</script>

<style scoped>

</style>
