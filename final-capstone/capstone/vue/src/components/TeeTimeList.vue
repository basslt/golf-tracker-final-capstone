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
      return new Promise((resolve, reject) => {
        matchPlayerService
          .getMatchPlayersByMatch(teeTimeId)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            console.log(error);
            reject(error);
          });
      });
    }
  },
  created() {
    this.getAllTeeTimes();
  },
  components: {
    TeeTimeCard
  }
};
</script>
