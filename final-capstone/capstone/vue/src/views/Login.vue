<template>
<div class="background" >
  <div class="container">
    <div id="login" v-show="showLoginForm" >
      <form @submit.prevent="login">
        <h3>Welcome Back!</h3>
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="username-input">
          <label for="username">Username:</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="password-input">
          <label for="password">Password:</label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <button class="login-button" type="submit"><span>Login</span></button>
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
      invalidCredentials: false,
      showLoginForm: false
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
            this.$router.push("/home");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  
  },
    
   mounted() {
     this.showLoginForm = true; // Set showLoginForm to true to display the login form by default
   }
};
</script>

<style scoped>

.background {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5); /* Adjust the alpha value (0.5) to control the transparency */
  display: flex;
  justify-content: center;
  align-items: center;
  
}

.container {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
   max-width: 400px; /* Adjust the width as needed */
  width: 100%; 
  display: flex;
  flex-direction: column;
  align-items: center;
  
}

.logo {
  text-align: center;
  margin-bottom: 20px;
}

.logo img {
  max-width: 200px;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

h3 {
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: center;
}

.alert {
  margin: 10px 0;
  color: red;
}

.username-input,
.password-input {
  margin: 10px 0;
}

label {
  font-weight: bold;
  margin-right: 10px;
}

/* input[type="text"],
input[type="password"] {
  padding: 5px;
  width: 200px;
} */

 input[type="text"],
input[type="password"] {
 padding: 10px;
  border: none;
  border-radius: 10px;
  background-color: #dddddd;
  font-size: 16px;
  color: #333;
  transition: box-shadow 0.3s ease;
}

.login-button {
 display: inline-block;
  padding: 10px 40px;
  background-color: #ffde59;
  color: white;
  border: none;
  border-radius: 15px; /* Adjust the value to control the roundness */
  cursor: pointer;
  margin-top: 20px;
  margin-bottom: 20px;
}
.login-button span {
  font-size: 20px; /* Adjust the font size for the login text */
  font-weight: bold;
}

.login-button:hover {
  background-color: #fce279;
}

.register-button {
  color: black;
  text-decoration: underline;
  cursor: pointer;
  margin: 10px;
  margin-bottom: 10px;
}


/* Media query for responsive design */
@media (max-width: 600px) {
  .container {
    max-width: 90%;
  }
}
</style>

