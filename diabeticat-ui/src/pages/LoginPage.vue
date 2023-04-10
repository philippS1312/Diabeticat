<template>
    
    <div id="welcome-page">

        <v-card>

            <v-img src="../assets/logo-transparent.png" />

            <v-text-field 
                prepend-inner-icon="mdi-email-outline" 
                clearable 
                label="Email"
                placeholder="user@example.com" 
                variant="outlined"/>

            <v-text-field 
                prepend-inner-icon="mdi-lock-outline" 
                clearable 
                label="Password"
                variant="outlined"
                :type="showPassword ? 'text' : 'password'" 
                :append-inner-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                @click:append-inner="showPassword = !showPassword"/>

            <v-btn
                prepend-icon="mdi-account-circle"
                rounded="lg"
                color="green"
                @click="login()">
                Login
            </v-btn>

            <br />
            <router-link class="back_link" to="/">Kein Account? Zurück</router-link>

        </v-card>

    </div>

</template>

<script setup>
    import { ref } from 'vue'
    import { useRouter } from 'vue-router';

    const router = useRouter();

    const showPassword = ref(false);

    function login() {
        router.push('/Home')
    }

</script>

<style scoped>

    * {
        margin: auto;   
    }

    #welcome-page {
        background-color: #A5DAD2;
        width: 100%;
        height: 100%;
        padding-top: 150px;
    }

    .v-card {
        background-color: white;
        width: 500px;
        height: 450px;
    }

    .v-text-field {
        width: 350px;
    }

    .v-img {
        width: 150px;
        height: 150px;
        margin: auto;
    }
    
    .v-btn {
        width: 200px;
        margin-bottom: 10px;
    }

    .text-h5 {
        margin-bottom: 15px;
    }

    .back_link {
        font-size: small;
    }

    p {
        font-size: large;
    }

    /* Mobile App Design */
    @media screen and (max-width: 600px){
        #welcome-page {
            background-color: #A5DAD2;
            width: 100%;
            height: 100%;
        }

        .v-card {
            width: 80%;
            height: 80%;
        }

        .v-text-field {
            width: 80%;
        }
    }

</style>