<template>
  <div class="background">
    <div class="container">
      <div class="main">
         <h2>Selected Course</h2>
      <p>{{ selectedCourse.data.name }} - {{ selectedCourse.data.city }}, {{ selectedCourse.data.state }}</p>
      </div>
        <div class="form-group">
            <div class="match">
              <label for="matchName">Match Name:</label>
              <input type="text" id="matchName" v-model="matchName" />
            </div>
            <div class="time">
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
            <button type="button" v-on:click="closeForm">Close</button>
          </div>
          </div>
      </div>
</template>

<script>
import userService from '../services/UserService';
// import SelectCourse from './SelectCourse.vue';
import teeTimeService from '../services/TeeTimeService'
import matchPlayerService from '../services/MatchPlayerService'

export default {
  components: {
    // SelectCourse
  },
  props: {
    leagueId: {
      type: Number,
      required: true
    },
    selectedCourse: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      matchName: '',
      time: null,
      organizerId: this.$store.state.user.id,
      leagueMembers: [],
      selectedMembers: [],
      // selectedCourseId: null,
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
    // handleCourseSelected(courseId) {
    //   this.selectedCourseId = courseId;
    // },
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
      console.log('courseId: ', this.selectedCourse.id);
      console.log('leagueId', this.leagueId);
      const teeTime = {
        matchName: this.matchName,
        courseId: this.selectedCourse.id,
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
          const createdTeeTime = response.data;
          this.showPlayerForm = true;
          this.submitButtonVisible = false;
          this.upcomingTeeTimes.push(createdTeeTime);
        })
        .catch(error => {
          console.log(error);
        });
    },
    closeForm() {
      this.$emit('close');
    },
    // created() {
    //   this.getLeagueMembers();
    // }
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

<style scoped>

.background {
   position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  
}
.container{
  display: flex;
  flex-direction: column;
  background-color: white;
  height: 200px;
}
.container {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
   max-width: 400px; /* Adjust the width as needed */
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  margin-bottom: 20px; 
  border-radius: 10px;
}

.form-group{
  display: flex;
  /* white-space: nowrap; */
  margin-top: 30px;
  padding: 10px;
}






/* .container{
  background: whitesmoke;
  width: 600px;
  display: flex;
  flex-direction: column;
  padding: 5px;
  justify-content: center;
  align-items: center;
  white-space: nowrap;
 
}
.form-group{
 display: flex;
 flex-direction: column;
 white-space: nowrap;
 
}

.match{
  display: flex;
  white-space: nowrap;
  
}

.time{
  display: flex;
  white-space: nowrap;
}



.description{
  position: flex;
  padding: 10px;
}

 input {
  /* width: 100%; */
  /* height: 2rem;
  padding: 0.25rem;
  font-size: 1rem; }*/


.submit{

  padding: 10px 40px;
  background-color: #FFDE59;
  color: white;
  font-weight: bolder;
  border: none;
  border-radius: 15px; 
  cursor: pointer;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
  margin-bottom: 20px;
  font-size: 16px;
}

button:hover{
   background-color:#fce279;
}

.heading{
  display: flex;
  justify-content: center;
  font-size: 24px;
  font-weight: bold;
  color: #000000;
  align-items: center;
  
} 

</style>