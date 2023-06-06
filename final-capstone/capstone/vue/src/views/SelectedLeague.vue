<template>
  <div >
        <header>
            <hamburger-menu />
        </header>
        <div class="main">
      <body>

        <main class="content">
            <!-- <league-leaderboard id="leaderboard" v-bind:league-id="leagueId"/> -->
            <league-member-list :league-id="leagueId" />
            <league-invite-form v-bind:league-id="leagueId" />
            <select-course  v-bind:league-id="leagueId"/>
            <create-league v-if="this.$store.state.showCreateForm"/>
            <tee-time-page/>
            <tee-time v-bind:league-id="leagueId"/>
        </main>
        <aside>
            <league-page-nav />
        </aside>
      </body>
      </div>
      
  </div>
</template>

<script>
// import LeagueLeaderboard from "../components/LeagueLeaderboard.vue"
import LeaguePageNav from "../components/LeaguePageNav.vue" 
import LeagueMemberList from '../components/LeagueMemberList.vue'
import HamburgerMenu from '../components/HamburgerMenu.vue'
import LeagueInviteForm from '../components/LeagueInviteForm.vue'
import SelectCourse from '../components/SelectCourse.vue'
import CreateLeague from '../components/CreateLeague.vue'
import TeeTimePage from '../components/TeeTimePage.vue'
import TeeTime from '../components/TeeTime.vue'
export default {
    components: {
        // LeagueLeaderboard,
        LeaguePageNav,
        LeagueMemberList,
        HamburgerMenu,
        LeagueInviteForm,
        SelectCourse,
        CreateLeague,
        TeeTimePage,
        TeeTime
    },
    data() {
        return {
            leagueId: null
        }
    },
    mounted() {
        this.leagueId = parseInt(this.$route.params.id);
    }

}

</script>

<style scoped>
body {
    display: grid;
    height: 100vh;
    width: 100vw;
    grid-template-columns: 0.5fr 1fr 1fr 1fr;
    grid-template-areas: 
        "header header header header"
        "sidebar content content content"
        "footer footer footer footer";

}

.content {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
}

header {
    grid-area: header;
}

footer {
    grid-area: footer;
}

aside {
    grid-area: sidebar;
    background-color: lightgray;

}

main {
    grid-area: content;
    align-items: center;
}

#leaderboard {
    
}


</style>