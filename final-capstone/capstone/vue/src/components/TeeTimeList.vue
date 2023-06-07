<template>
  <div>
    <h2>Tee Times:</h2>
    <div v-for="teeTime in teeTimes" :key="teeTime.id">
      <tee-time-card :tee-time="teeTime" :tee-time-players="getTeeTimePlayers(teeTime.id)" />
    </div>
  </div>
</template>

<script>
import teeTimeService from '../services/TeeTimeService';
import matchPlayerService from '../services/MatchPlayerService';
import TeeTimeCard from './TeeTimeCard.vue';

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
    getTeeTimePlayers(teeTimeId) {
        matchPlayerService
          .getMatchPlayersByMatch(teeTimeId)
          .then(response => {
            console.log(response.data);
            this.teeTimeId = response.data;
          })
     
  },
  created() {
    this.getAllTeeTimes();
  },
  components: {
    TeeTimeCard
  }
}
}
</script>