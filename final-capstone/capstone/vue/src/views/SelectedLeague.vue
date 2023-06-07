<template>
  <div >
        <header>
            <hamburger-menu />
        </header>
        <div class="main">
      <body>

        <main class="content">
            <league-leaderboard :league-id="leagueId"/>
            <league-invite-form v-bind:league-id="leagueId" />
            <create-league v-if="this.$store.state.showCreateForm"/>
            <tee-time-list />
            <tee-time v-bind:league-id="leagueId"/>
            <tee-time-card/>
            
        </main>
        <aside>
            <league-page-nav />
        </aside>
      </body>
      </div>
      
  </div>
</template>

<script>
import LeagueLeaderboard from "../components/LeagueLeaderboard.vue"
import LeaguePageNav from "../components/LeaguePageNav.vue" 
import HamburgerMenu from '../components/HamburgerMenu.vue'
import LeagueInviteForm from '../components/LeagueInviteForm.vue'
import CreateLeague from '../components/CreateLeague.vue'
import TeeTime from '../components/TeeTime.vue'
import TeeTimeList from '../components/TeeTimeList.vue'
import TeeTimeCard from '../components/TeeTimeCard.vue'


export default {
    components: {
        LeagueLeaderboard,
        LeaguePageNav,
        HamburgerMenu,
        LeagueInviteForm,
        CreateLeague,
        TeeTime,
        TeeTimeList,
        TeeTimeCard,
        
    },
    data() {
        return {
            leagueId: null
        }
    },
    created() {
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



</style>