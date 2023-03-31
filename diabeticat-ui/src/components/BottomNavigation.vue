<template>
    <v-bottom-navigation app grow mode="shift" :bg-color="color" v-model="value">
      <v-btn height="100%" @click="router.push('/')">
        <v-icon icon="mdi-home" />
      </v-btn>
      <v-btn height="100%" @click="router.push('/Home')">
        <v-icon icon="mdi-paw" />
      </v-btn>      
      <v-btn height="100%" @click="router.push('/Home')">
        <v-icon icon="mdi-medical-bag" />
      </v-btn>
      <v-btn height="100%" @click="router.push('/Home')">
        <v-icon icon="mdi-account" />
      </v-btn>
    </v-bottom-navigation>
  </template>

<script setup>
  import { useRouter } from 'vue-router';
  import { ref } from 'vue'
  const router = useRouter();

  const value = ref();


</script>

<style scoped>
    a{
        text-decoration: none;
        color: black;
    }
</style>