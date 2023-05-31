import axios from 'axios';

export default {
  getScoreById(scoreId) {
    return axios.get(`/scores/${scoreId}`)
      .then(response => response.data)
      .catch(error => {
        if (error.response && error.response.status === 404) {
          throw new Error('Score not found');
        }
        throw new Error('Failed to fetch score');
      });
  },

  getAllScores() {
    return axios.get('/scores')
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch scores');
      });
  },

  getScoresByMatch(matchId) {
    return axios.get(`/matches/${matchId}/scores`)
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch scores by match');
      });
  },

  getScoresByPlayer(playerId) {
    return axios.get(`/players/${playerId}/scores`)
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch scores by player');
      });
  },

  createScore(score) {
    return axios.post('/scores', score)
      .then(() => {})
      .catch(Error => {
        throw new Error('Failed to create score');
      });
  },

  updateScore(scoreId, score) {
    return axios.put(`/scores/${scoreId}`, score)
      .then(() => {})
      .catch(Error => {
        throw new Error('Failed to update score');
      });
  },

  deleteScore(scoreId) {
    return axios.delete(`/scores/${scoreId}`)
      .then(() => {})
      .catch(Error => {
        throw new Error('Failed to delete score');
      });
  }
};
