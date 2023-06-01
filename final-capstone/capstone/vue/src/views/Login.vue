<template>
<div class="background">
  <div class="container">
    <body >
      
    </body>
     <div class="logo">
      <img src="../assets/logo-white.png" alt="Logo" />
    </div>
    <div class = "wording">
    <h1 class="welcome-heading">Welcome to Playing Through</h1>
    <h2>Your all-inclusive golf tracking app</h2>
    <p class="description">Find courses and set up matches with your friends</p>
    <p class= "description2">Keep track of leaderboards for ultimate bragging rights</p>
    </div>
    <div id="login">
      <form @submit.prevent="login">
        <h3>Please Sign In</h3>
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="username-input">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="password-input">
          <label for="password">Password</label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <button class="sign-in-button" type="submit">Sign in</button>
        <p>
          <router-link class="register-button" :to="{ name: 'register' }">Not a user? Register here!</router-link>
        </p>
      </form>
    </div>
  </div>
</div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "Login",
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status === 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$store.commit("SET_LOGGED_USER_USERNAME", this.user.username);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.background {
    min-height: 100%;
    overflow-y: scroll;
      width: 100vw;
  height: 100vh;
} 


.container{
  
  /* display: flex;
  flex-direction:column;
  align-items: center;
  background-image: 100%;
  justify-content: center;
  background-image: url("../assets/loginbackground.jpg");
  background-size: cover;
  background-position:center;
  text-align: center;
  color: white;
  height: 99%;
  width: 299%; */
   display: grid;
   background-color: whitesmoke;
    grid-auto-columns: 1fr;
   grid-template-rows: repeat(3, 1fr);
   width: 100vw;
  height: 100vh;
  background-color: #004d33;
   min-height: 100%;
    overflow-y: scroll;

}





.logo {
  
  grid-row-start: 1;
  grid-row-end: 2;
  text-align: center;
  
 

}
.wording{
  grid-row-start: 2;
  grid-row-end: 3;
  text-align: center;
  color: white;
  
}
.logo img {
  width: 300px; 
}

#login {
  display: flex;
  grid-row-start: 3;
  grid-row-end: 4;
  text-align: center;
  font-size: 20px;
  justify-content: center;
  /* margin-bottom: 200px; */
  color: white;
  
}

h3 {
  color: #ffde59;
}

.username-input {
padding-bottom: 15px;
}
.username-input label{
margin-right: 10px;

}

.password-input label{
margin-right: 15px;
}

.register-button{
  color: #ffffff;
}
.sign-in-button{
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


.sign-in-button:hover {
  background-color: #f7ecc2;
}

</style>
