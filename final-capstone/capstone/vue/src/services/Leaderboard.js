import axios from 'axios';


export default {

  getOrderedLeaderboard(leagueId) {
    return axios.get(`/leaderboards/${leagueId}`);
  },

  // getLeaderboardById(leaderboardId) {
  //   return axios.get(`/leaderboards/${leaderboardId}`);
  // },

  getLeaderboardsByLeagueId(leagueId) {
    return axios.get(`/leaderboards/league/${leagueId}`);
  },

  getLeaderboardsByUserId(userId) {
    return axios.get(`/leaderboards/user/${userId}`);
  },

  addLeaderboard(leaderboard) {
    return axios.post(`/leaderboards`, leaderboard);
  },

  updateLeaderboard(leaderboardId, leaderboard) {
    return axios.put(`/leaderboards/${leaderboardId}`, leaderboard);
  },

  deleteLeaderboard(leaderboardId) {
    return axios.delete(`/leaderboards/${leaderboardId}`);
  },
};
