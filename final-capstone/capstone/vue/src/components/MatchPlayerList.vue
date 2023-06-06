<template>
  <div>
    <h4>User List</h4>
    <ul>
      <li v-for="user in userList" :key="user.id">{{ user.name }}</li>
    </ul>
    <button @click="showScoreLogForm">Add Score</button>
  </div>
</template>

<script>
import UserService from '../services/UserService';
export default {
  props: ['teeTimeId'],
  data() {
    return {
      userList: []
    };
  },
  created() {
    this.fetchUserList();
  },
  methods: {
    fetchUserList() {
      UserService.getUserListByTeeTimeId(this.teeTimeId)
        .then(response => {
          this.userList = response.data;
        })
        .catch(error => {
          console.log('Error fetching user list:', error);
        });
    },
    showScoreLogForm() {
      this.$emit('show-score-log-form');
    }
  }
};
</script>
<style scoped>
div {
display : block;
}
</style>
