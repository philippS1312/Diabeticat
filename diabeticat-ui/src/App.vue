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
//import { watch } from 'vue'
import { useRouter } from 'vue-router';
import store from "./store/index.js"

const router = useRouter();

function showNavBar() {
  // Hide NavigationBar for Welcome, Login and Register Page
  return router.currentRoute.value.fullPath != '/' && router.currentRoute.value.fullPath != '/login' && router.currentRoute.value.fullPath != '/register' 
}

async function checkSession() {
  var response
  if(localStorage.getItem("token")){
    response = await store.apiCall.requests.checkSession(localStorage.getItem("token"))
    if (response.status == 200) {
        console.log('checkSession: ' + response.data['Succuess'])
        if(response.data['Succuess'] != false){
                  // Set user informations in store
                  store.methods.setUserToken(localStorage.getItem("token"));
                  store.methods.setUserId(response.data['userId']);
                  store.methods.setUserName(response.data['username']);

                  // Get pets for user
                  const pets_response = await store.apiCall.requests.getPetsByUser(store.state.sessionKey);
                  store.methods.setUserPets(pets_response.data);

                  // Set petCount
                  store.methods.setPetCount();
                  
                  router.push('/home');
        } else {
          router.push('/');
        }
              } else {
                  console.log('SessionCheck failed: ' + response.statusText);
              }
            }
  }
checkSession()

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
