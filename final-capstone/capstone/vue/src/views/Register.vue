<template>
<div class="container">
    <div class="logo">
      <img src="../assets/logo-white.png" alt="Logo" />
    </div>
    <div class = "heading">
    <h1 class="welcome-heading">Youre Gonna Be Glad You Joined Playing Through</h1>
    <h2>Your all-inclusive golf tracking app</h2>
    </div>
  <div id="register" class="text-center">
    <form @submit.prevent="register">
      <h3>Create Account</h3>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="username-input">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="password-input">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="confirm-password-input">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button type="submit">Create Account</button>
      <p><router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
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
                path: '/login',
                query: { registration: 'success' },
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
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
.container {
  /* display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-image: url("../assets/registrationbackground.jpg");
  background-size: cover;
  background-position:center;
  text-align: center;
  color: white;
   height: 99%;
  width: 299%; */
   display: grid;
   background-color: whitesmoke;
    grid-auto-columns:  1fr;
   grid-template-rows: repeat(3, 1fr);
   width: 100vw;
  height: 100vh;
  background-color: #004d33;
   min-height: 100%;
    overflow-y: scroll;

}

.logo {
  
  size: 15px;
  grid-row-start: 1;
  grid-row-end: 2;
  text-align: center;
}

.logo img {
  width: 250px;
 
}

.heading {
  font-size: 36px;
  font-weight: bold;
  grid-row-start: 2;
  grid-row-end: 3;
  row-gap: -10px;
  
}

h1 {
  font-size: 36px;
  font-weight: bold;
  text-align: center;
  color: White;
}

h2 {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  color: White;
}
h3 {
  font-size: 36px;
  font-weight: bold;
  text-align: center;
  color: #004d33;
}


#register {
  background-color: rgba(255, 255, 255, 0.8);
  display: flex;
  grid-row-start: 3;
  grid-row-end: 4;
  text-align: center;
  justify-content: center;
  margin-top: -100px;
  
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

.username-input{
  margin-top: 15px;
}

.password-input{
  margin-top: 15px;
}

.confirm-password-input{
  margin-right: 55px;
  margin-top: 15px;
}

button{
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

button:hover{
   background-color: #f7ecc2;
}


</style>


