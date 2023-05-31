<template>
  <div class="home">
    <div class = "header"> <h1>Golf Tracker</h1>
    <img src="../assets/logo.png" alt="">
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
.home {
  padding: 20px;
  min-height: 100vh;
}
.header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.home.header h1 {
  font-size: 24px;
  font-weight: bold;
  color: rgb(0, 255, 34);
  margin-left: 10px;
}

.header img {
  max-width: 100px;
}




</style>