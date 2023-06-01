<template>
<div class="background">
  <div class="container">
     <div class="logo">
      <img src="../assets/logo.png" alt="Logo" />
    </div>
    <h1 class="welcome-heading">Welcome to Playing Through</h1>
    <h2>Your all-inclusive golf tracking app</h2>
    <p class="description">Find courses and set up matches with your friends</p>
    <p class= "description2">Keep track of leaderboards for ultimate bragging rights</p>
    <div id="login">
      <form @submit.prevent="login">
        <h3>Please Sign In</h3>
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="form-input-group">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
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
            this.$router.push("/");
            this.$store.commit("SET_LOGGED_USER_USERNAME", this.user.username);
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

.container{
  
  display: flex;
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
  width: 299%;
}

.logo {
  text-align: center;
}

.logo img {
  width: 500px;
  margin-top: -165px;
}

.welcome-heading {
  font-size: 36px;
  font-weight: bold;
  margin-top: -140px;
  margin-bottom: -15px;

}

h1 {
  font-size: 36px;
  font-weight: bold;
  text-align: center;
  color: white;
}

h2 {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  color: white;
}
h3 {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  color: #004d33;
}

.description {
  font-size: 18px;
  text-align: center;
  color: white;
  margin-bottom: -5px;
  margin-top: -2px;
}
.description2 {
  font-size: 18px;
  text-align: center;
  color: white;
  margin-bottom: 20px;
}

#login {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 5px;
  margin-bottom: 50px;
}

.form-input-group {
  margin-bottom: 1rem;
  color: #004d33;
}

label {
  margin-right: 0.5rem;
}

.login-form input {
  width: 100%;
  height: 40px;
  padding: 10px;
  margin-bottom: 10px;
  font-size: 16px;
}

.login-form button {
  width: 100%;
  height: 40px;
  background-color: #005400;
  color: white;
  border: none;
  font-size: 16px;
}

.register-link {
  font-size: 14px;
  margin-top: 20px;
}

.register-link a {
  color: white;
}
.sign-in-button,
.register-button {
  display: block;
  margin: 0 auto;
  width: 150px;
}
</style>
