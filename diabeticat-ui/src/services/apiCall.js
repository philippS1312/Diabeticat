import axios from 'axios'

const requests = {

    register(password, email, username){
      const response = axios.post('http://127.0.0.1:8000/api/createUser', {
          password: password,
          email: email,
          username: username
        }
      )
      
      return response;
    },

    login(username, password) {
      const response = axios.post('http://127.0.0.1:8000/api/login', {
          password: password,
          username: username
        }
      )

      return response;
    },

    checkSession(token) {
      const response = axios.post('http://127.0.0.1:8000/api/checkSession', {
          access_token: token
        }
      )
      return response;
    },

    createPet(token, name, type, birthday) {
      const response = axios.post('http://127.0.0.1:8000/api/createPet', {
          access_token: token,
          name: name,
          type: type,
          birthday: birthday  
        }
      )

      return response;
    },

    getPetsByUser(token) {
      const response = axios.post('http://127.0.0.1:8000/api/getPetsByUser', {
          access_token: token
        }
      )

      return response;
    },

    insertMeasurementData(token, petID, bloodSugar, insulinDose) {
      const date = new Date();
      
      const response = axios.post('http://127.0.0.1:8000/api/insertMeasurementData', {
          access_token: token,
          petid: petID,
          bloodSugar: bloodSugar,
          insulinDose: insulinDose,
          measureDate: date
        }
      )

      return response;
    },

    getMeasurementDataByPet(token, petID) {
      const response = axios.post('http://127.0.0.1:8000/api/getMeasurementDataByPet', {
          access_token: token,
          petid: petID,
        }
      )

      return response;
    },
    
}

export default {
    requests
};