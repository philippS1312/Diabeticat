import { createRouter, createWebHistory } from 'vue-router';

import WelcomePage from './pages/WelcomePage.vue'
import LoginPage from './pages/LoginPage.vue'
import LandingPage from './pages/LandingFrame.vue'
import Aa from './pages/RegisterDialog.vue'
import Bb from './pages/BFrame.vue'
import Cc from './pages/CFrame.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
      { path: '/', component: WelcomePage },
      { path: '/login', component: LoginPage },
      { path: '/Landing', component: LandingPage },
      { path: '/A', component: Aa },
      { path: '/B', component: Bb },
      { path: '/C', component: Cc },
      { path: '/:notFound(.*)', component: LandingPage }
    ]
  });
  
  export default router;