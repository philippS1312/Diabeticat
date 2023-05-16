<template>

  <v-card class="PetCard">
    <div class="click" @click="show = !show">

      <v-img src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg" height="200px" cover></v-img>

      <v-card-title>{{ pet.name }}</v-card-title>

      <v-card-subtitle>
          <p>{{ pet.type }}</p>
        <br>
          <p id="birthday">
            <v-icon icon="mdi-cake-variant"></v-icon>
            {{ pet.birthday }}
          </p>        
      </v-card-subtitle>

      <v-card-actions>
        <v-btn id="messure" size="small" variant="flat" @click="router.push(`/messung-erfassen/${pet.petId}`)">Messung durchführen</v-btn>
        <v-spacer></v-spacer>
        <!-- <v-btn :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"></v-btn> -->
      </v-card-actions>

    </div>
  </v-card>
  
</template>

<script setup>

  import { useRoute, useRouter } from 'vue-router';
  import { reactive } from 'vue';
  import store from "../store/index.js"

  const route = useRoute();
  const router = useRouter();

  const pet = reactive(store.state.pets.find(pet => pet.petId === parseInt(route.params.id)));
 

</script>

<style scoped>

  .PetCard {
    margin: 1em auto;
    padding: 1em;
    width: 90%;
  }

  .measurements {
    margin: 5%;
    margin-top: unset;
  }

  .v-chip {
    margin-right: 5px;
    margin-bottom: 10px;
  }

  #measurement-title, #no-data, #loading {
    text-align: left;
  }

  #birthday {
    font-weight: bolder;
    font-size: 1, 4em;
  }

  #messure{
    background-color: rgb(80, 33, 33);
    color: white;
  }
  
</style>
