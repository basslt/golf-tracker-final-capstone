<template>
   <div>
     <header>
        <hamburger-menu />
     </header>
     <div class="main">
        <body>
            <main class="content">
                <my-leagues class="myleagues"/>
                <course-carousel class="display" />
                <create-league v-if="this.$store.state.showCreateForm"/>
                <!-- <message-list /> -->
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
// import MessageList from '../components/MessageList.vue'


export default {
  components: {  
    HamburgerMenu,
    MyLeagues,
    CreateLeague,
    CourseCarousel,
    // MessageList
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

.content {
  display: flex;
  /* flex-direction: column; */
   align-items: flex; 
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
</style>