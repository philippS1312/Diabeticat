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

import NavigationBar from './components/BottomNavigation.vue'
import { watch } from 'vue'
import { useRouter } from 'vue-router';
import store from "./store/index.js"

const router = useRouter();

function showNavBar() {
  // Hide NavigationBar for Welcome, Login and Register Page
  return router.currentRoute.value.fullPath != '/' && router.currentRoute.value.fullPath != '/login' && router.currentRoute.value.fullPath != '/register' 
}

if(localStorage.getItem("token")){
  store.state.sessionKey = localStorage.getItem("token");
  console.log("localStorage if: " + store.state.sessionKey)
}

watch(store.state.sessionKey, (session) => {
  //localStorage.setItem("sessionStorage", session)
  console.log("localStorage watch: " + session)
})

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
