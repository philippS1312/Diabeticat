<template>
    <v-bottom-navigation app>
      <v-btn height="100%" width="25%">
        <router-link to="/Landing">Home</router-link>
      </v-btn>
      <v-btn height="100%" width="25%">
        <router-link to="/A">A</router-link>
      </v-btn>      
      <v-btn height="100%" width="25%">
        <router-link to="/B">B</router-link>
      </v-btn>
      <v-btn height="100%" width="25%">
        <router-link to="/C">C</router-link>
      </v-btn>
    </v-bottom-navigation>
  </template>

<script setup>

</script>

<style scoped>
    a{
        text-decoration: none;
        color: black;
    }
</style>