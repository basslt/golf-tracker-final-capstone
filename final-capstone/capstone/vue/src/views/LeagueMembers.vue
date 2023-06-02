<template>
  <div>
      <header>
          <main-navigation />
      </header>
      <div class="main">
          <body>
              <league-member-list />
          </body>
      </div>
  </div>
</template>

<script>
import LeagueMemberList from '../components/LeagueMemberList.vue'
import MainNavigation from '../components/MainNavigation.vue'

export default {
    components: {
        LeagueMemberList,
        MainNavigation
    }

}
</script>

<style>

</style>