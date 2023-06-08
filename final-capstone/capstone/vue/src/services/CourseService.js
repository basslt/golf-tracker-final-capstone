import axios from 'axios';

export default {
  getCourse(courseId) {
    return axios.get(`/courses/${courseId}`)
      .then(response => response.data)
      .catch(error => {
        throw error;
      });
  },
  
    getCourseName(courseId) {
      return axios.get(`/courses/coursename/${courseId}`)
        .then(response => response.data)
        .catch(error => {
          throw error;
        });
    },

  getAllCourses() {
    return axios.get(`/courses`)
     
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
  getCoursesByFilter(params)  {
    return axios.get(`/courses`, { params })
      .then(response => response.data)
      .catch(error => {
        throw new Error(`Error: ${error}`);
      });
  },
  
  // getCoursesByFilter(name, state, city) {
  //   const queryParams = new URLSearchParams();
  //   if (name) {
  //     queryParams.append('name', name);
  //   }
  //   if (state) {
  //     queryParams.append('state', state);
  //   }
  //   if (city) {
  //     queryParams.append('city', city);
  //   }
  
  //   const url = `/courses?${queryParams.toString()}`;
  
  //   return axios
  //     .get(url)
  //     .then(response => response.data)
  //     .catch(error => {
  //       throw error;
  //     });
  // }
};
