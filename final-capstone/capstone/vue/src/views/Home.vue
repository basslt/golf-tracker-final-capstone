<template>
  <div class="home">
    <div class = "header"> 
      <img src="../assets/logo.png" alt="">
       <router-link to="/create-league" class = "create-league-button"> Create League </router-link>
      <router-link to="/login" class="login-button">Login</router-link>
      <router-link to="/register" class="register-button">Register</router-link>
      
    </div>
    <create-league />
 
    <league-list />
    </div>
</template>

<script>
import CreateLeague from '../components/CreateLeague.vue';
import LeagueList from '../components/LeagueList.vue';

export default {
  components: { CreateLeague, LeagueList },
  name: "home"
};
</script>
<style scoped>


.header {
  background-color: #004d33;
  color: #ffffff;
  padding: 20px;
  display: flex;
  align-items: center;
}

.header img {
  max-width: 200px;
  margin-right: 20px;
}

.header-buttons {
  margin-left: auto;
  display: flex;
  align-items: center;
}

.login-button,
.register-button, .create-league {
  margin-left: 10px;
  color: black;
  
}


</style>