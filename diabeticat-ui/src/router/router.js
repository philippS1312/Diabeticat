import { createRouter, createWebHistory } from 'vue-router';

import WelcomePage from '../pages/WelcomePage.vue'
import LoginPage from '../pages/LoginPage.vue'
import CreatePetPage from '../pages/CreatePetPage.vue'
import RegisterPage from '../pages/RegisterDialog.vue'
import HomeScreen from '../pages/HomeScreen.vue'
import PetList from '../pages/PetList.vue'
import PetCard from '../components/PetCard.vue'
import MeasurementPage from '../pages/MeasurementPage.vue'
import store from "../store/index.js"



const router = createRouter({
    history: createWebHistory(),
    routes: [
      { path: '/', component: WelcomePage, meta: { requiresAuth: false }},
      { path: '/login', component: LoginPage, meta: { requiresAuth: false }},
      { path: '/register', component: RegisterPage, meta: { requiresAuth: false }},
      { path: '/Home', component: HomeScreen, meta: { requiresAuth: true }},
      { path: '/create', component: CreatePetPage, meta: { requiresAuth: true }},
      { path: '/PetCard/:id', component: PetCard, meta: { requiresAuth: true }},
      { path: '/PetList', component: PetList, meta: { requiresAuth: true }},
      { path: '/messung-erfassen', component: MeasurementPage, meta: { requiresAuth: true }},
      { path: '/:notFound(.*)', component: WelcomePage, meta: { requiresAuth: true }}
    ]
  });

  router.beforeEach((to,_,next) => {
    //console.log(store.state.sessionKey)
    //console.log(store.methods.userIsLoggedIn())
    
    if(to.meta.requiresAuth && !store.methods.userIsLoggedIn()){
      // console.log('beforEach True')
      next('/')
    }else{
      // console.log('beforEach False')
      //console.log(to.meta.requiresAuth)
      //console.log(!store.methods.userIsLoggedIn())
      next()
    }
    
  })
  
  export default router;