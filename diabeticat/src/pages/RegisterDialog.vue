<template>
            <v-card>
                <v-card-title class="text-h5">
                    Eine gute Entscheidung!
                </v-card-title>
                <v-card-text>Melde dich jetzt hier an.</v-card-text>
                <v-card-actions>
                    <v-spacer>
                        <v-text-field label="E-mail" required clearable></v-text-field>
                        <v-text-field
                            v-model="password"
                            :append-inner-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                            :type="showPassword ? 'text' : 'password'"
                            name="input-pw"
                            label="Passwort"
                            hint="Bitte gebe ein Passwort an."
                            counter
                            @click:append="showPassword = !showPassword"
                        ></v-text-field>
                        <v-text-field
                            v-model="confirmPassword"
                            :type="showPassword ? 'text' : 'password'"
                            name="input-pw2"
                            label="Passwort bestätigen"
                            hint="Bitte bestätige dein Passwort."
                            counter
                            @click:append="showPassword = !showPassword"
                        ></v-text-field>
                    </v-spacer>
                </v-card-actions>
                <slot></slot>
            </v-card>
</template>

<script setup>
import { ref } from 'vue'

const showPassword = ref(false)
const password = ref('');
const confirmPassword = ref('');

</script>