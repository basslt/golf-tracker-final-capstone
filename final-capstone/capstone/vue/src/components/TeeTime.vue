<template>
  <div>
    <form @submit.prevent="submitTeeTime">
      <label for="courseInput">Course:</label>
      <select id="courseInput" v-model="courseId">
        <option v-for="course in courses" :value="course.courseId" :key="course.courseId">
          {{ course.name }} - {{ course.city }}, {{ course.state }}
        </option>
      </select>
      
      <label for="timeInput">Time:</label>
      <input type="datetime-local" id="timeInput" v-model="time">
      
      <label for="playerInput">Player ID:</label>
      <input type="number" id="playerInput" v-model="playerId">
      
      <label for="organizerInput">Organizer ID:</label>
      <input type="number" id="organizerInput" v-model="organizerId">
      
      <button type="submit">Create Tee Time</button>
    </form>

    <CourseSearch @courses-updated="updateCourses" @course-selected="handleCourseSelected" />
  </div>
</template>

<script>
import CourseSearch from '../components/SelectCourse.vue';
import teeTimeService from '../services/TeeTimeService';

export default {
  components: {
    CourseSearch
  },
  data() {
    return {
      courseId: null,
      time: '',
      playerId: null,
      organizerId: null,
    };
  },
  methods: {
    handleCourseSelected(course) {
      this.courseId = course.courseId;
    },
    submitTeeTime() {
      const teeTimeData = {
        courseId: this.courseId,
        time: this.time,
        playerId: this.playerId,
        organizerId: this.organizerId
      };

      // Call the service to create the tee time in the database
      teeTimeService.createTeeTime(teeTimeData)
        .then(() => {
          console.log('Tee time created successfully!');
          // Reset the form fields
          this.courseId = null;
          this.time = '';
          this.playerId = null;
          this.organizerId = null;
        })
        .catch(error => {
          console.error('Error:', error);
        });
    },
    updateCourses(courses) {
    this.courses = courses;
  },
  }
};
</script>
