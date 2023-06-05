import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import LeagueHome from '../views/LeagueHome.vue'
import LeagueInvite from '../views/LeagueInvite.vue'
<<<<<<< HEAD
import AllMessagesView from '../views/AllMessagesView.vue'

import MessageView from '../views/MessageView.vue';
import ComposeMessage from '../components/ComposeMessage.vue';
import SentMessagesView from '../views/SentMessagesView.vue';
import ReceivedMessagesView from '../views/RecievedMessagesView.vue';

=======
import LandingPage from '../views/LandingPage.vue'
import SelectedLeague from '../views/SelectedLeague.vue'
import Messages from '../views/Messages.vue';
import Match from '../views/MatchView.vue';
>>>>>>> cdd24ad11c06bdc7234783d554d09b0eadcee21b
Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/home',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/league/:id",
      name: "SelectedLeague",
      component: SelectedLeague,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/leagues",
      name: "LeagueHome",
      component: LeagueHome,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/invite-to-league",
      name: "LeagueInvite",
      component: LeagueInvite,
      meta: {
        requiresAuth: true
      }
    },
