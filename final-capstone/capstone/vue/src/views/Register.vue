<template>
<div class= "background">
<div class="container">
    <div class="logo">
      <img src="../assets/official-logo.png" alt="Logo" />
    </div>
  <div id="register" class="text-center">
    <form @submit.prevent="register">
      <h3>Create Account</h3>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="fields">
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
      </div>
      <button type="submit">Create Account</button>
      <p><router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
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
  align-items: center;
  text-align: center;
   max-width: 700px; /* Adjust the width as needed */
  width: 100%;
  height: 600px;
}

.logo {
  margin-bottom: 50px;
  display: flex;
  align-items: center;
  align-content: center;
}

.logo img {
  max-width: 300px;
}

form {
  display: flex;
  flex-direction: column;
}

.fields {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

h3 {
  margin-top: 0;
  margin-bottom: 20px;
}

.alert {
  margin: 10px 0;
  color: red;
}

.username-input,
.password-input,
.confirm-password-input {
  margin: 10px 0;
}

label {
  font-weight: bold;
  margin-right: 10px;

}

input[type="text"],
input[type="password"] {
  padding: 5px;
  width: 200px;
}

button[type="submit"] {
  padding: 10px 20px;
  background-color: #ffde59;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

p {
  margin: 10px;
}

router-link {
  color: black;
  text-decoration: underline;
  cursor: pointer;
  margin-top: 10px;
}

</style>


