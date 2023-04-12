import { reactive } from 'vue';
import petsData from "../assets/pets.json"


const state = reactive({
    username: 'Testuser',
    petCount: 3,
    email: '',
    sessionKey: '',
    pets: petsData.pets,
});

const methods = {
    increasePetcount(){
        state.petCount++
    },
    getName(){
        return state.username
    },
    getInitials(name){
        return name.split(' ').map((n) => n[0]).join('');
    },
    getRandomColor(){
        const red = Math.floor(Math.random() * 256);
        const green = Math.floor(Math.random() * 256);
        const blue = Math.floor(Math.random() * 256);
        return `rgb(${red}, ${green}, ${blue})`;
    }
}

export default {
    state,
    methods,
};