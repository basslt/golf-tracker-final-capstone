<template>
<div class= "background">
<div class="container">
   <router-link to="/" class="back-button">
        <span>&lt; Back</span>
      </router-link>
    <div class="logo">
      <img src="../assets/official-logo.png" alt="Logo" />
    </div>
  <div id="register" class="text-center">
    <form @submit.prevent="register">
      
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class = "form">
      <div class="username-input">
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="password-input">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="confirm-password-input">
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      </div>
      <button type="submit"><span>Create Account</span></button>
      <p><router-link class = "account" :to="{ name: 'LandingPage' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
  </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/',
                query: {showLoginForm: true}
                // path: '/',
                // query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.background {
  background-color: #059262;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.container {
  background-color: #6AD6B2;
  padding: 20px;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  max-width: 700px;
  width: 100%;
  height: 600px;
}

.form {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  margin-right: 100px;
  margin-bottom: 20px;
}

.logo {
  margin-bottom: 10px;
  margin-left: 200px;
  display: flex;
  justify-content: center;
}

.logo img {
  max-width: 300px;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 40px;
  
}

.username-input,
.password-input,
.confirm-password-input {
  display: flex;
  align-items: center;
  margin: 10px 0;
}

label {
  font-weight: bold;
  margin-right: 10px;
  font-size: 25px;
}

input[type="text"],
input[type="password"] {

  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #fff;
  font-size: 16px;
  color: #333;
  transition: box-shadow 0.3s ease;
  
}


button[type="submit"] {
  display: inline-block;
  padding: 10px 20px;
  background-color: #ffde59;
  color: white;
  border: none;
  border-radius: 15px;
  cursor: pointer;
  margin-top: 20px;
  margin-bottom: 20px;
}

button span {
  font-size: 20px;
  font-weight: bold;
}

button[type="submit"]:hover {
  background-color: #fce279;
}

p {
  margin: 10px;
}

.account {
  color: black;
  text-decoration: underline;
  cursor: pointer;
  margin-top: 10px;
}
.back-button {
  margin-right: 600px;
  top: 10px;
  left: 10px;
  cursor: pointer;
  color: white;


}

.back-button span {
  font-size: 18px;
}
</style>


