import axios from 'axios';

export default {
  getUserIdByUsername(username) {
    return axios.get(`/users/id/${username}`)
  },

  getUsernameById(userId) {
    return axios.get(`/users/username/${userId}`)
      .then(response => response.data)
      .catch(error => {
        if (error.response && error.response.status === 404) {
          throw new Error('User not found');
        }
        throw new Error('Failed to fetch username');
      });
  },

  getUserById(userId) {
    return axios.get(`/users/${userId}`)
      
  },

  getAllUsers() {
    return axios.get('/users')
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch users');
      });
  },

  getUserByUsername(username) {
    return axios.get(`/users/username/${username}`)
      .then(response => response.data)
      .catch(error => {
        if (error.response && error.response.status === 404) {
          throw new Error('User not found');
        }
        throw new Error('Failed to fetch user');
      });
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
