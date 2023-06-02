<template>
  <div class="home">
    <div class="header"> 
      <img src="../assets/logo.png" alt="">
      <h2>golf leagues with friends</h2>
      <div class = "header-buttons">
        <router-link to="/login" class="login-button">Login</router-link>
      </div>
    </div>
    <div class="league-container">
      <league-list />
    </div>
    </div>
</template>

<script>
import LeagueList from '../components/LeagueList.vue';

export default {
components: {  
    LeagueList 
  },
  name: "main"
};
</script>

<style>

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

.login-button {
  margin-right: 10%;
  color: rgb(2, 2, 2);
  /* justify-content: center;  */
  text-decoration: none; 
}

.league-container{

}

</style>