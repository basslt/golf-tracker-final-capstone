<template>
  <div class="main">
      <router-link v-bind:to="{ name: 'LeagueMembers' }">Members</router-link>
      <!-- <router-link v-bind:to="{name : 'LeagueInvite' }">Invite Members</router-link> -->
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