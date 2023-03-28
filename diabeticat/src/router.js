import { createRouter, createWebHistory } from 'vue-router';

import LandingPage from './pages/LandingFrame.vue'
import RegisterDialog from './pages/RegisterDialog.vue'
import HomeScreen from './pages/HomeScreen.vue'
import Cc from './pages/CFrame.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
      { path: '/', redirect: '/Landing' },
      { path: '/Landingpage', component: LandingPage },
      { path: '/RegisterDialog', component: RegisterDialog },
      { path: '/HomeScreen', component: HomeScreen },
      { path: '/C', component: Cc },
      { path: '/:notFound(.*)', component: LandingPage }
    ]
  });
  
  export default router;