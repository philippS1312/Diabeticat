<template>
    <div id="welcome-page">
        <v-card>
            <v-btn 
                id="back" 
                icon="mdi-arrow-left"
                color="orange"
                @click="router.push('/')">
            </v-btn>
            <v-img src="../assets/logo-transparent.png" />
            <h1 class="text-h5">Register</h1>

            <v-text-field 
                v-model="email"
                prepend-inner-icon="mdi-email-outline" 
                clearable 
                label="Email"
                placeholder="user@example.com" 
                variant="outlined"/>

            <v-text-field 
                v-model="username"
                prepend-inner-icon="mdi-account-outline" 
                clearable 
                label="Dein Name"
                placeholder="Cat" 
                variant="outlined"/>

            <v-text-field 
                v-model="password"
                prepend-inner-icon="mdi-lock-outline" 
                clearable 
                label="Passwort"
                variant="outlined"
                counter
                :type="showPassword ? 'text' : 'password'" 
                :append-inner-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                @click:append-inner="showPassword = !showPassword"/>
            
            <v-text-field 
                prepend-inner-icon="mdi-lock-outline" 
                clearable 
                label="Passwort bestätigen"
                hint="Bitte bestätige dein Passwort."
                variant="outlined"
                counter
                :type="showPassword ? 'text' : 'password'"/>
            <v-btn
                id="register"
                prepend-icon="mdi-account-circle"
                rounded="lg"
                color="orange"
                @click="register()">
                Register
            </v-btn>
        </v-card>
    </div>
</template>

<script setup>

    import { ref } from 'vue'
    import { useRouter } from 'vue-router';
    import store from "../store/index.js"

    const router = useRouter();
    const showPassword = ref(false);

    let email = ref();
    let username = ref();
    let password = ref();

    async function register(){
        try {
            const response = await store.apiCall.requests.register(password.value, email.value, username.value);

            if (response.status == 200) {
                router.push('/login');
            } else {
                console.log('Register failed: ' + response.statusText)
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

    #welcome-page {
        background-color: #A5DAD2;
        width: 100%;
        height: 100%;
        padding-top: 150px;
    }
    .v-card {
        background-color: white;
        width: 500px;
        height: 600px;
    }
    .v-text-field {
        width: 350px;
    }
    .v-img {
        width: 150px;
        height: 150px;
        margin: auto;
    }
    .v-btn #register{
        width: 200px;
    }

    #back{
        position: absolute;
        margin-left: -45%;
        margin-top: 5%;
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
            width: 100%;
            height: 100%;
        }

        .v-card {
            width: 80%;
            height: 85%;
            height: auto;
            padding-bottom: 25px;
        }

        .v-text-field {
            width: 80%;
        }
    }

</style>