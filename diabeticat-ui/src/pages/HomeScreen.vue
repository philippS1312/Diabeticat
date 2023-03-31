<template>
    <h1>Home Screen</h1>
    <v-card>
        <v-card-title>Willkommen</v-card-title>
        <v-card-text>
            <p>Hallo {{ user }}, viel Spaß mit Diabeticat.</p>
        </v-card-text>
    </v-card>
    <v-card>
        <v-card-title>Haustiere</v-card-title>
        <v-card-text>
            <p>Du hast aktuell {{ petCount }} Haustiere hinterlegt.</p>
        </v-card-text>
        <v-card-action>
            <v-btn prepend-icon="mdi-plus" @click="addPet">Haustier hinzufügen</v-btn>
        </v-card-action>
    </v-card>
</template>
<script setup>
import { ref } from 'vue'

const user = ref('Testbenutzer')
const petCount = ref(0)

function addPet(){
    petCount.value = petCount.value + 1;
}

</script>