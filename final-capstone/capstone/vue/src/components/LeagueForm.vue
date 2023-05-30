<template>
  <div>
    <form @submit.prevent="submitForm">
      <input type="text" v-model="leagueName" placeholder="Enter league name" required>
      <button type="submit">Create</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      leagueName: ''
    };
  },
  methods: {
    submitForm() {
      this.$emit('create-league', this.leagueName);
      this.leagueName = ''; // Reset form field
    }
  }
}
</script>