<template>
  <v-card class="PetCard">
    <div class="click" @click="show = !show">

      <v-img src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg" height="200px" cover></v-img>

      <v-card-title>
        {{ pet.name }}
      </v-card-title>

      <v-card-subtitle>
          <p>{{ pet.type }}</p>
        <br>
          <p id="birthday">
            <v-icon icon="mdi-cake-variant"></v-icon>
            {{ pet.birthday }}
          </p>        
      </v-card-subtitle>
      <v-card-actions>
        <v-btn id="messure" size="small" variant="flat" @click="router.push('/Messure')">Messung durchführen</v-btn>
        <v-spacer></v-spacer>
        <v-btn :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"></v-btn>
      </v-card-actions>
    </div>
    <!-- <v-expand-transition>
      <div v-show="show">
        <v-divider></v-divider>

        <v-textarea label="Notizen" clearable clear-icon="mdi-close-circle" auto-grow variant="filled"
          :model-value="pet.Notizen">
        </v-textarea>
        <v-card>Platz für die letzten Messungen</v-card>
        <v-card>Platz für die Statistiken</v-card>
      </div>
    </v-expand-transition> -->
  </v-card>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import { ref, reactive } from 'vue';
import store from "../store/index.js"

const route = useRoute()
const router = useRouter();

console.log(route.params.id)

const show = ref(false)

const pet = reactive(store.state.pets.find(pet => pet.ID === parseInt(route.params.id)))

console.log(pet)

</script>

<style scoped>
.PetCard {
  margin: 5%;
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
