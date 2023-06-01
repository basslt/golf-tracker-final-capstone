<template>

  <div>
      <header>
              <router-link v-bind:to="{ name: 'home'}">Return to Home</router-link>
        </header>
      <league-invite-form />
  </div>
</template>

<script>
import LeagueInviteForm from '../components/LeagueInviteForm.vue';

export default {
    components: {
        LeagueInviteForm
    }

}
</script>

<style>

</style>