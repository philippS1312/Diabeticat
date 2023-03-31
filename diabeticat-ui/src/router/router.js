import { createRouter, createWebHistory } from 'vue-router';

import WelcomePage from '../pages/WelcomePage.vue'
import LoginPage from '../pages/LoginPage.vue'
import RegisterPage from '../pages/RegisterDialog.vue'
import HomeScreen from '../pages/HomeScreen.vue'


const router = createRouter({
    history: createWebHistory(),
    routes: [
      { path: '/', component: WelcomePage },
      { path: '/login', component: LoginPage },
      { path: '/register', component: RegisterPage },
      { path: '/Home', component: HomeScreen },
      { path: '/:notFound(.*)', component: WelcomePage }
    ]
  });
  
  export default router;