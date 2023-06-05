import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

const currentToken = localStorage.getItem('token');
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    loggedUser: {
      username: ''
    },
    messages: [],
    showCreateForm: false,
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_LOGGED_USER_USERNAME(state, username) {
      state.loggedUser.username = username;
    },
    SET_LOGGED_USER_ID(state, id) {
      state.loggedUser.id = id;
    },
    SET_ACTIVE_LEAGUE(state, id) {
      state.activeLeague = id;
    },
    SET_MESSAGES(state, data) {
      state.messages = data;
    },
    
    SET_SHOW_CREATE_FORM_STATUS(state, value) {
      state.showCreateForm = value;
    }
  },
  actions: {
    getUserById({ commit }, userId) {
      return axios
        .get(`/users/${userId}`)
        .then((response) => {
          const user = response.data;
          commit('SET_USER', user);
          return user;
        })
        .catch((error) => {
          throw new Error(`Failed to fetch user: ${error}`);
        });
    },
    
  },
  getters: {
    getUser(state) {
      return state.user;
    },
   
  }
});
