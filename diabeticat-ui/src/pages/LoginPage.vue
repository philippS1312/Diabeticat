<template>
    
    <div id="welcome-page">

        <v-card>

            <v-img src="../assets/logo-transparent.png" />
            <h1 class="text-h5">Log-In</h1>

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

        </v-card>

    </div>

</template>

<script setup>
    import { ref } from 'vue'
    import { useRouter } from 'vue-router';

    const router = useRouter();

    const showPassword = ref(false);

    function login() {
        console.log('Welcome!')
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
    }

    .text-h5 {
        margin-bottom: 15px;
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