<template>
  <div>
  
    <TeeTimeList :league-id="leagueId" @tee-time-selected="handleTeeTimeSelected" />
    <div v-if="selectedTeeTime">
      <h2>Selected Tee Time:</h2>
      <div>
        <span>Match Name: {{ selectedTeeTime.matchName }}</span>
        <span>Time: {{ selectedTeeTime.time }}</span>
      </div>
      <div>
        <h3>Players:</h3>
        <ul>
          <li v-for="player in selectedTeeTimePlayers" :key="player.id">
            {{ player.username }}
            <!-- Add score input fields here -->
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import TeeTimeList from './TeeTimeList.vue';
import matchPlayerService from '../services/MatchPlayerService';

export default {
  components: {
    TeeTimeList
  },
  data() {
    return {
      leagueId: 1,
      selectedTeeTime: null,
      selectedTeeTimePlayers: []
    };
  },
  methods: {
    handleTeeTimeSelected(teeTime) {
      this.selectedTeeTime = teeTime;
      this.getTeeTimePlayers(teeTime.id);
    },
    getTeeTimePlayers(teeTimeId) {
      matchPlayerService
        .getMatchPlayersByMatch(teeTimeId)
        .then(response => {
          this.selectedTeeTimePlayers = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};
</script>

