<template>
  <div>    
    <create-league />
    <router-link to="/createleague" class = "create-league-button"> Create League </router-link>
      
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

<style>

</style>