<template>
    <v-card class="PetCard" v-for="pet in store.state.pets" :key="pet.ID">
        <v-card @click="router.push(`/PetCard/${pet.ID}`)">
            <v-avatar :color="store.methods.getRandomColor()">
                <span class="text-h5">{{ store.methods.getInitials(pet.Name) }}</span>
            </v-avatar>
            <v-card-title>{{ pet.Name }}</v-card-title>
            <v-card-text v-if="true">
                <p>Art: {{ pet.Art }}</p>
                <p>Geburtsdatum: {{ pet.Geburtsdatum }}</p>
                <p>Notizen: {{ pet.Notizen }}</p>
            </v-card-text>
        </v-card>
    </v-card>
</template>

<script setup>
import store from "../store/index.js"
import { useRouter } from 'vue-router';
const router = useRouter();

</script>

<style scoped>
.PetCard{
    margin: 5%;
}
.v-avatar {
    margin-top: 1em;
}
</style>