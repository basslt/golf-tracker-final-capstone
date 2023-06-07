import axios from 'axios';

const BASE_URL = '/teetimes';

export default {
  getTeeTimeById(teeTimeId) {
    return axios.get(`${BASE_URL}/${teeTimeId}`);
  },

  getAllTeeTimes() {
    return axios.get(BASE_URL);
  },

  createTeeTime(teeTime) {
    return axios.post(BASE_URL, teeTime);
  },

  updateTeeTime(teeTimeId, teeTime) {
    return axios.put(`${BASE_URL}/${teeTimeId}`, teeTime);
  },

  deleteTeeTime(teeTimeId) {
    return axios.delete(`${BASE_URL}/${teeTimeId}`);
  },

  getTeeTimesByCourse(courseId) {
    return axios.get(`${BASE_URL}/course/${courseId}`);
  },

  getLeagueIdByTeeTimeId(teeTimeId) {
    return axios.get(`/teetimes/${teeTimeId}/league`);
  },
  getTeeTimeIdByMatchId(matchId) {
    return axios.get(`/matches/${matchId}/teetimeid`);
  },
  getLeagueIdByUsername(username) {
    return axios.get(`/leagues/${username}/leagueId`);
  }
};

