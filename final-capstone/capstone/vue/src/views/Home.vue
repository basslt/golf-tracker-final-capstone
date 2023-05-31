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
    <div><league-list /></div>
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

.header {
  background-color: #004d33;
  color: #ffffff;
  padding: 20px;
  display: flex;
  align-items: center;
}

.header img {
  max-width: 300px;
  margin-right: 10px;
}

.header-buttons {
  margin-right: auto;
  display: flex;
  align-items: right;
  justify-content: right;
   margin-left: 10px;

}

.login-button,
.register-button, .create-league-button {
  margin-left: 10px;
  color: rgb(2, 2, 2);
  justify-content: right;
  text-decoration: none;
}






</style>