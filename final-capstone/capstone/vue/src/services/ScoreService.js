import axios from 'axios';

export default {
  getScoreById(scoreId) {
    return axios.get(`/scores/${scoreId}`);
  },

  getLatestPlayerScore(leagueId, playerId){
      return axios.get(`/scores/${leagueId}/most-recent/${playerId}`)
  },

  getAllScores() {
    return axios.get('/scores');
  },

  getScoresByMatch(matchId) {
    return axios.get(`/matches/${matchId}/scores`);
  },

  getScoresByPlayer(playerId) {
    return axios.get(`/players/${playerId}/scores`);
  },

  createScore(score) {
    return axios.post('/scores', score);
  },

  updateScore(scoreId, score) {
    return axios.put(`/scores/${scoreId}`, score);
  },

  deleteScore(scoreId) {
    return axios.delete(`/scores/${scoreId}`);
  }
};
