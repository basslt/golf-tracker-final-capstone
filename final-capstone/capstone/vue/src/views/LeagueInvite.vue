<template>
  <div>
      <header>
        <main-navigation />
      </header>
      <div>
      <body>
        <league-invite-form />
      </body>
      </div>
  </div>
</template>

<script>
import LeagueInviteForm from '../components/LeagueInviteForm.vue';
import MainNavigation from '../components/MainNavigation.vue';

export default {
    components: {
        LeagueInviteForm,
        MainNavigation
    }

}
</script>

<style>

</style>