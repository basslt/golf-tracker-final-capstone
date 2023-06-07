<template>
  <div>
    <TeeTime v-for="teeTime in teeTimes" :key="teeTime.id" :teeTimeId="teeTime.id" @show-users="showUserList" />
    <MatchPlayerList v-if="selectedTeeTimeId" :teeTimeId="selectedTeeTimeId" @show-score-log-form="showScoreLogForm" />
    <ScoreLogForm v-if="selectedTeeTimeId && selectedUserId" :teeTimeId="selectedTeeTimeId" :userId="selectedUserId" />
  <h2>Tee Times</h2>
    <ul>
      <li v-for="teeTime in teeTimes" :key="teeTime.teeTimeId">
        <router-link :to="'/teetimes/' + teeTime.teeTimeId">{{ teeTime.matchName }}</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import TeeTime from '@/components/TeeTime.vue';
import MatchPlayerList from '@/components/MatchPlayerList.vue';
import ScoreLogForm from '@/components/ScoreLogForm.vue';
import TeeTimeService from '@/services/TeeTimeService'; // Import the TeeTimeService

export default {
  components: {
    TeeTime,
    MatchPlayerList,
    ScoreLogForm
  },
  data() {
    return {
      teeTimes: [],
      selectedTeeTimeId: null,
      selectedUserId: null
    };
  }, 
  mounted() {
      this.fetchTeeTimes();
  },
  methods: {
    fetchTeeTimes() {
      TeeTimeService.getAllTeeTimes()
        .then(response => {
          this.teeTimes = response.data; // Assign the fetched tee times to the teeTimes data property
        })
        .catch(error => {
          console.error('Failed to fetch tee times:', error);
        });
  },
    showUserList(teeTimeId) {
      this.selectedTeeTimeId = teeTimeId;
    },
    showScoreLogForm(userId) {
      this.selectedUserId = userId;
      this.$emit('show-score-log-form', this.selectedUserId);
    }
  }
};
</script>
