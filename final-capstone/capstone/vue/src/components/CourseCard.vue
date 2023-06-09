<template>

      <div class="course-card">
          <img :src="course.image" :alt="course.name"/>
          <h3>{{course.name}}</h3>
          <p>{{course.location}}</p>
      </div>

</template>

<script>
export default {
    props: {
        course: {
            type: Object,
            required: true
        }
    }
}

</script>

<style scoped>

.course-card {
    text-align: center;
    margin: 10px;
    height: 250px;
}

.course-card img {
    width: 350px;
    height: 200px;
    
    border-radius: 5%;
    margin-bottom: 1%;
}

p {
    margin-top: 1%;
    font-style: italic;
}

</style>