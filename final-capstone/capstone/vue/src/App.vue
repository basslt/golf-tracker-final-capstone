 <template>

  <div id="app">
   
    <div id="nav">
      <!-- <hamburger-menu/> -->
    </div>
    <router-view />
  </div>
</template>


<script>
//import MainNavigation from './components/MainNavigation.vue'

// import HamburgerMenu from './components/HamburgerMenu.vue'
export default {
  components: {
    //MainNavigation
    // HamburgerMenu
    
  }
}
</script>

<style>
* {
  font-family: sans-serif;
  box-sizing: border-box;
  margin: 0;
}
</style>