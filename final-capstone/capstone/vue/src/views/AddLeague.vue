<template>
 <div class="container">
   <div class = "heading">
     <h2>Create a New League</h2>
   </div>
  <div class="logo">
      <img src="../assets/logo.png" alt="Logo" />
    </div>
    <div class = "league-form">
    <create-league />
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
  display: grid;
  background-color: whitesmoke;
  grid-template-columns: 1fr; /* Single column */
  grid-template-rows: auto auto auto; /* Three rows with auto height */
  width: 100vw;
  height: 100vh; 
  align-items: center;
  justify-content: center;
  text-align: center;
  color: white;
   grid-row-gap: -50px;
}

.logo {
  grid-row: 1;
  justify-self: center;
  height: 10px;
  margin-top: -300px;
  
}

.heading{
  grid-row: 2;
  justify-self: center;
  align-items: center;
  font-size: 36px;
  font-weight: bold;
  color: #004d33;
  align-items: center;
  position: inherit;
  margin-top: 150px;
}


.league-form {
   grid-row: 3; /* Shorthand for grid-row-start: 3; grid-row-end: 4; */
  justify-self: center;
  margin-bottom: 200px;
  
  
}

form {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 5px;
}

label {

  color: #004d33;
}

</style>
