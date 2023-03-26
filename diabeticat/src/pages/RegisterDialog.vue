<template>
    <v-card>
        <v-card-title class="text-h5">Eine gute Entscheidung!</v-card-title>
        <v-card-text>Melde dich jetzt hier an.</v-card-text>
        <v-card-actions>
            <v-spacer>
                <v-text-field 
                    label="E-mail"
                    v-model="email" 
                    required 
                    clearable></v-text-field>
                <v-text-field
                    v-model="password"
                    :append-inner-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                    :type="showPassword ? 'text' : 'password'"
                    name="input-pw"
                    label="Passwort"
                    hint="Bitte gebe ein Passwort an."
                    counter
                    @click:append-inner="showPassword = !showPassword">
                </v-text-field>
                <v-text-field
                    v-model="confirmPassword"
                    :type="showPassword ? 'text' : 'password'"
                    name="input-confirmpw"
                    label="Passwort bestätigen"
                    hint="Bitte bestätige dein Passwort."
                    counter>
                </v-text-field>
                <v-checkbox >
                <template v-slot:label>
                    <div>
                        Ich stimme hiermit den 
                        <a target="_blank" href="https://www.youtube.com/watch?v=d1YBv2mWll0&ab_channel=Sordiway">Nutzungsbedingungen</a>
                        von Diabeticat zu.
                    </div>
                </template>
    </v-checkbox>
            </v-spacer>
        </v-card-actions>
        <slot></slot>
    </v-card>
</template>

<script setup>
import { ref } from 'vue'

const email = ref('');
const showPassword = ref(false);
const password = ref('');
const confirmPassword = ref('');

</script>