import { reactive } from 'vue';


const state = reactive({
    username: 'Testuser',
    petCount: 0,
    email: '',
    sessionKey: '',
});

const methods = {
    increasePetcount(){
        state.petCount++
    },
    getName(){
        return state.username
    }
}

export default {
    state,
    methods,
};