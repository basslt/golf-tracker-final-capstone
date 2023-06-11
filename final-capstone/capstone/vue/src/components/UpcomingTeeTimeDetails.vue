<template>
  <div class="popout-container">
      <div class="popout-card">  
          <h3>{{teeTime.matchName}}</h3>
          <p>{{ formatDateTime(teeTime.time)}} </p>
          <h2>{{courseName}}</h2>
        <div v-for="player in roundPlayers" v-bind:key="player.id">
            <p>{{player.username}}</p>
        </div>
      <span class="close-button" @click="closeDetails"> <i class="fa-solid fa-xmark" style="color: #000000;"></i></span> 
      </div>
  </div>
</template>

<script>
import userService from '../services/UserService';
import courseService from '../services/CourseService';

export default {
    props: {
        teeTime: {
            type: Object,
            required: true
        },
        course: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            roundPlayers: [],
            courseName: null,
            scores: [],
            playerScores: [],
            isEditable: false

        }
    },
    methods: {
        getAllRoundPlayers() {
            userService.findUsersInMatch(this.teeTime.teeTimeId).then( (response) => {
                console.log('roundPlayers: ', response.data);
                this.roundPlayers = response.data;
            });
        },
        getCourseName() {
            courseService.getCourseName(this.teeTime.courseId).then( (response) => {
                this.courseName = response;
            });
        },
        closeDetails() {
            this.$emit('close');
        },
        formatDateTime(timestamp) {
            const date = new Date(timestamp);
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            const year = date.getFullYear().toString().padStart(2, '0');
            const time = date.toLocaleString('default', { hour: 'numeric', minute: 'numeric' });
            return `${month}/${day}/${year}   ${time}`;
        },
    },
    created() {
        this.getAllRoundPlayers();
        this.getCourseName();
        this.getMatchScores();
    }
}

</script>

<style scoped>

.popout-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.popout-card {
  background-color: #fff;
  padding: 20px;
  border-radius: 4px;
}

</style>