<template>
  <div>
      <div>
          <select-course :course-id="selectedCourseId" @course-selected="handleCourseSelected"/>
      </div>
    <div>
      <label for="matchName">Match Name:</label>
      <input type="text" id="matchName" v-model="matchName">
    </div>
    <div>
      <label for="teeTime">Tee Time:</label>
      <input type="datetime-local" id="teeTime" v-model="time">
    </div>
    <div v-for="member in leagueMembers" :key="member.id">
      <input type="checkbox" :id="'user-' + member.id" :value="member.id" v-model="selectedMembers">
      <label :for="'user-' + member.id">{{ member.username }}</label>
    </div>
    <button @click="submitForm">Submit</button>
  </div>
</template>

<script>
import userService from '../services/UserService'
import SelectCourse from './SelectCourse.vue';
import teeTimeService from '../services/TeeTimeService'

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
      playerId: '',
      organizerId: this.$store.state.user.id,
      leagueMembers: [],
      selectedMembers: [],
      selectedCourseId: null,
      
    };
  },
  computed: {
    // Your computed properties
  },
  methods: {
    getLeagueMembers() {
      userService.findUsersInLeague(this.leagueId)
        .then(response => {
          this.leagueMembers = response.data;
          console.log(this.leagueMembers);
          
        })
        .catch(error => {
          console.log(error);
        });
    },
     handleCourseSelected(courseId) {
      this.selectedCourseId = courseId;
    },
    submitForm() {
      console.log('Match Name:', this.matchName);
      console.log('Tee Time:', this.time);
      console.log('Organizer Id: ', this.organizerId);
      console.log('courseId: ', this.selectedCourseId);
      console.log('leagueId', this.leagueId)

    const teeTime = {
    matchName: this.matchName,
    courseId: this.selectedCourseId,
    time: this.time,
    organizerId: this.organizerId,
    leagueId: this.leagueId
    
    
  }
      teeTimeService.createTeeTime(teeTime).then((response) =>{
          if(response.status === 201){
              console.log("Created Tee Time")
          }
      }).catch(error => {
          console.log(error)
      })
      // You can send the match name, tee time, and selected members to the server for further processing
      // Example: Call an API endpoint to create the tee time in the database
    },
     
  },
  watch: {
    leagueId: {
      immediate: true,
      handler() {
        this.getLeagueMembers();
      }
    },
    
  }
};
</script>
