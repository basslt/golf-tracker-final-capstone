<template>
  <div class="home">
    <div class="logo">
      <img src="../assets/official-logo.png" alt="">
    </div>
    <div class = "main-content">
      <h1>Create Golf Leagues & Connect with Friends</h1>
      <h2>"Schedule Tee Times, View Leaderboards, and Discover Courses - All in One Place!"</h2>
      <button
      class = "login-button" @click="showLoginForm = true" >
       <span>Login</span>
        </button>
    </div>
    <Login v-show="showLoginForm" @close="showLoginForm = false" />
    </div>
</template>


<script>
import Login from '../views/Login.vue'; // Import the Login component

export default {
  components: {
    Login // Register the Login component
  },
  data() {
    return {
      showLoginForm: this.$route.query.showLoginForm === 'true' || false,
    };
  }
};
</script>

<style scoped>

* {
  font-family: sans-serif;
  box-sizing: border-box;
  margin: 0;
}

.home {
  display: flex;
  flex-direction: column;
  background-color: #059262;
  width: 100vw;
  height: 100vh;
  
}

.logo {
  justify-content: center;
  display: flex;
  height: 400px;
  
  
  
}

img {
  max-width: 400px; 
  
}

.main-content {
  color: white;
  text-align: center; 
  
}

.main-content h1 {
  font-size: 50px;
  margin-bottom: 50px;
  
}

.main-content h2 {
  font-size: 24px;
  
}

.login-button{
  margin-top: 100px;
  display: inline-block;
  padding: 20px 60px;
  background-color: #ffde59;
  color: white;
  border: none;
  border-radius: 15px; /* Adjust the value to control the roundness */
  cursor: pointer;
  
  
}
.login-button span {
  font-size: 30px; /* Adjust the font size for the login text */
  font-weight: bold;
}

.login-button:hover {
  background-color: #fce279;
}



</style>