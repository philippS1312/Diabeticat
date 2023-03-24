<template>
  <v-app>
    <v-app-bar color="primary" app dark>
    <v-icon icon="home" />
      <v-toolbar-title>App Bar</v-toolbar-title>
    </v-app-bar>
    <v-main>
      <v-container>
        Lorem ipsum dolor sit amet consectetur adipisicing
        elit. Quis blanditiis beatae praesentium repudiandae
        sunt officiis, vero maxime officia obcaecati nisi
        excepturi. Sit laboriosam provident laborum? Rem
        recusandae ea possimus illo.
      </v-container>
      <router-view></router-view>
    </v-main>
    <NavigationBar></NavigationBar>
  </v-app>
</template>

<script setup>
import NavigationBar from './components/Navigation.vue'

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  margin-top: 60px;
}
</style>
