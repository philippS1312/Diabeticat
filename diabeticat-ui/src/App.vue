<template>
  <v-app>
    <v-app-bar color="primary" app dark v-if="false">
      <v-icon icon="mdi-home" />
      <v-toolbar-title>App Bar</v-toolbar-title>
      <v-btn @click="test()">Test</v-btn>
      <v-btn @click="toggle = !toggle">Toggle</v-btn>

    </v-app-bar>
    <v-main>
      <router-view></router-view>
    </v-main>
    
    <NavigationBar v-if="showNavBar()"></NavigationBar>

  </v-app>
</template>

<script setup>

import { onMounted } from 'vue';
import NavigationBar from './components/BottomNavigation.vue'
//import { watch } from 'vue'
import { useRouter } from 'vue-router';
import store from './store';
//import store from "./store/index.js"

const router = useRouter();

function showNavBar() {
  // Hide NavigationBar for Welcome, Login and Register Page
  return router.currentRoute.value.fullPath != '/' && router.currentRoute.value.fullPath != '/login' && router.currentRoute.value.fullPath != '/register' 
}

onMounted(async () => {
  console.log('App mounted')
  const loggedIn = await store.methods.checkLogin()
  console.log('loggedIn: ' + loggedIn)
  if(loggedIn){
    router.push('/home')
  } else {
    if(router.currentRoute.value.fullPath != '/' && router.currentRoute.value.fullPath != '/login' && router.currentRoute.value.fullPath != '/register'){
      console.log('redirect to login')
      router.push('/')
    }
  }

});

</script>

<style>

  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
  }

  .v-main {
    background-color: #A5DAD2;
  }

  body {
    margin: 0 auto;
  }

  /* Mobile App Design */
  @media screen and (max-width: 600px){
    
  }

</style>
