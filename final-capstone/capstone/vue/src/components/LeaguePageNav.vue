<template>
  <div class="main">
      <button>View All Members</button>
      <router-link v-bind:to="{name :  ">Invite Members</router-link>
      <button>Schedule Tee Time</button>
  </div>
</template>

<script>
export default {

}
</script>

<style>
    div.main {
        display: flex;
        flex-direction: column;
    }

    button {
        margin-top: 15%;
    }
</style>