<template>
  <div>
        <header>
            <hamburger-menu />
        </header>
        <div class="main">
      <body>

        <main class="content">
            <div class="leaderboard">
            <league-leaderboard v-bind:league-id="leagueId"/>
            
            <create-league v-if="this.$store.state.showCreateForm"/>
            <tee-time-list />
            
            <tee-time-card/>

            <button class="new-tee-time-button" @click="showTeeTimeCreateForm=true">Schedule Tee Time</button>
            <tee-time-form v-if="showTeeTimeCreateForm" v-bind:league-id="leagueId" @close="showTeeTimeCreateForm=false"/>
            
            <button class="league-invite-form" @click="showLeagueInviteForm=true">Invite Members</button>
            <league-invite-form v-if="showLeagueInviteForm" v-bind:league-id="leagueId" @close="showLeagueInviteForm=false" />

            <league-member-list v-bind:league-id="leagueId"/>

        </main>
      </body>
      </div>
      
  </div>
</template>

<script>
import LeagueLeaderboard from "../components/LeagueLeaderboard.vue"
import HamburgerMenu from '../components/HamburgerMenu.vue'
import LeagueInviteForm from '../components/LeagueInviteForm.vue'
import CreateLeague from '../components/CreateLeague.vue'
import TeeTimeForm from '../components/TeeTimeForm.vue'
import TeeTimeList from '../components/TeeTimeList.vue'
import TeeTimeCard from '../components/TeeTimeCard.vue'
import LeagueMemberList from '../components/LeagueMemberList.vue'


export default {
    components: {
        LeagueLeaderboard,
        HamburgerMenu,
        LeagueInviteForm,
        CreateLeague,
        TeeTimeForm,
        TeeTimeList,
        TeeTimeCard,
        LeagueMemberList
    },
    data() {
        return {
            leagueId: null,
            showTeeTimeCreateForm: false,
            showLeagueInviteForm: false,
        }
    },
    created() {
        this.leagueId = parseInt(this.$route.params.id);
    }

}

</script>

<style scoped>


.leaderboard{
    display: flex;
    align-items: center;
    justify-content: center;
    padding-top: 100px;

}


.new-tee-time-button{
    
}


/* body {
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
    
} */


</style>