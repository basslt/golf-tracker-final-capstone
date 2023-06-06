import axios from 'axios';


export default {
  getLeaderboardById(leaderboardId) {
    return axios.get(`/leaderboards/${leaderboardId}`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },
  getTopPlayersByScoringAverage() {
    const url = `leaderboard/top-players`;
    return axios.get(url)
  },
  getLeaderboardsByLeagueId(leagueId) {
    return axios.get(`/leaderboards/league/${leagueId}`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  getLeaderboardsByUserId(userId) {
    return axios.get(`/leaderboards/user/${userId}`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  addLeaderboard(leaderboard) {
    return axios.post(`/leaderboards`, leaderboard)
      .then(() => {})
      .catch(error => {
        throw error;
      });
  },

  updateLeaderboard(leaderboardId, leaderboard) {
    return axios.put(`/leaderboards/${leaderboardId}`, leaderboard)
      .then(() => {})
      .catch(error => {
        throw error;
      });
  },

  deleteLeaderboard(leaderboardId) {
    return axios.delete(`/leaderboards/${leaderboardId}`)
      .then(() => {})
      .catch(error => {
        throw error;
      });
  },
};
