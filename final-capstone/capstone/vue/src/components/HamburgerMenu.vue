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
          <li><a href="#"><i class="fa-solid fa-house" style="color: #ffffff;"></i> Home </a></li>
        </router-link>
        <!-- <router-link v-bind:to="{ name: 'LeagueHome' }">
          <li><a href="#">Leagues</a></li>
        </router-link> -->
          <li @click="showCreateForm"><a href="#"><i class="fa-solid fa-circle-plus" style="color: #ffffff;"></i> Create League</a></li>
        <router-link v-bind:to="{ name: 'Messages' }">
          <li><a href="#"> <i class="fa-solid fa-message" style="color: #ffffff;"></i> Messages </a></li>
        </router-link>
        <router-link v-bind:to="{ name: 'LandingPage' }" v-on:click="logOut">
          <li><a href="#"><i class="fa-solid fa-arrow-right-from-bracket" style="color: #ffffff;"></i> Log Out</a></li>
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
  height: 120px;
 
}

.hamburger-menu {

  /* z-index: 2; */
    display: flex;
  align-items: center;
  position: relative;
  /* z-index: 2; */
  margin-left: 20px;
  padding-right: 10px;
  
  
}

.menu-items {
  /* Initial state */
  
  opacity: 0;
  transform: translateY(-10px);
  transition: opacity 0.3s ease-in-out, transform 0.3s ease-in-out;
  list-style: none;
  padding-top: 100px;
  position: absolute;
  
  top: 0;
  padding-left: 20px;
  white-space: nowrap;
  border-radius: 15px;
  background: #059262;
  width: 210px;
  height: 310px;
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
 box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); 
  transition: box-shadow 0.3s ease;
  border-radius: 5px;
  padding: 5px;
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

.fa-solid.fa-house, .fa-solid.fa-circle-plus, .fa-solid.fa-message, .fa-solid.fa-arrow-right-from-bracket  {
  margin-right: 5px;
}





</style>