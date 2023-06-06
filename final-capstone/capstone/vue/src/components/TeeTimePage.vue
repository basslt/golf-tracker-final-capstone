<template>
  <div>
    <h3>Created Tee Times:</h3>
    <ul>
      <li v-for="teeTime in teeTimes" :key="teeTime.id">
        Match Name: {{ teeTime.matchName }} - Tee Time: {{ teeTime.time }}
      </li>
    </ul>
  </div>
</template>

<script>
import teeTimeService from '../services/TeeTimeService';

export default {
  data() {
    return {
      teeTimes: []
    };
  },
  mounted() {
    this.fetchTeeTimes();
  },
  methods: {
    fetchTeeTimes() {
      teeTimeService
        .getAllTeeTimes() // Adjust this method based on your implementation to fetch all tee times
        .then(response => {
          this.teeTimes = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};
</script>

