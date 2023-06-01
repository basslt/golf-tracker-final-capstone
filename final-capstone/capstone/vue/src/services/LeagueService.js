import axios from 'axios';


export default {
  getLeagues() {
    return axios.get(`/league`);
  },

  getLeagueById(leagueId) {
    return axios.get(`/league/${leagueId}`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  getLeagueByName(name) {
    return axios.get(`/league?name=${name}`) .then(response => {
      return response.data; // Return the response data
    })
    .catch(error => {
      console.error('Failed to retrieve league by name:', error);
      throw error; // Throw the error to be handled by the calling function
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
