import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import LeagueHome from '../views/LeagueHome.vue'
import LeagueInvite from '../views/LeagueInvite.vue'
import LandingPage from '../views/LandingPage.vue'
import SelectedLeague from '../views/SelectedLeague.vue'
import Messages from '../views/Messages.vue';
import Match from '../views/MatchView.vue';
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
    {
      path: "/",
      name: "LandingPage",
      component: LandingPage,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/messages",
      name: "Messages",
      component: Messages,
      meta: {
        requiresAuth: true
      }
    },
    {
    path: "/match",
    name: "Match",
    component: Match,
    meta: {
      requiresAuth: true
    }
    }
    
  ]
});

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
