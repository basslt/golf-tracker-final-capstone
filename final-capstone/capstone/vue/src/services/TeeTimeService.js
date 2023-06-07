import axios from 'axios';

const BASE_URL = '/teetimes';

export default {
  getTeeTimeById(teeTimeId) {
    return axios.get(`${BASE_URL}/${teeTimeId}`)
  },

  getAllTeeTimes() {
    return axios.get(BASE_URL)
     
  },

  createTeeTime(teeTime) {
    return axios.post(BASE_URL, teeTime)
  },

  updateTeeTime(teeTimeId, teeTime) {
    return axios.put(`${BASE_URL}/${teeTimeId}`, teeTime)
      .then(() => {})
      .catch(error => {
        throw new error('Failed to update TeeTime');
      });
  },

  deleteTeeTime(teeTimeId) {
    return axios.delete(`${BASE_URL}/${teeTimeId}`)
      .then(() => {})
      .catch(error => {
        throw new error('Failed to delete TeeTime');
      });
  },

  getTeeTimesByCourse(courseId) {
    return axios.get(`${BASE_URL}/course/${courseId}`)
      .then(response => response.data)
      .catch(error => {
        throw new error('Failed to fetch TeeTimes by Course');
      });
  },
  getLeagueIdByTeeTimeId(teeTimeId) {
    return axios.get(`/teetimes/${teeTimeId}/league`)
  },
  getTeeTimeIdByMatchId(matchId) {
    return axios.get(`/matches/${matchId}/teetimeid`)
  },
  getLeagueIdByUsername(username) {
    return axios.get(`/leagues/${username}/leagueId`);
  }
};

