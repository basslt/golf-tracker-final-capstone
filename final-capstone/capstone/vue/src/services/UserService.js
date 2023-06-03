import axios from 'axios';

export default {
  getUserIdByUsername(username) {
    return axios.get(`/users/${username}/id`);
  },

  getUsernameById(userId) {
    return axios.get(`/users/username/${userId}`);
  },

  getUserById(userId) {
    return axios.get(`/users/${userId}`);
  },

  getAllUsers() {
    return axios.get('/users');
  },

  getUserByUsername(username) {
    return axios.get(`/users/${username}`);
  },

  createUser(username, password, role) {
    const user = {
      username: username,
      password: password,
      role: role
    };
    return axios.post('/users', user)
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to create user');
      });
  }
};
