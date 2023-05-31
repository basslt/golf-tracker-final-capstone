<template>
  <div class="main">
      <body>
        <main>
            <league-leaderboard />
        </main>
        <aside>
            <league-page-nav />
        </aside>
      </body>
      
  </div>
</template>

<script>
import LeagueLeaderboard from "../components/LeagueLeaderboard.vue"
import LeaguePageNav from "../components/LeaguePageNav.vue"

export default {
    components: {
        LeagueLeaderboard,
        LeaguePageNav
    }

}
</script>

<style>
body {
    display: grid;
    height: 100vh;
    width: 100vw;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas: 
        "header header header"
        "sidebar content content"
        "footer footer footer";

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
}


</style>
