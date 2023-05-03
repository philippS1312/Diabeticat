import { reactive } from 'vue';

const state = reactive({
    sessionKey: '',
    userID: null,
    username: '',
    email: '',
    petCount: 0,
    pets: [],
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
    userIsLoggedIn() {
        return (state.sessionKey != null && state.sessionKey != '')
    }
}

export default {
    state,
    methods,
};