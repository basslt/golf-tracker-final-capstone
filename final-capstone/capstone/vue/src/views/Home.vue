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
/* Global styles */
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f1f1f1;
}

/* Header styles */
.header {
  background-color: #004d33;
  color: #ffffff;
  padding: 20px;
  display: flex;
  align-items: center;
}

.header h1 {
  font-size: 24px;
  font-weight: bold;
  margin-left: 10px;
}

.header img {
  max-width: 100px;
}

/* Button styles */
.button {
  display: inline-block;
  padding: 10px 20px;
  background-color: #008000;
  color: #ffffff;
  text-decoration: none;
  border-radius: 5px;
  transition: background-color 0.3s ease;
}

.button:hover {
  background-color: #005400;
}

/* Container styles */
.container {
  max-width: 960px;
  margin: 0 auto;
  padding: 20px;
}

/* Form styles */
.form {
  background-color: #ffffff;
  padding: 20px;
  border-radius: 5px;
}

.form input[type="text"],
.form input[type="password"],
.form select {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 3px;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

.form button[type="submit"] {
  padding: 10px 20px;
  background-color: #008000;
  color: #ffffff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.form button[type="submit"]:hover {
  background-color: #005400;
}




</style>