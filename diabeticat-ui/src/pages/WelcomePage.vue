<template>
    
    <div id="welcome-page">

        <v-card>

            <v-img src="../assets/logo-transparent.png" />
            <h1 class="text-h5">Diabeticat</h1>

            <v-btn
                prepend-icon="mdi-account-circle"
                rounded="lg"
                color="green"
                @click="$router.push('/login')">
                Login
            </v-btn>
            
            <p>oder</p>

            <v-btn 
                prepend-icon="mdi-lock"
                rounded="lg"
                color="blue-grey"
                @click="$router.push('/register')">
                Registrieren
            </v-btn>
        </v-card>

    </div>

</template>

<script setup>


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

    .v-img {
        width: 150px;
        height: 150px;
        margin: auto;
    }
    
    .v-btn {
        width: 200px;
        margin: 20px;
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
    }

</style>