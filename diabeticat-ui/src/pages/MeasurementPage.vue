<template>
    <div id="create_page">

        <v-card>

            <!-- Button Back -->
            <v-btn 
                id="back" 
                icon="mdi-arrow-left"
                color="grey-lighten-2"
                @click="router.back()">
            </v-btn>

            <v-img src="../assets/logo-transparent.png" />

            <h1>Messdaten erfassen</h1>

            <v-form>

                <!-- Haustier -->
                <v-select
                    v-model="selectedPet"
                    :items="pets"
                    item-title ="name"
                    item-value="petId"
                    label="Haustier auswählen"
                    required />

                <!-- Blutzucker -->
                <v-text-field 
                    v-model="bloodSugar"
                    label="Blutzucker"
                />

                <!-- Insulin Dosis -->
                <v-text-field 
                    v-model="insulinDose"
                    label="Insulin Dosis"
                />

                <!-- Button Anlegen -->
                <v-btn
                    color="success"
                    class="button_create"
                    @click="create()"
                >
                Anlegen
                </v-btn>

            </v-form>
        
        </v-card>

    </div>
</template>

<script setup>

    import { ref } from 'vue'
    import '@vuepic/vue-datepicker/dist/main.css'
    import { useRoute, useRouter } from 'vue-router';
    import store from "../store/index.js"
    import apiCall from "../services/apiCall"
    import { onMounted } from 'vue';

    const route = useRoute();
    const router = useRouter(); 
    const pets = JSON.parse(JSON.stringify(store.state.pets));
    const providedPetId = parseInt(route.params.id);

    let selectedPet = ref(providedPetId && !isNaN(providedPetId) ? providedPetId : null);
    let bloodSugar = ref();
    let insulinDose = ref();

    onMounted(() => {
        // console.log(pets)
    });

    async function create() {
        try {
            const response_create = await apiCall.requests.insertMeasurementData(store.state.sessionKey, selectedPet.value, bloodSugar.value, insulinDose.value);

            if (response_create.status == 200) {
                router.push('/home');
            } else {
                console.log('Create measurement data failed: ' + response_create.statusText)
            }

        } catch (error) {
            console.log(error);
        }
    }

</script>


<style scoped>
    
    * {
        margin: auto;
    }

    #back{
        position: absolute;
        margin-left: -45%;
        margin-top: 5%;
    }

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

    .v-input {
        width: 80%;
    }

    .datepicker {
        width: 80%;
        z-index: 1;
        position: relative;
    }

    .button_create {
        margin: 10px;
    }

    /* Mobile App Design */
    @media screen and (max-width: 600px){

        .v-card {
            height: auto;
            padding-bottom: 25px;
        }

    }

</style>
