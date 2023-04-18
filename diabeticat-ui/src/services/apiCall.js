import axios from 'axios'

const requests = {
    register(){
        axios.post('http://127.0.0.1:8000/hallo')
        
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