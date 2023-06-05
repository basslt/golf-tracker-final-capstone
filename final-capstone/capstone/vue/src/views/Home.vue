<template>
   <div>
     <header>
        <hamburger-menu />
     </header>
     <div class="main">
        <body>
            <main class="content">
                <my-leagues />
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

export default {
  components: {  
    HamburgerMenu,
    MyLeagues,
    CreateLeague
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
.main {
    display: grid;
    /* height: 100vh; */
    width: 100vw; 
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-areas: 
        "header header header header"
        "content content content content"
        "footer footer footer footer";

}

.header {
    width: 100vw;
    
}

.content{
  grid-area: content;
}




/* .hamburger-menu {
   grid-row-start: 1;
   grid-row-end: 1;
  grid-column-start: 1; 
  grid-column-end:  4;
  background-color: black;
  width: 1000px;
}  */
/* 
.logo {
  display: flex;
  justify-content: flex-end;
}

.main {
  grid-row: 2 / span 2;
  grid-column: 1 / span 4;
  display: flex;
  justify-content: center;
  align-items: center;
} */
</style>
