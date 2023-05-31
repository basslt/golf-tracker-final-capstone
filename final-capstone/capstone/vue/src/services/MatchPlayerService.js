import axios from 'axios';

export default {
  getMatchPlayerById(matchPlayerId) {
    return axios.get(`/matchplayers/${matchPlayerId}`)
      .then(response => response.data)
      .catch(error => {
        if (error.response && error.response.status === 404) {
          throw new Error('Match player not found');
        }
        throw new Error('Failed to fetch match player');
      });
  },

  getAllMatchPlayers() {
    return axios.get('/matchplayers')
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch match players');
      });
  },

  getMatchPlayersByMatch(matchId) {
    return axios.get(`/matches/${matchId}/matchplayers`)
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch match players');
      });
  },

  getMatchPlayersByPlayer(playerId) {
    return axios.get(`/players/${playerId}/matchplayers`)
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch match players');
      });
  },

  createMatchPlayer(matchPlayer) {
    return axios.post('/matchplayers', matchPlayer)
      .then(() => {})
      .catch(Error => {
        throw new Error('Failed to create match player');
      });
  },

  updateMatchPlayer(matchPlayerId, matchPlayer) {
    return axios.put(`/matchplayers/${matchPlayerId}`, matchPlayer)
      .then(() => {})
      .catch(Error => {
        throw new Error('Failed to update match player');
      });
  },

  deleteMatchPlayer(matchPlayerId) {
    return axios.delete(`/matchplayers/${matchPlayerId}`)
      .then(() => {})
      .catch(Error => {
        throw new Error('Failed to delete match player');
      });
  }
};
