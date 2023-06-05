import axios from 'axios';

export default {
  getMatchById(matchId) {
    return axios.get(`/matches/${matchId}`)
      .then(response => response.data)
      .catch(error => {
        if (error.response && error.response.status === 404) {
          throw new Error('Match not found');
        }
        throw new Error('Failed to fetch match');
      });
  },
  logScore(matchId, score) {
    return axios.post(`/matches/${matchId}/scores`, score)
     
    },

  getAllMatches() {
    return axios.get('/matches')
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch matches');
      });
  },

  getMatchesByLeague(leagueId) {
    return axios.get('/matches', { params: { leagueId: leagueId } })
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch matches');
      });
  },

  addMatch(match) {
    return axios.post('/matches', match)
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to add match');
      });
  },

  updateMatch(matchId, match) {
    return axios.put(`/matches/${matchId}`, match)
      .then(response => response.data)
      .catch(error => {
        if (error.response && error.response.status === 404) {
          throw new Error('Match not found');
        }
        throw new Error('Failed to update match');
      });
  },

  deleteMatch(matchId) {
    return axios.delete(`/matches/${matchId}`)
      .then(() => {})
      .catch(error => {
        if (error.response && error.response.status === 404) {
          throw new Error('Match not found');
        }
        throw new Error('Failed to delete match');
      });
  }
};
