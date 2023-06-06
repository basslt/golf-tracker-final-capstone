<template>
  <div>
    <h2>Tee Times:</h2>
    <ul>
      <li v-for="teeTime in teeTimes" :key="teeTime.id">
        <div>
          <span>{{ teeTime.matchName }}</span>
          <span>{{ teeTime.time }}</span>
          <button @click="selectTeeTime(teeTime)">Select</button>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import teeTimeService from '../services/TeeTimeService';

export default {
  props: {
    leagueId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      teeTimes: []
    };
  },
  methods: {
    getAllTeeTimes() {
      teeTimeService
        .getAllTeeTimes(this.leagueId)
        .then(response => {
          this.teeTimes = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    selectTeeTime(teeTime) {
      this.$emit('tee-time-selected', teeTime);
    }
  },
  created() {
    this.getAllTeeTimes();
  }
};
</script>


