<template>
    
    <div id="welcome-page">

        <v-card>

            <!-- Back Button -->
            <v-btn 
                id="back" 
                icon="mdi-arrow-left"
                color="orange"
                @click="router.push('/')">
            </v-btn>

            <v-img src="../assets/logo-transparent.png" />
            <h1 class="text-h5">Log-In</h1>

            <v-text-field 
                v-model="username"
                prepend-inner-icon="mdi-email-outline" 
                clearable 
                label="Username"
                variant="outlined"/>

            <v-text-field 
                v-model="password"
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
                class="login_button"
                @click="login()">
                Login
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

    let username = ref()
    let password = ref()

    async function login() {
        try {
            const response = await store.apiCall.requests.login(username.value, password.value);

            if (response.status == 200) {
                // Set user informations in store
                store.methods.setUserToken(response.data['access_token']);
                store.methods.setUserId(response.data['payload']['userId']);
                store.methods.setUserEmail(response.data['payload']['email']);
                store.methods.setUserName(response.data['payload']['username']);

                // Get pets for user
                const pets_response = await store.apiCall.requests.getPetsByUser(store.state.sessionKey);
                store.methods.setUserPets(pets_response.data);

                // Set petCount
                store.methods.setPetCount();

                router.push('/home');
            } else {
                console.log('Login failed: ' + response.statusText);
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
        width: unset;
    }

    #back{
        position: absolute;
        margin-left: -45%;
        margin-top: 5%;
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
    
    .login_button {
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