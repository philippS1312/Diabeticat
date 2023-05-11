<template>
  <v-card class="MeasurementData">
      <!-- Messdaten Übersicht -->
      <v-card-title id="measurement-title">Messdaten</v-card-title>
      
      <v-card-text>

        <!-- Ansicht: Daten Laden -->
        <div id="loading" v-if="loading">
            Lade Daten...
        </div>

        <!-- Ansicht: Daten vorhanden -->
        <div v-else-if="measurements.length > 0">

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

            <div v-if="filteredMeasurements.length > 0">

              <!-- Line Chart -->
              <MeasurementChart 
                id="chart"
                :key="chartLabels"
                :timePeriod="chartLabels" 
                :bloodSugar="chartBloodsugar"
                :insulinDosis="chartInsulindosis">
              </MeasurementChart>

              <!-- Tabelle -->
              <v-expansion-panels>
                <v-expansion-panel>
                  <v-expansion-panel-title>Details</v-expansion-panel-title>
                  <v-expansion-panel-text>

                    <v-table density="compact" id="table">
                      <thead>
                          <tr>
                          <th class="text-left">Datum & Uhrzeit</th>
                          <th class="text-left">Blutzucker</th>
                          <th class="text-left">Insulin Dosis</th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr v-for="entry in filteredMeasurements" :key="entry.measurementDataId">
                          <!-- <td class="text-left">{{ entry.measureDate }}</td> -->
                          <td class="text-left">{{ dateToString(entry.measureDate) }}</td>
                          <td class="text-left">{{ entry.bloodSugar }} mg/dl</td>
                          <td class="text-left">{{ entry.insulinDose }}</td>
                          </tr>
                      </tbody>
                    </v-table>

                  </v-expansion-panel-text>
                </v-expansion-panel>
              </v-expansion-panels>

            </div>

            <div class="no-data" v-else>
              Keine Daten im ausgewählten Zeitraum vorhanden.
            </div>
            
        </div>
        
        <!-- Ansicht: Keine Daten -->
        <div class="no-data" v-else>
            Keine Daten verfügbar.
        </div>

      </v-card-text>
  </v-card>
  
</template>
  
<script setup>
  import store from "../store/index.js"
  import { useRoute } from 'vue-router';
  import { ref, onMounted, watchEffect, computed, reactive } from 'vue';
  import MeasurementChart from "./MeasurementChart.vue";

  const route = useRoute();
  const pet = reactive(store.state.pets.find(pet => pet.petId === parseInt(route.params.id)));
  const measurements = ref([]);
  const loading = ref(true);
  const selectedOption = ref(0);

  const options = [
    { label: 'Heute', value: 0 },
    { label: 'Gestern', value: 1 },
    { label: 'Letzte Woche', value: 7 },
    { label: 'Letzter Monat', value: 30 },
    { label: 'Alle', value: 'all' }
  ];

  let chartLabels = null
  let chartBloodsugar = null
  let chartInsulindosis = null

  const filteredMeasurements = computed(() => {
    if (selectedOption.value === 'all') {
      return measurements.value;
    } else {
      return filterMeasurements()
    }
  });

  watchEffect(() => {
    if (selectedOption.value === 'all') {
      setChartData();
    } else {
      filteredMeasurements.value = filterMeasurements()
    }
    setChartData();
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
    setChartData();
  }
}
  function filterMeasurements() {
    const now = new Date()
    const dateToFilterTo = new Date(now.getTime() - selectedOption.value * 24 * 60 * 60 * 1000)

    const filteredData = []
    measurements.value.forEach(e => {
      const elementDateTmp = new Date(e.measureDate)
      const elementDate = new Date(elementDateTmp.getFullYear(), elementDateTmp.getMonth(), elementDateTmp.getDate(), elementDateTmp.getHours(), elementDateTmp.getMinutes())

      switch (selectedOption.value) {
        case 0:
        case 1:

          if (elementDate.getDate() === dateToFilterTo.getDate() && elementDate.getMonth() === dateToFilterTo.getMonth() && elementDate.getFullYear() === dateToFilterTo.getFullYear()) {
            filteredData.push(e)
          }
          break

        case 7:
        case 30:
          if (elementDate.getTime() >= dateToFilterTo.getTime()) {
            filteredData.push(e)
          }
          break
      }
    })
    
    return filteredData
  }

  function dateToString(date) {
    const dateTmp = new Date(date)
    
    let day = dateTmp.getDate();
    let month = dateTmp.getMonth() + 1;
    let year = dateTmp.getFullYear();

    let hours = dateTmp.getHours();
    let minutes = dateTmp.getMinutes();

    // Füge führende Nullen hinzu
    day = day < 10 ? "0" + day : day;
    month = month < 10 ? "0" + month : month;
    year = year < 10 ? "0" + year : year;
    hours = hours < 10 ? "0" + hours : hours;
    minutes = minutes < 10 ? "0" + minutes : minutes;

    return day + "." + month + "." + year + " um " + hours + ":" + minutes + " Uhr"
  }

  function setChartData() {
    // Reset
    chartBloodsugar = []
    chartInsulindosis = []
    chartLabels = []

    // Set chart values
    filteredMeasurements.value.forEach(e => {
      chartBloodsugar.push(e.bloodSugar)
      chartInsulindosis.push(e.insulinDose)
      chartLabels.push(getDateTime(new Date(e.measureDate)))
    })
  }

  function getDateTime(date) {
    let tmp = null
    let tmpDate = null
    let tmpTime = null

    // Show Time only, if "gestern" is selected
    if (selectedOption.value !== 1 && selectedOption.value !== 0) {
      let day = date.getDate();
      let month = date.getMonth() + 1;
      let year = date.getFullYear();

      // Füge führende Nullen hinzu
      day = day < 10 ? "0" + day : day;
      month = month < 10 ? "0" + month : month;
      year = year < 10 ? "0" + year : year;

      tmpDate = day + "." + month + "." + year
    }

    let hours = date.getHours();
    let minutes = date.getMinutes();

    // Füge führende Nullen hinzu
    hours = hours < 10 ? "0" + hours : hours;
    minutes = minutes < 10 ? "0" + minutes : minutes;

    tmpTime = hours + ":" + minutes

    if (tmpDate !== null) {
      tmp = tmpDate + " " + tmpTime
    } else {
      tmp = tmpTime
    }

    return tmp
  }

  onMounted(() => {
    fetchMeasurements();
  });

</script>
<style scoped>
  
  .MeasurementData {
      margin: 1em auto;
      padding: 1em;
      width: 90%;
  }

  #measurement-title {
    font-size: x-large;
    margin-bottom: 10px;
  }

  #chart, #table {
    padding: 20px;
  }

  .v-chip {
    margin-right: 5px;
  }

  .no-data {
    padding-top: 35px;
  }

</style>
  