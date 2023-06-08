<template>
  <div>
    <div class="modal">
        <div class="modal-content">
          <button class="close-btn">×</button>
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

<style>
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