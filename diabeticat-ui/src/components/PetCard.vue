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
        <v-btn id="messure" size="small" variant="flat" @click="router.push('/messung-erfassen')">Messung durchführen</v-btn>
        <v-spacer></v-spacer>
        <!-- <v-btn :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"></v-btn> -->
      </v-card-actions>

    </div>
  </v-card>

  <!-- Messdaten Übersicht -->
  <v-card class="measurements">
    <v-card-title id="measurement-title">Messdaten</v-card-title>
    <v-card-text>
      <div id="loading" v-if="loading">
        Lade Daten...
      </div>
      <div v-else-if="measurements.length > 0">
        <v-table density="compact">
          <thead>
            <tr>
              <th class="text-left">ID</th>
              <th class="text-left">Blutzucker</th>
              <th class="text-left">Insolin Dosis</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="entry in measurements" :key="entry.measurementDataId">
              <td class="text-left">{{ entry.measurementDataId}}</td>
              <td class="text-left">{{ entry.bloodSugar }} mg</td>
              <td class="text-left">{{ entry.insulinDose }} mg</td>
            </tr>
          </tbody>
        </v-table>
      </div>
      <div id="no-data" v-else>
        Keine Daten verfügbar.
      </div>
    </v-card-text>
  </v-card>

</template>

<script setup>

  import { useRoute, useRouter } from 'vue-router';
  import { reactive } from 'vue';
  import store from "../store/index.js"
  import { ref, onMounted } from 'vue';

  const route = useRoute();
  const router = useRouter();

  const pet = reactive(store.state.pets.find(pet => pet.petId === parseInt(route.params.id)));
  const measurements = ref([]);
  const loading = ref(true);

  async function fetchMeasurements() {
    try {
      const response = await store.apiCall.requests.getMeasurementDataByPet(store.state.sessionKey, pet.petId);
      measurements.value = response.data
    } catch (error) {
      console.error(error)
    } finally {
      loading.value = false
    }
  }

  onMounted(fetchMeasurements);

</script>

<style scoped>

  .PetCard {
    margin: 5%;
  }

  .measurements {
    margin: 5%;
    margin-top: unset;
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
