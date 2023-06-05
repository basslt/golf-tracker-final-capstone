import axios from 'axios';

const BASE_URL = '/teetimes';

export default {
  getTeeTimeById(teeTimeId) {
    return axios.get(`${BASE_URL}/${teeTimeId}`)
      .then(response => response.data)
      .catch(error => {
        throw new error('Failed to fetch TeeTime by ID');
      });
  },

  getAllTeeTimes() {
    return axios.get(BASE_URL)
      .then(response => response.data)
      .catch(error => {
        throw new error('Failed to fetch all TeeTimes');
      });
  },

  createTeeTime(teeTime) {
    return axios.post(BASE_URL, teeTime)
      .then(() => {})
      .catch(error => {
        throw new error('Failed to create TeeTime');
      });
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
  }
};

