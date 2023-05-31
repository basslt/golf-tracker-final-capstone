import axios from 'axios';

export default {
  getCourse(courseId) {
    return axios.get(`/courses/${courseId}`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  getAllCourses() {
    return axios.get(`/courses`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  getCoursesByState(state) {
    return axios.get(`/courses?state=${state}`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  getCoursesByCity(city) {
    return axios.get(`/courses?city=${city}`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  addCourse(course) {
    return axios.post(`/courses`, course)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  updateCourse(courseId, course) {
    return axios.put(`/courses/${courseId}`, course)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },

  deleteCourse(courseId) {
    return axios.delete(`/courses/${courseId}`)
      .then(() => {})
      .catch(error => {
        throw error;
      });
  },
};
