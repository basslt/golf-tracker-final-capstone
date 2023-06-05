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
      this.isOpen = false;
    }
  }
};
</script>

<style scoped>
.container {
  position: relative;
  display: flex;
  align-items: center;
  flex-grow: 1;
  background-color: #059262;
  height: 100px;
}

.hamburger-menu {

  z-index: 2;
    display: flex;
  align-items: center;
  position: relative;
  z-index: 2;
  margin-left: 20px;
  
}

.menu-items {
  /* Initial state */
  display: none;
  opacity: 0;
  transform: translateY(-10px);
  transition: opacity 0.3s ease-in-out, transform 0.3s ease-in-out;
  list-style: none;
  padding: 0;
  margin-top: 10px;
  position: fixed;
  top: 0;
  padding-left: 20px;
  padding-top: 100px;
  border-radius: 15px;
  background: #059262;
  width: 200px;
  height: 400px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.menu-items.open {
  /* Open state */
  display: block;
  opacity: 1;
  transform: translateY(0);
}


.menu-icon {
  
 
  width: 40px;
  height: 25px;
  
  cursor: pointer;
  z-index: 2;
}

.menu-icon span {
  display: block;
  position: absolute;
  align-self: center;
  height: 3px;
  width: 100%;
  background: rgb(255, 255, 255);
  transition: transform 0.3s ease-in-out;
  margin-left: 50px;
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



.menu-items li  {
  padding: 10px;
}




.menu-items a {
  color: rgb(255, 255, 255);
  text-decoration: none;
  font-weight: bold;
  font-size: 20px;
}

.menu-items a:hover {
  font-weight: bold;
}

.container .logo {
  display: flex;
  flex-grow: 1;
  justify-content: center;
  align-items: center;
}

.container .logo img {
  width: auto;
  height: 275px;
}
</style>