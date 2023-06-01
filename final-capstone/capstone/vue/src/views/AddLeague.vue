<template>
  <div>    
    <create-league />
      
  </div>
</template>

<script>
import CreateLeague from '../components/CreateLeague.vue'

export default {
  components: { 
    CreateLeague 
  }

   
}
</script>




<style scoped>

.container{
  
  display: flex;
  flex-direction:column;
  align-items: center;
  background-image: 100%;
  justify-content: center;
  background-image: url("../assets/Empty_Leaderboard.jpg");
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
