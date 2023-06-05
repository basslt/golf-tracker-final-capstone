<template>
  <div class="background">
    <div class="container">
      <h2>h2</h2>
      <button class="exit-button" v-on:click="closeMessage">x</button>
    </div>
      
  </div>
</template>

<script>
export default {
  name: "message-detail",
  props: {
        message: {
            type: Object
        }
  },
  methods: {
    closeMessage() {
      this.$emit('close');
    }
  }

}
</script>

<style scoped>

.background {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5); /* Adjust the alpha value (0.5) to control the transparency */
  display: flex;
  justify-content: center;
  align-items: center;
}

.container {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
   max-width: 400px; /* Adjust the width as needed */
  width: 100%; 
  display: flex;
  justify-content: center;
  align-items: center;
}

.exit-button {
  position: absolute;
  right: 35%;
  top: 25%;
}

</style>