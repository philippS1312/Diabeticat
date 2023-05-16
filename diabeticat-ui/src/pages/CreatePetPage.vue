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

            <h1>Haustier anlegen</h1>

            <v-form>

                <!-- Name -->
                <v-text-field 
                    v-model="name"
                    label="Name"
                />

                <!-- Art -->
                <v-select
                v-model="type"
                    label="Art"
                    :items="types"
                    required />

                <!-- Geburtsdatum -->
                <label class="birthday">Geburtsdatum</label>
                <VueDatePicker 
                    v-model="date"
                    class="datepicker"
                ></VueDatePicker>

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
    import VueDatePicker from '@vuepic/vue-datepicker';
    import '@vuepic/vue-datepicker/dist/main.css'
    import { useRouter } from 'vue-router';
    import store from "../store/index.js"

    const router = useRouter(); 
    const types = ['Katze', 'Hund', 'Krokodil']

    let name = ref();
    let type = ref();
    let date = ref();

    async function create() {
        try {
            const response_create = await store.apiCall.requests.createPet(store.state.sessionKey, name.value, type.value, date.value);
            if (response_create.status == 200) {
                // Update store
                const pets_response = await store.apiCall.requests.getPetsByUser(store.state.sessionKey);
                store.methods.setUserPets(pets_response.data)

                // Update petCount
                store.methods.setPetCount();

                router.push('/home');
            } else {
                console.log('Create Pet failed: ' + response_create.statusText)
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
        min-height: 850px;
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