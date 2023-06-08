<template>
<div>
  <div class="container"  >
    <form class="filter-form" v-if="showForm && !selectedCourse" @submit.prevent="submitFilters"  >
      <h2>Select Your Course</h2>
      <div class="name-input">
      <label class="name-label" for="nameInput">Name:</label>
      <input type="text" id="nameInput" v-model="name">
      </div>
      <div class="state-input">
      <label class="state-label" for="stateInput">State Abbr:</label>
      <input type="text" id="stateInput" v-model="state">
      </div>
      <div class="city-input">
      <label class="city-label" for="cityInput">City:</label>
      <input type="text" id="cityInput" v-model="city">
      </div>
      <button type="submit"> <span>Filter</span> </button>
    </form>
</div>
    <div v-if="selectedCourse">
      <h2>Selected Course</h2>
      <p>{{ selectedCourse.data.name }} - {{ selectedCourse.data.city }}, {{ selectedCourse.data.state }}</p>
     
    </div>
    <div v-if="showCourseListModal" class="modal">
      <div class="modal-content">
        <button class="close-btn" @click="closeCourseListModal">×</button>
        <ul class="course-list">
          <li v-for="course in filteredCourses" :key="course.courseId" class="course-item">
            {{ course.name }} - {{ course.city }}, {{ course.state }}
             <button @click="selectCourse(course)" class="selectCourseButton">Select</button>
          </li>
        </ul>
      </div>
    </div>
     </div>
 
</template>

<script>
import courseService from '../services/CourseService';
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
    showCourseListModal: false
    };
  },
  computed: {
    filteredCourses() {
      if (this.selectedCourse) {
        return [this.selectedCourse.data]; 
      } else {
        return this.courses;
      }
    },
    
    
  },
  methods: {
    

    closeCourseListModal() {
      this.showCourseListModal = false;
    },

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
          this.showCourseListModal = true;
          
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
      this.showForm = false; 
      this.showCourseListModal = false;
     
    },
  
  },
   
}
;
</script>
<style scoped>


.container {
  display: flex;
  flex-direction: column;
  white-space: nowrap;
  justify-content: center;
  align-items: center;
  background: #059262;
  border-radius:  5px;
  height: 300px;
  width: 300px;
}

.filter-form {
  display: flex;
  flex-direction: column;
  white-space: nowrap;
  justify-content: center;
  align-items: center;
}

.filter-form h2 {
  color: white;
  margin: 5px;
}
.name-input, .state-input, .city-input {
margin: 5px;
}

.name-input label, .state-input label, .city-input label {
 color: white;
  margin: 10px;
  
}
.name-input  [type="text"], .state-input [type="text"], .city-input [type="text"]{
/* display: flex; */
background: white;
border-radius: 5px;
width: 200px;
margin-left: 10px;
height: 20px;

/* justify-content: center;
align-items: center; */
display: flex;
border: none;

}

/* Optional: Adjust the spacing as needed */
.filter-form button  {
 color: white;
 font-weight: bold;
font-size: 15px;
}

.filter-form button {
  margin-top: 20px;
  width: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 5px 30px;
  background-color: #ffde59;
  border: none;
  border-radius: 10px;


}

.filter-form button:hover{
  background-color: #fce279;
  cursor: pointer;
}

















.modal {
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

.modal-content {
  width: 80%;
  max-height: 80%;
  overflow-y: auto; 
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  position: relative;
}

.close-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 20px;
  cursor: pointer;
  margin: 0px;
}
.course-list {
  margin: 0;
  padding: 0;
  list-style-type: none;
}

.selectCourseButton {
  padding: 0px;
  margin: 3px;
}
</style>
