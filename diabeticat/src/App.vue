<template>
  <v-app>
    <v-app-bar
      color="primary"
      app
      dark
    >
    <v-icon icon="home" />
      <v-toolbar-title>App Bar!</v-toolbar-title>
    </v-app-bar>
    <v-main>
      <v-container
        >Lorem ipsum dolor sit amet consectetur adipisicing
        elit. Quis blanditiis beatae praesentium repudiandae
        sunt officiis, vero maxime officia obcaecati nisi
        excepturi. Sit laboriosam provident laborum? Rem
        recusandae ea possimus illo.</v-container
      >
    </v-main>
    <v-bottom-navigation
      v-model="value"
      app
    >
      <v-btn
        value="music"
        height="100%"
      >
        <span>Music</span>
        <v-icon>mdi-music</v-icon>
      </v-btn>      <v-btn
        value="books"
        height="100%"
      >
        <span>Books</span>
        <v-icon>mdi-book</v-icon>
      </v-btn>      <v-btn
        value="photos"
        height="100%"
      >
        <span>Photos</span>
        <v-icon>mdi-image</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </v-app>
</template>

<script setup>

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
