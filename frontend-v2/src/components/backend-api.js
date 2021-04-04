import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 1000
});

export default {
    // vladelen() {return AXIOS.get(`/api/vladelen`);},
    // update(vladelenId) {return AXIOS.post(`/api/vladelen` + vladelenId);},
    // save() {return AXIOS.post(`/api/vladelen`);},
}


