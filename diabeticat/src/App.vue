<template>
  <v-app>
    <v-app-bar color="primary" app dark v-if="false">
      <v-icon icon="mdi-home" />
      <v-toolbar-title>App Bar</v-toolbar-title>
      <v-btn @click="test()">Test</v-btn>
      <v-btn @click="toggle = !toggle">Toggle</v-btn>

    </v-app-bar>
    <v-main>
      <v-container v-if="toggle">
        Lorem ipsum dolor sit amet consectetur adipisicing
        elit. Quis blanditiis beatae praesentium repudiandae
        sunt officiis, vero maxime officia obcaecati nisi
        excepturi. Sit laboriosam provident laborum? Rem
        recusandae ea possimus illo.
      </v-container>
      <router-view></router-view>
    </v-main>
    
    <NavigationBar v-if="showNavBar()"></NavigationBar>

  </v-app>
</template>

<script setup>

import NavigationBar from './components/BottomNavigation.vue'
import { ref } from 'vue'
import { useRouter } from 'vue-router';

const toggle = ref(false)

const router = useRouter();

function showNavBar() {
  // Hide NavigationBar for Welcome or Login Page
  return router.currentRoute.value.fullPath != '/' && router.currentRoute.value.fullPath != '/login'
}

function test()
{ 
  router.push('/C')
}
</script>

<style>

  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
  }

  body {
    margin: 0 auto;
  }

  /* Mobile App Design */
  @media screen and (max-width: 600px){
    
  }

</style>
