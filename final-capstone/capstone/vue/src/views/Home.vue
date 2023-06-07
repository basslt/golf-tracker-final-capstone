<template>
   <div class="background">
     <header>
        <hamburger-menu />
     </header>
    
     <div class="main">
        <body>
            <main class="content">
                <my-leagues class="myleagues"/>
                <course-carousel class="display" />
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
import CourseCarousel from '../components/CourseCarousel.vue';


export default {
  components: {  
    HamburgerMenu,
    MyLeagues,
    CreateLeague,
    CourseCarousel,
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
/* .main {
  display: flex;
  flex-direction: column;
  align-items: center;
} */

.background {
  background-image: url('../assets/backgroundgolfer.png');
  height: 100vh;
}

.content {
  display: flex;
  /* flex-direction: column; */
   align-items: flex; 
   /* height: 100vh; */
}

.myleagues {
  display: flex;
  align-self: flex-start;
 margin: 100px;
 margin-left: 300px;
}

.display{
  display: flex;
  margin-top: 405px;

}

/* body {
  background-image: url('../assets/MainBackground.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  margin: 0;
  padding: 0;
  background-size: 100%; */
  /* height: 100vh; */
/* } */

</style>