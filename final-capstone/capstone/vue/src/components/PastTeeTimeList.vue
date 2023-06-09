<template>
    <div class="teetimes-container">
        <h2>Previous Matches</h2>
        <div class="previous">
            <past-tee-time-card v-for="teeTime in pastTeeTimes" v-bind:key="teeTime.id" :teeTime="teeTime" v-on:tee-time-click="showPastTeeTime(teeTime)" />
        </div>
    </div>
</template>

<script>
import teeTimeService from '../services/TeeTimeService';
import PastTeeTimeCard from '../components/PastTeeTimeCard.vue';

export default {
    components: {
        PastTeeTimeCard
    },
    props: {
        leagueId: {
            type: Number,
            required: true
        }
    },
    data() {
        return {
            pastTeeTimes: []
        }
    },
    methods: {
        getPastTeeTimes() {
            teeTimeService.getPastTeeTimesByLeague(this.leagueId).then( (response) => {
                if (response.status === 200) {
                    this.pastTeeTimes = response.data;
                }
            });
        },
        showPastTeeTime(teeTime) {
            this.$emit('past-tee-time-click', teeTime);
        }
    },
    created() {
        this.getPastTeeTimes();
    }
}
</script>

<style scoped>

.previous {
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