<template>
  <div>
    <form @submit.prevent="submitFilters">
      <label for="nameInput">Name:</label>
      <input type="text" id="nameInput" v-model="name">
      
      <label for="stateInput">State:</label>
      <input type="text" id="stateInput" v-model="state">
      
      <label for="cityInput">City:</label>
      <input type="text" id="cityInput" v-model="city">
      
      <button type="submit">Filter</button>
    </form>
    
    <ul>
      <li v-for="course in filteredCourses" :key="course.courseId">
        {{ course.name }} - {{ course.city }}, {{ course.state }}
      </li>
    </ul>
  </div>
</template>

<script>
import courseService from '../services/CourseService'

export default {
  data() {
    return {
      name: '',
      state: '',
      city: '',
      courses: [] // Assuming you have an array of courses fetched from the API
    };
  },
  computed: {
    filteredCourses() {
      return this.courses;
    }
  },
  methods: {
    submitFilters() {
        const params = {};

        if (this.name) {
            params.name = this.name;
        }

        if (this.state) {
            params.state = this.state;
        }

        if (this.city) {
            params.city = this.city;
  }
      // Call the getCoursesByFilter() helper method to fetch filtered courses
      courseService.getCoursesByFilter(params)
        .then(courses => {
          // Update the courses array with the response data
          this.courses = courses;
          console.log(courses);
        })
        .catch(error => {
          console.error('Error:', error);
        });
    }
  }
};
</script>