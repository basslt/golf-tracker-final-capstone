<template>
  <div>
    <form v-if="showForm && !selectedCourse" @submit.prevent="submitFilters">
      <label for="nameInput">Name:</label>
      <input type="text" id="nameInput" v-model="name">
      
      <label for="stateInput">State Abbr:</label>
      <input type="text" id="stateInput" v-model="state">
      
      <label for="cityInput">City:</label>
      <input type="text" id="cityInput" v-model="city">
      
      <button type="submit">Filter</button>
    </form>
    
    <div v-if="selectedCourse">
      <h2>Selected Course</h2>
      <p>{{ selectedCourse.data.name }} - {{ selectedCourse.data.city }}, {{ selectedCourse.data.state }}</p>
     
    </div>

    <ul v-if="!selectedCourse">
      <li v-for="course in filteredCourses" :key="course.courseId">
        {{ course.name }} - {{ course.city }}, {{ course.state }}
        <button @click="selectCourse(course)">Select</button>
      </li>
    </ul>
     
  </div>
</template>

<script>
import courseService from '../services/CourseService';
//import LeagueMembership from '../services/LeagueMembership';
//import LeagueService from '../services/LeagueService';
//import userService from '../services/UserService'
//import LeagueMembership from '../services/LeagueMembership';

export default {
  
  data() {
    return {
      name: '',
      state: '',
      city: '',
      courses: [],
      selectedCourse: null,
      showForm: true,
    leagues: [],
    leagueMembersNames: [],
    };
  },
  computed: {
    filteredCourses() {
      if (this.selectedCourse) {
        return [this.selectedCourse.data]; // Return the selected course data as an array
      } else {
        return this.courses;
      }
    },
    
    
  },
  methods: {
    submitFilters() {
      const params = {
        name: this.name,
        state: this.state,
        city: this.city
      };

      courseService.getCoursesByFilter(params)
        .then(courses => {
          this.courses = courses;
          this.selectedCourse = null;
          console.log(courses);
          
          
        })
        .catch(error => {
          console.error('Error:', error);
        });
       
    },
    

    selectCourse(course) {
      this.selectedCourse = {
        id: course.courseId,
        data: course
        
      };
    
      console.log(course.courseId);
      this.selectedCourseIdLocal = course.courseId;
      this.$emit('course-selected', course.courseId);
      this.showForm = false; // Hide the form
     
    },
  
  },
   
}
;
</script>
