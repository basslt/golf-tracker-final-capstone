import axios from 'axios';



export default {
  getAllLeagueMemberships() {
    return axios.get(`/leaguemembership`);
  },

  getLeagueMembershipByLeagueMembershipId(leagueMembershipId) {
    return axios.get(`/leaguemembership/${leagueMembershipId}`);
  },

  getLeagueMembersByLeagueId(leagueId) {
    return axios.get(`/league/${leagueId}/leaguemembership`)
  },

  // getLeagueMembershipByLeagueId(leagueId) {
  //   return axios.get(`/league/${leagueId}/leaguemembership`)
  // },

  // getLeagueMembershipByLeagueId(leagueId) {
  //   return axios.get(`/leaguemembership/${leagueId}`)
  // },

  // getLeagueMembershipByLeagueId(leagueId) {
  //   return axios.get(`/leaguemembership/league/${leagueId}`)
  // },


  getLeagueMembershipByUserId(userId) {
    return axios.get(`/users/${userId}/leaguemembership`);
  },

  addLeagueMembership(leagueMembership) {
    return axios.post(`/leaguemembership`, leagueMembership);
  },

  updateLeagueMembership(leagueMembershipId, leagueMembership) {
    return axios.put(`/leaguemembership/${leagueMembershipId}`, leagueMembership);
  },

  deleteLeagueMembership(leagueMembershipId) {
    return axios.delete(`/leaguemembership/${leagueMembershipId}`);
  },
  
};
