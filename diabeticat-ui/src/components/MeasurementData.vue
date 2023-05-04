<template>

    <v-card class="MeasurementData">

        <!-- Messdaten Übersicht -->
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
  import store from "../store/index.js"
  import { useRoute } from 'vue-router';
  import { ref, onMounted, watchEffect, computed, reactive } from 'vue';

  const route = useRoute();

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
  
    .MeasurementData {
        margin: 1em auto;
        padding: 1em;
        width: 90%;
    }
    
  </style>
  