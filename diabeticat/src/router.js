import { createRouter, createWebHistory } from 'vue-router';

import Landing from './pages/Landing.vue'
import Aa from './pages/A.vue'
import Bb from './pages/B.vue'
import Cc from './pages/C.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
      { path: '/', redirect: '/Landing' },
      { path: '/Landing', component: Landing },
      { path: '/A', component: Aa },
      { path: '/B', component: Bb },
      { path: '/C', component: Cc },
      { path: '/:notFound(.*)', component: Landing }
    ]
  });
  
  export default router;