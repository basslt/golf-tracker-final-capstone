<template>
  <div>
     <header>
        <hamburger-menu />
     </header>
     <div class="main">
        <body>
            <main class="content">
                <league-list />
                 <create-league v-if="this.$store.state.showCreateForm"/>
            </main>
        </body>
     </div>
     
  </div>
</template>

<script>
import LeagueList from "../components/LeagueList.vue"
import CreateLeague from "../components/CreateLeague.vue"
import HamburgerMenu from '../components/HamburgerMenu.vue'

export default {
    components: {
        LeagueList,
        CreateLeague,
        HamburgerMenu
    }

}
</script>

<style scoped>

.main { 
    display: flex;
    justify-content: center;
    align-items: center;
}

.content {
    display: flex;
    justify-content: center;
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
