<template>
    <v-bottom-navigation app grow mode="shift">
      <v-btn height="100%" @click="router.push('/home')">
        <v-icon icon="mdi-home" />
      </v-btn>
      <v-btn height="100%" @click="router.push('/PetList')">
        <v-icon icon="mdi-paw" />
      </v-btn>      
      <v-btn height="100%" @click="router.push('/messung-erfassen')">
        <v-icon icon="mdi-medical-bag" />
      </v-btn>
<!-- 
      <v-btn height="100%" @click="router.push('/settings')">
        <v-icon icon="mdi-account-cog" />
      </v-btn> 
-->
      <v-btn height="100%" @click="logout()">
        <v-icon icon="mdi-logout" />
      </v-btn>
    </v-bottom-navigation>
  </template>

<script setup>
  import { useRouter } from 'vue-router';
  import store from "../store/index.js"
  const router = useRouter();

  function logout(){
    store.methods.logout();
    router.push('/')
  }


</script>

<style scoped>
    a{
        text-decoration: none;
        color: black;
    }
</style>