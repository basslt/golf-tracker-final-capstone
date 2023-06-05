<template>
<div class="container">    
    <div class="hamburger-menu">
      <div class="menu-icon" :class="{ open: isOpen }" @click="toggleMenu">
        <span></span>
        <span></span>
        <span></span>
      </div>
      <ul class="menu-items" :class="{ open: isOpen }">
        <router-link v-bind:to="{ name: 'home' }">
          <li><a href="#">Home</a></li>
        </router-link>
        <router-link v-bind:to="{ name: 'LeagueHome' }">
          <li><a href="#">Leagues</a></li>
        </router-link>
          <li @click="showCreateForm"><a href="#">Create a League</a></li>
        <router-link v-bind:to="{ name: 'Messages' }">
          <li><a href="#">Messages</a></li>
        </router-link>
        <router-link v-bind:to="{ name: 'LandingPage' }" v-on:click="logOut">
          <li><a href="#">Log Out</a></li>
        </router-link>
      </ul>
    </div>
    <div class="logo">
      <img src="../assets/official-logo.png" alt="">
    </div>
    <div class = "main" >
    <!-- <CreateLeague v-show="showCreateForm" v-close="showCreateForm = false"/> hey 
    <CreateLeague v-close="showCreateForm = false"/> hey -->
    </div>
  </div>
      
      
  
        
</template>

<script>

export default {
  components: {

  },
  data() {
    return {
      isOpen: false,
    };
  },
  methods: {
    toggleMenu() {
      this.isOpen = !this.isOpen;
    },
    logOut() {
      this.$store.commit("LOGOUT");
      this.$router.push("/");
    },
    showCreateForm() {
      this.$store.commit("SET_SHOW_CREATE_FORM_STATUS", true);
    }
  }
};
</script>

<style scoped>

.container .logo {
    display: flex;
    flex-grow: 1;
    justify-content: center;
}

.container .logo img {
  width: auto;
  height: 275px;
}

.main{
  display: absolute;
  top: 0; 
  left: 0; 
  width: 100%;
  height: 100%; 
  justify-content: center;
  align-items: center;
}

.container {
    display: flex;
    align-items: center;
    flex-grow: 1;    
    background-color: #059262;
}

/* .hamburger-menu {
  display: flex;
  align-items: center;
} */

.menu-icon {
  display: block;
  width: 40px;
  height: 25px;
  position: relative;
  cursor: pointer;
  z-index: 2;
}

.menu-icon span {
  display: block;
  position: absolute;
  height: 3px;
  width: 100%;
  background: rgb(255, 255, 255);
  transition: transform 0.3s ease-in-out;
  margin-left: 20px;
}

.menu-icon span:nth-child(1) {
  top: 0;
}

.menu-icon span:nth-child(2) {
  top: 50%;
  transform: translateY(-50%);
}

.menu-icon span:nth-child(3) {
  bottom: 0;
}

.menu-items {
  list-style: none;
  padding: 0;
  margin: 0;
  position: fixed;
  top: 0;
  left: -200px;
  background: #059262;
  width: 200px;
  height: 100vh;
  /* border: 1px solid #333; */
  z-index: 1;
  transition: left 0.3s ease-in-out;
  margin-top: 95px;
}

.menu-items.open {
  left: 0;
}

.menu-items li {
  padding: 10px;
}

.menu-items a {
  color: rgb(255, 255, 255);
  text-decoration: none;
  font-weight: bold;
  font-size: 20px;
}

.menu-items :hover {
  font-weight: bold;
}

</style>