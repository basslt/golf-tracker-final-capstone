<template>
  <div class="teetimes-container">
      <h2>Upcoming Matches</h2>
      <div class="upcoming">
        <!-- <div class="tee-time-card">
            <div class="tee-time-info"> -->
                <upcoming-tee-time-card v-for="teeTime in upcomingTeeTimes" v-bind:key="teeTime.id" :teeTime="teeTime" v-on:tee-time-click="showUpcomingTeeTime(teeTime)"/> 
            <!-- </div>
        </div> -->
      </div>
  </div>
</template>

<script>
import teeTimeService from '../services/TeeTimeService';
import UpcomingTeeTimeCard from '../components/UpcomingTeeTimeCard.vue'

export default {
    components: {
        UpcomingTeeTimeCard
    },
    props: {
        leagueId: {
            type: Number,
            required: true
        }
    },
    data() {
        return {
            upcomingTeeTimes: [],
        }
    },
    methods: {
        getUpcomingTeeTimes() {
            teeTimeService.getUpcomingTeeTimesByLeague(this.leagueId).then( (response) => {
                if (response.status === 200) {
                    console.log(response.status);
                    this.upcomingTeeTimes = response.data;
                }
            });
        },
        showUpcomingTeeTime(teeTime) {
            this.$emit('upcoming-tee-time-click', teeTime);
        }

    },
    created() {
        this.getUpcomingTeeTimes();
    },
    watch: {
    leagueId: {
      immediate: true,
      handler() {
        this.getUpcomingTeeTimes();
      }
    }
  }
}
</script>

<style scoped>

.recent, .upcoming {
  height: 300px;
  overflow: auto; 
  border: #059262;
  /* border-radius: 10px; */
  box-shadow: 10px 5px 10px lightgray;
}

.teetimes-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 20px;
}

h2 {
  margin: 10px;
}



</style>