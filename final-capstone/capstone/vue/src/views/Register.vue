<template>
<div class="container">
    <div class="logo">
      <img src="../assets/logo.png" alt="Logo" />
    </div>
    <h1 class="welcome-heading">Youre Gonna Be Glad You Joined Playing Through</h1>
    <h2>Your all-inclusive golf tracking app</h2>
    
  <div id="register" class="text-center">
    <form @submit.prevent="register">
      <h3>Create Account</h3>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
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
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-image: url("../assets/registrationbackground.jpg");
  background-size: cover;
  background-position:center;
  text-align: center;
  color: white;
  

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
  margin-bottom: 20px;
  margin-top: -140px;
  margin-bottom: -15px;
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


