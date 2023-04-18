<template>
    <v-card>
        <v-card-title>Willkommen Miau...</v-card-title>
        <v-img src="../assets/logo-transparent.png" />
        <v-card-text>
            <p>Hallo {{ store.methods.getName() }}, viel Spaß mit Diabeticat.</p>
        </v-card-text>
    </v-card>
    <v-card>
        <v-card-title>Haustiere</v-card-title>
        <v-card-text>
            <p>Du hast aktuell {{ store.state.petCount }} Haustiere hinterlegt.</p>
        </v-card-text>
        <div class="text-center">
            <v-slide-group>
                <v-slide-group-item v-for="pet in store.state.pets" :key="pet.ID">
                    <v-card @click="router.push(`/PetCard/${pet.ID}`)">
                        <v-avatar :color="store.methods.getRandomColor()">
                            <span class="text-h5">{{ store.methods.getInitials(pet.Name) }}</span>
                        </v-avatar>
                        <v-card-title>{{ pet.Name }}</v-card-title>
                        <v-card-text v-if="true">
                            <p>{{ pet.Art }}</p>
                            <p>{{ pet.Geburtsdatum }}</p>
                        </v-card-text>
                    </v-card> 
                </v-slide-group-item>
            </v-slide-group>
        </div>
        
        <v-card-action>
            <v-btn prepend-icon="mdi-plus" @click="router.push(`/create`)">Haustier hinzufügen</v-btn>
        </v-card-action>
    </v-card>
</template>

<script setup>
    import store from "../store/index.js"
    import { watch } from "vue"
    import { useRouter } from 'vue-router';
    const router = useRouter();

    
    watch()

</script>

<style scoped>
.v-card {
    margin: 1em auto;
    padding: 1em;
    width: 90%;
}
.v-img {
        width: 150px;
        height: 150px;
        margin: auto;
}
</style>