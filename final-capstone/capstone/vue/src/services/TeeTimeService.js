import axios from 'axios';

export default {
  getTeeTimeById(teeTimeId) {
    return axios.get(`/${teeTimeId}`)
      .then(response => response.data)
      .catch(error => {
        if (error.response && error.response.status === 404) {
          throw new Error('Tee time not found');
        }
        throw new Error('Failed to fetch tee time');
      });
  },

  getAllTeeTimes() {
    return axios.get('/')
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch tee times');
      });
  },

  getTeeTimesByCourse(courseId) {
    return axios.get('/', {
        params: {
          courseId: courseId
        }
      })
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch tee times by course');
      });
  },

  getTeeTimesByPlayer(playerId) {
    return axios.get('/', {
        params: {
          playerId: playerId
        }
      })
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch tee times by player');
      });
  },

  createTeeTime(teeTime) {
    return axios.post('/', teeTime)
      .then(() => {})
      .catch(Error => {
        throw new Error('Failed to create tee time');
      });
  },

  updateTeeTime(teeTimeId, teeTime) {
    return axios.put(`/${teeTimeId}`, teeTime)
      .then(() => {})
      .catch(Error => {
        throw new Error('Failed to update tee time');
      });
  },

  deleteTeeTime(teeTimeId) {
    return axios.delete(`/${teeTimeId}`)
      .then(() => {})
      .catch(Error => {
        throw new Error('Failed to delete tee time');
      });
  }
};
