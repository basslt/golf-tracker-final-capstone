import axios from 'axios';

export default {
  getUserIdByUsername(username) {
    return axios.get(`/users/${username}/id`)
      .then(response => response.data)
      .catch(error => {
        if (error.response && error.response.status === 404) {
          throw new Error('User not found');
        }
        throw new Error('Failed to fetch user ID');
      });
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
      // .then(response => response.data)
      // .catch(Error => {
      //   throw new Error('Failed to fetch users');
      // });
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
  },
getCurrentUser() {
  const token = localStorage.getItem('authToken');
  if (!token) {
    
    return null;
  }

  return axios.get('/users/me', {
    headers: {
      Authorization: `Bearer ${token}`
    }
  })
    .then(response => response.data)
    .catch(error => {
      if (error.response && error.response.status === 401) {
        // Token is invalid or expired, clear it from localStorage
        localStorage.removeItem('authToken');
      }
      throw new Error('Failed to fetch current user');
    });
  },
}