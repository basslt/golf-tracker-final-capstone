<template>
  <div class="home">
    <div class = "header"> 
      <img src="../assets/logo.png" alt="">
      <h2>golf leagues with friends</h2>
      <div class = "header-buttons">
       <router-link to="/create-league" class = "create-league-button"> Create League </router-link>
      <router-link to="/login" class="login-button">Login</router-link>
      <router-link to="/register" class="register-button">Register</router-link>
      
    </div>
    </div>
    <div class = "main">
      <league-list />
      </div>
    <!-- <create-league /> -->
    
    </div>
</template>

<script>
// import CreateLeague from '../components/CreateLeague.vue';
import LeagueList from '../components/LeagueList.vue';

export default {
  components: {  LeagueList },
  name: "home"
};
</script>
<style scoped>
* {
    font-family: Tahoma, Verdana, Segoe, sans-serif;
    box-sizing: border-box;
    
}

.home{
   height: 100vh;
  width: 100vw;
}

.header {
  display: flex;
  flex-direction: row;
  align-items: center;
  height: 100px;
  justify-content: center;
  padding-top: 25px;
  padding-bottom: 25px;
}

.header img {
  max-width: 300px;
  margin-right: 10px;
}

.header h2 {
  color: #004d33;
  align-content: center;
  margin-right: 1000px;
  white-space: nowrap;
  size: 10px;
  
}

.header-buttons {
  margin-right: auto;
  display: flex;
  align-items: center;
  justify-content: center;
   margin-left: 20px;
   white-space: nowrap;

}

.login-button,
.register-button, .create-league-button {
  margin-left: 20px;
  color: rgb(2, 2, 2);
  justify-content: center;
  text-decoration: none;
}
.main{
  background-color: rgb(180, 180, 180);
  margin-top: 15px;
  justify-content: right;
  align-content: right;
}






</style>