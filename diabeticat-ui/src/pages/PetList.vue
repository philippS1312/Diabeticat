<template>
    <v-card class="PetCard" v-for="pet in store.state.pets" :key="pet.petId">
        <v-card @click="router.push(`/Pet/${pet.petId}`)">
            <v-avatar :color="pet.color">
                <span class="text-h5">{{ store.methods.getInitials(pet.name) }}</span>
            </v-avatar>
            <v-card-title>{{ pet.name }}</v-card-title>
            <v-card-text v-if="true">
                <p>Art: {{ pet.type }}</p>
                <p>Geburtsdatum: {{ pet.birthday }}</p>
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
    margin: 1em auto;
    width: 90%;
}
.v-avatar {
    margin-top: 1em;
}
</style>