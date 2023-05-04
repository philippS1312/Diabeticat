<template>
    <v-bottom-navigation app grow mode="shift" :bg-color="color" v-model="value">
      <v-btn height="100%" @click="router.push('/home')">
        <v-icon icon="mdi-home" />
      </v-btn>
      <v-btn height="100%" @click="router.push('/PetList')">
        <v-icon icon="mdi-paw" />
      </v-btn>      
      <v-btn height="100%" @click="router.push('/messung-erfassen')">
        <v-icon icon="mdi-medical-bag" />
      </v-btn>
      <v-btn height="100%" @click="logout()">
        <v-icon icon="mdi-logout" />
      </v-btn>
    </v-bottom-navigation>
  </template>

<script setup>
  import { useRouter } from 'vue-router';
  import { ref } from 'vue'
  import store from "../store/index.js"
  const router = useRouter();

  const value = ref();

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