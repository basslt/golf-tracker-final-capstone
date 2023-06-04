import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import AddLeague from '../views/AddLeague.vue'
import LeagueHome from '../views/LeagueHome.vue'
import LeagueInvite from '../views/LeagueInvite.vue'
import AllMessagesView from '../views/AllMessagesView.vue'

import MessageView from '../views/MessageView.vue';
import ComposeMessage from '../components/ComposeMessage.vue';
import SentMessagesView from '../views/SentMessagesView.vue';
import ReceivedMessagesView from '../views/RecievedMessagesView.vue';

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
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
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
      path: "/add-league",
      name: "AddLeague",
      component: AddLeague,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/league/:id",
      name: "League",
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
      path: '/messages',
      component: AllMessagesView,
    },
    {
      path: '/messages/:id',
      component: MessageView,
      props: true,
    },
    {
      path: '/compose',
      component: ComposeMessage,
    },
    {
      path: '/sent',
      component: SentMessagesView,
    },
    {
      path: '/received',
      component: ReceivedMessagesView,
    },
    {
      path: '*',
      redirect: '/messages',
    },
  ],
})

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
