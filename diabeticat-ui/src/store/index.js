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
    getName() {
        return state.username
    },
    getInitials(name) {
        return name.split(' ').map((n) => n[0]).join('');
    },
    getSessionKey() {
        return state.sessionKey;
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
    },
    async loadUserData() {
        const pets_response = await apiCall.requests.getPetsByUser(state.sessionKey);
        methods.setUserPets(pets_response.data);
        methods.setPetCount();
    },
    logout() {
        methods.setUserToken('expired')
    },
    async setUserData(id, name){
        // Set user informations in store
        methods.setUserToken(localStorage.getItem("token"));
        methods.setUserId(id);
        methods.setUserName(name);

        // Get pets for user
        const pets_response = await apiCall.requests.getPetsByUser(state.sessionKey);
        methods.setUserPets(pets_response.data);

        // Set petCount
        methods.setPetCount();    
    },
    async checkSession() {
        var response
        if (localStorage.getItem("token")) {
            if(localStorage.getItem("token") == 'expired'){
                return false;
            }
            response = await apiCall.requests.checkSession(localStorage.getItem("token"))
            if (response.status == 200) {
                if (response.data['Succuess'] == false) {
                    console.log('checkSession failed');
                    return false;
                } else {
                    console.log('checkSession success');
                    if(state.sessionKey == null){
                        console.log('SessionKey null setUserData')
                        methods.setUserData(response.data['userId'], response.data['username'])
                    }
                    return true;
                }
            } else {
                console.log('checkSession failed: ' + response.statusText);
            }
        }
    },
    async checkLogin(){
        var response
        if (localStorage.getItem("token")) {
            if(localStorage.getItem("token") == 'expired'){
                return false;
            }
            response = await apiCall.requests.checkSession(localStorage.getItem("token"))
            if (response.status == 200) {
                if (response.data['Succuess'] == false) {
                    console.log('checkLogin failed');
                    return false;
                } else {
                    console.log('checkLogin failed');
                    methods.setUserData(response.data['userId'], response.data['username'])
                    return true;
                }
            } else {
                console.log('checkLogin failed: ' + response.statusText);
            }
        }
    },
}

export default {
    state,
    methods,
    apiCall
};