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
        <v-card-action>
            <v-btn prepend-icon="mdi-plus" @click="store.methods.increasePetcount">Haustier hinzufügen</v-btn>
        </v-card-action>
    </v-card>
</template>

<script setup>
    import store from "../store/index.js"

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