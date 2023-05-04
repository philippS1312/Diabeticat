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

  <!-- Messdaten Übersicht -->
  <v-card class="measurements">
    <v-card-title id="measurement-title">Messdaten</v-card-title>
    <v-card-text>

      <!-- Ansicht Daten Laden -->
      <div id="loading" v-if="loading">
        Lade Daten...
      </div>

      <!-- Ansicht Daten vorhanden -->
      <div v-else-if="filteredMeasurements.length > 0">

        <!-- Filter (Chips) -->
        <div class="text-left">
          <v-chip v-for="option in options"
                :key="option.value"
                :value="option.value"
                @click="selectedOption = option.value"
                :color="selectedOption === option.value ? 'primary' : undefined">
            {{ option.label }}
          </v-chip>
        </div>

        <!-- Tabelle -->
        <v-table density="compact">
          <thead>
            <tr>
              <th class="text-left">ID</th>
              <th class="text-left">Blutzucker</th>
              <th class="text-left">Insulin Dosis</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="entry in filteredMeasurements" :key="entry.measurementDataId">
              <td class="text-left">{{ entry.measurementDataId}}</td>
              <td class="text-left">{{ entry.bloodSugar }} mg/dl</td>
              <td class="text-left">{{ entry.insulinDose }}</td>
            </tr>
          </tbody>
        </v-table>
      </div>

      <!-- Ansicht keine Daten -->
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
  import { ref, onMounted, watchEffect, computed } from 'vue';

  const route = useRoute();
  const router = useRouter();

  const pet = reactive(store.state.pets.find(pet => pet.petId === parseInt(route.params.id)));
  const measurements = ref([]);
  const loading = ref(true);
  const selectedOption = ref('all');

  const options = [
    { label: 'Alle', value: 'all' },
    { label: 'letzte 5 Werte', value: 5 },
    { label: 'letzte 10 Werte', value: 10 },
    { label: 'letzte 20 Werte', value: 20 },
  ];

  const filteredMeasurements = computed(() => {
    if (selectedOption.value === 'all') {
      return measurements.value;
    } else {
      return measurements.value.slice(-selectedOption.value);
    }
  });

  watchEffect(() => {
    if (selectedOption.value === 'all') {
      return;
    } else {
      filteredMeasurements.value = measurements.value.slice(-selectedOption.value);
    }
  });

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
