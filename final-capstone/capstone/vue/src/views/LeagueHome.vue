<template>
  <div>
     <header>
        <hamburger-menu />
     </header>
     <div class="main">
        <body>
            <main class="content">
                <league-list />
                <create-league />
            </main>
        </body>
     </div>
      
  </div>
</template>

<script>
// import MainNavigation from "../components/MainNavigation.vue"
import LeagueList from "../components/LeagueList.vue"
import CreateLeague from "../components/CreateLeague.vue"
import HamburgerMenu from '../components/HamburgerMenu.vue'

export default {
    components: {
        // MainNavigation,
        LeagueList,
        CreateLeague,
        HamburgerMenu
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
