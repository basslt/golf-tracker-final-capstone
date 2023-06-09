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
            <div class="player-form" v-if="showPlayerForm && !allMembersInvited">
              <h3>Add players to your match:</h3>
              <div v-for="member in leagueMembers" :key="member.id">
                <div class="select">
                  {{ member.username }}
                  <button  @click="addPlayers(member.id)">Select</button>
                </div>
              </div>
            </div>
            </div>
            <div class="buttons">
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
          this.leagueMembers = response.data
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
  z-index: 9999;

}

.container {
  background-color: #059262;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
   max-width: 400px; /* Adjust the width as needed */
  width: 100%; 
  display: flex;
  flex-direction: column;
  align-items: center;
  
}

h2{
  margin-bottom: 20px;
  color: white;
}
.main p{
  margin-bottom: 10px;
  color: white;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  padding: 5px;
  border-radius: 5px;
  white-space: nowrap;
}
.main{
  display: flex;
 flex-direction: column;
 justify-content: center;
 align-self: center;
 align-items: center;
 margin: 10px;
}
.form-group{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  align-self: center;

}

.match, .time{
margin: 10px;
color: white;

}

.match input, .time input{
  display: flex;
  align-self: flex-start;
  border-radius: 5px;
  border: none;
  height: 25px;

}


button{

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

h3{
  color: white;
  margin: 10px;
}

.heading{
  display: flex;
  justify-content: center;
  font-size: 24px;
  font-weight: bold;
  color: #000000;
  align-items: center;
  
} 

.select button{
  padding: 5px 15px;
  background-color: #ffde59;
  color: white;
  font-weight: bolder;
  border: none;
  border-radius: 15px; 
  cursor: pointer;
  /* margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
  margin-bottom: 20px; */
  font-size: 15px;
  /* display: flex;
  align-self: center; */
}

.player-form{
  display: flex;
  flex-direction: column;
  align-self: center;
}

</style>