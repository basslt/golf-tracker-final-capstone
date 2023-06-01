<template>
  <div class="home">
    <div class = "header"> 
      <img src="../assets/logo.png" alt="">
      <h2>golf leagues with friends</h2>
      <div class = "header-buttons">
       <router-link v-bind:to="{ name: 'AddLeague' }" class="create-league-button"> Create League </router-link>
      <router-link to="/login" class="login-button">Login</router-link>
      <router-link to="/register" class="register-button">Register</router-link>
      
    </div>
    </div>
    <div class = "main">
      <league-list />
      </div>
      <div class = "main2"></div>
      <div class = "main3"></div>
    <!-- <create-league /> -->
    
    </div>
</template>

<script>
// import CreateLeague from '../components/CreateLeague.vue';
import LeagueList from '../components/LeagueList.vue';

export default {
  components: {  
    LeagueList 
  },
  name: "main"
};
</script>
<style scoped>
/* * {
    font-family: Tahoma, Verdana, Segoe, sans-serif;
    box-sizing: border-box;
    
} */

.home{
   display: grid;
   background-color: whitesmoke;
   grid-template-columns: 1fr, 1fr, 1fr;
   grid-auto-rows: 100px;
   width: 100vw;
   height: 100vh;
}

.header {


  grid-column-start: 1;
  grid-column-end: 4;
  grid-row-start: 1;
  grid-row-end: 2;
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

.login-button,
.register-button, .create-league-button {
  margin-left: 20px;
  color: rgb(2, 2, 2);
  /* justify-content: center;  */
  text-decoration: none; 
}
.main{
  background-color: rgb(180, 180, 180);
  margin-top: 15px;
  /* justify-content: right;
  align-content: right; */
  grid-column-start: 2;
  grid-row-start: 2;
  grid-row-end: 4;
}



</style>