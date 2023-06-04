import axios from 'axios';



export default {
  getAllLeagueMemberships() {
    return axios.get(`/leaguemembership`);
  },

  getLeagueMembershipByLeagueMembershipId(leagueMembershipId) {
    return axios.get(`/leaguemembership/${leagueMembershipId}`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
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
    return axios.get(`/users/${userId}/leaguemembership`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  addLeagueMembership(leagueMembership) {
    return axios.post(`/leaguemembership`, leagueMembership)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  updateLeagueMembership(leagueMembershipId, leagueMembership) {
    return axios.put(`/leaguemembership/${leagueMembershipId}`, leagueMembership)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  deleteLeagueMembership(leagueMembershipId) {
    return axios.delete(`/leaguemembership/${leagueMembershipId}`)
      .then(() => {})
      .catch(error => {
        throw error;
      });
  },
};
