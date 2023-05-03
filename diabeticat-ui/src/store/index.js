import { reactive } from 'vue';
import apiCall from "../services/apiCall"

const state = reactive({
    sessionKey: null,
    userID: null,
    username: null,
    email: null,
    petCount: null,
    pets: [],
});




const methods = {
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
    },
    setUserToken(token) {
        state.sessionKey = token;
        localStorage.setItem("token", token)
    },
    setUserId(id) {
        state.userID = id;
    },
    setUserEmail(email) {
        state.email = email;
    },
    setUserName(username) {
        state.username = username;
    },
    setUserPets(pets) {
        state.pets = pets;
    },
    setPetCount() {
        state.petCount = state.pets.length;
    }
}

export default {
    state,
    methods,
    apiCall
};