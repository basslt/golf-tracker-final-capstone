<template>
  <div>
    <div class="modal">
        <div class="modal-content">
          <button class="close-btn"><i class="fa-solid fa-xmark" style="color: #000000;"></i></button>
          <ul class="course-list">
            <li v-for="course in filteredCourses" :key="course.courseId" class="course-item">
              {{ course.name }} - {{ course.city }}, {{ course.state }}
              <button @click="selectCourse(course)" class="selectCourseButton"><i class="fa-solid fa-arrow-right" style="color: #ffffff;"></i></button>
            </li>
          </ul>
        </div>
      </div>
  </div>
</template>

<script>

export default {
  props: {
    filteredCourses: {
      type: Array,
      required: true
    },
  },
  methods: {
      selectCourse(course) {
          const selectedCourse = {
            id: course.courseId,
            data: course
          };
          console.log(course.courseId);
          this.selectedCourseIdLocal = selectedCourse.id;
          this.$emit('course-selected', selectedCourse);
          // this.showForm = false; 
          // this.$emit('close');
          // this.showCourseListModal = false;
          // this.$emit('toggle-form'); 
          },
  }

}
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

.selectCourseButton{
background-color: #059262;
color: white;
border-radius: 5px;
border: none;
padding: 10px 40px;
font-size: 20px;
width: 25px;


}

.selectCourseButton:hover{
  cursor: pointer;
   box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
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
  width: 50%;
  max-height: 50%;
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
  
  padding: 0;
  list-style-type: circle;
}

li{
  margin: 10px;
}

.selectCourseButton {
  padding: 0px;
  margin: 3px;
}
</style>