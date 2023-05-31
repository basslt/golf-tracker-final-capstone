<template>
  <div>
    <h2>Leagues</h2>
    <ul>
      <li v-for="league in leagues" :key="league.id">
        {{ league.name }}
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      leagues: []
    };
  },
  created() {
    this.fetchLeagues();
  },
  methods: {
    fetchLeagues() {
      axios.get('league')
        .then(response => {
          this.leagues = response.data;
        })
        .catch(error => {
          console.error('Error fetching leagues:', error);
        });
    }
  }
};
</script>

