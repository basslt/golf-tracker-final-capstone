<template>
  <div>
      <header>
          <main-navigation />
      </header>
      <div class="main">
          <body class="content">
              <league-member-list />
              <league-invite-form />
          </body>
      </div>
  </div>
</template>

<script>
import LeagueMemberList from '../components/LeagueMemberList.vue'
import MainNavigation from '../components/MainNavigation.vue'
import LeagueInviteForm from '../components/LeagueInviteForm.vue'

export default {
    components: {
        LeagueMemberList,
        MainNavigation,
        LeagueInviteForm
    }

}
</script>

<style>
.main {
    display: flex;
}

.content {
    flex-grow: 1;
    justify-content: space-evenly;
    align-items: center;
}

</style>