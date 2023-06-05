import axios from 'axios';


export default {
  getLeagues() {
    return axios.get(`/league`);
  },

  getLeagueById(leagueId) {
    return axios.get(`/league/${leagueId}`);
  },

  getLeagueByName(name) {
    return axios.get(`/league?name=${name}`);
  },

  getLeaguesByUserId(userId) {
    return axios.get(`/league/leaguemembership/${userId}`);
  },
  getLeaguesByOrganizerId(userId){
    return axios.get(`/league/leaguelist/${userId}`)
  },

  addLeague(league) {
    return axios.post(`/league`, league);
  },

  updateLeague(leagueId, league) {
    return axios.put(`/league/${leagueId}`, league);
  },

  deleteLeague(leagueId) {
    return axios.delete(`/league/${leagueId}`);
  }

};
