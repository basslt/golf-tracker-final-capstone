<template>
  <div>
    <header>
      <main-navigation />
    </header>
    <div class="main">
      <body>
        
        <!-- <div class="header"> 
          <img src="../assets/logo.png" alt="">
          <h2>golf leagues with friends</h2>
          <div class="header-buttons">
            <router-link v-bind:to="{ name: 'AddLeague' }" class="create-league-button"> Create League </router-link>
            <router-link v-bind:to="{ name: 'LandingPage'}" class="log-out-button">Log Out</router-link>
          </div>
        </div> -->
        <div class="main">
          <league-list />
        </div>
        <div class="foot">
          <p>footer - fill in</p>
        </div>
      </body>
    </div>
  </div>
</template>

<script>
import LeagueList from '../components/LeagueList.vue';
import MainNavigation from '../components/MainNavigation.vue'

export default {
  components: {  
    LeagueList,
    MainNavigation,
  },
  name: "main"
};
</script>
<style scoped>

.home{
  
}

body{
   display: grid;
   background-color: whitesmoke;
   width: 100vw;
   height: 100vh;
   grid-template-columns: 1fr, 1fr, 1fr;
   grid-template-rows: 15%, 75%, 10%;
   grid-template-areas: 
    "header header header"
    "main main main"
    "foot foot foot";
}

.header {

  grid-area: header;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100px;
  background-color: #ffffff;
  padding: 0 20px;
}

.header img {
  max-width: 300px;
  margin-right: 10px;
}

.header h2 {
  color: #004d33;
  white-space: nowrap;
  size: 10px; 
  
}

.header-buttons {
   white-space: nowrap; 
   display: flex;
  align-items: center;
  justify-content: flex-end;
  flex-grow: 1;
  margin-right: 10px;

}

.log-out-button, .create-league-button {
  margin-right: 10%;
  color: rgb(2, 2, 2);
  /* justify-content: center;  */
  text-decoration: none; 
}

.create-league-button{
  
  display: inline-block;
  padding: 10px 20px;
  background-color: #fdd430;
  color: #fff;
  border-radius: 25px;
  border: none;
  font-size: 16px;
  text-align: center;
  text-decoration: none;
  transition: background-color 0.3s ease;
}

.create-league-button:hover{
   background-color: #f7ecc2;
}

.main{
  grid-area: main;
}

.foot {
  grid-area: foot;
  display: inline-block;
}



</style>