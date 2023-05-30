<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <create-league />
  </div>
</template>

<script>
import CreateLeague from '../components/CreateLeague.vue';
export default {
  components: { CreateLeague },
  name: "home"
};
</script>
