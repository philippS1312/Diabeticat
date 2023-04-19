import axios from 'axios'

const requests = {

    register(password, email, username){
      const response = axios.post('http://127.0.0.1:8000/createUser', {
          password: password,
          email: email,
          username: username
        }
      )
      
      return response;
    },

    login(username, password) {
      const response = axios.post('http://127.0.0.1:8000/login', {
          password: password,
          username: username
        }
      )

      return response;
    },

    createPet(token, name, type, birthday) {
      const response = axios.post('http://127.0.0.1:8000/createPet', {
          access_token: token,
          name: name,
          type: type,
          birthday: birthday  
        }
      )

      return response;
    },

    getPetsByUser(token) {
      const response = axios.post('http://127.0.0.1:8000/getPetsByUser', {
          access_token: token
        }
      )

      return response;
    },

    getPets(){
        return{
            "pets": [
                {
                  "ID": 1,
                  "Name": "Fido",
                  "Art": "Hund",
                  "Geburtsdatum": "2015-07-12",
                  "Notizen": "Liebt es, Ball zu spielen" 
                },
                {
                  "ID": 2,
                  "Name": "Whiskers",
                  "Art": "Katze",
                  "Geburtsdatum": "2018-02-14",
                  "Notizen": "Hat Angst vor Hunden" 
                },
                {
                  "ID": 3,
                  "Name": "Speedy",
                  "Art": "Hamster",
                  "Geburtsdatum": "2020-11-02",
                  "Notizen": "Läuft gerne in seinem Rad"
                },
                {
                  "ID": 4,
                  "Name": "Hans",
                  "Art": "Hamster",
                  "Geburtsdatum": "2020-11-02",
                  "Notizen": "Läuft gerne in seinem Rad"
                }
              ]
        }
    }
}

export default {
    requests
};