import { createRouter, createWebHistory } from 'vue-router';

import WelcomePage from '../pages/WelcomePage.vue'
import LoginPage from '../pages/LoginPage.vue'
import RegisterPage from '../pages/RegisterDialog.vue'
import HomeScreen from '../pages/HomeScreen.vue'
import PetList from '../pages/PetList.vue'
import PetCard from '../components/PetCard.vue'


const router = createRouter({
    history: createWebHistory(),
    routes: [
      { path: '/', component: WelcomePage },
      { path: '/login', component: LoginPage },
      { path: '/register', component: RegisterPage },
      { path: '/Home', component: HomeScreen },
      { path: '/PetCard/:id', component: PetCard },
      { path: '/PetList', component: PetList },
      { path: '/:notFound(.*)', component: WelcomePage }
    ]
  });
  
  export default router;