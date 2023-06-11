<template>
  <div class="teetimes-container">
    <div class="tee-time-card">
      <div class="teetime-info">
            <p>{{teeTime.matchName}}</p>
            <p>{{ formatDateTime(teeTime.time)}}</p>
            <p>{{this.course.name}}</p>
      </div>
      <div class="tee-time-buttons">
          <button class="yellow-button" @click="handleClickedTeeTime"> View Details or Enter Scores <i class="fa-solid fa-pen" style="color: #ffffff;"></i></button>
      </div>
  </div>
  </div>
</template>

<script>
import courseService from '../services/CourseService';

export default {
    name: "upcoming-tee-time-card",
    components: {
    },
    props: {
        teeTime: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            course: {},
            showTeeTimeDetails: false
        }
    },
    methods: {
        getCourseName() {
            courseService.getCourse(this.teeTime.courseId).then( (response) => {
                this.course = response;
            })
        },
        formatDateTime(timestamp) {
            const date = new Date(timestamp);
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            const time = date.toLocaleString('default', { hour: 'numeric', minute: 'numeric' });
            return `${month}/${day} ${time}`;
        },
        handleClickedTeeTime() {
            this.$emit('tee-time-click');
        }
    },
    created() {
        this.getCourseName();
    }
}
</script>

<style scoped>

.recent, .upcoming{
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

.tee-time-card {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #059262;
  color: white;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 20px;
}

.tee-time-info p {
    margin: 5px;
}

.yellow-button{
  border-radius: 10px;
  background: none;
  border: none;
  color:white;
  font-weight: bold;
  padding: 5px;
}

.yellow-button:hover{
  cursor: pointer;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); 
  transition: box-shadow 0.3s ease;
}

</style>