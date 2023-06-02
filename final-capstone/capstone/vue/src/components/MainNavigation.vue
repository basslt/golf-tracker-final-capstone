<template>
  <div class="container">
      <div class="logo">
        <img src="../assets/logo_smaller.png" alt="">
        <h2>golf leagues with friends</h2>
      </div>
      <div class="links">
        <div class="links-container">
          <router-link v-bind:to="{ name: 'home'}">Home</router-link> |
          <router-link v-bind:to="{ name: 'LeagueHome'}">Leagues</router-link> |
          <button>Courses</button>
          <button>Log Out</button>
          <router-link v-bind:to="{ name: 'AddLeague' }" class="create-league-button">Create League</router-link>
        </div>
      </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>

.logo {
    display: flex;
    flex-grow: 1;
    align-items: center;
}

.container {
    display: flex;
    align-items: center;
    flex-grow: 1;    
    
}

.links {
    flex-grow: 1;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    
}


/* .links-container {
    display: flex;
    align-items: center;
} */

</style>