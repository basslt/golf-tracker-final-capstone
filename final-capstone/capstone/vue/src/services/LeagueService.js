import axios from 'axios';



export default {
  getLeagues() {
    return axios.get(`/league`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  getLeagueById(leagueId) {
    return axios.get(`/league/${leagueId}`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  getLeagueByName(name) {
    return axios.get(`/league?name=${name}`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  addLeague(league) {
    return axios.post(`/league`, league);
  },

  updateLeague(leagueId, league) {
    return axios.put(`/league/${leagueId}`, league)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  deleteLeague(leagueId) {
    return axios.delete(`/league/${leagueId}`)
      .then(() => {})
      .catch(error => {
        throw error;
      });
  },
};
