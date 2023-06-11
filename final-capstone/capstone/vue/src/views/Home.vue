<template>
   <div class="background" >
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


.background{
 background-image: url('../assets/backgroundgolfer.png');
}
.main {
  
  
  height: 830px;
  display: flex;
  justify-content: center;
  align-self: center;
  align-items: center;
  flex-direction: row;
}

.content {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 50px;
}

.myleagues{
  
  margin: 40px;
}

.display{
  display: flex;
 justify-content: center;
 align-items: center;
margin-left: 40px;
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