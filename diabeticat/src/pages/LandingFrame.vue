<template>
    <h1>Home</h1>
        <v-btn @click="dialog = true">Registrieren</v-btn>
        <v-row justify="center">
            <v-dialog v-model="dialog" persistent width="auto">
            <RegisterDialog>
                <v-btn variant="text" @click="dialog = false">Registrierung abbrechen</v-btn>
            </RegisterDialog>
        </v-dialog>
    </v-row>
</template>

<script setup>
import { ref } from 'vue'
import RegisterDialog from './RegisterDialog.vue'


const dialog =ref(false) 

</script>