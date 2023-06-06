<template>
   <div>
     <header>
        <hamburger-menu />
     </header>
     <div class="main">
        <body>
            <main class="content">
                <my-leagues />
                <course-carousel />
                <create-league v-if="this.$store.state.showCreateForm"/>
            </main>
        </body>
     </div>
 
     </div>
</template>

<script>
import HamburgerMenu from '../components/HamburgerMenu.vue';
import MyLeagues from '../components/MyLeagues.vue';
import CreateLeague from '../components/CreateLeague.vue';
import CourseCarousel from '../components/CourseCarousel.vue'


export default {
  components: {  
    HamburgerMenu,
    MyLeagues,
    CreateLeague,
    CourseCarousel
  },
  name: "main",
  data() {
    return {
      showForm: '',
    }
  },
};
</script>

<style scoped>

.content{
  display: flex;
  flex-direction: column;
  align-items: center;
}



</style>
