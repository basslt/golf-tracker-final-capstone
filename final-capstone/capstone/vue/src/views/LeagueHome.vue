<template>
  <div>
     <header>
        <main-navigation />
     </header>
     <div class="main">
        <body>

            <!-- <header>
                <router-link v-bind:to="{ name: 'home'}">Return to Home</router-link>
            </header> -->
            <main class="content">
                <league-list />
            </main>
            <aside>
                <league-page-nav />
            </aside>
        </body>
     </div>
      
  </div>
</template>

<script>
//import LeagueLeaderboard from "../components/LeagueLeaderboard.vue"
//import LeaguePageNav from "../components/LeaguePageNav.vue"
import MainNavigation from "../components/MainNavigation.vue"
import LeagueList from "../components/LeagueList.vue"

export default {
    components: {
        //LeagueLeaderboard,
        //LeaguePageNav,
        MainNavigation,
        LeagueList
    }

}
</script>

<style>

.main { 
    display: flex;
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
