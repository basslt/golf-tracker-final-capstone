import axios from 'axios';

export default {
  getMatchPlayerById(matchPlayerId) {
    return axios.get(`/matchplayers/${matchPlayerId}`);
  },

  getAllMatchPlayers() {
    return axios.get('/matchplayers');
  },

  getMatchPlayersByMatch(matchId) {
    return axios.get(`/matches/${matchId}/matchplayers`);
  },

  getMatchPlayersByPlayer(playerId) {
    return axios.get(`/players/${playerId}/matchplayers`);
  },

  createMatchPlayer(matchPlayer) {
    return axios.post('/matchplayers', matchPlayer);
  },

  updateMatchPlayer(matchPlayerId, matchPlayer) {
    return axios.put(`/matchplayers/${matchPlayerId}`, matchPlayer);
  },

  deleteMatchPlayer(matchPlayerId) {
    return axios.delete(`/matchplayers/${matchPlayerId}`);
  }
};
