<template>
    <div class="background">
      <div class="container">
        <div class="main">
            <filter-course v-if="showFilterCourse" @toggle-form="toggleForms" @course-selected="handleCourseSelected"/>
            <tee-time-create v-if="showTeeTimeCreate" v-bind:league-id="leagueId" :selected-course="selectedCourse" />
        </div>
      </div>
</div>
</template>

<script>
import FilterCourse from './FilterCourse.vue'
import TeeTimeCreate from './TeeTimeCreate.vue'

export default {
    components: {
        FilterCourse,
        TeeTimeCreate
    },
    props: {
        leagueId: {
            type: Number,
            required: true
        }
    },
    data() {
        return {
            showTeeTimeCreate: false,
            showFilterCourse: true,
            selectedCourse: null
        }
    },
    methods: {
        toggleForms(selectedCourse) {
            console.log("hey");
            this.selectedCourse = selectedCourse;
            this.showFilterCourse = false;
            this.showTeeTimeCreate = true;
        },
        // handleCourseSelected(selectedCourse) {
        //     console.log("hey");
        //     this.selectedCourse = selectedCourse;
        //     this.showFilterCourse = false;
        //     this.showTeeTimeCreate = true;
        // }
    }
}
</script>

<style scoped>

.background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5); /* Adjust the alpha value (0.5) to control the transparency */
  display: flex;
  justify-content: center;
  align-items: center;
}

.container {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  max-width: 400px; /* Adjust the width as needed */
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.main {
  display: flex;
  flex-direction: column;
  background-color: #059262;
  align-items: center;
  justify-content: center;
  padding: 20px;
  margin-bottom: 20px;
  border-radius: 10px;
}

 input[type="text"]
   {
 padding: 10px;
  border-width: 2px;
  border-radius: 10px;
  border-color: black;
  background-color: #FFFBFB;
  font-size: 16px;
  color: rgb(0, 0, 0);
  transition: box-shadow 0.3s ease;
 }

</style>