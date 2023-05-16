import { createRouter, createWebHistory } from 'vue-router';

import WelcomePage from '../pages/WelcomePage.vue'
import LoginPage from '../pages/LoginPage.vue'
import CreatePetPage from '../pages/CreatePetPage.vue'
import RegisterPage from '../pages/RegisterDialog.vue'
import HomeScreen from '../pages/HomeScreen.vue'
import PetList from '../pages/PetList.vue'
import PetPage from '../pages/PetPage.vue'
import SettingsPage from '../pages/SettingsPage.vue'
import MeasurementPage from '../pages/MeasurementPage.vue'
import store from "../store/index.js"



const router = createRouter({
    history: createWebHistory(),
    routes: [
      { path: '/', component: WelcomePage, meta: { requiresAuth: false }},
      { path: '/login', component: LoginPage, meta: { requiresAuth: false }},
      { path: '/register', component: RegisterPage, meta: { requiresAuth: false }},
      { path: '/home', component: HomeScreen, meta: { requiresAuth: true }},
      { path: '/settings', component: SettingsPage, meta: { requiresAuth: true }},
      { path: '/create', component: CreatePetPage, meta: { requiresAuth: true }},
      { path: '/Pet/:id', component: PetPage, meta: { requiresAuth: true }},
      { path: '/PetList', component: PetList, meta: { requiresAuth: true }},
      { path: '/messung-erfassen', component: MeasurementPage, meta: { requiresAuth: true }},
      { path: '/messung-erfassen/:id', component: MeasurementPage, meta: { requiresAuth: true }},
      { path: '/:notFound(.*)', component: WelcomePage, meta: { requiresAuth: true }}
    ]
  });

  router.beforeEach(async (to, _, next) => {
    console.log('requiresAuth ' + to.meta.requiresAuth)
    const checkSession = await store.methods.checkSession();
    if (checkSession) {
      if (to.meta.requiresAuth === false) {
        next('/home')
      }else {
        next()
      }
    }else if (to.meta.requiresAuth) {
      next('/')
    }
    else {
      next()
    }
  }
)
  
  
  export default router;