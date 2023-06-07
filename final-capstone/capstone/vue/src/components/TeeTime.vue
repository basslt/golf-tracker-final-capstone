<template>
  <div>
    <div>
      <select-course :course-id="selectedCourseId" @course-selected="handleCourseSelected" />
    </div>
    <div>
      <label for="matchName">Match Name:</label>
      <input type="text" id="matchName" v-model="matchName" />
    </div>
    <div>
      <label for="teeTime">Tee Time:</label>
      <input type="datetime-local" id="teeTime" v-model="time" />
    </div>
      <div v-if="allMembersInvited" >
      <p>You have invited all members.</p>
    </div>
    <div v-if="showPlayerForm && !allMembersInvited">
      <h3>Add players to your match:</h3>
      <div v-for="member in leagueMembers" :key="member.id">
        <div>
          {{ member.username }}
          <button  @click="addPlayers(member.id)">Select</button>
        </div>
      </div>
    </div>
    <button v-if="submitButtonVisible" @click="submitForm">Submit</button>
  </div>
</template>
<script>
import userService from '../services/UserService';
import SelectCourse from './SelectCourse.vue';
import teeTimeService from '../services/TeeTimeService'
import matchPlayerService from '../services/MatchPlayerService'
export default {
  components: { SelectCourse },
  props: {
    leagueId: {
      type: Number,
      required: true
    },
  },
  data() {
    return {
      matchName: '',
      time: null,
      organizerId: this.$store.state.user.id,
      leagueMembers: [],
      selectedMembers: [],
      selectedCourseId: null,
      showPlayerForm: false,
      teeTimeId: null,
      submitButtonVisible:true,
        allMembersInvited: false,
    };
  },
  computed: {
    
  },
  methods: {
    getLeagueMembers() {
  userService
    .findUsersInLeague(this.leagueId)
    .then(response => {
      const loggedUserId = this.$store.state.user.id;
      this.leagueMembers = response.data.filter(member => member.id !== loggedUserId);
      console.log(this.leagueMembers);
    })
    .catch(error => {
      console.log(error);
    });
},
    handleCourseSelected(courseId) {
      this.selectedCourseId = courseId;
    },
    addPlayers(memberId) {
      console.log('Member ID:', memberId);
      const matchPlayer = {
        matchId: this.teeTimeId,
        playerId: memberId
        
      };
      console.log(matchPlayer)
      matchPlayerService
        .createMatchPlayer(matchPlayer)
        .then(response => {
          if (response.status === 201) {
            console.log('Created Match Players');
            const index = this.leagueMembers.findIndex(member => member.id === memberId);
          if (index !== -1) {
            this.leagueMembers.splice(index, 1);
          }
           if (this.leagueMembers.length === 0) {
            this.allMembersInvited = true;
  }
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    submitForm() {
      console.log('Match Name:', this.matchName);
      console.log('Tee Time:', this.time);
      console.log('Organizer Id: ', this.organizerId);
      console.log('courseId: ', this.selectedCourseId);
      console.log('leagueId', this.leagueId);

      const teeTime = {
        matchName: this.matchName,
        courseId: this.selectedCourseId,
        time: this.time,
        organizerId: this.organizerId,
        leagueId: this.leagueId
      };

      teeTimeService
        .createTeeTime(teeTime)
        .then(response => {
          console.log('Created Tee Time');
          console.log(response.data);
          this.teeTimeId = response.data;
          this.showPlayerForm = true;
          this.submitButtonVisible = false
        })
        .catch(error => {
          console.log(error);
        });
    }
  },
  watch: {
    leagueId: {
      immediate: true,
      handler() {
        this.getLeagueMembers();
      }
    }
  }
};
</script>