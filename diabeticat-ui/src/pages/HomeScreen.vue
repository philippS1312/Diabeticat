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
                <v-slide-group-item v-for="pet in store.state.pets" :key="pet.petid">
                    <v-card @click="router.push(`/PetCard/${pet.petid}`)">
                        <v-avatar :color="store.methods.getRandomColor()">
                            <span class="text-h5">{{ store.methods.getInitials(pet.name) }}</span>
                        </v-avatar>
                        <v-card-title>{{ pet.name }}</v-card-title>
                        <v-card-text v-if="true">
                            <p>{{ pet.type }}</p>
                            <p>{{ pet.birthday }}</p>
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
    
    import store from "../store/index.js";
    import { useRouter } from 'vue-router';
    import { onMounted } from 'vue';

    const router = useRouter();

    onMounted(() => {
        //console.log(JSON.parse(JSON.stringify(store.state.pets)))
        console.log(store.state.sessionKey)
        console.log(store.methods.userIsLoggedIn())
        console.log(store.state.pets)
    })

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