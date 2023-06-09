import axios from 'axios';

export default {
  // getUserIdByUsername(username) {
  //   return axios.get(`/users/${username}/id`);
  // },

  getUserIdByUsername(username) {
    return axios.get(`/users/${username}/id`);
  },

  getUsernameById(userId) {
    return axios.get(`/users/${userId}/username`);
  },

    getUsernamesByIds(userIds) {
      const joinedIds = userIds.join(',');
      return axios.get(`/users/${joinedIds}/username`);
    },
  

  getUserById(userId) {
    return axios.get(`/users/${userId}`);
  },

  getAllUsernames() {
    return axios.get(`/users/username`);
  },

  getAllUsers() {
    return axios.get('/users');
  },

  findUsersNotInLeague(leagueId) {
    return axios.get(`/users/leaguemembership/not-in-league/${leagueId}`);
  },

  findUsersInLeague(leagueId) {
    return axios.get(`users/leaguemembership/league/${leagueId}`);
  },

  findUsersInMatch(matchId) {
    return axios.get(`users/matches/${matchId}`);
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