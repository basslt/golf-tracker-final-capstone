<template>
  <div>    <div>{{ teeTime.matchName }}</div>
    <div>{{ teeTime.time }}</div>
    <router-link :to="'/teetimes/' + teeTime.id">View Players</router-link>
    <h3>Tee Time ID: {{ teeTimeId }}</h3>
    <button @click="showUserList">Show Users</button>

  </div>
</template>

<script>
export default {
  props: ['teeTimeId'],
  methods: {
    showUserList() {
      this.$emit('show-users', this.teeTimeId);
    }
  }
};
</script>

<style scoped>
div {
display : block;
}
</style>