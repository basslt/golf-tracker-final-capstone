<template>
  <div>
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
