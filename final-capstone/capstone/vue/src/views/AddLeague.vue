<template>
<div>
  <div>
    <create-league />
  </div>
    <div class="container">
      <h1></h1>
     <div class="logo">
      <img src="../assets/logo.png" alt="Logo" />
    </div>
    </div>
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
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-image: url("../assets/createleague.jpg");
  background-size: cover;
  background-position: center;
  text-align: center;
  color: white;
  height: 100%;
  width: 299%;
}

.logo {
  margin-top: -450px;
}

h2 {
  font-size: 36px;
  font-weight: bold;
  margin-bottom: 20px;
}

form {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 5px;
}

label {

  color: #004d33;
}

input {
  width: 100%;
  height: 2rem;
  padding: 0.25rem;
  font-size: 1rem;
}

button {
  padding: 0.5rem 1rem;
  text-align: center;
  background-color: #004d33;
  color: #fff;
  border: none;
  cursor:grab;
}
</style>
